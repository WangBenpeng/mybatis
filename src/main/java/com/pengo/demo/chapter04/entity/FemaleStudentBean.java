package com.pengo.demo.chapter04.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author Benpeng
 * @date 2023/3/28
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
public class FemaleStudentBean extends StudentBean{
    private List<StudentHealthFemaleBean> studentHealthFemaleList = null;

}
