package com.shiminzhong.servicemqtt.device.controller;

import com.shiminzhong.servicemqtt.device.entity.Message;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;
import com.shiminzhong.servicemqtt.device.entity.Statistics;
import com.shiminzhong.servicemqtt.device.service.StatisticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;

    //盈利、使用列表
    @PostMapping("/list")
    public ResponseResult stateRenew(@RequestBody Statistics statistics) {

        return statisticsService.list(statistics);
    }


    //盈利、使用列表
    @PostMapping("/list/whole")
    public ResponseResult stateRenew2(@RequestBody Statistics statistics) {

        return statisticsService.list2(statistics);
    }
}
