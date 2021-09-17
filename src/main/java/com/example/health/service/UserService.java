package com.example.health.service;

import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import com.example.health.mapper.UserMapper;

import com.example.health.tools.InterfaceVisitPost;
import com.example.health.tools.Schedule;
import com.example.health.tools.SendEmailToUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;
    @Autowired
    SendEmailToUser sendEmailToUser;

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
    public String insertUser(User user, String inviteUser) {
        logger.info("添加用户 ==== " + user + " ==== 邀请人 === " + inviteUser);
        // 发送请求，判断注册账号密码是否正确
        Map<String, String> map = new HashMap<>();
        map.put("user", user.getUser());
        map.put("password", user.getPassword());
        // 访问接口，返回True表示正确否则为失败
        String isPasswordTrue = new InterfaceVisitPost().post(MyConfig.IntegerFacePath_IsPasswordTrue, map);

        // 账号密码不正确情况，推荐增加天数失效
        if (isPasswordTrue.equals("False")) {
            logger.error("注册账号密码错误 === 提醒用户修改");

            String message = "感谢您使用自动健康打卡系统，系统检测到您的账号：" + user.getUser() + " 的账号或密码有误。" +
                    "请您核实并在系统中修改。系统地址：http://81.70.250.230:8081/autoPunch/#/。新用户邀请失效。";
            String title = "注册失败===账号或密码不正确请及时修改";
            sendEmailToUser.sendEmailToUser(user, title, message);
            return "fail";
        }

        if (isPasswordTrue.equals("True")) {
            // 账号密码正确情况查找邀请人信息
            List<User> userList = findUserByUser(inviteUser);
            logger.info("查找到的邀请人 ==== " + userList);

            // 如果能查到用户信息且注册者账号密码正确，执行添加操作
            if (userList.size() > 0) {
                logger.info("执行增加邀请人数和增加天数 ==== " + userList);
                // 增加用户邀请人数
                addInviteNumsByUser(inviteUser);
                logger.info("增加用户邀请人数 ==== " + inviteUser);
                // 增加邀请人打卡天数，邀请一个人增加15天
                updateUserDays(userList.get(0).getId(), userList.get(0).getDays() + 15);
                logger.info("增加邀请人打卡天数，邀请一个人增加15天 ==== " + userList.get(0));
                // 注册成功，邀请人天数增加提醒用户注册成功
                String message = "感谢您使用自动健康打卡系统，账号：" + user.getUser() + " 注册成功。每日凌晨自动打卡系统会帮助您" +
                        "进行自动打卡，同时会发送邮件提醒您是否打卡成功，请您及时关注邮箱动态。\n\n" +
                        "系统地址：http://81.70.250.230:8081/autoPunch/#/。您可以通过邀请新用户获得更多打卡天数。";
                String title = "注册成功===感谢您的关注";
                sendEmailToUser.sendEmailToUser(user, title, message);
            }
            userMapper.insertUser(user);
            return "success";
        } else {
            return "fail";
        }
    }

    // 根据邮箱查询用户，不返回密码
    public List<User> findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    // 根据用户名查询用户，返回查询到用户个数
    public int countUserByUser(String user) {
        return userMapper.findUserByUser(user).size();
    }

    // 根据用户名查询用户，返回查询到的用户
    public List<User> findUserByUser(String user) {
        return userMapper.findUserByUser(user);
    }

    // 通过用户名和密码查询用户
    public List<User> findUserByUserAndPassword(String user, String password) {
        return userMapper.findUserByUserAndPassword(user, password);
    }

    // 更新用户信息
    public int updateUserById(String user, String password, String email, String city_code, String address, int open, int id) {
        logger.info("修改用户信息 === " + user + password + email + city_code + address + open);
        return userMapper.updateUserById(user, password, email, city_code, address, open, id);
    }

    // 增加用户邀请人数
    public int addInviteNumsByUser(String user) {
        User inviteUser = userMapper.findUserByUser(user).get(0);
        return userMapper.addInviteNumsByUser(user, inviteUser.getInviteNums() + 1);
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
        new Schedule().showTimer(22, 0, 10, 1000 * 60 * 60 * 24, task);
        logger.info("定时任务修改用户每日状态22：00：10执行，每隔一天执行一次");
        return "定时任务修改用户每日状态22：00：10执行，每隔一天执行一次";
    }
}
