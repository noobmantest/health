package com.example.health.controller;

import com.example.health.entity.User;
import com.example.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    public String helloWord() {
        System.out.println("fhsdtrysdf");
        return "hello, Spring Boot";
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        System.out.println("sdfasdfadgf");
        return userService.findAll();
    }

    @RequestMapping("/autoPunch")
    public String autoPunch(){
        String s = userService.autoPunch();
        return s;
    }

    @RequestMapping("/autoPunch1")
    public String autoPunch1(){
        String s = userService.autoPunch1();
        return s;
    }
}
