package com.example.health.schedule;


import com.example.health.entity.User;
import com.example.health.service.UserService;
import com.example.health.tools.SendEmailToUser;
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
    @Autowired
    SendEmailToUser sendEmailToUser;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // 定时每天0,2,4,6点20执行打卡
    @Scheduled(cron = "0 20 0,2,4,6 * * ?")
    public void scheduledPunch() {
        logger.info("开始打卡 === 使用Scheduled方式 ==== ");
        List<User> userList = userService.findAll();
        for (User user : userList) {
            userPunch.punch(user);
        }
    }

    // 定时更改每天打卡状态
    @Scheduled(cron = "0 10 22 * * ?")
    public void scheduledChangeToday() {
        logger.info("定时修改每天打卡状态 === 使用Scheduled方式 ==== ");
        List<User> userList = userService.findAll();
        for (User user : userList) {
            // 用户还有打卡天数则进行修改，否则不修改
            if (user.getDays() > 0 && user.getOpen() == 1) {
                userService.updateUserToday(user.getId(), "0");
                logger.info("定时修改每天打卡状态 === 使用Scheduled方式 ==== 修改后 === " + user);
            }
        }
    }

    // 定时提醒今天打卡状态修改情况
    @Scheduled(cron = "0 50 22 * * ?")
    public void scheduleRemind() {
        logger.info("定时提醒我打卡状态修改");
        List<User> userList = userService.findAll();
        boolean flag = true;
        String message = "";

        int count = 0; // 记录失败个数
        // 检查是否都已经修改
        for (User user : userList) {
            if (user.getToday().equals("0")) {
                // 成功情况
                message += user.getUser() + "：修改今日打卡状态成功，用户剩余" + user.getDays() +
                        "天！目前today：" + user.getToday() + "\n";
            } else if (user.getOpen() == 0 || user.getDays() < 1) {
                message += user.getUser() + "：天数用完或关闭打卡，不需要修改今日打卡状态，用户剩余" +
                        user.getDays() + "天！目前today：" + user.getToday() + "\n";
            } else {
                // 失败情况
                flag = false;
                count++;
                message += user.getUser() + "：修改今日打卡状态失败，用户剩余" + user.getDays() +
                        "天！目前today：" + user.getToday() + "\n";
            }
        }
        if (flag) {
            // 如果都已经修改完成
            String title = "修改成功===" + userList.size() + "个被修改";
            sendEmailToUser.sendEmailToManager("1945716435@qq.com", title, message);
        } else {
            // 如果修改失败
            String title = "修改有失败者===" + count + "个被修改";
            sendEmailToUser.sendEmailToManager("1945716435@qq.com", title, message);
        }
    }


    // 定时提醒打卡成功情况，7点提醒我
    @Scheduled(cron = "0 0 7 * * ?")
    public void scheduledRemindMe() {
        logger.info("定时提醒我所有用户打卡情况");
        List<User> userList = userService.findAll();
        boolean flag = true;
        String message = "";
        int count = 0; // 记录失败个数
        for (User user : userList) {
            // 判断今天是否打卡，为1表示成功，为0表示失败
            if (user.getToday().equals("1")) {
                // 成功情况
                message += user.getUser() + "：打卡成功！用户剩余" + user.getDays() +
                        "天！目前today：" + user.getToday() + "\n";
            } else if (user.getOpen() == 0 || user.getDays() < 1) {
                message += user.getUser() + "：用户天数为0或者关闭打卡，不进行打卡！用户剩余" +
                        user.getDays() + "天！目前today：" + user.getToday() + "\n";
            } else {
                // 失败情况
                flag = false;
                count++;
                message += user.getUser() + "：打卡失败！用户剩余" + user.getDays() +
                        "天！目前today：" + user.getToday() + "\n";
            }
        }

        if (flag) {
            // 如果都打卡成功
            String title = "打卡成功" + userList.size() + "人，没有失败情况";
            sendEmailToUser.sendEmailToManager("1945716435@qq.com", title, message);
//            sendEmailTools.sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
//                    "1945716435@qq.com", "打卡成功" + userList.size() + "人，没有失败情况", message);
        } else {
            // 如果都打卡失败
            String title = "打卡有失败者===失败" + count + "人===及时处理";
            sendEmailToUser.sendEmailToManager("1945716435@qq.com", title, message);

//            sendEmailTools.sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
//                    "1945716435@qq.com", "打卡有失败者===失败" + count + "人===及时处理", message);
        }
    }

    // 当用户天数少于5天时候提醒用户,每天16:20提醒
    @Scheduled(cron = "0 20 16 * * ?")
    public void scheduledRemindDays() {
        List<User> userList = userService.findAll();
        for (User user : userList) {
            if (user.getDays() > 0 && user.getDays() <= 5) {
                String message = "感谢您使用自动健康打卡系统，您的可用打卡天数剩余" + user.getDays() + "天！获取更多天数可通过" +
                        "邀请更多用户获取。每邀请一名新用户可获取15天打卡天数。\n\n 付费获取打卡天数可回复此邮件了解更多。";
                String title = "打天天数少于5天===可通过邀请用户获得更多打卡天数";
                sendEmailToUser.sendEmailToUser(user, title, message);
//                sendEmailTools.sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
//                        user.getEmail(), "打天天数少于5天===可通过邀请用户获得更多打卡天数", message);
            }
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
