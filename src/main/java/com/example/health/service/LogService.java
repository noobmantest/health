package com.example.health.service;

import com.example.health.entity.Log;
import com.example.health.mapper.LogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogMapper logMapper;

    public List<Log> findAll() {
        List<Log> logs = logMapper.findAll();
        return logs;
    }

    // 添加日志
    public int insertLog(Log log){
        logger.info("添加日志 ==== " +log);
        int i = logMapper.insertLog(log);
        logger.info("添加日志结果 ==== " +i);
        return i;
    }

    public List<Log> findByUser(String user) {
        List<Log> byUser = logMapper.findByUser(user);
        return byUser;
    }
}
