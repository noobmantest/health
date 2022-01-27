package com.example.health;

import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import com.example.health.service.UserService;
import com.example.health.tools.AutoPunchOperate;
import com.example.health.tools.InterfaceVisitPost;
import com.example.health.tools.SendEmailToUser;
import com.example.health.tools.UserPunch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserPunchTest {

}

@SpringBootTest
class StudyActuatorApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    UserPunch userPunch;
    @Autowired
    SendEmailToUser sendEmailToUser;


    @Test
    void contextLoads() {
        User user = new User("20177720415", "07024612", 30, "0",
                "1945716435@qq.com", "410100", "测试地址");
        Map<String, String> map = new HashMap<>();
        map.put("user", user.getUser());
        map.put("password", user.getPassword());
        map.put("address", user.getAddress());
        map.put("city_code", user.getCity_code());

        // 访问接口
        String res = new InterfaceVisitPost().post("http://81.70.250.230:8888/login", map);
        System.out.println(res);


    }

}