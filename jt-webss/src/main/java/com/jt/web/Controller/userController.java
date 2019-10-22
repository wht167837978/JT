package com.jt.web.Controller;


import com.jt.web.pojo.UUser;
import com.jt.web.service.UserService;
import com.jt.web.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private UserService userService;

    @RequestMapping("/{modules}")
    public String module(@PathVariable("modules") String modules) {
        return modules;
    }

    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult saveUser(UUser user) {
        try {
            System.out.println();
            userService.saveUser(user);


            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "新增用户失败");
    }

    //实现用户登陆操作
    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult findUserByUP(UUser user, HttpServletResponse response) {
        try {
            String tiken = userService.findUserByup(user);
            if (StringUtils.isEmpty(tiken)) {
                throw new RuntimeException();
            }
            Cookie cookie = new Cookie("JT_TICKET", tiken);
            //设置cookie生命周期
            cookie.setMaxAge(3600000);
            cookie.setPath("/");
            //添加cookie
            response.addCookie(cookie);
            return SysResult.oK();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SysResult.build(201, "用户名密码错误");
    }

    //实现用户的注销操作
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //cookie有多个
        Cookie[] cookies = request.getCookies();
        String token = null;
        //便利cookie
        for (Cookie cookie : cookies) {
            //获取cookie名字为"JT_TICKET"值
            if ("JT_TICKET".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        //cookie不为空
        if (!StringUtils.isEmpty(token)) {
            //删除redis缓存
            Set<String> sentinels = new HashSet<String>();
            sentinels.add("172.16.48.110:26379");
            JedisSentinelPool pool =
                    new JedisSentinelPool("mymaster", sentinels);
            Jedis jedis = pool.getResource();
            jedis.del("" + token);
            //创建cookie
            Cookie cookie = new Cookie("JT_TICKET", "");
            cookie.setPath("/");
            //设置cookie生命周期立即销毁
            cookie.setMaxAge(0);
            //设置cookie
            response.addCookie(cookie);

        }
        //重定向到主页
        return "redirect:/index.html";
    }
}
