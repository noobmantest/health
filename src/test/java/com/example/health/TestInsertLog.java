package com.example.health;

import com.example.health.config.MyConfig;
import com.example.health.tools.InterfaceVisit;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestInsertLog {
    @Test
    void test() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("user", "123456");
        map.put("password", "123123123");
        map.put("reason", "success");
        map.put("time", String.valueOf(System.currentTimeMillis()));

        String post = new InterfaceVisit().post(MyConfig.LocalInterFace + "log/insertLog", map);
        System.out.println(post);
    }

    @Test
    void testGetDate() {
        System.out.println(new Date().toString());
    }
}
