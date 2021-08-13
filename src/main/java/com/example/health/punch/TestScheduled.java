package com.example.health.punch;

import com.example.health.entity.User;

import java.util.*;

public class TestScheduled {
    static int count = 0;

    public static void showTimer(int hourOfDay, int minute, int second, List<User> users) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ++count;
                System.out.println("时间=" + new Date() + " 执行了" + count + "次"); // 1次

//                AutoScript1 autoScript1 = new AutoScript1(users);
//                autoScript1.start();
                for (User user : users) {
                    if (user.getToday().equals("0") || user.getDays() > 0) {
                        String res = AutoScript.autoScript(user.getUser(), user.getPassword());
                        System.out.println(res);
                        if (res.equals("验证码")) {
                            break;
                        } else if (res.equals("密码错误") || res.equals("账号找不到")) {
                            continue;
                        } else if (res.equals("未知")) {
                            System.out.println(user.getUser() + "----" + user.getPassword() + "未知错误。。。。。");
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

        int period = 60 * 60 * 1000;
        //每天的date时刻执行task，每隔2秒重复执行
        timer.schedule(task, date);

        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
    }

    public static void main(String[] args) {
//        showTimer(17,35, 10);
    }
}