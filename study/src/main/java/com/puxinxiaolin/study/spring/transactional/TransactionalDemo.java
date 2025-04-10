package com.puxinxiaolin.study.spring.transactional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalDemo {

    @Transactional(propagation = Propagation.REQUIRED)
    public void required() {
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void requiresNew() {
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void supports() {
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void notSupported() {
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void mandatory() {
    }

    @Transactional(propagation = Propagation.NEVER)
    public void never() {
    }

    @Transactional(propagation = Propagation.NESTED)
    public void nested() {
    }

}
