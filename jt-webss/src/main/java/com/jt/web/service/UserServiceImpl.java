package com.jt.web.service;

import com.fasterxml.jackson.databind.ObjectMapper;



import com.jt.web.pojo.UUser;
import com.jt.web.vo.HttpClientService;
import com.jt.web.vo.SysResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private HttpClientService httpClientService;


    private static ObjectMapper objectMapper=new ObjectMapper();

    public void saveUser(UUser user) {

        try {
//定义远程url
        String url="http://sso.jt.com/user/register";
       Map<String,String> params = new HashMap<String,String>();
        params.put("username",user.getUsername());
        params.put("password",user.getPassword());
        params.put("phone",user.getPhone());
        params.put("email",user.getEmail());


            String SysResultJson = httpClientService.doPost(url, params,"utf-8");

            SysResult sysResult = objectMapper.readValue(SysResultJson, SysResult.class);
            if(sysResult.getStatus()!=200){
                throw new RuntimeException();
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    public String findUserByup(UUser user) {
        String token=null;
        String url="http://sso.jt.com/user/login";
        Map<String, String> params = new HashMap<String, String>();
        params.put("username",user.getUsername());
        String Md5= DigestUtils.md5Hex(user.getPassword());
        params.put("password",Md5);
        String resultJson = httpClientService.doPost(url, params);
        try {
            SysResult sysResult=objectMapper.readValue(resultJson,SysResult.class);

            if(sysResult.getStatus()==200){
                token=(String) sysResult.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return token;
    }
}
