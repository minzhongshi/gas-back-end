package com.shiminzhong.servicevod.controller;


import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.shiminzhong.servicevod.model.ResponseResult;
import com.shiminzhong.servicevod.model.condition.PwdModifyInfo;
import com.shiminzhong.servicevod.model.entity.AppUser;
import com.shiminzhong.servicevod.service.IAliyunSMSservice;
import com.shiminzhong.servicevod.service.IAppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

import static cn.hutool.core.lang.Validator.isMobile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "AuthController")
public class AuthController {

    private static ExpiringMap<String, String> CODE_CACHE;
    static {
        CODE_CACHE = ExpiringMap.builder().expiration(5 * 60, TimeUnit.SECONDS)
                .expirationPolicy(ExpirationPolicy.CREATED)
                .build();
    }

    @Autowired
    private IAliyunSMSservice aliyunSMSservice;

    @Autowired
    private IAppUserService appUserService;

    @PostMapping("/authentication/getVerifyCode")
    @ApiOperation(value = "发送短信验证码", httpMethod = "POST")
    public ResponseResult getVerifyCode(String mobile) {
        Assert.state(isMobile(mobile), "参数错误！");

        String code = RandomUtil.randomNumbers(6);

        if (aliyunSMSservice.sendCode(mobile, code)) {
            CODE_CACHE.put(mobile, code);
            return new ResponseResult(code);
        }

        throw new RuntimeException("短信发送异常！");
    }

    @PostMapping("/oauth/password")
    @ApiOperation(value = "修改密码", httpMethod = "POST")
    public ResponseResult getVerifyCode(@RequestBody PwdModifyInfo pwdModifyInfo) {
        Assert.state(!ObjectUtils.isEmpty(pwdModifyInfo)
                && StringUtils.isNotBlank(pwdModifyInfo.getAccountNumber()), "参数错误！");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AppUser user = new AppUser();
        user.setId(pwdModifyInfo.getAccountNumber());
        if ("WEB".equalsIgnoreCase(pwdModifyInfo.getAccountType())) {
            user.setPassword("123456");
        } else if ("APP".equalsIgnoreCase(pwdModifyInfo.getAccountType())) {
            user.setPassword(pwdModifyInfo.getPassword());
            user.setPhone(pwdModifyInfo.getMobile());
            if (!StringUtils.equals(CODE_CACHE.get(user.getPhone()), pwdModifyInfo.getVerifyCoder())) {
                return new ResponseResult(400,"验证码错误或过期！");
//                throw new IllegalStateException("验证码错误或过期！");
            } else {
                CODE_CACHE.remove(user.getPhone());
            }
        } else {
            throw new IllegalStateException("accountType 参数错误！");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        boolean result = appUserService.updateById(user);
        return new ResponseResult(result ? 1 : 0);
    }


}
