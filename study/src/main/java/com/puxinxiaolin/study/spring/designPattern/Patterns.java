package com.puxinxiaolin.study.spring.designPattern;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class Patterns {
    // 模版方法模式
    final RestTemplate restTemplate;

    // BeanFactory 和 ApplicationContext 用了工厂模式
    final ApplicationContext applicationContext;

    // 观察者模式（注解）
    public void event() {
        // 用 ApplicationContext 推送事件，在需要的地方用 @EventListener 监听事件，进行业务逻辑的扩展
        Object object = new Object();
        applicationContext.publishEvent(object);
    }

    // 单例模式：Spring 容器里的每一个Bean都是单例的

    // 代理模式：AOP 面向切面

}
