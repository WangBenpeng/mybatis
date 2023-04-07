package com.pengo.demo.chapter06.JdkProxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Benpeng
 * @date 2023/3/29
 */
@Slf4j
public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello(String name) {
        log.info("===> hello {}", name);
    }
}
