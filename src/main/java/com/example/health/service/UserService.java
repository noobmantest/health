package com.example.health.service;

import com.example.health.entity.User;
import com.example.health.mapper.UserMapper;

import com.example.health.tools.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimerTask;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    // 查找所有用户
    public List<User> findAll() {
        List<User> userList = userMapper.findAll();
        logger.info("查询所有用户 ==== " + userList);
        return userList;
    }

    // 更改今天状态
    public int updateUserToday(int id, String today) {
        logger.info("修改今天打卡状态==== id=" + id + "today= " + today);
        return userMapper.updateUserToday(id, today);
    }

    // 更改剩余天数
    public int updateUserDays(int id, int days) {
        logger.info("修改打卡天数==== id=" + id + " ==== days=" + days);
        return userMapper.updateUserDays(id, days);
    }

    // 添加用户
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    // 根据邮箱查询用户，不返回密码
    public List<User> findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    // 根据用户名查询用户，返回查询到用户个数
    public int countUserByUser(String user) {
        return userMapper.countUserByUser(user).size();
    }

    // 通过用户名和密码查询用户
    public List<User> findUserByUserAndPassword(String user, String password) {
        return userMapper.findUserByUserAndPassword(user, password);
    }

    // 更改每天打卡动态
    public String changeUsersToday() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 查找所有用户 逐个更改用户今日状态
                List<User> userList = userService.findAll();
                for (User user : userList) {
                    if (user.getToday().equals("1")) {
                        logger.info("每日定时任务 === 修改今日打卡状态 === " + user);
//                        userMapper.updateUserToday(user.getId(), "0");
                        userService.updateUserToday(user.getId(), "0");
                        logger.info("每日定时任务 === 修改今日打卡状态 === 更改成功 === " + user);
                    }
                }
            }
        };

        // 定时22：00：10执行，每隔一天执行一次
        new Schedule().showTimer(22, 00, 10, 1000 * 60 * 60 * 24, task);
        logger.info("定时任务修改用户每日状态22：00：10执行，每隔一天执行一次");
        return "定时任务修改用户每日状态22：00：10执行，每隔一天执行一次";
    }
}
