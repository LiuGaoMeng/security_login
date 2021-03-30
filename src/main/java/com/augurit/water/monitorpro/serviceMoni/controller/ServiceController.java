package com.augurit.water.monitorpro.serviceMoni.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController("serviceRestController")
@RequestMapping("/moni")
public class ServiceController {
    Map<String,Object> adminService=new HashMap<>();
    Map<String,Object> backService=new HashMap<>();
    Map<String,Object> receiveService=new HashMap<>();
    Map<String,Object> redisService=new HashMap<>();
    Map<String,Object> ssoService=new HashMap<>();
    Map<String,Object> testService=new HashMap<>();
    @RequestMapping("/serviceMess")
    public List<Map<String,Object>> serviceMess(@RequestParam(value = "message", required = false) String message,
                                                @RequestParam(value = "time", required = false) String time,
                                                @RequestParam(value = "port", required = false) int port){
        switch (port){
            case 20005://admin(物联网云平台管理)
                adminService.put("message",message);
                adminService.put("time",time);
                break;
            case 20003://back（物联网后台）:20003
                backService.put("message",message);
                backService.put("time",time);
                break;
            case 20014://receive（接收单）:20014
                receiveService.put("message",message);
                receiveService.put("time",time);
                break;
            case 20016://redis:20016
                redisService.put("message",message);
                redisService.put("time",time);
                break;
            case 20004://sso（单点服务）:20004
                ssoService.put("message",message);
                ssoService.put("time",time);
                break;
            case 8093://sso（单点服务）:20004
                testService.put("message",message);
                testService.put("time",time);
                break;
        }
        return null;
    }
    @RequestMapping("/getServiceMess")
    public List<Map<String,Object>> serviceMess() throws ParseException {
        List<Map<String,Object>> dataList=new ArrayList<>();
        long currentTime =System.currentTimeMillis();
        Date date2=new Date();
        if (ssoService.size()>0){
            Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ssoService.get("time").toString());
            Double timeDis=new Double(currentTime-date.getTime())/1000/60;
            if (timeDis<=15){
                dataList.add(ssoService);
            }
        }
        if (adminService.size()>0){
            Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(adminService.get("time").toString());
            Double timeDis=new Double(currentTime-date.getTime())/1000/60;
            if (timeDis<=15){
                dataList.add(adminService);
            }
        }
        if (backService.size()>0){
            Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(backService.get("time").toString());
            Double timeDis=new Double(currentTime-date.getTime())/1000/60;
            if (timeDis<=15){
                dataList.add(backService);
            }
        }
        if (receiveService.size()>0){
            Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(receiveService.get("time").toString());
            Double timeDis=new Double(currentTime-date.getTime())/1000/60;
            if (timeDis<=15){
                dataList.add(receiveService);
            }
        }
        if (redisService.size()>0){
            Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(redisService.get("time").toString());
            Double timeDis=new Double(currentTime-date.getTime())/1000/60;
            if (timeDis<=15){
                dataList.add(redisService);
            }
        }
        if (testService.size()>0){
            Date date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(testService.get("time").toString());
            Double timeDis=new Double(currentTime-date.getTime())/1000/60;
            if (timeDis<=15){
                dataList.add(testService);
            }
        }
        return dataList;
    }
}
