package com.puxinxiaolin.study.spring.dynamicProxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggingInvocationHandler implements InvocationHandler {
    private final Object target;

    public LoggingInvocationHandler(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Logging before method execution: " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("Logging after method execution: " + method.getName());

        return result;
    }

}
