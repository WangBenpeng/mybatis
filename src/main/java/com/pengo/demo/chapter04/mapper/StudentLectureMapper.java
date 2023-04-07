package com.pengo.demo.chapter04.mapper;

import com.pengo.demo.chapter04.entity.StudentLectureBean;

/**
 * @author Benpeng
 * @date 2023/3/24
 */
public interface StudentLectureMapper {
    StudentLectureBean findStudentLectureByStuId(Integer id);
}
