package com.example.health.test;

import com.example.health.config.MyConfig;
import com.example.health.tools.InterfaceVisit;
import com.example.health.tools.InterfaceVisitPost;
import org.junit.Test;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestInsertLog {
    public static void main(String[] args) throws IOException {
        new TestInsertLog().test();
    }

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
    public void testPostMethod() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("user", "123456");
        map.put("password", "123123123");
        map.put("reason", "success");
        map.put("time", String.valueOf(System.currentTimeMillis()));

        String post = new InterfaceVisitPost().post(MyConfig.LocalInterFace + "log/insertLog", map);
        System.out.println(post);
    }
}
