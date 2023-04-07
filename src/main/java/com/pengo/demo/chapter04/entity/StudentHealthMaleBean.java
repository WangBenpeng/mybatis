package com.pengo.demo.chapter04.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Benpeng
 * @date 2023/3/28
 */
@Data
public class StudentHealthMaleBean implements Serializable {
    private static final long serialVersionUID = 45416868392876375L;
    private Integer id;
    private String studentId;
    private String checkDate;
    private String heart;
    private String liver;
    private String spleen;
    private String lung;
    private String kidney;
    private String prostate;
    private String note;
}
