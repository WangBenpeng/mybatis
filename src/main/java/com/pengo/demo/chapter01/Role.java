package com.pengo.demo.chapter01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

/**
 * @author Benpeng
 * @date 2023/3/19
 */
@ToString
@Data
@Alias("role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Long id;
    private String roleName;
    private String note;
}
