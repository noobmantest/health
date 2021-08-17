package com.example.health.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("user", "用户名");
        map.put("password", "密码");
        map.put("reason", "原因");
        map.put("time", String.valueOf(System.currentTimeMillis()));

        Logger logger = LoggerFactory.getLogger("测试");
        logger.info("日志信息 ==== " + map);
        System.out.println(map);
    }

}
