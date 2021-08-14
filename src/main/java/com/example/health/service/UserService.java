package com.example.health.service;

import com.example.health.entity.User;
import com.example.health.mapper.UserMapper;
import com.example.health.punch.AutoScript;
import com.example.health.punch.AutoScript1;
import com.example.health.punch.TestScheduled;
import com.example.health.tools.ScheduledPunch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    private static boolean flag = true;
    private static boolean flag1 = true;
    private static boolean flag2 = true;
    private static boolean flag3 = true;
    private static boolean flag4 = true;
    private static boolean flag5 = true;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public String autoPunch1() {
        if (flag1) {
            List<User> users = userMapper.findAll();
            Thread thread = new Thread() {
                @Override
                public void run() {
                    ScheduledPunch.showTimer(0, 20, 10, users);
                    ScheduledPunch.showTimer(2, 20, 10, users);
                    ScheduledPunch.showTimer(4, 20, 10, users);
                    ScheduledPunch.showTimer(6, 20, 10, users);
                    ScheduledPunch.showTimer(8, 20, 10, users);
//                    TestScheduled.showTimer(2, 20, 10, users);
//                    TestScheduled.showTimer(4, 20, 10, users);
//                    TestScheduled.showTimer(6, 20, 10, users);
//                    TestScheduled.showTimer(8, 20, 10, users);
//                    TestScheduled.showTimer(9, 20, 10, users);

                }
            };
            thread.start();
            flag1 = false;
            return "打卡启动";
        } else {
            return "打卡已经启动";
        }

    }

    public int updateUser(int id, int today) {
        int i = userMapper.updateUser(id, today);
        return i;
    }

    public int updateUserDays(int id, int days) {
        int i = userMapper.updateUserDays(id, days);
        return i;
    }
}
