package com.puxinxiaolin.study.collections.set;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class CommonSet {

    public void demonstrate() {
        TreeSet<String> treeSet = new TreeSet<>();
        HashSet<String> hashSet = new HashSet<>();

        ConcurrentHashMap<Object, Object> hashMap = new ConcurrentHashMap<>();
        Set<String> set = ConcurrentHashMap.newKeySet();
    }

}
