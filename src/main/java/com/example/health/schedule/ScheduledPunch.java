package com.example.health.schedule;

import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import com.example.health.service.UserService;
import com.example.health.tools.SendEmailTools;
import com.example.health.tools.UserPunch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledPunch {
    @Autowired
    UserService userService;
    @Autowired
    UserPunch userPunch;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 定时每天0,2,4,8点50执行打卡
    @Scheduled(cron = "0 10 0,2,4,8 * * ?")
    public void scheduledPunch() {
        logger.info("开始打卡 === 使用Scheduled方式 ==== ");
        List<User> userList = userService.findAll();
        for (User user : userList) {
            userPunch.punch(user);
        }
    }

    // 定时更改每天打卡状态
    @Scheduled(cron = "0 10 23 * * ?")
    public void scheduledChangeToday() {
        logger.info("定时修改每天打卡状态 === 使用Scheduled方式 ==== ");
        List<User> userList = userService.findAll();
        for (User user : userList) {
            // 用户还有打卡天数则进行修改，否则不修改
            if (user.getDays() > 0){
                userService.updateUserToday(user.getId(), "0");
                logger.info("定时修改每天打卡状态 === 使用Scheduled方式 ==== 修改后 === " + user);
            }
        }
    }

    // 定时提醒用户今天打卡状态修改情况
    @Scheduled(cron = "0 50 23 * * ?")
//    @Scheduled(cron = "0 38 11 * * ?")
    public void scheduleRemind() {
        logger.info("定时提醒我打卡状态修改");
        List<User> userList = userService.findAll();
        boolean flag = true;
        String message = "";
        String title = "修改成功===" + userList.size() + "个被修改";
        // 检查是否都已经修改
        for (User user : userList) {
            if (!user.getToday().equals("0")) {
                // 不成功情况
                flag = false;
                message += user.getUser() + "：修改今日打卡状态失败，用户剩余" + user.getDays() + "天！\n";
            } else {
                message += user.getUser() + "：修改今日打卡状态成功，用户剩余" + user.getDays() + "天！\n";
            }
        }
        if (flag) {
            // 如果都已经修改完成
            new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                    "1945716435@qq.com", title, message);
        } else {
            // 如果修改失败
            new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                    "1945716435@qq.com", "修改失败===及时处理", message);
        }
    }


    // 定时提醒打卡成功情况
    @Scheduled(cron = "0 20 8 * * ?")
    public void scheduledRemindMe() {
        logger.info("定时提醒我所有用户打卡情况");
        List<User> userList = userService.findAll();
        boolean flag = true;
        String message = "";
        for (User user : userList) {
            if (!user.getToday().equals("1")) {
                // 不成功情况
                flag = false;
                message += user.getUser() + "：打卡成功！用户剩余" + user.getDays() + "天！\n";
            } else {
                // 成功情况
                message += user.getUser() + "：打卡失败！用户剩余" + user.getDays() + "天！\n";
            }
        }

        if (flag) {
            // 如果都打卡成功
            new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                    "1945716435@qq.com", "打卡成功" + userList.size() + "人，没有失败情况", message);
        } else {
            // 如果都打卡失败
            new SendEmailTools().sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                    "1945716435@qq.com", "打卡有失败者===及时处理", message);
        }
    }

    // Scheduled 测试执行
    @Scheduled(cron = "20 21 11 * * ?")
    public void scheduledTest() {
//        logger.info("定时修改每天打卡状态 === Scheduled 测试执行 ==== ");
//        List<User> userList = userService.findAll();
//        for (User user : userList) {
//            userService.updateUserToday(user.getId(), "0");
//        }

//        logger.info("开始打卡 === 使用Scheduled方式 ==== ");
//        List<User> userList = userService.findAll();
//        for (User user : userList) {
//            userPunch.punch(user);
//        }
    }
}
