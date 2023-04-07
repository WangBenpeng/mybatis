package com.pengo.demo.chapter01.handler;

/**
 * @author Benpeng
 * @since 2023/4/3 11:06
 */
public class HandlerTest {
    public static void main(String[] args) {
        MyHandler m1 = new MyHandler("aaa");
        MyHandler m2 = new MyHandler("bbb");
        MyHandler m3 = new MyHandler("ccc");
        MyHandler m4 = new MyHandler("ddd");
        m1.setHandler(m2);
        m2.setHandler(m3);
        m3.setHandler(m4);

        m1.operator();
    }
    
    
}
