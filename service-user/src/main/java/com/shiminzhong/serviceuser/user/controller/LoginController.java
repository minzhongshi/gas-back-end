package com.shiminzhong.serviceuser.user.controller;

import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.entity.ResponseResult;
import com.shiminzhong.serviceuser.user.service.LoginServcie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private LoginServcie loginServcie;

    @PostMapping ("/user/login")
    public ResponseResult login(@RequestBody AppUser appUser) {
        //登录
       return loginServcie.login(appUser);
    }


    @RequestMapping("/user/logout")
    public ResponseResult logout() {
        //登出
        return loginServcie.logout();
    }
}
