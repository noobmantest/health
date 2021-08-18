package com.example.health.controller;

import com.example.health.entity.User;
import com.example.health.service.AutoPunchService;
import com.example.health.service.UserService;
import com.example.health.tools.ScheduledPunch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;


@RestController
public class AutoPunchController {
    @Autowired
    AutoPunchService autoPunchService;
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(this.getClass());
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
        List<User> users = userService.findAll();
        Date date = new Date();
        Thread thread = new Thread() {
            @Override
            public void run() {
                ScheduledPunch.showTimer(date.getHours(), date.getMinutes()+1, 30, users);
            }
        };
        thread.start();
        return "autoPunchRunNow打卡启动";
    }

    @RequestMapping("/autoPunch")
    public String autoPunch() {
        String s = autoPunchService.autoPunch();
        return s + "autoPunch，启动定时每天22：00：10更改today状态";
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

    @RequestMapping("/autoPunch4")
    public String autoPunch4() {
        String s = autoPunchService.autoPunch4();
        return s + "autoPunch4";
    }

    @RequestMapping("/autoPunch5")
    public String autoPunch5() {
        String s = autoPunchService.autoPunch5();
        return s + "autoPunch5";
    }
}
