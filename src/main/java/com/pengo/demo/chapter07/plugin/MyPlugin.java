package com.pengo.demo.chapter07.plugin;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * @author Benpeng
 * @since 2023/4/4 10:18
 */
@Intercepts({
        @Signature(type = Executor.class,   //拦截对象
                method = "update",      //拦截方法
                args = {MappedStatement.class, Object.class})       //拦截方法参数

})
@Slf4j
public class MyPlugin implements Interceptor {

    Properties props = null;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("before......");
        Object obj = invocation.proceed();
        log.info("after......");
        return obj;
    }

    @Override
    public Object plugin(Object target) {
        log.info("调用生成代理对象。。。");
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties props) {
        log.info("set properties: " + props.get("dbType"));
        this.props = props;
    }
}
