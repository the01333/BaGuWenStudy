package com.puxinxiaolin;

import com.puxinxiaolin.study.service.impl.MyServiceImpl;
import com.puxinxiaolin.study.spring.AppConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class StudyApplication {
    public static void main(String[] args) {
//        SpringApplication.run(StudyApplication.class, args);

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyServiceImpl myService = context.getBean(MyServiceImpl.class);
        myService.performTask();
    }
}
