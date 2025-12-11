package com.puxinxiaolin.study.collections.map;

import java.util.*;

public class UnModifyOperation {
    
    public void demonstrate() {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        Collection<String> unmodifiabledCollection = Collections.unmodifiableCollection(list);
        unmodifiabledCollection.add("D");
    }

    public static void main(String[] args) {
        new UnModifyOperation().demonstrate();
    }
    
}
