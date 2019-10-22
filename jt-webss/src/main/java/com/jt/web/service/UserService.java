package com.jt.web.service;


import com.jt.web.pojo.UUser;
import com.jt.web.vo.SysResult;

public interface UserService {
    void saveUser(UUser user);

    String findUserByup(UUser user);
}
