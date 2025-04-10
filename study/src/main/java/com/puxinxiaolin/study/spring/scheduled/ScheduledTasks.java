package com.puxinxiaolin.study.spring.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    // 每隔5秒执行一次
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("Current Time: " + System.currentTimeMillis());
    }

    // 每隔5秒执行一次，上一次任务结束后再等待5秒
    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTimeWithFixedDelay() {
        System.out.println("Current Time with Fixed Delay: " + System.currentTimeMillis());
    }

    // 第一次延迟1秒后执行，然后每隔5秒执行一次
    @Scheduled(initialDelay = 1000, fixedRate = 5000)
    public void reportCurrentTimeWithInitialDelay() {
        System.out.println("Current Time with Initial Delay: " + System.currentTimeMillis());
    }

    // 使用Cron表达式来定义任务执行时间
    @Scheduled(cron = "0 0 * * * ?")
    public void reportCurrentTimeWithCron() {
        System.out.println("Cron Scheduled Task: " + System.currentTimeMillis());
    }

}
