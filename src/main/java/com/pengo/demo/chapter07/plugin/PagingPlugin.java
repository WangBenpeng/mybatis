package com.pengo.demo.chapter07.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Benpeng
 * @since 2023/4/4 18:13
 */
@Slf4j
@Intercepts({
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class}
        )
})
public class PagingPlugin implements Interceptor {

    private Integer defaultPage;
    private Integer defaultPageSize;
    private Boolean defaultUseFlag;
    private Boolean defaultCheckFlag;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler stmtHandler = getUnProxyObject(invocation);
        MetaObject metaStatementHandler = SystemMetaObject.forObject(stmtHandler);
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        //不是select
        if (!checkSelect(sql)) {
            return invocation.proceed();
        }
        BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
        Object parameterObject = boundSql.getParameterObject();
        PageParams pageParams = getPageParams(parameterObject);
        //没有分页参数，不启用插件
        if (pageParams == null) {
            return invocation.proceed();
        }
        //获取参数，获取不到使用默认值
        Integer pageNum = pageParams.getPage() == null ? this.defaultPage : pageParams.getPage();
        Integer pageSize = pageParams.getPageSize() == null ? this.defaultPageSize : pageParams.getPageSize();
        Boolean useFlag = pageParams.getUseFlag() == null ? this.defaultUseFlag : pageParams.getUseFlag();
        Boolean checkFlag = pageParams.getCheckFlag() == null ? this.defaultCheckFlag : pageParams.getCheckFlag();
        //不使用分页插件
        if (!useFlag) {
            return invocation.proceed();
        }
        int total = getTotal(invocation, metaStatementHandler, boundSql);
        //回填总数到分页参数
        setTotalToPageParams(pageParams, total, pageSize);
        //检查当前页码的有效性
        checkPage(checkFlag, pageNum, pageParams.getTotalPage());
        //修改SQL
        return changeSQL(invocation, metaStatementHandler, boundSql, pageNum, pageSize);
    }

    private Object changeSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, Integer page, Integer pageSize) {
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        String newSql = "select * from (" + sql + ") $_paging_table limit ?,?";
        metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) invocation.proceed();
            int count = ps.getParameterMetaData().getParameterCount();
            ps.setInt(count - 1, (page - 1) * pageSize);
            ps.setInt(count,pageSize);
        } catch (SQLException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return ps;
    }

    private void checkPage(Boolean checkFlag, Integer pageNum, Integer totalPage) {
        if (checkFlag) {
            if (pageNum > totalPage) {
                throw new RuntimeException("查询失败，查询页码【" + pageNum + "】大于总页数【" + totalPage + "】");
            }
        }
    }

    private void setTotalToPageParams(PageParams pageParams, int total, Integer pageSize) {
        pageParams.setTotal(total);
        int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
        pageParams.setTotalPage(totalPage);
    }

    private int getTotal(Invocation ivt, MetaObject metaStatementHandler, BoundSql boundSql) {
        //获取当前的mappedStatement
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        //配置对象
        Configuration cfg = mappedStatement.getConfiguration();
        //当前需要执行的SQL
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        String countSql = "select count(*) as total from (" + sql + ") $_pagind";
        Connection connection = (Connection) ivt.getArgs()[0];
        PreparedStatement ps = null;
        int total = 0;
        try {
            ps = connection.prepareStatement(countSql);
            BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            handler.setParameters(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        log.info("总条数：{}", total);

        return total;
    }

    private PageParams getPageParams(Object parameterObject) {
        if (parameterObject == null) {
            return null;
        }
        PageParams pageParams = null;
        if (parameterObject instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
            Set<String> keySet = paramMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = paramMap.get(key);
                if (value instanceof PageParams) {
                    return ((PageParams) value);
                }
            }
        } else if (parameterObject instanceof PageParams) {
            pageParams = ((PageParams) parameterObject);
        }
        return pageParams;
    }

    private boolean checkSelect(String sql) {
        String trimSql = sql.trim();
        int idx = trimSql.toLowerCase().indexOf("select");
        return idx == 0;
    }

    /**
     * 从代理对象中分离出真实对象
     * @author Benpeng
     * @since 2023/4/6 08:59
     */
    private StatementHandler getUnProxyObject(Invocation ivt) {
        StatementHandler statementHandler = (StatementHandler) ivt.getTarget();
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        //分离代理对象链
        Object object = null;
        while (metaStatementHandler.hasGetter("h")) {
            object = metaStatementHandler.getValue("h");
        }
        if (object == null) {
            return statementHandler;
        }
        return ((StatementHandler) object);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String strDefaultPage = properties.getProperty("default.page", "1");
        String strDefaultPageSize = properties.getProperty("default.pageSize", "50");
        String strDefaultUseFlag = properties.getProperty("default.useFlag", "false");
        String strDefaultCheckFlag = properties.getProperty("default.checkFlag", "false");
        this.defaultPage = Integer.parseInt(strDefaultPage);
        this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
        this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
        this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
    }
}
