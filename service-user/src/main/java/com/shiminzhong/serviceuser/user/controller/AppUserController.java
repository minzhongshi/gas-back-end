package com.shiminzhong.serviceuser.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shiminzhong.serviceuser.model.condition.UserQuery;
import com.shiminzhong.serviceuser.user.entity.AppUser;
import com.shiminzhong.serviceuser.user.entity.ResponseResult;
import com.shiminzhong.serviceuser.user.mapper.AppUserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shiminzhong.serviceuser.user.service.IAppUserService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shiminzhong
 * @since 2023-05-11
 */
@RestController
@Api(tags = "AppUserController")
public class AppUserController {


    @Resource
    private IAppUserService iAppUserService;

    @Resource
    private AppUserMapper appUserMapper;

    @RequestMapping("/user/information")
    @PreAuthorize("hasAuthority('system:information:list')")
    public ResponseResult userList(@RequestBody AppUser appUser){

        return iAppUserService.information(appUser);
    }

    @PreAuthorize("hasAuthority('system:information:deleted')")
    @PostMapping("/user/deleted")
    @ApiOperation(value = "userDelete", httpMethod = "POST")
    public ResponseResult userDelete(String id) {
        Assert.state(StringUtils.isNotBlank(id), "参数错误！");
        return appUserMapper.deleteById(id) == 1 ? new ResponseResult(200, "删除成功",1) : new ResponseResult(200, "未找到该用户",0);
    }

    @PostMapping("/user/edit")
    @ApiOperation(value = "userEdit", httpMethod = "POST")
    public ResponseResult userEdit(@RequestBody AppUser appUser) {
        Assert.state(!ObjectUtils.isEmpty(appUser) && StringUtils.isNotBlank(appUser.getId()), "参数错误！");
        return iAppUserService.updateById(appUser) ? new ResponseResult(200, "修改成功",1) : new ResponseResult(200, "未找到该用户",0);
    }

    @PreAuthorize("hasAuthority('system:information:add')")
    @PostMapping("/user/add")
    @ApiOperation(value = "userAdd", httpMethod = "POST")
    public ResponseResult userAdd(@RequestBody AppUser appUser) {
        Assert.state(!ObjectUtils.isEmpty(appUser)
                        && StringUtils.isNotBlank(appUser.getId())
                        && StringUtils.isNotBlank(appUser.getUsername())
                        && appUser.getGender() != null
                        && StringUtils.isNotBlank(appUser.getIdCard())
                        && StringUtils.isNotBlank(appUser.getEmail())
                        && StringUtils.isNotBlank(appUser.getAddress())
                        && ("WEB".equalsIgnoreCase(appUser.getRole()) || "APP".equalsIgnoreCase(appUser.getRole()))
                        && StringUtils.isNotBlank(appUser.getPhone())
                        && (appUser.getHead() == 0 || appUser.getHead() == 1)
//                        && appUser.getRegionId() != null
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

    @PreAuthorize("hasAuthority('system:information:list')")
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
                .likeRight(StringUtils.isNotBlank(userQuery.getIdCard()), AppUser::getIdCard, userQuery.getIdCard())
                .eq(StringUtils.isNotBlank(userQuery.getRegionId()), AppUser::getRegionId, userQuery.getRegionId())
                .eq(userQuery.getGender() != null, AppUser::getGender, userQuery.getGender())
                .eq(userQuery.getStatus() != null, AppUser::getStatus, userQuery.getStatus())
                .eq(userQuery.getAge() != null, AppUser::getAge, userQuery.getAge())
                .eq(userQuery.getHead() != null, AppUser::getHead, userQuery.getHead())
                .gt(StringUtils.isNotBlank(userQuery.getOperationStartTime()), AppUser::getCreateTime, userQuery.getOperationStartTime())
                .lt(StringUtils.isNotBlank(userQuery.getOperationEndTime()), AppUser::getCreateTime, userQuery.getOperationEndTime());
        page = iAppUserService.page(page, queryWrapper);
        return new ResponseResult(200,"查询成功" ,page);
    }

    @PostMapping("/user/status")
    @ApiOperation(value = "userStatus", httpMethod = "POST")
    public ResponseResult userStatus(@RequestBody AppUser appUser) {
        Assert.state(!ObjectUtils.isEmpty(appUser)
                && StringUtils.isNotBlank(appUser.getId())
                && appUser.getStatus() != null
                , "参数错误！");
        AppUser appUser1 = new AppUser();
        appUser1.setId(appUser.getId());
        appUser1.setStatus(appUser.getStatus());
        return iAppUserService.updateById(appUser1) ? new ResponseResult(200, "修改成功",1) : new ResponseResult(200, "未找到该用户",0);
    }
}
