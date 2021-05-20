package com.augurit.water.monitorpro.login.mapper;

import com.augurit.water.monitorpro.config.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User findByUserName(@Param("username") String username);

    int register(@Param("username")String username, @Param("password")String password);
    List<Map<String,Object>> checkUser(@Param("username")String username, @Param("password")String password);
}
