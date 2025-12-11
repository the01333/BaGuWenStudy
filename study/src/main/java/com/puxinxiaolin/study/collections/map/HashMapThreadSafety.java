package com.puxinxiaolin.study.collections.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapThreadSafety {
    
    public void ensureBySynchronized() {
        Map<Object, Object> map = Collections.synchronizedMap(new HashMap<>());
    }
    
    public void ensureByConcurrentHashMap() {
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        String key = "1";
        int hashCode = key.hashCode();
    }
    
}
