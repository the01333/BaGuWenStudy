package com.puxinxiaolin.study.collections.map.hasCode;

public class DealWithHashInterrupt {

    public void deal() {
        int[] arr = new int[10];

        String key = "1";
        // 扰动函数 -> 解决hash冲突
        int hashCode = key.hashCode();
        int hash = hashCode ^ (hashCode >>> 16);
        
        // 根据最后的hash去计算对应的数组索引
        int index = (arr.length - 1) & hash;
    }

}
