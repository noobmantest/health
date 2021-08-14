package com.example.health.controller;

import com.example.health.entity.Log;
import com.example.health.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {
    @Autowired
    LogService logService;

    @RequestMapping("/log/findAll")
    public List<Log> findAll() {
        return logService.findAll();
    }

    @RequestMapping("/log/insertLog")
    public int insertLog(Log log) {
        return logService.insertLog(log);
    }

    @RequestMapping("/log/findByUser")
    public List<Log> findByUser(String user) {
        return logService.findByUser(user);
    }
}
