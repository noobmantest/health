package com.example.health.test;

import java.util.Arrays;

public class TestRegx {
    public static void main(String[] args) {
        String str = "success,sdfak234834><<<sdfa\"sdfa\n dasdljk感谢你今日上报健康状况多发发送的发生是的发送到";
        String regx1 = "sdfdj888";
        System.out.println(str.matches(regx1));

        System.out.println("===============================");
        System.out.println(str.substring(0, 7));
    }
}
