package com.example.health.tools;

import com.example.health.entity.Log;
import com.example.health.entity.User;
import com.example.health.service.LogService;
import com.example.health.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
 * 用户打卡，
 * 调用AutoPunchOperate.autoPunch()方法
 * 根据返回值判断打卡结果
 * 根据返回值进行数据库的系列操作
 * 根据返回结果发送邮件给用户
 * */


@Component
public class UserPunch {
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @Autowired
    SendEmailToUser sendEmailToUser;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String punch(User user) {
        logger.info("尝试打卡 ==== " + user);
        // 判断条件：今天打卡状态为0，用户打卡天数>0，开关为1
        if (user.getToday().equals("0") && user.getDays() > 0 && user.getOpen() == 1) {
            logger.info("符合条件用户开始打卡 ==== " + user);
            // 访问打卡接口
            String res = new AutoPunchOperate().autoPunch(user);

            // 切割字符串，获取success，否则是不成功情况
            String isSuccess = res.substring(0, 7);


            // 成功情况
            if (isSuccess.equals("success")) {
                // 成功情况
                // 剩余天数减少1
//                new UserOperate().daysDown(user, -1);
                userService.updateUserDays(user.getId(), user.getDays() - 1);
                // 今天打卡状态更改为 1，表示打卡成功
//                new UserOperate().todayChange(user, "1");
                userService.updateUserToday(user.getId(), "1");
                // 添加log数据
                Log log = new Log(user.getUser(), user.getPassword(), isSuccess, String.valueOf(System.currentTimeMillis()));
                logger.info("成功情况添加log数据 ==== " + log);
                logService.insertLog(log);

                // 发送邮件给用户
                String message = new Date().toString() + "账号: " + user.getUser() +
                        "：打卡成功！感谢使用，请您关注每日邮件提醒。自动打卡服务还剩余" + user.getDays() + "天。推荐给更多用户可获得更多打卡天数哦！";
                try {
                    String title = "每日健康打卡";
                    sendEmailToUser.sendEmailToUser(user, title, message + res);
//                    new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
//                            user.getEmail(), "每日健康打卡", message + res);
                } catch (Exception e) {
                    // 发送邮件失败情况，添加日志
                    logger.error("邮件发送失败 ==== " + user);
                    Log logFail = new Log(user.getUser(), user.getPassword(), "sendEmailError", String.valueOf(System.currentTimeMillis()));
                    // 发送邮件失败添加日志
                    logger.error("发送邮件失败添加日志 ==== " + logFail);
                    logService.insertLog(logFail);
                    e.printStackTrace();
                }
            } else if (new Date().getHours() >= 6) {
                // 打卡不成功情况，提醒用户
                logger.error("脚本异常且在6点之后 ==== 发送邮件给用户提醒手动打卡 ==== " + user);
                String message = new Date().toString() + " 账号: " + user.getUser() +
                        "：自动打卡失败。请核实账号、密码、地市选择是否正确，请手动打卡，今日打卡不计算入天数哦。" +
                        "感谢使用，请您关注每日邮件提醒。自动打卡服务还剩余" + user.getDays() + "天。";
                try {
                    String title = "每日健康打卡==失败";
                    sendEmailToUser.sendEmailToUser(user, title, message);
//                    new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
//                            user.getEmail(), "每日健康打卡==失败", message);
                } catch (Exception e) {
                    logger.error("邮件发送失败 ==== " + user);
                    // 发送邮件失败情况，添加日志
//                    new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "sendEmailError");
                    Log logFail = new Log(user.getUser(), user.getPassword(), "sendEmailError", String.valueOf(System.currentTimeMillis()));
                    // 发送邮件失败添加日志
                    logger.error("发送邮件失败添加日志 ==== " + logFail);
                    logService.insertLog(logFail);
                    e.printStackTrace();
                }
            } else {
                logger.error("访问打卡接口失败 ==== " + user);
            }
            return "";
        } else {
            logger.warn("不符合符合条件用户=== 不进行打卡 ==== " + user);
        }
        return "";
    }
}