package com.shiminzhong.servicemqtt.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shiminzhong.servicemqtt.device.entity.Message;
import com.shiminzhong.servicemqtt.device.entity.PointSupplyStation;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;

public interface MessageService extends IService<Message> {
    ResponseResult delete(Message message);
}
