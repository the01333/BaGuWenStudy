package com.puxinxiaolin.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread is running");
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable is running...");
    }
}

class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Callable result";
    }
}

public class CreateSamples {
    public static void main(String[] args) {
        // 继承Thread类
        MyThread myThread = new MyThread();
        myThread.start();

        // 实现Runnable接口
        MyRunnable myRunnable = new MyRunnable();
        Thread t = new Thread(myThread);
        t.start();

        // 实现Callable接口和使用FutureTask
        MyCallable myCallable = new MyCallable();
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            System.out.println("Result: " + futureTask.get());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                System.out.println("Thread task from pool is running");
            });
        }
        executorService.shutdown();

        // lambda 写法
        Thread t2 = new Thread(() -> {
            System.out.println("Lambda thread is running");
        });
        t2.setPriority(Thread.NORM_PRIORITY);
    }
}
