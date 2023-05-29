package com.shiminzhong.servicemqtt.device.controller;

import com.shiminzhong.servicemqtt.device.entity.PointSupplyStation;
import com.shiminzhong.servicemqtt.device.entity.Region;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;
import com.shiminzhong.servicemqtt.device.service.PointSupplyStationService;
import com.shiminzhong.servicemqtt.device.service.RegionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mqtt")
public class MqttPutController {

    @Resource
    private PointSupplyStationService pointSupplyStationService;

    @Resource
    private RegionService regionService;


    //点供站的启动与停止
    @PostMapping("/state")
    public ResponseResult stateRenew(@RequestBody PointSupplyStation pointSupplyStation) {

        return pointSupplyStationService.Renew(pointSupplyStation);
    }

    //点供站的启动与停止
    @PostMapping("/redion")
    public ResponseResult stateRenew(@RequestBody Region region) {

        return regionService.Region(region);
    }
}
