package com.shiminzhong.servicemqtt.device.service.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiminzhong.servicemqtt.device.entity.Message;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;
import com.shiminzhong.servicemqtt.device.entity.Statistics;
import com.shiminzhong.servicemqtt.device.mapper.StatisticsMapper;
import com.shiminzhong.servicemqtt.device.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {

    @Resource
    private StatisticsMapper statisticsMapper;
    @Override
    public ResponseResult list(Statistics statistics){
        List<Statistics> users = statisticsMapper.selectList(new QueryWrapper<Statistics>()
                .lambda().orderBy(true, false, Statistics::getUsed));
        if (users.size()<=5){
            return new ResponseResult<>(200,"查询成功",users);
        }
        return new ResponseResult<>(200,"查询成功",users.subList(0,5));
    }

    @Override
    public ResponseResult list2(Statistics statistics){
        List<Statistics> users = statisticsMapper.selectList(new QueryWrapper<Statistics>()
                .lambda().orderBy(true, false, Statistics::getUsed));

        return new ResponseResult<>(200,"查询成功",users);
    }
}
