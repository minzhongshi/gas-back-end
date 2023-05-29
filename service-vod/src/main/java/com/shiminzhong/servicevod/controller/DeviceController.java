package com.shiminzhong.servicevod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.shiminzhong.servicevod.model.ResponseResult;
import com.shiminzhong.servicevod.model.condition.DeviceModifyInfo;
import com.shiminzhong.servicevod.model.entity.PointSupplyStation;
import com.shiminzhong.servicevod.model.entity.Weekly;
import com.shiminzhong.servicevod.mqtt.MQTTConnect;
import com.shiminzhong.servicevod.service.IPointSupplyStationService;
import com.shiminzhong.servicevod.service.IWeeklyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author suan
 * @since 2023-05-14
 */
@RestController
@RequestMapping("/device")
@Api(tags = "DeviceController")
public class DeviceController {

    @Autowired
    private IPointSupplyStationService pointSupplyStationService;

    @Autowired
    private IWeeklyService weeklyService;

    @Resource
    private MQTTConnect mqttConnect;

    @PostMapping("/modify")
    @ApiOperation(value = "设备更新", httpMethod = "POST")
    public ResponseResult deviceModify(@RequestBody DeviceModifyInfo deviceModifyInfo) {
        Assert.state(!ObjectUtils.isEmpty(deviceModifyInfo)
                && StringUtils.isNotBlank(deviceModifyInfo.getStandId()), "参数错误！");
        PointSupplyStation supplyStation = new PointSupplyStation();
        BeanUtils.copyProperties(deviceModifyInfo, supplyStation);
        //先发布消息
        String theme = "modify/dgz/"+deviceModifyInfo.getStandId();
        String msg= "";
        try {
            mqttConnect.sub(theme);
        } catch (MqttException e) {
            return new ResponseResult(400,"失败，未能订阅主题");
        }
        msg = deviceModifyInfo.toString();
        try {
            mqttConnect.pub(theme,msg,1);
        } catch (MqttException e) {
            return new ResponseResult(400,"失败，未能发布消息");
        }
        boolean result = pointSupplyStationService.updateById(supplyStation);
        return new ResponseResult(result ? 1 : 0);
    }

    @PostMapping("/days")
    @ApiOperation(value = "七天统计", httpMethod = "POST")
    public ResponseResult deviceModify(String siteId, String deviceName) {
        Assert.state(StringUtils.isNotBlank(siteId) || StringUtils.isNotBlank(deviceName), "参数错误！");

        LocalDate endTime = LocalDate.now();
        LocalDate startTime = endTime.plusWeeks(-1);
        String sTimeStr = startTime.format(DateTimeFormatter.ISO_DATE);
        String endTimeStr = endTime.format(DateTimeFormatter.ISO_DATE);

        LambdaQueryWrapper<Weekly> queryWrapper = Wrappers.lambdaQuery(Weekly.class)
                .eq(StringUtils.isNotBlank(siteId), Weekly::getStandId, siteId)
                .eq(StringUtils.isNotBlank(deviceName), Weekly::getDeviceName, deviceName)
                .gt(Weekly::getTime, sTimeStr)
                .lt(Weekly::getTime, endTimeStr);
        return new ResponseResult(weeklyService.list(queryWrapper));
    }

}
