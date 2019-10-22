package com.jt.cart.vo;

import java.io.*;

public class Io {
    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("/Users/hailey/Desktop/GitTest/test/tomcat插件.txt");
            BufferedInputStream buffin = new BufferedInputStream(in);
            FileOutputStream out = new FileOutputStream("/Users/hailey/Desktop/GitTest/test/tomcat1插件.txt");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
            int len;
            byte[] bs=new byte[1024];
            while ((len=buffin.read(bs))!=-1){
                bufferedOutputStream.write(bs,0,len);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
