package com.pengo.demo.chapter01.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Benpeng
 * @date 2023/3/23
 */
@Slf4j
public class MyDatabaseIdProvider implements DatabaseIdProvider {

    private Properties properties = null;

    @Override
    public void setProperties(Properties p) {
        log.info("use my database provider set properties:{}", p);
        this.properties = p;
    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        log.info("use my database provider get dbId");
        String dbName = dataSource.getConnection().getMetaData().getDatabaseProductName();
        String property = this.properties.getProperty(dbName);
        return property;
    }
}
