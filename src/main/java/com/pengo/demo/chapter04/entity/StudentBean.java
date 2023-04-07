package com.pengo.demo.chapter04.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Benpeng
 * @date 2023/3/24
 */
@ToString
@Data
public class StudentBean implements Serializable {
    private static final long serialVersionUID = -3195929699669976830L;
    private Integer id;
    private String cnname;
    private Integer sex;
    private Integer selfcardNo;
    private String note;
    private StudentSelfcardBean studentSelfcard;
    private List<StudentLectureBean> studentLectureList;
}
