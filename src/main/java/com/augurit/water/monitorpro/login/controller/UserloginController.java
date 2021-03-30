package com.augurit.water.monitorpro.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userloginRestController")
@RequestMapping("/user")
public class UserloginController {
    @RequestMapping("/login")
    public String login(String username,String password){

        return "hello";
    }
}
