package com.pengo.demo.chapter01.mapper;

import com.pengo.demo.chapter01.Role;
import com.pengo.demo.chapter07.plugin.PageParams;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author Benpeng
 * @date 2023/3/20
 */
public interface RoleMapper {
    List<Role> getRole(Long id);

    int insertRole(Role role);

    int insertRoleReturnId(Role role);

    int deleteRole(Long id);

    Role findRole(String roleName);

    Map<String,String> getRoleReturnMap(Long id);

    List<Role> findRoles(Role role);

    int updateRole(Role role);

    List<Role> findRolesByRowBounds(String roleName, RowBounds rowBounds);

    List<Role> findRolesByPageParams(@Param("roleName") String roleName, PageParams pageParams);
}
