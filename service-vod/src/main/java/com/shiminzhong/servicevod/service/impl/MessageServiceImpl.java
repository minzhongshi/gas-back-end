package com.shiminzhong.servicevod.service.impl;

import com.shiminzhong.servicevod.model.entity.Message;
import com.shiminzhong.servicevod.mapper.MessageMapper;
import com.shiminzhong.servicevod.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

    @Override
    public List<Message> siteMessages(String siteHeadId) {
        System.out.println(baseMapper.siteMessages(siteHeadId));
        return baseMapper.siteMessages(siteHeadId);
    }
}
