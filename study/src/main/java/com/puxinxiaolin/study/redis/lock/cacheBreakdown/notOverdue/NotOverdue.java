package com.puxinxiaolin.study.redis.lock.cacheBreakdown.notOverdue;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ExecutorService;

/**
 * @Description: 不过期
 * @Author: YCcLin
 * @Date: 2025/4/10 20:31
 */
@RequiredArgsConstructor
public class NotOverdue {
    final RedisTemplate<String, Object> redisTemplate;
    final ExecutorService executorService;

    public String getValue(String key) {
        String value = (String) redisTemplate.opsForValue().get(key);
        CacheEntry cacheEntry = JSON.parseObject(value, CacheEntry.class);
        if (cacheEntry == null || cacheEntry.isExpired()) {
            // 异步更新缓存
            executorService.submit(() -> {
                String result = "走数据库";
                redisTemplate.opsForValue()
                        .set(key, new CacheEntry(result, System.currentTimeMillis() + 3600 * 1000));
            });
        }

        return cacheEntry != null ? cacheEntry.getValue() : null;
    }

}

class CacheEntry {
    @Getter
    private String value;
    private Long expireTime;

    public CacheEntry(String value, Long expireTime) {
        this.value = value;
        this.expireTime = expireTime;
    }

    public Boolean isExpired() {
        return System.currentTimeMillis() > expireTime;
    }

}