package com.jt.sso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.UUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
//    @Autowired(required=false)
//    private JedisSentinelPool sentinelPool;

    private static ObjectMapper objectMapper=new ObjectMapper();

    public boolean findchekUser(String param, Integer type) {
       String cloumn=null;
       switch (type){
           case 1:
               cloumn="username";
               break;
           case 2:
               cloumn="phone";
               break;
           case 3:
               cloumn="email";
               break;
       }
        System.out.println(param);
        System.out.println(type);
        int count=userMapper.findCheckUser(cloumn,param);
        System.out.println(count);
        return count==0?false:true;
    }

    public void saveUser(UUser user) {
        //加密密码
        String md5Pass= DigestUtils.md5Hex(user.getPassword());
        user.setPassword(md5Pass);
        user.setEmail(user.getPhone());
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());

        userMapper.insert(user);
    }

    public String findUserByUP(UUser user) {
        String token=null;
        UUser userByUp = userMapper.findUserByUp(user);

        if(userByUp==null){
            return token;
        }

        try {



            token=DigestUtils.md5Hex("JT_TICKET_"+System.currentTimeMillis()+user.getUsername());
            String UserJson=objectMapper.writeValueAsString(userByUp);
            Set<String> sentinels = new HashSet<String>();
            sentinels.add("172.16.48.110:26379");
            JedisSentinelPool pool =
                    new JedisSentinelPool("mymaster", sentinels);
            //new JedisSentinelPool(masterName, sentinels, poolConfig)
            Jedis jedis = pool.getResource();
            jedis.setex(""+token,360000,UserJson+"");

            System.out.println("获取数据:"+jedis.get(""+token));
            pool.returnResource(jedis);
//            Jedis jedis = sentinelPool.getResource();
//            jedis.set(""+token,UserJson+"");
//            sentinelPool.returnResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return token;
    }
}
