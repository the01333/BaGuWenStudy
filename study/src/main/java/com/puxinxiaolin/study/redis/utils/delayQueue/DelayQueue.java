package com.puxinxiaolin.study.redis.utils.delayQueue;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 轮询延时队列并执行任务
 * @Author: YCcLin
 * @Date: 2025/4/28 11:03
 */
@Slf4j
public class DelayQueue {
    private static final JedisPool JEDIS_POOL = new JedisPool("localhost", 6379);

    public static void main(String[] args) {
        String delayQueue = "delay_queue";

        try (Jedis jedis = JEDIS_POOL.getResource()) {
            while (true) {
                long currentTime = System.currentTimeMillis();
                // score: [0, currentTime] 返回从第一个开始，共一个元素
                Set<Tuple> tasks = jedis.zrangeByScoreWithScores(delayQueue, 0, currentTime, 0, 1);

                if (tasks.isEmpty()) {
                    // 没有任务需要执行，休眠一段时间
                    TimeUnit.SECONDS.sleep(1);
                    continue;
                }

                for (Tuple task : tasks) {
                    String taskId = task.getElement();
                    try {
                        // 执行任务
                        executeTask(taskId);
                        // 从队列中移除已执行的任务
                        jedis.zrem(delayQueue, taskId);
                    } catch (Exception e) {
                        log.error("任务执行失败, taskId: {}, 错误信息: {}", taskId, e.getMessage(), e);
                    }
                }
            }
        } catch (InterruptedException e) {
            log.error("发生异常:{}", e.getMessage(), e);
        }
    }

    private static void executeTask(String taskId) {
        log.info("开始执行任务: {}", taskId);

        try {
            // 模拟任务执行逻辑
            System.out.println("Executing task: " + taskId);
        } catch (Exception e) {
            log.error("任务执行失败, taskId: {}, 错误信息: {}", taskId, e.getMessage(), e);
            throw e;
        }
    }
}
