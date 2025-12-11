package com.puxinxiaolin.study.juc.objectSingleton;

/**
 * @Description: 双重校验锁实现对象单例
 * @Author: YCcLin
 * @Date: 2025/5/25 13:19
 */
public class Singleton {

    private volatile static Singleton uniqueInstance;

    public Singleton() {
    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }

        return uniqueInstance;
    }
}
