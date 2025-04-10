package com.puxinxiaolin.study.spring.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafetyDemo {

    private AtomicInteger counter = new AtomicInteger(0);

    public void add() {
        counter.incrementAndGet();
    }

    public int get() {
        return counter.get();
    }

}

class Counter {
    private int count = 0;

    public synchronized int get() {
        return count;
    }

    public synchronized void add() {
        count++;
    }
}
