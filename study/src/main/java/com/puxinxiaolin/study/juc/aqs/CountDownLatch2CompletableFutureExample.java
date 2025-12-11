package com.puxinxiaolin.study.juc.aqs;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class CountDownLatch2CompletableFutureExample {
    public static void main(String[] args) throws InterruptedException {
        version3();
    }

    private static void version3() {
        List<String> filePaths = Arrays.asList("1", "2", "3", "4", "5", "6");

        List<CompletableFuture<String>> fileFutures = filePaths.stream()
                .map(CountDownLatch2CompletableFutureExample::doSomething)
                .collect(Collectors.toList());

        CompletableFuture<Void> finalFuture = CompletableFuture.allOf(
                fileFutures.toArray(new CompletableFuture[fileFutures.size()])
        );

        try {
            finalFuture.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("all down");
    }

    private static CompletableFuture<String> doSomething(String filePath) {
        return CompletableFuture.supplyAsync(() -> {
            // 逻辑处理
            return null;
        });
    }
    
    private static void version2() {
        CompletableFuture<Void> task1 = CompletableFuture.supplyAsync(() -> {
            // 逻辑处理
            return null;
        });
        CompletableFuture<Void> task2 = CompletableFuture.supplyAsync(() -> {
            // 逻辑处理
            return null;
        });

        // 以下省略四个 task

        // 这里要传全部的 task
        CompletableFuture<Void> headerFuture = CompletableFuture.allOf(task1, task2);

        try {
            headerFuture.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("all down");
    }

    private static void version1() throws InterruptedException {
        int threadCount = 6;

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        final ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            threadPool.submit(() -> {
                try {
                    // 逻辑处理
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("finish");
    }
}
