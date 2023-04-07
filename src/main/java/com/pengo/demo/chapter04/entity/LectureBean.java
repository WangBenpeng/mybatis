package com.pengo.demo.chapter04.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Benpeng
 * @date 2023/3/24
 */
@ToString
@Data
public class LectureBean implements Serializable {
    private static final long serialVersionUID = -4453111553467815475L;
    private Integer id;
    private String lectureName;
    private String note;
}
