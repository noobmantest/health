package com.example.health.test;

import com.example.health.config.MyConfig;
import com.example.health.tools.InterfaceVisit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestVisitAutoPunch {
    public static void main(String[] args) {

//        http://81.70.250.230:8888/login?password=02134812&address=文化路97号&city_code=410100&user=20177720411
        Map<String, String> map = new HashMap<>();
        map.put("password", "07024612");
        map.put("address", "文化路97号");
        map.put("city_code", "410100");
        map.put("user", "20177720415");
        String post = null;
        try {
            post = new InterfaceVisit().post("http://192.168.123.175:8888/login", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(post);
    }
}
