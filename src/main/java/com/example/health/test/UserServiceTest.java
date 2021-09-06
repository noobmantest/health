package com.example.health.test;

import com.example.health.entity.User;
import com.example.health.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void userServiceTestInsert() {
//        (String user, String password, int days, String today, String email, String city_code, String address)
        User user = new User("2222", "11111", 30, "0", "asdf.qq.com", "410100", "测试地址");

        userService.insertUser(user);
    }
}
