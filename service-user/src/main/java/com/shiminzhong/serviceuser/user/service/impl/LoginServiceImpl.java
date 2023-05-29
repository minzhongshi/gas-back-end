package com.shiminzhong.serviceuser.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.entity.LoginUser;
import com.shiminzhong.serviceuser.user.entity.ResponseResult;
import com.shiminzhong.serviceuser.user.mapper.AppUserMapper;
import com.shiminzhong.serviceuser.user.service.LoginServcie;
import com.shiminzhong.serviceuser.utils.JwtUtil;
import com.shiminzhong.serviceuser.utils.RedisCache;
import com.shiminzhong.serviceuser.utils.WebUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginServcie {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisCache redisCache;

    @Resource
    private AppUserMapper appUserMapper;
    @Override
    public ResponseResult login(AppUser appUser) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(appUser.getId(),appUser.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);


        //查询用户信息
        LambdaQueryWrapper<AppUser> wrapper = new LambdaQueryWrapper<>(); //条件构造器
        wrapper.eq(AppUser::getId,appUser.getId());
        AppUser appUser2 = appUserMapper.selectOne(wrapper);
        //如果没通过，给出对应提示
        if (Objects.isNull(authentication)){
            return new ResponseResult(400,  "密码或账号错误");
//            throw new RuntimeException("登陆失败");
        } else if ((!Objects.equals(appUser.getRole(), "admin")) & (!Objects.equals(appUser2.getRole(), appUser.getRole())) ) {
            return new ResponseResult(404,  "该账号没有权限");
        }else if (appUser2.getStatus()!=0) {
            throw new DisabledException("该账号已被禁用");
        }

        //如果认证通过，使用userid生成jwt jwt存入ResponseResult
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userid = loginUser.getAppUser().getId();
        String jwt = JwtUtil.createJWT(userid);
//        Map<String,String>map = new HashMap<>();
//        map.put("token",jwt);
        JSONObject jsonObject =  JSON.parseObject(JSON.toJSONString(loginUser.getAppUser()));

        jsonObject.put("token",jwt);
        //把完整的用户信息存入redis userid作为key
        redisCache.setCacheObject("login:"+userid,loginUser);
        return new ResponseResult(200,  "登录成功",jwt,jsonObject);
    }

    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的用户id
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
        String userid = loginUser.getAppUser().getId();
        redisCache.deleteObject("login:"+userid);
        return new ResponseResult(200,"退出成功");
    }
}
