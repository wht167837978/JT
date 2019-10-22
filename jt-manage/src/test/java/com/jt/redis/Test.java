package com.jt.redis;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Test {
    @Autowired
    private UserService userService;
@org.junit.Test
    public List<User> findUserList(){
    return userService.findUserList();

}
}
