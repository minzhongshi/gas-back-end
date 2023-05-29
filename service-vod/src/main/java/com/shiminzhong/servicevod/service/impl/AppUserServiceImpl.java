package com.shiminzhong.servicevod.service.impl;

import com.shiminzhong.servicevod.model.entity.AppUser;
import com.shiminzhong.servicevod.mapper.AppUserMapper;
import com.shiminzhong.servicevod.service.IAppUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suan
 * @since 2023-05-15
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements IAppUserService {

}
