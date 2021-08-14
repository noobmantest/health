package com.example.health.tools;

import com.example.health.config.MyConfig;
import com.example.health.entity.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AutoPunchOperate {
    public String autoPunch(User user) {
        Map<String, String> map = new HashMap<>();
        map.put("user", user.getUser());
        map.put("password", user.getPassword());

        String res = null;
        try {
            // 访问接口
            res = new InterfaceVisit().post(MyConfig.IntegerFacePath, map);
        } catch (IOException e) {
            // 失败时添加失败log
            new LogInterfaceOperate().insertLog(user.getUser(), user.getPassword(), "insertLogInterfaceError");
            e.printStackTrace();
        }
        System.out.println(res);
        return res;
    }
}
