package com.example.health.controller;

import com.example.health.entity.User;
import com.example.health.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String helloWord() {
        logger.info("访问hello接口 ==== ");
        System.out.println("fhsdtrysdf");
        return "hello, Spring Boot";
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    // 添加用户
    @RequestMapping("/insertUser")
    public int insertUser(User user) {
        logger.info("添加用户 ==== " + user);
//        System.out.println(user);
        return userService.insertUser(user);
    }

    // 通过邮箱查找用户
    @RequestMapping("/findUserByEmail")
    public List<User> findUserByEmail(String email) {
        return userService.findUserByEmail(email);
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
