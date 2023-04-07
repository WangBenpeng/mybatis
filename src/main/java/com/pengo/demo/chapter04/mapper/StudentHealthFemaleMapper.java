package com.pengo.demo.chapter04.mapper;

import com.pengo.demo.chapter04.entity.StudentHealthFemaleBean;
import org.apache.ibatis.annotations.Param;

/**
 * @author Benpeng
 * @date 2023/3/28
 */
public interface StudentHealthFemaleMapper {
    StudentHealthFemaleBean findStudentHealthFemaleByStuId(@Param("stuId") Integer stuId);

}
