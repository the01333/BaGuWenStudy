package com.puxinxiaolin.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeanFactoryAndApplicationContext {
    public static void main(String[] args) {
        ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext context2 = new FileSystemXmlApplicationContext("F:\\idea\\code\\BaGuWenStudy\\BaGuWenStudy\\study\\src\\main\\resources\\applicationContext.xml");

        ApplicationContext context3 = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
