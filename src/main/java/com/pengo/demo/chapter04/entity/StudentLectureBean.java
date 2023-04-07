package com.pengo.demo.chapter04.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Benpeng
 * @date 2023/3/24
 */
@ToString
@Data
public class StudentLectureBean implements Serializable {
    private static final long serialVersionUID = 692574103773890427L;
    private Integer id;
    private Integer studentId;
    private Integer lectureId;
    private BigDecimal grade;
    private String note;
    private LectureBean lecture;
}
