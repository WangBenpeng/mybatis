package com.pengo.demo.chapter04.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Benpeng
 * @date 2023/3/24
 */
@ToString
@Data
public class StudentSelfcardBean implements Serializable {
    private static final long serialVersionUID = 3595779546799648936L;
    private Integer id;
    private Integer studentId;
    private String native_;
    private Date issueDate;
    private Date endDate;
    private String note;
}
