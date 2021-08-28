package com.example.health.controller;

import com.example.health.entity.User;
import com.example.health.service.AutoPunchService;
import com.example.health.service.UserService;
import com.example.health.tools.UserPunch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AutoPunchController {
    @Autowired
    AutoPunchService autoPunchService;
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/oneTouchStart")
    public String oneTouchStart() {
        return autoPunchService.oneTouchStart();
    }

    // 测试执行
    @RequestMapping("/autoPunchRunNow")
    public String autoPunchRunNow() {
        // 改变今天的状态
        List<User> userList = userService.findAll();
        logger.info("测试执行 === 改变用户今天状态 == ");
        for (User user : userList) {
            if (user.getToday().equals("1")) {
                logger.info("每日定时任务 === 修改今日打卡状态 === " + user.getUser());
                userService.updateUserToday(user.getId(), "0");
            }
        }
        // 开始打卡
        // 更改后再次查询
        userList = userService.findAll();
        for (User user : userList) {
            new UserPunch().punch(user);
        }
        return "autoPunchRunNow打卡启动";
    }

    // 定时更改用户状态任务
    @RequestMapping("/changeUsersToday")
    public String changeUsersToday() {
        String changeUsersToday = autoPunchService.changeUsersToday();
        return changeUsersToday;
    }

    @RequestMapping("/autoPunch")
    public String autoPunch() {
        String s = autoPunchService.autoPunch();
        return s + "autoPunch";
    }

    @RequestMapping("/autoPunch1")
    public String autoPunch1() {
        String s = autoPunchService.autoPunch1();
        return s + "autoPunch1";
    }

    @RequestMapping("/autoPunch2")
    public String autoPunch2() {
        String s = autoPunchService.autoPunch2();
        return s + "autoPunch2";
    }

    @RequestMapping("/autoPunch3")
    public String autoPunch3() {
        String s = autoPunchService.autoPunch3();
        return s + "autoPunch3";
    }


    @RequestMapping("/autoPunch5")
    public String autoPunch5() {
        String s = autoPunchService.autoPunch5();
        return s + "autoPunch5";
    }
}
