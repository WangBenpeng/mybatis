package com.pengo.demo.chapter09;

import lombok.Data;
import lombok.Getter;

/**
 * @author Benpeng
 * @since 2023/4/7 16:37
 */
@Getter
public enum Color {
    RED(1,"红"),
    YELLOW(2,"黄"),
    BLUE(3,"蓝"),
    ;

    Color(int code, String name) {
        this.code = code;
        this.name = name;
    }

    private final int code;
    private final String name;

    public static Color getEnumByType(int code) {
        for (Color value : Color.values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }
}
