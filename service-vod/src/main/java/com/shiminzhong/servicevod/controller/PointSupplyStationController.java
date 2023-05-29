package com.shiminzhong.servicevod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shiminzhong.servicevod.model.dto.DeviceInfo;
import com.shiminzhong.servicevod.model.dto.RegionExtParameter;
import com.shiminzhong.servicevod.model.entity.PointSupplyStation;
import com.shiminzhong.servicevod.model.ResponseResult;
import com.shiminzhong.servicevod.model.condition.SupplyStationQuery;
import com.shiminzhong.servicevod.service.IPointSupplyStationService;
import com.shiminzhong.servicevod.service.IRegionParameterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@RestController
@RequestMapping("/station")
@Api(tags = "PointSupplyStationController")
public class PointSupplyStationController {

    @Autowired
    private IPointSupplyStationService pointSupplyStationService;

    @Autowired
    private IRegionParameterService regionParameterService;

    @PostMapping("/pointSupplyStationQuery")
    @ApiOperation(value = "点供站查询", httpMethod = "POST")
    public ResponseResult pointSupplyStationQuery(@RequestBody SupplyStationQuery supplyStationQuery) {
        Assert.state(!ObjectUtils.isEmpty(supplyStationQuery), "参数错误！");

        PointSupplyStation pointSupplyStation = new PointSupplyStation();
        BeanUtils.copyProperties(supplyStationQuery, pointSupplyStation);
        pointSupplyStation.setName(null);

        LambdaQueryWrapper<PointSupplyStation> wrapper = Wrappers.lambdaQuery(pointSupplyStation);
        if (StringUtils.isNotBlank(supplyStationQuery.getName())) {
            wrapper.like(PointSupplyStation::getName, supplyStationQuery.getName());
        }

        List<PointSupplyStation> records = pointSupplyStationService.list(wrapper);
        return new ResponseResult(records);
    }

    @PostMapping("/deviceQuery")
    @ApiOperation(value = "设备查询", httpMethod = "POST")
    public ResponseResult deviceQuery(String standId) {
        Assert.state(!ObjectUtils.isEmpty(standId), "参数错误！");
        List<RegionExtParameter> regionDevices = regionParameterService.regionDevices(standId);
        Map<String, List<DeviceInfo>> groupDevices = new HashMap<>();
        if (!CollectionUtils.isEmpty(regionDevices)) {
            for (RegionExtParameter regionDevice : regionDevices) {
                groupDevices.computeIfAbsent(regionDevice.getEquipmentArea(), k -> new ArrayList<DeviceInfo>()).add(regionDevice);
            }
            return new ResponseResult(groupDevices);
        }
        groupDevices.put("storageTank", Collections.emptyList());
        groupDevices.put("flowmeter", Collections.emptyList());
        return new ResponseResult(groupDevices);
    }

}
