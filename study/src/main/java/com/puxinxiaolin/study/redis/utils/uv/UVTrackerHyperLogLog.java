package com.puxinxiaolin.study.redis.utils.uv;

import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Description: 使用 HyperLogLog 统计UV
 * @Author: YCcLin
 * @Date: 2025/4/28 11:23
 */
public class UVTrackerHyperLogLog {
    private final Jedis jedis;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public UVTrackerHyperLogLog(String redisHost, int redisPort) {
        jedis = new Jedis(redisHost, redisPort);
    }

    public void recordVisit(String userId) {
        String date = LocalDate.now().format(FORMATTER);
        String key = "uv:hll:" + date;

        jedis.pfadd(key, userId);
        // 30天过期
        jedis.expire(key, 30 * 24 * 60 * 60);
    }

    public long getUV(String date) {
        String key = "uv:hll:" + date;

        return jedis.pfcount(key);
    }

    public long getUVRange(String startDate, String endDate) {
//        LocalDate start = LocalDate.parse(startDate, FORMATTER);
//        LocalDate end = LocalDate.parse(endDate, FORMATTER);
//
//        String[] keys = start.datesUntil(end.plusDays(1))
//                .map(date -> "uv:hll:" + date.format(FORMATTER))
//                .toArray(String[]::new);
//
//        String tempKey = "temp:key:range";
//        // 两者取并集，返回tempKey
//        jedis.pfmerge(tempKey, keys);
//        long uvCount = jedis.pfcount(tempKey);
//        jedis.del(tempKey);
//
//        return uvCount;
        return 0;
    }

    public static void main(String[] args) {
        UVTrackerHyperLogLog trackerHyperLogLog = new UVTrackerHyperLogLog("localhost", 6379);

        trackerHyperLogLog.recordVisit("user1");
        trackerHyperLogLog.recordVisit("user2");

        String now = LocalDate.now().format(FORMATTER);
        System.out.println("UV: " + trackerHyperLogLog.getUV(now));

        System.out.println("UV between 2025-04-01 and 2025-04-28: " + trackerHyperLogLog.getUVRange("2025-04-01", "2025-04-28"));
    }

}
