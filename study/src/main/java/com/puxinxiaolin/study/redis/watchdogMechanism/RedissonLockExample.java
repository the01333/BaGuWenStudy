package com.puxinxiaolin.study.redis.watchdogMechanism;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RedissonLockExample {
//    private static Config config = new Config().useSingleServer().setAddress("redis://127.0.0.1:6379");
    private static RedissonClient redissonClient;

    static {
//        redissonClient = Redisson.create(config);
    }

    public static void main(String[] args) {
        RLock rLock = redissonClient.getLock("myLock");

        try {
            if (rLock.tryLock(10, 30, TimeUnit.SECONDS)) {
                try {
                    // 业务逻辑
                    System.out.println("Lock acquired, executing business logic");

                    // 模拟长时间运行的任务
                    TimeUnit.SECONDS.sleep(20);
                } finally {
                    rLock.unlock();
                    System.out.println("Lock released");
                }
            } else {
                System.out.println("Lock acquisition failed");
            }
        } catch (InterruptedException e) {
            log.error("RedissonLockExample.Lock acquisition failed, error message:{}", e.getMessage(), e);
        } finally {
            redissonClient.shutdown();
        }
    }
}
