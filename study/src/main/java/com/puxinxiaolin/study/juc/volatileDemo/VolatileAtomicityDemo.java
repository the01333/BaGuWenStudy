package com.puxinxiaolin.study.juc.volatileDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileAtomicityDemo {
    
//    public volatile static int inc = 0;
//    public synchronized void increase() {
//        inc++;
//    }
    
//    public AtomicInteger inc = new AtomicInteger(0);
//    public synchronized void increase() {
//        inc.incrementAndGet();
//    }

    public volatile static int inc = 0;
    Lock lock = new ReentrantLock();
    public void increase() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        VolatileAtomicityDemo demo = new VolatileAtomicityDemo();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                for (int j = 0; j < 500; j++) {
                    demo.increase();
                }
            });
        }

        TimeUnit.MICROSECONDS.sleep(1500);
        System.out.println(inc);
        threadPool.shutdown();
    }
    
}
