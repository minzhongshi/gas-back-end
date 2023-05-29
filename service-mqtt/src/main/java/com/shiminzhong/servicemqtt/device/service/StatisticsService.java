package com.shiminzhong.servicemqtt.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shiminzhong.servicemqtt.device.entity.Message;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;
import com.shiminzhong.servicemqtt.device.entity.Statistics;

public interface StatisticsService extends IService<Statistics> {
     ResponseResult list(Statistics statistics);

     ResponseResult list2(Statistics statistics);
}
