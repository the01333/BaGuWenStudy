package com.puxinxiaolin.study.collections.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class HashSetThreadSafety {
    
    public void ensureBySynchronized() {
        Set<Object> set = new HashSet<>();

        synchronized (set) {
            Iterator<Object> iterator = set.iterator();
            while (iterator.hasNext()) {
                Object value = iterator.next();
                // 执行逻辑
            }
        }
    }
    
    public void ensureBySynchronizedSet() {
        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
    }
    
    public void ensureByCopyOnWriteArraySet() {
        Set<Object> set = new CopyOnWriteArraySet<>();
    }
    
    public void ensureByConcurrentHashMap() {
        Set<String> set = ConcurrentHashMap.newKeySet();
    }
    
}
