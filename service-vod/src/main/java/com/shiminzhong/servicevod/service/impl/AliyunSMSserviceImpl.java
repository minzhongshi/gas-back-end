package com.shiminzhong.servicevod.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.shiminzhong.servicevod.service.IAliyunSMSservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AliyunSMSserviceImpl implements IAliyunSMSservice {

    private static final Logger LOG = LoggerFactory.getLogger(AliyunSMSserviceImpl.class);

    @Value("${aliyun.sms.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.sms.secret}")
    private String secret;

    @Value("${aliyun.sms.codeSMSTemplate}")
    private String codeSMSTemplate;

    @Value("${aliyun.sms.signName}")
    private String signName;

    @Override
    public boolean sendCode(String mobile, String code) {
        if (!StringUtils.hasLength(mobile)) {
            return false;
        }

        //default 地域节点，默认就好  后面是 阿里云的 id和秘钥（这里记得去阿里云复制自己的id和秘钥哦）
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", signName);    //申请阿里云 签名名称（暂时用阿里云测试的，自己还不能注册签名）
        request.putQueryParameter("TemplateCode", codeSMSTemplate); //申请阿里云 模板code（用的也是阿里云测试的）
        request.putQueryParameter("TemplateParam", "{'code':'" + code + "'}");
        request.setSysProduct("Dysmsapi");
        request.setSysDomain("dysmsapi.aliyuncs.com"); //云服务(不能修改)
        request.setSysVersion("2017-05-25"); //版本号(不能修改)
        request.setSysAction("SendSms"); // 发送方式(不能修改)

        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (Exception e) {
            LOG.error("sendCode error", e);
            return false;
        }

    }
}
