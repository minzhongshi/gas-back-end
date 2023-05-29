package com.shiminzhong.servicevod.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiminzhong.servicevod.model.ResponseResult;
import com.shiminzhong.servicevod.model.condition.UserQuery;
import com.shiminzhong.servicevod.model.entity.AppUser;
import com.shiminzhong.servicevod.service.IAppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author shiminzhong
 * @since 2023-05-11
 */
//@RestController
//@Api(tags = "AppUserController")
public class AppUserController {


    @Resource
    private IAppUserService iAppUserService;

    @PostMapping("/user/deleted")
    @ApiOperation(value = "userDelete", httpMethod = "POST")
    public ResponseResult userDelete(String id) {
        Assert.state(StringUtils.isNotBlank(id), "参数错误！");
        AppUser user = new AppUser();
        user.setId(id);
        user.setDeleted(1);
        return iAppUserService.updateById(user) ? new ResponseResult(200, 1) : new ResponseResult(200, 0);
    }

    @PostMapping("/user/edit")
    @ApiOperation(value = "userEdit", httpMethod = "POST")
    public ResponseResult userEdit(@RequestBody AppUser appUser) {
        Assert.state(!ObjectUtils.isEmpty(appUser) && StringUtils.isNotBlank(appUser.getId()), "参数错误！");
        return iAppUserService.updateById(appUser) ? new ResponseResult(200, 1) : new ResponseResult(200, 0);
    }

    @PostMapping("/user/add")
    @ApiOperation(value = "userAdd", httpMethod = "POST")
    public ResponseResult userAdd(@RequestBody AppUser appUser) {
        Assert.state(!ObjectUtils.isEmpty(appUser)
                        && StringUtils.isNotBlank(appUser.getId())
                        && StringUtils.isNotBlank(appUser.getUsername())
                        && appUser.getGender() != null
                        && StringUtils.isNotBlank(appUser.getIdCard())
                        && StringUtils.isNotBlank(appUser.getAddress())
                        && ("WEB".equalsIgnoreCase(appUser.getRole()) || "APP".equalsIgnoreCase(appUser.getRole()))
                        && StringUtils.isNotBlank(appUser.getPhone())
                        && (appUser.getHead() == 0 || appUser.getHead() == 1)
                        && appUser.getRegionId() != null
                , "参数错误！");
        appUser.setPassword(new BCryptPasswordEncoder().encode("123456"));
        appUser.setVersion(0);
        appUser.setDeleted(0);
        appUser.setStatus(0);
        if (appUser.getAge() == null) {
            appUser.setAge(0);
        }
        appUser.setCreateTime(LocalDateTime.now());
        return iAppUserService.save(appUser) ? new ResponseResult(200, 1) : new ResponseResult(200, 0);
    }

    @PostMapping("/user/list")
    @ApiOperation(value = "userList", httpMethod = "POST")
    public ResponseResult userList(@RequestBody UserQuery userQuery) {
        Assert.state(!ObjectUtils.isEmpty(userQuery), "参数错误！");
        IPage page = new Page(userQuery.getPageNumber(), userQuery.getPageSize());
        LambdaQueryWrapper<AppUser> queryWrapper = Wrappers.lambdaQuery(AppUser.class);
        queryWrapper.eq(StringUtils.isNotBlank(userQuery.getId()), AppUser::getId, userQuery.getId())
                .eq(StringUtils.isNotBlank(userQuery.getUsername()), AppUser::getUsername, userQuery.getUsername())
                .eq(StringUtils.isNotBlank(userQuery.getPhone()), AppUser::getPhone, userQuery.getPhone())
                .eq(StringUtils.isNotBlank(userQuery.getRole()), AppUser::getRole, userQuery.getRole())
                .eq(StringUtils.isNotBlank(userQuery.getIdCard()), AppUser::getId, userQuery.getIdCard())
                .eq(userQuery.getGender() != null, AppUser::getGender, userQuery.getGender())
                .eq(userQuery.getStatus() != null, AppUser::getStatus, userQuery.getStatus())
                .eq(AppUser::getDeleted, 0);
        page = iAppUserService.page(page, queryWrapper);
        return new ResponseResult(200, page);
    }
}
