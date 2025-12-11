package com.puxinxiaolin.study.redis.utils.uv;

import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 使用Set统计UV
 * @Author: YCcLin
 * @Date: 2025/4/28 11:23
 */
public class UVTrackerSet {
    private final Jedis jedis;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public UVTrackerSet(String redisHost, int redisPort) {
        jedis = new Jedis(redisHost, redisPort);
    }

    public void recordVisit(String userId) {
        String date = LocalDate.now().format(FORMATTER);
        String key = "uv:set:" + date;

        jedis.sadd(key, userId);
        // 30天过期
        jedis.expire(key, 30 * 24 * 60 * 60);
    }

    public long getUV(String date) {
        String key = "uv:set:" + date;

        return jedis.scard(key);
    }

    public long getUVRange(String startDate, String endDate) {
//        LocalDate start = LocalDate.parse(startDate, FORMATTER);
//        LocalDate end = LocalDate.parse(endDate, FORMATTER);
//
//        String[] keys = start.datesUntil(end.plusDays(1))
//                .map(date -> "uv:set:" + date.format(FORMATTER))
//                .toArray(String[]::new);
//
//        String tempKey = "temp:key:range";
//        // 两者取并集，返回tempKey
//        jedis.sunionstore(tempKey, keys);
//        Long uvCount = jedis.scard(tempKey);
//        jedis.del(tempKey);
//
//        return uvCount;
        return 0;
    }

    public static void main(String[] args) {
        UVTrackerSet trackerSet = new UVTrackerSet("localhost", 6379);

        trackerSet.recordVisit("user1");
        trackerSet.recordVisit("user2");

        String now = LocalDate.now().format(FORMATTER);
        System.out.println("UV: " + trackerSet.getUV(now));

        System.out.println("UV between 2025-04-01 and 2025-04-28: " + trackerSet.getUVRange("2025-04-01", "2025-04-28"));
    }

}
