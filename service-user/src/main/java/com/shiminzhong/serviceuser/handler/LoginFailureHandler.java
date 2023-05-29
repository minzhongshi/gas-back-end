package com.shiminzhong.serviceuser.handler;

import com.alibaba.fastjson.JSON;
import com.shiminzhong.serviceuser.user.entity.ResponseResult;
import com.shiminzhong.serviceuser.utils.JwtUtil;
import com.shiminzhong.serviceuser.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof BadCredentialsException){
            ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "用户名或者密码错误");
            String json = JSON.toJSONString(result);
            WebUtils.renderString(response,json);
        }

    }
}
