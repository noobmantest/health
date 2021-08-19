package com.example.health.tools;


import java.util.*;

public class Schedule {

    // 定时任务方法,传入每天执行的时间和任务
    public void showTimer(int hourOfDay, int minute, int second, long period, TimerTask task) {
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
        timer.schedule(task, date, period);

        //每天的date时刻执行task, 仅执行一次
        //timer.schedule(task, date);
    }
}
