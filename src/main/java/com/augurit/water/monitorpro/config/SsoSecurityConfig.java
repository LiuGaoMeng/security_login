package com.augurit.water.monitorpro.config;

import com.augurit.water.monitorpro.login.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserServiceImpl userService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
                http
                        .authorizeRequests()
                        .antMatchers("/login").permitAll()
                .antMatchers("/css/**","/js/**","/lib/**","/user/**").permitAll()
                .antMatchers("/**").access("hasAnyRole('ADMIN', 'USER')")
                .anyRequest()
                 .authenticated()
                .and()
                        .formLogin().loginPage("/login.html")
                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/main/main.html",true)
//                        .failureUrl("/login.html")
                        .successHandler(new AuthenticationSuccessHandler() {//登录成功处理逻辑
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                                Object principal = authentication.getPrincipal();//获取当前登录用户的信息
                                httpServletResponse.setContentType("application/json;charset=utf-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                httpServletResponse.setStatus(200);
                                Map<String, Object> map = new HashMap<>();
                                map.put("status", 200);
                                map.put("msg", "登录成功！");
                                ObjectMapper om = new ObjectMapper();
                                out.write(om.writeValueAsString(map));
                                out.flush();
                                out.close();
//                                httpServletResponse.sendRedirect("/main/main.html");
//                                httpServletRequest.getRequestDispatcher("/main/main.html")
//                                        .forward(httpServletRequest,httpServletResponse);
                            }
                        })
                        .failureHandler(new AuthenticationFailureHandler() {//登录失败处理逻辑
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                                httpServletResponse.setContentType("application/json;charset=utf-8");
                                PrintWriter out = httpServletResponse.getWriter();
                                httpServletResponse.setStatus(401);
                                Map<String, Object> map = new HashMap<>();
                                map.put("status", 401);
                                if(e instanceof LockedException){//判断失败信息
                                    map.put("msg", "账户被锁定，登录失败！");
                                }else if(e instanceof BadCredentialsException){
                                    map.put("msg", "账户名或密码输入错误，登录失败！");
                                }else if(e instanceof DisabledException){
                                    map.put("msg", "账户被禁用，登录失败！");
                                }else if(e instanceof AccountExpiredException){
                                    map.put("msg", "账户已过期，登录失败！");
                                }else if(e instanceof CredentialsExpiredException){
                                    map.put("msg", "密码已过期，登录失败！");
                                }else{
                                    map.put("msg", "登录失败！");
                                }
                                ObjectMapper om = new ObjectMapper();
                                out.write(om.writeValueAsString(map));
                                out.flush();
                                out.close();
//                                httpServletResponse.sendRedirect("/login.html");
                            }
                        })
                        .permitAll();
    }
}
