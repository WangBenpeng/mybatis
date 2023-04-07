package com.pengo.demo.chapter09;

import org.apache.ibatis.annotations.Param;

/**
 * @author Benpeng
 * @since 2023/4/7 16:39
 */
public interface ColorMapper {
    ColorBean getColor(@Param("id") Integer id);
}
