package com.pengo.demo.chapter04;

import com.alibaba.fastjson.JSON;
import com.pengo.demo.chapter01.MybatisUtil;
import com.pengo.demo.chapter04.entity.StudentBean;
import com.pengo.demo.chapter04.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author Benpeng
 * @date 2023/3/24
 */
@Slf4j
public class StudentDemo {
    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        testSqlSession();
    }

    static void testSqlSession() {
        SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        StudentBean student1 = mapper.getStudent(1);
        log.info("use sql session again");
        StudentBean student = mapper.getStudent(1);
        sqlSession.commit();

        log.info("create new sql session");
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        StudentMapper mapper1 = sqlSession1.getMapper(StudentMapper.class);
        StudentBean student2 = mapper1.getStudent(1);
    }

    static void testStudent(SqlSession sqlSession) {
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
//        StudentBean zhangsan = studentMapper.getStudent(2);
//        log.info("health : {}",zhangsan.toString());
//        log.info("self card:{}",zhangsan.getStudentSelfcard());
//        log.info("lecture : {}",zhangsan.getStudentLectureList());
        List<StudentBean> allStudent = studentMapper.findAllStudent();
        log.info("all student : {}", JSON.toJSONString(allStudent));
    }


}
