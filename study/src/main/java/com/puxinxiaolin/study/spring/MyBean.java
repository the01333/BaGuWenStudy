package com.puxinxiaolin.study.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Component
public class MyBean implements DisposableBean, InitializingBean {

    public MyBean() {
    }

    public MyBean(String name) {
    }

    @Override
    public void destroy() throws Exception {
        log.info("MyBean is destroyed by DisposableBean interface...");
    }

    @PreDestroy
    public void print() {
        System.out.println("MyBean is destroyed by @PreDestroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("MyBean is init by InitializingBean interface...");
    }

    @PostConstruct
    public void init() {
        System.out.println("MyBean is init by @PostConstruct...");
    }
}
