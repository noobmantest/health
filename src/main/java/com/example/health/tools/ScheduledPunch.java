package com.example.health.tools;


import com.example.health.config.MyConfig;
import com.example.health.entity.User;

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
                        if (res.equals("Success")) {
                            // 成功情况
                            // 剩余天数减少1
                            new UserOperate().daysDown(user, -1);
                            // 今天打卡状态更改为 1，表示打卡成功
                            new UserOperate().todayChange(user, "1");
                            // 发送邮件给用户
                            String message = new Date().toString() + "：打卡成功！感谢使用，请您关注每日邮件提醒。自动打卡服务还剩余" + user.getDays() + "天。";
                            try {
                                new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                                        user.getEmail(), "每日健康打卡", message);
                            } catch (Exception e) {
                                // 发送邮件失败情况，添加日志
                                new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "sendEmailError");
                                e.printStackTrace();
                            }
                        } else if (res.equals("accountOrPassword")) {
                            // 账号或者密码错误情况
                            // 剩余天数减少1
                            new UserOperate().daysDown(user, -1);
                            // 今天不再打卡，改变今天打卡状态，避免以后进程扫描到
                            new UserOperate().todayChange(user, "1");
                            // 发送邮件给用户
                            String message = new Date().toString() +
                                    "：账号或密码错误！请及时修改账号和密码，今日记得手动打卡哦。" +
                                    "感谢使用，请您关注每日邮件提醒。自动打卡服务还剩余" + user.getDays() + "天。";
                            try {
                                new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                                        user.getEmail(), "每日健康打卡", message);
                            } catch (Exception e) {
                                // 发送邮件失败情况，添加日志
                                new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "sendEmailError");
                                e.printStackTrace();
                            }
                        } else if (res.equals("scriptException")) {
                            // 如果时间在9点之后且者脚本异常，提示用户手动打卡
                            int nowHours = new Date().getHours();
                            if (nowHours > 9) {
                                String message = new Date().toString() +
                                        "：脚本异常，请手动打卡，今日打卡不计算入天数哦。" +
                                        "感谢使用，请您关注每日邮件提醒。自动打卡服务还剩余" + user.getDays() + "天。";
                                try {
                                    new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                                            user.getEmail(), "每日健康打卡", message);
                                } catch (Exception e) {
                                    // 发送邮件失败情况，添加日志
                                    new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "sendEmailError");
                                    e.printStackTrace();
                                }
                            }
                        } else if (res.equals("verificationCode")) {
                            // 如果时间在9点之后且弹出验证码，提示用户手动打卡
                            int nowHours = new Date().getHours();
                            if (nowHours >= 9) {
                                String message = new Date().toString() +
                                        "：脚本异常，请手动打卡，今日打卡不计算入天数哦。" +
                                        "感谢使用，请您关注每日邮件提醒。自动打卡服务还剩余" + user.getDays() + "天。";
                                try {
                                    new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                                            user.getEmail(), "每日健康打卡", message);
                                } catch (Exception e) {
                                    // 发送邮件失败情况，添加日志
                                    new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "sendEmailError");
                                    e.printStackTrace();
                                }
                            } else {
                                // 如果时间在9点之前且弹出验证码，打断接下来的脚本执行
                                break;
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

        // 如果第一次执行定时任务的时间 小于 当前的时间
        // 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        if (date.before(new Date())) {
            date = new ScheduledPunch().addDay(date, 1);
        }
        // 到date时间执行一次，且每间隔一天执行一次
        timer.schedule(task, date, 1000 * 60 * 60 * 24);

        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
    }

    // 增加或减少天数
    public Date addDay(Date date, int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);
        return startDT.getTime();
    }
}