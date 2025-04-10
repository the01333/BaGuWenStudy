package com.puxinxiaolin.study.spring.designPattern.publishEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyEventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishEvent() {
        MyEvent object = new MyEvent(this);
        publisher.publishEvent(object);
    }

}
