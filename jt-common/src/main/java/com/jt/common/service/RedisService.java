package com.jt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {

//    //有的工程需要，有的工程不需要。设置required=false，有就注入，没有就不注入。
//    @Autowired(required = false)
//    private ShardedJedisPool shardedJedisPool;
//
//    //字符串添加
//    public void set(String key, String value) {
//        ShardedJedis Jedis = shardedJedisPool.getResource();
//        Jedis.set(key, value);
//        shardedJedisPool.returnResource(Jedis);
//    }
//
//    //添加设置超时时间（秒为单位）
//    public void set(String key, String value, int seconds) {
//        ShardedJedis Jedis = shardedJedisPool.getResource();
//        Jedis.setex(key, seconds, value);
//        shardedJedisPool.returnResource(Jedis);
//    }
//
//    //获取字符串值
//    public String Get(String key) {
//        ShardedJedis Jedis = shardedJedisPool.getResource();
//        String result = Jedis.get(key);
//        shardedJedisPool.returnResource(Jedis);
//        return result;
//    }
//

}
