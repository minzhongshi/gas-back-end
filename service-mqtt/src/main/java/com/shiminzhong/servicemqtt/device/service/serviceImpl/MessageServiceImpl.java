package com.shiminzhong.servicemqtt.device.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiminzhong.servicemqtt.device.entity.Message;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;
import com.shiminzhong.servicemqtt.device.mapper.MessageMapper;
import com.shiminzhong.servicemqtt.device.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Resource
    private MessageMapper messageMapper;
    @Override
    public ResponseResult delete(Message message){
        int pieces=messageMapper.deleteById(message.getId());
        if (pieces == 1){
            return new ResponseResult<>(200,"删除成功");
        }
        return new ResponseResult<>(400,"删除异常");
    }
}
