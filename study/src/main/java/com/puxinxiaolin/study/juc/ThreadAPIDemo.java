package com.puxinxiaolin.study.juc;

import java.util.concurrent.locks.LockSupport;

public class ThreadAPIDemo {
    public static void main(String[] args) throws InterruptedException {
        // new(new Thread()) runnable(new调用start()) waiting time_waiting blocked terminated

        // runnable -> waiting
        runnable2Waiting();
        
        // runnable -> time_waiting
        runnable2TimeWaiting();
        
        // runnable -> blocked
        runnable2Blocked();
        
        // 其他状态切换到 runnable
        others2Runnable();
    }

    private static void others2Runnable() {
        Object obj = new Object();

        obj.notify();
        
        obj.notifyAll();
        
        LockSupport.unpark(Thread.currentThread());
        
        // join 的线程执行完毕
        
        // 如果是超时等待状态, 超时时间到了也会切换到 runnable
    }

    private static void runnable2Blocked() {
        // 1: wait() 被 notify() 之后, 重新获取锁失败
        // 2: synchronized 块/方法未获取到锁
    }

    private static void runnable2TimeWaiting() throws InterruptedException {
        // 1
        Object obj = new Object();
        obj.wait(1000);
        
        // 2
        Thread.sleep(1000);
        
        // 3
        LockSupport.parkNanos(1000);
        
        // 4
        LockSupport.parkUntil(2000);
    }

    private static void runnable2Waiting() throws InterruptedException {
        // 1
        Object obj = new Object();
        obj.wait();
        
        // 2
        Thread t1 = new Thread(() -> {
            // 具体操作
        }, "t1");
        t1.join();

        // 3
        LockSupport.park();
    }
}
