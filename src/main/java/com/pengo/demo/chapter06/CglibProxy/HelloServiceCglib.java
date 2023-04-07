package com.pengo.demo.chapter06.CglibProxy;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Benpeng
 * @date 2023/3/29
 */
@Slf4j
public class HelloServiceCglib implements MethodInterceptor {

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        Object obj = enhancer.create();
        return obj;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("########CGLIB动态代理########");
        Object result = null;
        log.info("===> 准备调用HelloService");
        result = methodProxy.invokeSuper(o, objects);
        log.info("<=== 已经调用HelloService");
        log.info("result : {}", result);
        return result;
    }
}
