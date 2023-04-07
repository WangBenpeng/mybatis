package com.pengo.demo.chapter04.mapper;

import com.pengo.demo.chapter04.entity.StudentHealthMaleBean;
import org.apache.ibatis.annotations.Param;

/**
 * @author Benpeng
 * @date 2023/3/28
 */
public interface StudentHealthMaleMapper {
    StudentHealthMaleBean findStudentHealthMaleByStuId(@Param("stuId") Integer stuId);
}
