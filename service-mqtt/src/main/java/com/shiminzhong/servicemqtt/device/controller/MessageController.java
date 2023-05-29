package com.shiminzhong.servicemqtt.device.controller;

import com.shiminzhong.servicemqtt.device.entity.Message;
import com.shiminzhong.servicemqtt.device.entity.PointSupplyStation;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;
import com.shiminzhong.servicemqtt.device.service.MessageService;
import com.shiminzhong.servicemqtt.device.service.PointSupplyStationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;
//消息删除
    @PostMapping("/delete")
    public ResponseResult stateRenew(@RequestBody Message message) {

        return messageService.delete(message);
    }
}
