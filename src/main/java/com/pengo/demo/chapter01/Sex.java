package com.pengo.demo.chapter01;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Benpeng
 * @date 2023/3/21
 */
@AllArgsConstructor
@Getter
public enum Sex {
    MALE(1, "男"),
    FEMALE(2, "女"),
    ;


    private int id;
    private String name;

    public static Sex getSex(int id) {
        if (id == 1) {
            return MALE;
        } else if (id == 2) {
            return FEMALE;
        } else {
            return null;
        }
    }
}
