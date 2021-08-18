package com.example.health.config;

import java.util.HashMap;
import java.util.Map;

public class MyConfig {
    //    本地服务时
//        public static final String IntegerFacePath = "http://192.168.123.175:8888/login";
//        public static final String LocalInterFace = "http://192.168.123.175:8889/";


    // 放在远程服务器上时
    public static final String LocalInterFace = "http://81.70.250.230:8889/";
    public static final String IntegerFacePath = "http://81.70.250.230:8888/login";


    // 发送邮箱
    public static final String fromEmail = "2039808149@qq.com";
    public static final String fromEmailAuthorizationCode = "taffogconzptibic";
    //    public static  Map<Integer,String> = new HashMap<Integer, String>();

}
