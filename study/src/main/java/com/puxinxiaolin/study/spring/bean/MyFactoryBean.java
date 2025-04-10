package com.puxinxiaolin.study.spring.bean;

import com.puxinxiaolin.study.spring.MyBean;
import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<MyBean> {

    // 静态工厂初始化 Bean
    public static MyBean createInstanceByStatic(String name) {
        return new MyBean(name);
    }

    // 实例工厂初始化 Bean
    public MyBean createInstanceByExample() {
        return new MyBean();
    }

    // FactoryBean interface 初始化 Bean
    @Override
    public MyBean getObject() throws Exception {
        return new MyBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
