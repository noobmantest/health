package com.example.health.service;

import com.example.health.entity.User;
import com.example.health.mapper.UserMapper;
import com.example.health.tools.ScheduledChangeUsersToday;
import com.sun.xml.internal.ws.spi.db.DatabindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public int updateUserToday(int id, String today) {
        return userMapper.updateUserToday(id, today);
    }

    public int updateUserDays(int id, int days) {
        return userMapper.updateUserDays(id, days);
    }

    // 添加用户
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }


    // 更改每天打卡动态
    public void changeUsersToday() {
        List<User> userList = userMapper.findAll();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (User user : userList) {
                    if (user.getToday().equals("1")) {
                        userMapper.updateUserToday(user.getId(), "0");
                    }
                }
            }
        };

        // 定时22：00：10执行，每隔一天执行一次
        ScheduledChangeUsersToday.showTimer(22, 00, 10, 1000 * 60 * 60 * 24, task);
        System.out.println(new Date().toString() + "：定时22：00：10执行，每隔一天执行一次");
    }
}
