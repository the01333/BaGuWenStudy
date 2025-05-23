package com.puxinxiaolin.study.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.puxinxiaolin.study.service.*.*(..))")
    public void logBefore() {
        System.out.println("Logging before method execution");
    }

}
