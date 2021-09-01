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
//        List<User> userList = userService.findAll();
//        userPunch.punch(userList.get(0));
//        new InterfaceVisitPost().post("")
    }

}