package com.example.health.tools;

import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class UserPunch {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public String punch(User user) {
        LoggerFactory.getLogger(this.getClass()).info("尝试打卡 ==== " + user);
        if (user.getToday().equals("0") && user.getDays() > 0) {
            LoggerFactory.getLogger(this.getClass()).info("符合条件用户开始打卡 ==== " + user);
            // 访问打卡接口
            String res = new AutoPunchOperate().autoPunch(user);

            // 访问log数据接口 添加log数据
            new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), res);

            // 成功情况
            if (res.equals("success")) {
                // 成功情况
                // 剩余天数减少1
                new UserOperate().daysDown(user, -1);
                // 今天打卡状态更改为 1，表示打卡成功
                new UserOperate().todayChange(user, "1");
                // 发送邮件给用户
                String message = new Date().toString() + "账号: " + user.getUser() + "：打卡成功！感谢使用，请您关注每日邮件提醒。自动打卡服务还剩余" + user.getDays() + "天。";
                try {
                    new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                            user.getEmail(), "每日健康打卡", message);
                } catch (Exception e) {
                    // 发送邮件失败情况，添加日志
                    LoggerFactory.getLogger(this.getClass()).info("邮件发送失败 ==== " + user);
                    new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "sendEmailError");
                    e.printStackTrace();
                }
            } else if (new Date().getHours() >= 8) {
                // 打卡不成功情况，提醒用户
                LoggerFactory.getLogger(this.getClass()).info("脚本异常且在9点之后 ==== 发送邮件给用户提醒手动打卡 ==== " + user);
                String message = new Date().toString() + " 账号: " + user.getUser() +
                        "：自动打卡失败。请核实账号、密码、地市选择是否正确，请手动打卡，今日打卡不计算入天数哦。" +
                        "感谢使用，请您关注每日邮件提醒。自动打卡服务还剩余" + user.getDays() + "天。";
                try {
                    new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                            user.getEmail(), "每日健康打卡==失败", message);
                } catch (Exception e) {
                    LoggerFactory.getLogger(this.getClass()).info("邮件发送失败 ==== " + user);
                    // 发送邮件失败情况，添加日志
                    new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "sendEmailError");
                    e.printStackTrace();
                }
            } else {
                logger.info("访问打卡接口失败 ==== " + user);
            }
            return "";
        }else {
            LoggerFactory.getLogger(this.getClass()).info("不符合符合条件用户=== 不进行打卡 ==== " + user);
        }
        return "";
    }
}