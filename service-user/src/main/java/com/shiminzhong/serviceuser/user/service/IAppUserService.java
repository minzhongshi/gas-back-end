package com.shiminzhong.serviceuser.user.service;

import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiminzhong.serviceuser.user.entity.ResponseResult;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shiminzhong
 * @since 2023-05-11
 */

public interface IAppUserService extends IService<AppUser> {
    ResponseResult information(AppUser appUser);
}
