package com.example.health.controller;

import com.example.health.service.AutoPunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoPunchController {
    @Autowired
    AutoPunchService autoPunchService;

    @RequestMapping("/autoPunch")
    public String autoPunch() {
        String s = autoPunchService.autoPunch();
        return s + "autoPunch";
    }

    @RequestMapping("/autoPunch1")
    public String autoPunch1() {
        String s = autoPunchService.autoPunch1();
        return s + "autoPunch1";
    }

    @RequestMapping("/autoPunch2")
    public String autoPunch2() {
        String s = autoPunchService.autoPunch2();
        return s + "autoPunch2";
    }

    @RequestMapping("/autoPunch3")
    public String autoPunch3() {
        String s = autoPunchService.autoPunch3();
        return s + "autoPunch3";
    }

    @RequestMapping("/autoPunch4")
    public String autoPunch4() {
        String s = autoPunchService.autoPunch4();
        return s + "autoPunch4";
    }

    @RequestMapping("/autoPunch5")
    public String autoPunch5() {
        String s = autoPunchService.autoPunch5();
        return s + "autoPunch5";
    }
}
