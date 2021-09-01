package com.example.health.test;

import com.example.health.entity.User;
import com.example.health.tools.AutoPunchOperate;

import java.util.Map;

public class TestAutoPunchOperate {
    public static void main(String[] args) {
        User user = new User();
        user.setCity_code("410100");
        user.setUser("20177720415");
        user.setPassword("07024612");
        user.setAddress("扶沟县");
        new AutoPunchOperate().autoPunch(user);
    }
}
