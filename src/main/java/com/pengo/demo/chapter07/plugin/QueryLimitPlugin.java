package com.pengo.demo.chapter07.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Benpeng
 * @since 2023/4/4 11:00
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class QueryLimitPlugin implements Interceptor {

    private int limit;
    private String dbType;
    private static final String LMT_TABLE_NAME = "limit_table_name_";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler stmtHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStmtObject = SystemMetaObject.forObject(stmtHandler);
        while (metaStmtObject.hasGetter("h")) {
            Object object = metaStmtObject.getValue("h");
            metaStmtObject = SystemMetaObject.forObject(object);
        }
        while (metaStmtObject.hasGetter("target")) {
            Object object = metaStmtObject.getValue("target");
            metaStmtObject = SystemMetaObject.forObject(object);
        }
        String sql = (String) metaStmtObject.getValue("delegate.boundSql.sql");
        String limitSql;
        if (Objects.equals("mysql", this.dbType) && sql.indexOf(LMT_TABLE_NAME) == -1) {
            sql = sql.trim();
            limitSql = "select * from ( " + sql + " ) " + LMT_TABLE_NAME + " limit " + limit;
            metaStmtObject.setValue("delegate.boundSql.sql", limitSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String strLimit = ((String) properties.getProperty("limit", "10"));
        this.limit = Integer.parseInt(strLimit);
        this.dbType = properties.getProperty("dbType", "mysql");
    }
}
