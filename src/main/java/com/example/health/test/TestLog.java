package com.example.health.test;


import com.example.health.HealthApplication;
import com.example.health.entity.Log;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
    public static void main(String[] args) {
        Log log = new Log("user.getUser()", "user.getPassword()", "isSuccess", String.valueOf(System.currentTimeMillis()));
        System.out.println(log);
    }
}
