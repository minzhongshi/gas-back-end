package com.shiminzhong.servicevod.service;

public interface IAliyunSMSservice {

    //发送验证码
    boolean sendCode(String mobile, String code);
}
