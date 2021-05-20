package com.augurit.water.monitorpro.login.service.impl;

import com.augurit.water.monitorpro.config.User;
import com.augurit.water.monitorpro.login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
//    @Override
//    public User findByUserName(String username) {
//        User user=userMapper.findByUserName(username);
//        if(user == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        }
//        return user;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.findByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
