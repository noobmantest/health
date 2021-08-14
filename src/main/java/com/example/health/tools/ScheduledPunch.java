package com.example.health.tools;


import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import com.example.health.punch.AutoScript;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

public class ScheduledPunch {
    static int count = 0;

    public static void showTimer(int hourOfDay, int minute, int second, List<User> users) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ++count;
                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次

                for (User user : users) {
                    if (user.getToday().equals("0") || user.getDays() > 0) {
                        // 访问打卡接口
                        String res = new AutoPunchOperate().autoPunch(user);

                        // 访问天界log数据接口 添加log数据
                        new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), res);

                        // 如果弹出验证码则打断
                        if (res.equals("verificationCode")) {
                            break;
                        }else if (res.equals("success")){
                            // 成功情况
                            // 剩余天数减少1
                            new UserOperate().daysDown(user, -1);
                            // 今天打卡状态更改为 1，表示打卡成功
                            new UserOperate().todayChange(user, "1");
                            // 发送邮件给用户
                            String message = new Date().toString() + "打卡成功！感谢使用，请您关注每日邮件提醒";
                            try {
                                new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                                        user.getEmail(),"每日健康打卡", message);
                            } catch (Exception e) {
                                // 发送邮件失败情况，添加日志
                                new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "sendEmailError");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };

        //设置执行时间
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);//每天
        //定制每天的21:09:00执行，
        calendar.set(year, month, day, hourOfDay, minute, second);
        Date date = calendar.getTime();
        Timer timer = new Timer();
        System.out.println(date);

        // 到date时间执行一次
        timer.schedule(task, date);

        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
    }
}