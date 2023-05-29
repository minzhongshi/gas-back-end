package com.shiminzhong.serviceuser.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.entity.LoginUser;
import com.shiminzhong.serviceuser.user.entity.ResponseResult;
import com.shiminzhong.serviceuser.user.mapper.AppUserMapper;
import com.shiminzhong.serviceuser.user.mapper.MenuMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AppUserMapper appUserMapper;

    @Resource
    private MenuMapper menuMapper;

    //重写验证方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<AppUser> wrapper = new LambdaQueryWrapper<>(); //条件构造器
        wrapper.eq(AppUser::getId,username);
        AppUser appUser = appUserMapper.selectOne(wrapper);
        //如果没查到就抛出异常
        if (Objects.isNull(appUser)){
            throw new BadCredentialsException("工号或者密码错误！");
        }
        // TODO 查询对应的权限信息
        List<String>list =menuMapper.selectPermsByUserId(appUser.getId());

        //把数据封装成UserDetails返回
        return new LoginUser(appUser,list);
    }
}
