package com.puxinxiaolin;

import com.puxinxiaolin.study.spring.dynamicProxy.cglib.LoggingMethodHandler;
import com.puxinxiaolin.study.spring.service.CglibService;
import net.sf.cglib.proxy.Enhancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);

        // 1. AOP test
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        MyServiceImpl myService = context.getBean(MyServiceImpl.class);
//        myService.performTask();

        // 2.1. dynamic proxy for jdk test
//        MyService myService = new MyServiceImpl();
//
//        LoggingInvocationHandler handler = new LoggingInvocationHandler(myService);
//        MyService result = (MyService) Proxy.newProxyInstance(
//                myService.getClass().getClassLoader(),
//                myService.getClass().getInterfaces(),
//                handler
//        );
//        result.performTask();

        // 2.2. dynamic proxy for cglib test
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(CglibService.class);
        enhancer.setCallback(new LoggingMethodHandler());
        CglibService result = (CglibService) enhancer.create();
        result.performTask();
    }
}
