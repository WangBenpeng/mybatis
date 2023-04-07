package com.pengo.demo.chapter01.factory;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;

/**
 * @author Benpeng
 * @date 2023/3/22
 */
@Slf4j
public class MyObjectFactory extends DefaultObjectFactory {

    private static final long serialVersionUID = -2754194227513554563L;

    @Override
    public <T> T create(Class<T> type) {
        log.info("使用定制对象工厂的create方法构建单个对象");
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        log.info("使用定制对象工厂的create方法构建列表对象");
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("定制属性：{}", properties);
        super.setProperties(properties);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}
