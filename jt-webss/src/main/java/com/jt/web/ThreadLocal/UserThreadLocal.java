package com.jt.web.ThreadLocal;

import com.jt.web.pojo.UUser;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

public class UserThreadLocal {
    private static ThreadLocal<UUser> UserThreadLocal=new ThreadLocal<UUser>();

    public static void set(UUser user){
        UserThreadLocal.set(user);
    }
    public static UUser get(){

        return UserThreadLocal.get();
    }

    //由于gc没有权限回收ThreadLocal.所以调用ThtredLocal时需要
    //额外的注意内存泄漏问题
    public static void remove(){

        UserThreadLocal.remove();
    }
}
