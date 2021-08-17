package com.example.health.tools;

import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserOperate {
    Logger logger = LoggerFactory.getLogger(this.toString());

    // 改变用户今天的打卡状态
    public void todayChange(User user, String today) {
        logger.info("改变用户今天的打卡状态, 改变为 ==== " + today + " ==== " + user);
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(user.getId()));
        map.put("today", today);
        String post = null;
        try {
            post = new InterfaceVisit().post(MyConfig.LocalInterFace + "updateUserToday", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(post);
    }

    // days添加 传入添加天数
    public void daysDown(User user, int addDays) {
        logger.info("增加用户自动打卡天数, 增加 ==== " + addDays + " ==== " + user);

        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(user.getId()));
        map.put("days", String.valueOf(user.getDays() + addDays));
        String post = null;
        try {
            post = new InterfaceVisit().post(MyConfig.LocalInterFace + "updateUserDays", map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(post);
    }
}
