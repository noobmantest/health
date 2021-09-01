package com.example.health.schedule;

import com.example.health.entity.User;
import com.example.health.service.UserService;
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
    @Scheduled(cron = "0 50 0,2,4,8 * * ?")
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
            userService.updateUserToday(user.getId(), "0");
            logger.info("定时修改每天打卡状态 === 使用Scheduled方式 ==== 修改后 === " + user);
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
