package com.pengo.demo.chapter01.mapper;

import com.pengo.demo.chapter01.User;

/**
 * @author Benpeng
 * @date 2023/3/22
 */
public interface UserMapper {
    User getUser(Long id);

    int insertUser(User user);
}
