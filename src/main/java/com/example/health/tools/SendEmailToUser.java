package com.example.health.tools;

import com.example.health.config.MyConfig;
import com.example.health.entity.Log;
import com.example.health.entity.User;
import com.example.health.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * 给用户发送邮件
 * 对SendEmailTools的再次封装
 * */

@Component
public class SendEmailToUser {
    @Autowired
    LogService logService;
    @Autowired
    SendEmailTools sendEmailTools;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendEmailToUser(User user, String title, String message) {
        try {
            sendEmailTools.sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                    user.getEmail(), title, message);
        } catch (Exception e) {
            logger.error("发送邮件失败===" + user.getUser());
            // 插入日志
            Log logFail = new Log(user.getUser(), user.getPassword(), "sendEmailError",
                    String.valueOf(System.currentTimeMillis()));
            logService.insertLog(logFail);
            e.printStackTrace();
        }
    }

    public void sendEmailToManager(String managerEmail, String title, String message) {
        try {
            sendEmailTools.sendEmail(MyConfig.fromEmail, MyConfig.fromEmailAuthorizationCode,
                    managerEmail, title, message);
        } catch (Exception e) {
            logger.error("发送给管理员邮件失败===" + managerEmail);
            e.printStackTrace();
        }
    }
}
