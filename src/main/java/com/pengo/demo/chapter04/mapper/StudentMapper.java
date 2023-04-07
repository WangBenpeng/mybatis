package com.pengo.demo.chapter04.mapper;

import com.pengo.demo.chapter04.entity.StudentBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Benpeng
 * @date 2023/3/24
 */
public interface StudentMapper {
    StudentBean getStudent(@Param("id") Integer id);

    List<StudentBean> findAllStudent();
}
