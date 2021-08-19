package com.example.health.service;

import com.example.health.entity.User;
import com.example.health.tools.Schedule;
import com.example.health.tools.UserPunch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimerTask;

@Service
public class AutoPunchService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;
    private static boolean updateTodayScheduleFlag = true;
    private static boolean flag = true;
    private static boolean flag1 = true;
    private static boolean flag2 = true;
    private static boolean flag3 = true;
    private static boolean flag4 = true;
    private static boolean flag5 = true;

    //定时更新用户每日状态
    public String changeUsersToday() {
        logger.info("启动定时任务 === 每日更新用户今日状态  === 访问接口");
        if (updateTodayScheduleFlag) {
            logger.info("启动定时任务 === 每日更新用户今日状态 === 启动");
            String changeUsersToday = userService.changeUsersToday();
            updateTodayScheduleFlag = false;
            return changeUsersToday;
        } else {
            logger.info("启动定时任务 === 每日更新用户今日状态  === 已经启动");
            return "定时更新用户每日状态 === 已经启动";
        }
    }

    public String autoPunch() {
        logger.info("启动定时任务 === 每日健康打卡00:20:10  === 访问接口");
        if (flag) {
            logger.info("启动定时任务 === 每日健康打卡00:20:10 === 启动");
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    List<User> userList = userService.findAll();
                    logger.info("开始循环每个用户打卡 ==== " + userList);
                    for (User user : userList) {
                        logger.info("开始循环每个用户打卡 ==== " + user);
                        if (user.getToday().equals("0") && user.getDays() > 0) {
                            logger.info("用户符合条件开始打卡 ==== " + user);
                              new UserPunch().punch(user);
                        } else {
                            logger.info("用户不符合条件，跳过该用户 ==== " + user);
                        }
                    }
                }
            };

            new Schedule().showTimer(0, 20, 10, 24 * 60 * 60 * 1000, task);
            flag = false;
            return "启动定时任务 === 每日健康打卡00:20:10 === 启动";
        } else {
            logger.info("启动定时任务 === 每日健康打卡00:20:10 === 已经启动");
            return "启动定时任务 === 每日健康打卡00:20:10 === 已经启动";
        }

    }

    public String autoPunch1() {
        logger.info("启动定时任务 === 每日健康打卡02:20:10  === 访问接口");
        if (flag1) {
            logger.info("启动定时任务 === 每日健康打卡02:20:10 === 启动");
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    List<User> userList = userService.findAll();
                    logger.info("开始循环每个用户打卡 ==== " + userList);
                    for (User user : userList) {
                        logger.info("开始循环每个用户打卡 ==== " + user);
                        if (user.getToday().equals("0") && user.getDays() > 0) {
                            logger.info("用户符合条件开始打卡 ==== " + user);
                            String punch = new UserPunch().punch(user);
                        } else {
                            logger.info("用户不符合条件，跳过该用户 ==== " + user);
                        }
                    }
                }
            };

            new Schedule().showTimer(2, 20, 10, 24 * 60 * 60 * 1000, task);
            flag1 = false;
            return "启动定时任务 === 每日健康打卡02:20:10 === 启动";
        } else {
            logger.info("启动定时任务 === 每日健康打卡02:20:10 === 已经启动");
            return "启动定时任务 === 每日健康打卡02:20:10 === 已经启动";
        }

    }

    public String autoPunch2() {
        logger.info("启动定时任务 === 每日健康打卡04:20:10  === 访问接口");
        if (flag2) {
            logger.info("启动定时任务 === 每日健康打卡04:20:10 === 启动");
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    List<User> userList = userService.findAll();
                    logger.info("开始循环每个用户打卡 ==== " + userList);
                    for (User user : userList) {
                        logger.info("开始循环每个用户打卡 ==== " + user);
                        if (user.getToday().equals("0") && user.getDays() > 0) {
                            logger.info("用户符合条件开始打卡 ==== " + user);
                            String punch = new UserPunch().punch(user);
                        } else {
                            logger.info("用户不符合条件，跳过该用户 ==== " + user);
                        }
                    }
                }
            };

            new Schedule().showTimer(4, 20, 10, 24 * 60 * 60 * 1000, task);
            flag2 = false;
            return "启动定时任务 === 每日健康打卡04:20:10 === 启动";
        } else {
            logger.info("启动定时任务 === 每日健康打卡04:20:10 === 已经启动");
            return "启动定时任务 === 每日健康打卡04:20:10 === 已经启动";
        }

    }

    public String autoPunch3() {
        logger.info("启动定时任务 === 每日健康打卡06:20:10  === 访问接口");
        if (flag3) {
            logger.info("启动定时任务 === 每日健康打卡06:20:10 === 启动");
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    List<User> userList = userService.findAll();
                    logger.info("开始循环每个用户打卡 ==== " + userList);
                    for (User user : userList) {
                        logger.info("开始循环每个用户打卡 ==== " + user);
                        if (user.getToday().equals("0") && user.getDays() > 0) {
                            logger.info("用户符合条件开始打卡 ==== " + user);
                            String punch = new UserPunch().punch(user);
                        } else {
                            logger.info("用户不符合条件，跳过该用户 ==== " + user);
                        }
                    }
                }
            };

            new Schedule().showTimer(6, 20, 10, 24 * 60 * 60 * 1000, task);
            flag3 = false;
            return "启动定时任务 === 每日健康打卡06:20:10 === 启动";
        } else {
            logger.info("启动定时任务 === 每日健康打卡06:20:10 === 已经启动");
            return "启动定时任务 === 每日健康打卡06:20:10 === 已经启动";
        }

    }


    public String autoPunch5() {
        logger.info("启动定时任务 === 每日健康打卡08:20:10  === 访问接口");
        if (flag5) {
            logger.info("启动定时任务 === 每日健康打卡08:20:10 === 启动");
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    List<User> userList = userService.findAll();
                    logger.info("开始循环每个用户打卡 ==== " + userList);
                    for (User user : userList) {
                        logger.info("开始循环每个用户打卡 ==== " + user);
                        if (user.getToday().equals("0") && user.getDays() > 0) {
                            logger.info("用户符合条件开始打卡 ==== " + user);
                            String punch = new UserPunch().punch(user);
                        } else {
                            logger.info("用户不符合条件，跳过该用户 ==== " + user);
                        }
                    }
                }
            };

            new Schedule().showTimer(8, 20, 10, 24 * 60 * 60 * 1000, task);
            flag5 = false;
            return "启动定时任务 === 每日健康打卡08:20:10 === 启动";
        } else {
            logger.info("启动定时任务 === 每日健康打卡08:20:10 === 已经启动");
            return "启动定时任务 === 每日健康打卡08:20:10 === 已经启动";
        }

    }
}
