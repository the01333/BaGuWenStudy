package com.puxinxiaolin.study.spring.designPattern.publishEvent;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    public MyEvent(Object source) {
        super(source);
    }

}

