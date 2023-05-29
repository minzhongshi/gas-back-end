package com.shiminzhong.serviceuser.user.service;

import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.entity.ResponseResult;

public interface LoginServcie {
    ResponseResult login(AppUser appUser);

    ResponseResult logout();
}
