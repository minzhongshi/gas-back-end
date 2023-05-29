package com.shiminzhong.servicevod.service;

import com.shiminzhong.servicevod.model.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
public interface IMessageService extends IService<Message> {
    List<Message> siteMessages(String siteHeadId);
}
