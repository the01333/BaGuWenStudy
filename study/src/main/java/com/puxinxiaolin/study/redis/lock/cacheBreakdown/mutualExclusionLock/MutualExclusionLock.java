package com.puxinxiaolin.study.redis.lock.cacheBreakdown.mutualExclusionLock;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Description: 互斥锁演示
 * @Author: YCcLin
 * @Date: 2025/4/10 20:17
 */
@RequiredArgsConstructor
public class MutualExclusionLock {
    final RedisTemplate<String, Object> redisTemplate;

    public String getValue(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        if (value == null) {
            // 尝试获取锁
            Boolean lockAcquired = redisTemplate.opsForValue().setIfAbsent(key, "1");
            if (Boolean.TRUE.equals(lockAcquired)) {
                try {
                    // 双重检查锁，防止重复查询数据库
                    value = (String) redisTemplate.opsForValue().get(key);
                    if (value == null) {
                        value = "走数据库";
                        redisTemplate.opsForValue().set(key, value, 3600);
                    }
                } finally {
                    // 释放锁
                    redisTemplate.delete(key);
                }
            } else {
                while (redisTemplate.opsForValue().get(key) == null) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        return value;
    }

}
