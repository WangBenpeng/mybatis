package com.pengo.demo.chapter01;

import com.pengo.demo.chapter01.mapper.RoleMapper;
import com.pengo.demo.chapter07.plugin.PageParams;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author Benpeng
 * @date 2023/3/28
 */
@Slf4j
public class RoleDemo {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = new Role();
//        List<Role> roles = mapper.findRoles(role);
//        log.info("roles : {}", roles);
        role.setRoleName("test");
//        List<Role> roles1 = mapper.findRoles(role);
//        log.info("roles : {}", roles1);
//        role.setNote("wait");
//        List<Role> roles2 = mapper.findRolesByRowBounds("test", new RowBounds(0, 5));
        PageParams pageParams = new PageParams();
        pageParams.setPage(1);
        pageParams.setPageSize(2);
        pageParams.setUseFlag(true);
        List<Role> roles2 = mapper.findRolesByPageParams("test", pageParams);
        log.info("pageParams:{}", pageParams);
        log.info("size : {}", roles2.size());
        log.info("roles : {}", roles2);

//        log.debug("=======debug message======");
//        log.info("=======info message======");
//        log.warn("=======warn message======");
//        log.error("=======error message======");
//        role.setId(23L);
//        mapper.insertRole(role);

//        role.setId(22L);
//        role.setRoleName("update" + System.currentTimeMillis());
//        mapper.updateRole(role);
//        role.setNote("note" + System.currentTimeMillis());
//        mapper.updateRole(role);
        sqlSession.commit();
    }
}
