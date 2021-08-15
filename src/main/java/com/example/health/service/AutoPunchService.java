package com.example.health.service;

import com.example.health.entity.User;
import com.example.health.mapper.UserMapper;
import com.example.health.tools.ScheduledPunch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoPunchService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    private static boolean flag = true;
    private static boolean flag1 = true;
    private static boolean flag2 = true;
    private static boolean flag3 = true;
    private static boolean flag4 = true;
    private static boolean flag5 = true;

    public String autoPunch() {
        if (flag) {
            List<User> users = userMapper.findAll();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    ScheduledPunch.showTimer(0, 20, 10, users);
                }
            };
            thread.start();
            flag = false;
            userService.changeUsersToday();
            return "打卡启动";
        } else {
            return "打卡已经启动";
        }

    }

    public String autoPunch1() {
        if (flag1) {
            List<User> users = userMapper.findAll();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    ScheduledPunch.showTimer(2, 20, 10, users);
                }
            };
            thread.start();
            flag1 = false;
            return "打卡启动";
        } else {
            return "打卡已经启动";
        }

    }

    public String autoPunch2() {
        if (flag2) {
            List<User> users = userMapper.findAll();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    ScheduledPunch.showTimer(4, 20, 10, users);
                }
            };
            thread.start();
            flag2 = false;
            return "打卡启动";
        } else {
            return "打卡已经启动";
        }

    }

    public String autoPunch3() {
        if (flag3) {
            List<User> users = userMapper.findAll();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    ScheduledPunch.showTimer(6, 20, 10, users);
                }
            };
            thread.start();
            flag3 = false;
            return "打卡启动";
        } else {
            return "打卡已经启动";
        }

    }

    public String autoPunch4() {
        if (flag4) {
            List<User> users = userMapper.findAll();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    ScheduledPunch.showTimer(7, 20, 10, users);
                }
            };
            thread.start();
            flag4 = false;
            return "打卡启动";
        } else {
            return "打卡已经启动";
        }

    }

    public String autoPunch5() {
        if (flag5) {
            List<User> users = userMapper.findAll();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    ScheduledPunch.showTimer(8, 20, 10, users);
                }
            };
            thread.start();
            flag5 = false;
            return "打卡启动";
        } else {
            return "打卡已经启动";
        }

    }
}
