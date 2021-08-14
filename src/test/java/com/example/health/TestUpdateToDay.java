package com.example.health;

import com.example.health.config.MyConfig;
import com.example.health.tools.InterfaceVisit;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestUpdateToDay {
    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "0");
        map.put("today", "5");
        String post = new InterfaceVisit().post(MyConfig.LocalInterFace + "updateUser", map);
        System.out.println(post);
    }

    @Test
    void test() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "0");
        map.put("days", "31");
        String post = new InterfaceVisit().post(MyConfig.LocalInterFace + "updateUserDays", map);
        System.out.println(post);
    }
}
