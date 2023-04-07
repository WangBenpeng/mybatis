package com.pengo.demo.chapter04.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author Benpeng
 * @date 2023/3/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class MaleStudentBean extends StudentBean{
    private List<StudentHealthMaleBean> studentHealthMaleList = null;

//    @Override
//    public String toString() {
//        return "super : " + super.toString() + "MaleStudentBean{" +
//                "studentHealthMaleList=" + studentHealthMaleList +
//                '}';
//    }
}
