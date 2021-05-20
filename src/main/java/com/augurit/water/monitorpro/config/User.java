package com.augurit.water.monitorpro.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails {
    private Integer id;

    private String username;

    private String password;

    private List<Role> roles;

    // private String role;

    // private String status;

    // private boolean checkLockIsOrNot=true;

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
    //不涉及用户角色，直接赋予管理员角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return auths;
    }

    //账户是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //账户是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //密码是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
