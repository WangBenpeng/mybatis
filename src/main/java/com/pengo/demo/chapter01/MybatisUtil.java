package com.pengo.demo.chapter01;

import com.pengo.demo.chapter01.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Benpeng
 * @date 2023/3/20
 */
@Slf4j
public class MybatisUtil {

    private static SqlSessionFactory sqlSessionFactory = null;
    public static SqlSessionFactory getSqlSessionFactory() {
        InputStream inputStream = null;
        if (sqlSessionFactory == null) {
            try {
                String resource = "mybatis_config.xml";
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
                return sqlSessionFactory;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSessionFactory getSqlSessionFactoryByCode() {
        //datasource
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mybatis?serverTimezone=Asia/Shanghai");

        //transaction
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        //environment
        Environment environment = new Environment("development", transactionFactory, dataSource);

        //configuration
        Configuration configuration = new Configuration(environment);

        //alias
        configuration.getTypeAliasRegistry().registerAlias("role", Role.class);

        //mapper
        configuration.addMapper(RoleMapper.class);

        //sqlSessionFactory
        return new SqlSessionFactoryBuilder().build(configuration);
    }

    /**
     * properties代码配置：优先级1
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactoryByDecode() {
        InputStream cfgStream = null;
        InputStreamReader cfgReader = null;
        InputStream proStream = null;
        InputStreamReader proReader = null;
        Properties properties = null;

        try {
            cfgStream = Resources.getResourceAsStream("mybatis_config.xml");
            cfgReader = new InputStreamReader(cfgStream);
            proStream = Resources.getResourceAsStream("jdbc.properties");
            proReader = new InputStreamReader(proStream);
            properties = new Properties();
            properties.load(proReader);
            properties.setProperty("username", decode(properties.getProperty("username")));
            properties.setProperty("password", decode(properties.getProperty("password")));
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(cfgReader, properties);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
            try {
                if (proReader != null)
                    proReader.close();
                if (proStream != null)
                    proStream.close();
                if (cfgReader != null)
                    cfgReader.close();
                if (cfgStream != null) {
                    cfgStream.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return sqlSessionFactory;
    }

    private static String decode(String encode) {
        if (Objects.equals(encode, "decodeName")) {
            return "root";
        } else if (Objects.equals(encode, "decodePassword")) {
            return "rootroot";
        } else {
            return encode;
        }
    }

}
