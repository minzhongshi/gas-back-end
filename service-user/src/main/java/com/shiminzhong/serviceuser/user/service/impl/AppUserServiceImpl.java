package com.shiminzhong.serviceuser.user.service.impl;

import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.entity.ResponseResult;
import com.shiminzhong.serviceuser.user.mapper.AppUserMapper;
import com.shiminzhong.serviceuser.user.service.IAppUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shiminzhong
 * @since 2023-05-11
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

    @Override
    public ResponseResult information(AppUser appUser) {
        return new ResponseResult(200,  "查询成功");
    }
}
