package com.example.health;

import com.example.health.entity.User;
import com.example.health.service.UserService;
import com.example.health.tools.InterfaceVisitPost;
import com.example.health.tools.UserPunch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

public class UserPunchTest {

}

@SpringBootTest
class StudyActuatorApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    UserPunch userPunch;


    @Test
    void contextLoads() {

//        System.out.println(userService);
//        User user = new User("2222", "11111", 30, "0", "asdf.qq.com", "410100", "测试地址");
//        userService.insertUser(user);
//        System.out.println(userService.findAll());
    }

}