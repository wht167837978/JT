package com.jt.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.*;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

public class TestRedis {
//    @Autowired
//    private Jedis jedis;
    //测试字符串
    @Test
    public void testString() {
        Jedis jedis = new Jedis("172.16.1.135", 6379);
        Long start=System.currentTimeMillis();
        jedis.set("18066", "今天星期一1");
        Long end=System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(jedis.get("1806"));
    }
    //测试哈希值
    @Test
    public void testHash() {

        Jedis redis = new Jedis("172.16.1.135", 6379);
        redis.hset("person","id","100");
        redis.hset("person","name","1806");
        redis.hset("person","age","4");
        System.out.println(redis.hgetAll("person"));
    }
    @Test
//list类型
    public void testList(){
        Jedis redis = new Jedis("172.16.1.135", 6379);

        redis.rpush("list","1","2","3","4");
        System.out.println(redis.lpop("list"));
        System.out.println(redis.lpop("list"));
        System.out.println(redis.lpop("list"));
        System.out.println(redis.lpop("list"));
        System.out.println(redis.lpop("list"));
        System.out.println(redis.lpop("list"));

    }
    @Test
//分片
    public void testshard(){
        JedisPoolConfig pool = new JedisPoolConfig();
        pool.setMaxTotal(1000);
        pool.setTestOnBorrow(true);

        List<JedisShardInfo> shards=new ArrayList<JedisShardInfo>();
        shards.add(new JedisShardInfo("172.16.1.139",6379));
        shards.add(new JedisShardInfo("172.16.1.139",6380));
        shards.add(new JedisShardInfo("172.16.1.139",6381));
        ShardedJedisPool redis = new ShardedJedisPool(pool, shards);
        ShardedJedis resource = redis.getResource();
        resource.set("shards","保存分片数据");
        System.out.println(resource.get("shards"));
        redis.returnResource(resource);


    }

}
