package com.augurit.water.monitorpro.login.controller;

import com.augurit.water.monitorpro.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("userloginRestController")
@RequestMapping("/user")
public class UserloginController {
    @Autowired
    private UserLoginService userService;
    @RequestMapping("/register")
    public String register(String username,String password){
      return userService.register(username,password);
    }
}
