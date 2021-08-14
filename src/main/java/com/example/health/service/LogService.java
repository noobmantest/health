package com.example.health.service;

import com.example.health.entity.Log;
import com.example.health.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public List<Log> findAll() {
        List<Log> logs = logMapper.findAll();
        return logs;
    }

    public int insertLog(Log log){
        int i = logMapper.insertLog(log);
        return i;
    }

    public List<Log> findByUser(String user) {
        List<Log> byUser = logMapper.findByUser(user);
        return byUser;
    }
}
