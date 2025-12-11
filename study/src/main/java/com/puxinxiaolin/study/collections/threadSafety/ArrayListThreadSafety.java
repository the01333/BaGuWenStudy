package com.puxinxiaolin.study.collections.threadSafety;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: 确保 ArrayList 的线程安全
 * @Author: YCcLin
 * @Date: 2025/4/10 21:57
 */
public class ArrayListThreadSafety {

    public void ensureBySynchronized() {
        List<String> list = new ArrayList<>();

        synchronized (list) {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String value = iterator.next();
                // 执行逻辑
            }
        }
    }

    public void ensureByCollections() {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
    }

    public void ensureByCopyOnWriteArrayList() {
        List<String> list = new CopyOnWriteArrayList<>();
    }

}
