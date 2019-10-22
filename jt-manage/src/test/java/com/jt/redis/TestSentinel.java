package com.jt.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class TestSentinel {
    @Autowired()
    private JedisSentinelPool jedisSentinelPool;
    @Test
    public void testSentinel(){
//        Set<String> sentinels=new HashSet<>();
//        sentinels.add("172.16.48.110:26379");
//
//        JedisSentinelPool pool=new JedisSentinelPool("mymaster",sentinels);
//
     Jedis jedis = jedisSentinelPool.getResource();
        jedis.set("aa","哨兵");
        System.out.println(jedis.get("aa"));
        jedisSentinelPool.returnResource(jedis);


    }
}
