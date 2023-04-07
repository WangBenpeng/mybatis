package com.pengo.demo.chapter01;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author Benpeng
 * @date 2023/3/22
 */
@Data
@ToString
public class User {
    private Long id;
    private String userName;
    private String cnname;
    private String mobile;
    private Sex sex;
    private String email;
    private String note;
    private Date birthday;
}
