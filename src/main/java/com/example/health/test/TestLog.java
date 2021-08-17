package com.example.health.test;


import com.example.health.HealthApplication;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HealthApplication.class);
        Logger logger2 = LoggerFactory.getLogger("日志信息");
        logger2.info("===测试日志输出===");
    }
}
