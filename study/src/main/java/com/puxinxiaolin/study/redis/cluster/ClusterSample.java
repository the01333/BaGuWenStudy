package com.puxinxiaolin.study.redis.cluster;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 集群模式根据key找到节点并获取数据
 * @Author: YCcLin
 * @Date: 2025/4/26 10:52
 */
public class ClusterSample {

    public static void main(String[] args) {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("specific ip", 7000));
        nodes.add(new HostAndPort("specific ip", 7001));
        nodes.add(new HostAndPort("specific ip", 7002));

        try (JedisCluster cluster = new JedisCluster(nodes)) {
            cluster.set("myKey", "myValue");

            String value = cluster.get("myKey");
            System.out.println(value);
        }
    }

}
