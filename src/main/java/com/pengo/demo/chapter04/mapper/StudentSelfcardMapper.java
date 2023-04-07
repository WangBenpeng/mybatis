package com.pengo.demo.chapter04.mapper;

import com.pengo.demo.chapter04.entity.StudentSelfcardBean;

/**
 * @author Benpeng
 * @date 2023/3/24
 */
public interface StudentSelfcardMapper {
    StudentSelfcardBean findStudentSelfcardByStudentId(Integer studentId);
}
