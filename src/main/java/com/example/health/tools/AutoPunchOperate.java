package com.example.health.tools;

import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import com.example.health.service.AutoPunchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AutoPunchOperate {
    Logger logger = LoggerFactory.getLogger(AutoPunchService.class);
    public String autoPunch(User user) {
        Map<String, String> map = new HashMap<>();
        map.put("user", user.getUser());
        map.put("password", user.getPassword());
        map.put("address", user.getAddress());
        map.put("city_code", user.getCity_code());

        String res = null;
        try {
            // 访问接口
            logger.info("访问自动打卡接口 ==== " + user);
            res = new InterfaceVisit().post(MyConfig.IntegerFacePath, map);
        } catch (IOException e) {
            logger.info("访问自动打卡接口失败 ==== " + map);
            // 失败时添加失败log
            new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "autoPunchInterfaceError");
            e.printStackTrace();
        }
        System.out.println(res);
        return res;
    }
}
