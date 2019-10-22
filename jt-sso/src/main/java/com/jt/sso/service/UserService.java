package com.jt.sso.service;

import com.jt.sso.pojo.UUser;

public interface UserService {
    boolean findchekUser(String param,Integer type);
   void saveUser(UUser user);

    String findUserByUP(UUser user);
}
