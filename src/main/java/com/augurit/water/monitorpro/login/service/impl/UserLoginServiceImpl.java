package com.augurit.water.monitorpro.login.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.augurit.water.monitorpro.login.mapper.UserMapper;
import com.augurit.water.monitorpro.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String register(String username, String password) {
        Map<String,Object> resultMap=new HashMap<>();
        List<Map<String,Object>> list=userMapper.checkUser(username,password);
        if (list.size()>0){
            resultMap.put("message","用户名密码已存在！");
            resultMap.put("status",false);
        }else{
            int addFAlg=userMapper.register(username,password);
            if (addFAlg>0){
                resultMap.put("message","注册成功！");
                resultMap.put("status",true);
            }else{
                resultMap.put("message","注册失败");
                resultMap.put("status",false);
            }
        }
        return JSONObject.toJSONString(resultMap);
    }
}
