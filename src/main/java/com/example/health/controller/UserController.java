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

    // 查询所有
    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    // 添加用户，成功返回 1 不成功返回 0
//    @RequestMapping("/insertUser")
//    public int insertUser(String user, String password, int days, String today, String email,
//                          String address, String city_code) {
//        User insertUser = new User(user, password, days, today, email, city_code, address);
//        return userService.insertUser(insertUser);
//    }

    // 添加用户，成功返回 1 不成功返回 0。inviteUser为邀请码，邀请码为空时不增加邀请人数
    @RequestMapping("/insertUser")
    public String insertUser(User insertUser, String inviteUser) {
        return userService.insertUser(insertUser, inviteUser);
    }

    // 通过邮箱查找用户，返回内容中没有密码，为了安全
    @RequestMapping("/findUserByEmail")
    public List<User> findUserByEmail(String email) {
        return userService.findUserByEmail(email);
    }

    // 修改今天打卡状态，1表示今天过卡了，0表示今天还没有打卡
    @RequestMapping("/updateUserToday")
    public int updateUser(int id, String today) {
        int i = userService.updateUserToday(id, today);
        return i;
    }

    // 修改打卡天数，传入正数表示增加，负数表示减少
    @RequestMapping("/updateUserDays")
    public int updateUserDays(int id, int days) {
        int i = userService.updateUserDays(id, days);
        return i;
    }

    // 根据用户名查询用户，返回查询到用户个数
    @RequestMapping("/countUserByUser")
    public int countUserByUser(String user) {
        return userService.countUserByUser(user);
    }

    // 通过用户名和密码查询用户
    @RequestMapping("/findUserByUserAndPassword")
    public List<User> findUserByUserAndPassword(String user, String password) {
        return userService.findUserByUserAndPassword(user, password);
    }

    // 更细腻用户信息
    @RequestMapping("/updateUserById")
    public int updateUserById(String user, String password, String email, String city_code, String address, int open, int id) {
        return userService.updateUserById(user, password, email, city_code, address, open, id);
    }

}
