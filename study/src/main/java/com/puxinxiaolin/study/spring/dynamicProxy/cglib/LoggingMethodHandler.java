package com.puxinxiaolin.study.spring.dynamicProxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class LoggingMethodHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        System.out.println("Logging before method execution: " + method.getName());
        Object result = proxy.invokeSuper(o, args);
        System.out.println("Logging after method execution: " + method.getName());

        return result;
    }

}
