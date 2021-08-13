package com.example.health.service;

import com.example.health.entity.User;
import com.example.health.mapper.UserMapper;
import com.example.health.punch.AutoScript;
import com.example.health.punch.AutoScript1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public String autoPunch() {
        List<User> users = userMapper.findAll();

        for (User user : users) {
//            System.out.println(user.getToday());
            if (user.getDays() > 0 && user.getToday().equals("0")) {
                AutoScript autoScript = new AutoScript(user.getUser(), user.getPassword());
                autoScript.run();
                try {
                    Thread.sleep(1000*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return "打卡中";
    }

    public String autoPunch1() {
        List<User> users = userMapper.findAll();
        AutoScript1 autoScript1 = new AutoScript1(users);
        autoScript1.start();

        return "打卡中";
    }
}
