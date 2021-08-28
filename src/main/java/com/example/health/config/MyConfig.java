package com.example.health.config;

import java.util.HashMap;
import java.util.Map;

public class MyConfig {
    //    本地服务时
        public static final String IntegerFacePath = "http://127.0.0.1:8888/login";
        public static final String LocalInterFace = "http://127.0.0.1:8889/";


    // 放在远程服务器上时
//    public static final String LocalInterFace = "http://81.70.250.230:8889/";
//    public static final String IntegerFacePath = "http://81.70.250.230:8888/login";


    // 发送邮箱
    public static final String fromEmail = "noobmantest@foxmail.com";
    public static final String fromEmailAuthorizationCode = "taffogconzptibic";
    //    public static  Map<Integer,String> = new HashMap<Integer, String>();

}
