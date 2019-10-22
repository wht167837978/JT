package com.jt.manage.service;

import com.jt.manage.mapper.UserMapper;
import com.jt.manage.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService  {
    @Autowired
    private UserMapper userMapper;

    public List<User> findUserList() {
        return userMapper.findUserList();
    }
}
