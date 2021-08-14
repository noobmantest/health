package com.example.health.tools;

import com.example.health.config.MyConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogInterfaceOperate {
    // 添加log
    public void insertLog(String user, String password, String reason){
        Map<String, String> map = new HashMap<>();
        map.put("user", user);
        map.put("password", password);
        map.put("reason", reason);
        map.put("time", String.valueOf(System.currentTimeMillis()));

        String post = null;
        try {
            post = new InterfaceVisit().post(MyConfig.LocalInterFace + "log/insertLog", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(post);
    }
}
