package com.example.health.tools;

import com.example.health.config.MyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogInterfaceOperate {
    // 添加log
    public void insertLog(String user, String password, String reason){
        Logger logger = LoggerFactory.getLogger(this.getClass());
        Map<String, String> map = new HashMap<>();
        map.put("user", user);
        map.put("password", password);
        map.put("reason", reason);
        map.put("time", String.valueOf(System.currentTimeMillis()));

        String post = null;
        try {
            logger.info("存日志进数据库 ==== " + map);
            post = new InterfaceVisit().post(MyConfig.LocalInterFace + "log/insertLog", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(post);
    }
}
