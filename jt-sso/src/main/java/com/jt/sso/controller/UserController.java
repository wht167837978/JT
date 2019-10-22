package com.jt.sso.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.jt.sso.pojo.UUser;
import com.jt.sso.service.UserService;
import com.jt.sso.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public String findCheckUser(@PathVariable String param, @PathVariable Integer type, String callback) throws JsonProcessingException {
        boolean flag = userService.findchekUser(param, type);

//    SysResult sysResult = SysResult.oK(flag);
        SysResult sysResult = new SysResult(flag);

//    sysResult.setStatus(200);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(sysResult);

        return callback + "(" + json + ")";
    }

    @RequestMapping("/register")
    @ResponseBody
    public SysResult saveUser(UUser user) {


        try {
            userService.saveUser(user);
            return SysResult.oK();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户新增失败");
    }

    @RequestMapping("/login")
    @ResponseBody
    public SysResult findUserByUP(UUser user) {
        try {
            String token = userService.findUserByUP(user);


            if (!StringUtils.isEmpty(token))
                return SysResult.oK(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户密码错误");

    }

    //根据token的数据查询用户信息
    @RequestMapping("/query/{token}")
    @ResponseBody
    public String findUserBytoken(@PathVariable("token") String token, String callback) {

       String jsonp = null;
        try {
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("172.16.48.110:26379");
        JedisSentinelPool pool =
                new JedisSentinelPool("mymaster", sentinels);
        Jedis jedis = pool.getResource();
        String json = jedis.get("" + token);

            if (StringUtils.isEmpty(json)) {


                jsonp = objectMapper.writeValueAsString(SysResult.build(201, "重新登录"));

            }else {
                jsonp = objectMapper.writeValueAsString(SysResult.oK(json));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return callback+"("+jsonp+")";
    }



}

