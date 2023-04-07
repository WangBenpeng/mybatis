package com.pengo.demo.chapter01.handler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Benpeng
 * @since 2023/4/3 11:04
 */
@Slf4j
public class MyHandler extends MyAbstractHandler implements Handler{

    private final String name;

    public MyHandler(String name) {
        this.name = name;
    }

    @Override
    public void operator() {
        log.info(name + " deal!");
        if (getHandler() != null) {
            getHandler().operator();
        }
    }
}
