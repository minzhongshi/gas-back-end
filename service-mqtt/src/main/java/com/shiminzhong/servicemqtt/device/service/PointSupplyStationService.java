package com.shiminzhong.servicemqtt.device.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shiminzhong.servicemqtt.device.entity.PointSupplyStation;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;

public interface PointSupplyStationService extends IService<PointSupplyStation> {

    ResponseResult Renew(PointSupplyStation pointSupplyStation);
}
