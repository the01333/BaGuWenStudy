package com.puxinxiaolin.study.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.puxinxiaolin")
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }

}
