package com.jt.web.HandelItspt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.web.ThreadLocal.UserThreadLocal;
import com.jt.web.pojo.UUser;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class UserInterceptor implements HandlerInterceptor {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieValue = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("JT_TICKET".equals(cookie.getName())) {
                cookieValue = cookie.getValue();
                break;
            }
        }
        if (!StringUtils.isEmpty(cookieValue)) {
            Set<String> sentinels = new HashSet<String>();
            sentinels.add("172.16.48.110:26379");
            JedisSentinelPool pool =
                    new JedisSentinelPool("mymaster", sentinels);
            Jedis jedis = pool.getResource();
            String json = jedis.get("" + cookieValue);
            if (!StringUtils.isEmpty(json)) {
                UUser uUser = objectMapper.readValue(json, UUser.class);
                UserThreadLocal.set(uUser);
                return true;
            }
        }
        response.sendRedirect("/user/login.html");
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
