package com.pengo.demo.chapter06.JdkProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Benpeng
 * @date 2023/3/29
 */
@Slf4j
public class HelloServiceProxy implements InvocationHandler {

    /**
     * 真实对象
     */
    private Object target;

    public Object bind(Object target) {
        this.target = target;
        Object proxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return proxyInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("########JDK动态代理########");
        Object result = null;
        log.info("===> 准备调用HelloService");
        result = method.invoke(target, args);
        log.info("<=== 已经调用HelloService");
        log.info("result : {}", result);
        return result;
    }
}
