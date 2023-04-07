package com.pengo.demo.chapter09;

import com.pengo.demo.chapter01.MybatisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

/**
 * @author Benpeng
 * @since 2023/4/7 16:47
 */
@Slf4j
public class ColorDemo {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtil.getSqlSessionFactory().openSession();
        ColorMapper mapper = sqlSession.getMapper(ColorMapper.class);
        ColorBean color = mapper.getColor(2);
        log.error("color:{}", color);
    }
}
