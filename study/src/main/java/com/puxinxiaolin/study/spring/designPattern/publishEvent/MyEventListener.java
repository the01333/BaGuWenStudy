package com.puxinxiaolin.study.spring.designPattern.publishEvent;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener implements ApplicationListener<MyEvent> {

    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("收到事件：" + event.getSource());
    }

}
