package com.shiminzhong.servicemqtt.device.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.shiminzhong.servicemqtt.device.entity.PointSupplyStation;
import com.shiminzhong.servicemqtt.device.entity.Region;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;

public interface RegionService extends IService<Region> {
    ResponseResult Region(Region region);
}
