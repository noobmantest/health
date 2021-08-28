package com.example.health.tools;

import com.example.health.config.MyConfig;
import com.example.health.entity.User;
import com.example.health.service.AutoPunchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/*
* 访问打卡接口
* 使用post方式
* 返回打卡结果
* */
public class AutoPunchOperate {
    Logger logger = LoggerFactory.getLogger(AutoPunchService.class);
    public String autoPunch(User user) {
        Map<String, String> map = new HashMap<>();
        map.put("user", user.getUser());
        map.put("password", user.getPassword());
        map.put("address", user.getAddress());
        map.put("city_code", user.getCity_code());

        // 访问接口
        logger.info("访问自动打卡接口 ==== " + user);
        String res = new InterfaceVisitPost().post(MyConfig.IntegerFacePath, map);
//        System.out.println(res);
        return res;
    }
}
