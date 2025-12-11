package com.puxinxiaolin.study.redis.cluster.masterAndSlaveReplication;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

@Slf4j
public class PipelineExample {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);

        Pipeline pipelined = jedis.pipelined();
        
        try {
            for (int i = 0; i < 10; i++) {
                pipelined.set("key_" + i, "value_" + i);
            }

            pipelined.sync();
        } catch (Exception e) { 
            log.error("发生异常:{}", e.getMessage(), e);
        } finally {
            jedis.close();
        }
    }

}
