package com.example.health.controller;

import com.example.health.entity.User;
import com.example.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String helloWord() {
        System.out.println("fhsdtrysdf");
        return "hello, Spring Boot";
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        System.out.println("sdfasdfadgf");
        return userService.findAll();
    }

    // 添加用户
    @RequestMapping("/insertUser")
    public int insertUser(User user) {
        return userService.insertUser(user);
    }

    // 修改今天打卡状态
    @RequestMapping("/updateUserToday")
    public int updateUser(int id, String today) {
        int i = userService.updateUserToday(id, today);
        return i;
    }

    // 修改打卡天数
    @RequestMapping("/updateUserDays")
    public int updateUserDays(int id, int days) {
        int i = userService.updateUserDays(id, days);
        return i;
    }
}
