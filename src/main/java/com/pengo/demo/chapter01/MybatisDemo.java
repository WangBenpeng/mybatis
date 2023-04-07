package com.pengo.demo.chapter01;

import com.pengo.demo.chapter01.mapper.RoleMapper;
import com.pengo.demo.chapter01.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Benpeng
 * @date 2023/3/20
 */
@Slf4j
public class MybatisDemo {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
            sqlSession = sqlSessionFactory.openSession();
//            sqlSession = MybatisUtil.getSqlSessionFactoryByCode().openSession();
//            sqlSession = MybatisUtil.getSqlSessionFactoryByDecode().openSession();

            testRole(sqlSession);

//            insertUser(sqlSession);
//            getUser(sqlSession);

            sqlSession.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            sqlSession.rollback();
        } finally {
            if (sqlSession != null)
                sqlSession.close();
        }
//        log.debug("this is debug message");
//        log.info("info message");
//        log.error("ERROR MESSAGE!!!!!!!!!");
//        throw new RuntimeException("I'm runtime message...");
    }

    static void testRole(SqlSession sqlSession) {
        //            Role role2 = sqlSession.selectOne("com.pengo.demo.chapter01.RoleMapper.getRole", 1L);
//            log.info(role2.toString());
//            Role role3 = sqlSession.selectOne("getRole", 1L);
//            log.info(role3.toString());
//        log.info("database ID: {}", sqlSession.getConfiguration().getDatabaseId());

        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
//        List<Role> role = mapper.getRole(20L);
//        log.info(role.toString());

        Map<String, String> roleMap = mapper.getRoleReturnMap(20L);
        log.info(roleMap.toString());
//            Role role1 = mapper.findRole("管理员");
//            log.info("use my type handler:" + role1);

//        Role insRole = new Role();
//        insRole.setRoleName("testName");
//        insRole.setNote("wait delete");
//        mapper.insertRole(insRole);
//        log.info("role ID:{}", insRole.getId());

//        Role insRoleReturnId = new Role();
//        insRoleReturnId.setRoleName("testName");
//        insRoleReturnId.setNote("wait delete");
//        mapper.insertRoleReturnId(insRoleReturnId);
//        log.info("return role ID:{}", insRoleReturnId.getId());


//        Role role = mapper.getRole(2L);
//        log.info(role.toString());

//            mapper.deleteRole(2L);
    }


    static void insertUser(SqlSession sqlSession) {
        //测试枚举
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUserName("zhangsan");
        user.setCnname("张三");
        user.setMobile("18888888888");
        user.setSex(Sex.MALE);
        user.setEmail("zhangsan@123.com");
        user.setNote("test enum handler");
        user.setBirthday(new Date());
        userMapper.insertUser(user);
    }

    static void getUser(SqlSession sqlSession) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user1 = userMapper.getUser(15L);
        log.info("enum user: {}", user1.toString());
    }
}
