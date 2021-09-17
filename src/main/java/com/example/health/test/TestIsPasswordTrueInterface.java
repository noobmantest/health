package com.example.health.test;

import com.example.health.config.MyConfig;
import com.example.health.tools.InterfaceVisitPost;

import java.util.HashMap;
import java.util.Map;

public class TestIsPasswordTrueInterface {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("user", "201777204151");
        map.put("password", "070246122");

        // 访问接口
        String res = new InterfaceVisitPost().post(MyConfig.IntegerFacePath_IsPasswordTrue, map);
        if (res=="True"){
            System.out.println("账号密码正确");
        }
        System.out.println(res);
    }
}
