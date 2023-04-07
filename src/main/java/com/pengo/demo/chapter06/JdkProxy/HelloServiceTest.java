package com.pengo.demo.chapter06.JdkProxy;

import com.pengo.demo.chapter06.CglibProxy.HelloServiceCglib;

/**
 * @author Benpeng
 * @date 2023/3/29
 */
public class HelloServiceTest {
    public static void main(String[] args) {
        HelloServiceProxy helloServiceProxy = new HelloServiceProxy();
        HelloService helloService = (HelloService) helloServiceProxy.bind(new HelloServiceImpl());
        helloService.sayHello("zhangsan");
        HelloServiceCglib helloServiceCglib = new HelloServiceCglib();
        HelloService helloService1 = (HelloService) helloServiceCglib.getInstance(new HelloServiceImpl());
        helloService1.sayHello("lisi");
    }
}
