package com.example.health.service;

import com.example.health.entity.User;
import com.example.health.mapper.UserMapper;
import com.example.health.punch.AutoScript;
import com.example.health.punch.AutoScript1;
import com.example.health.punch.TestScheduled;
import com.example.health.tools.ScheduledPunch;
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

    public int updateUserToday(int id, int today) {
        int i = userMapper.updateUserToday(id, today);
        return i;
    }

    public int updateUserDays(int id, int days) {
        int i = userMapper.updateUserDays(id, days);
        return i;
    }
}
