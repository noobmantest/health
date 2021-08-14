package com.example.health.tools;


import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import com.example.health.punch.AutoScript;

import java.io.IOException;
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