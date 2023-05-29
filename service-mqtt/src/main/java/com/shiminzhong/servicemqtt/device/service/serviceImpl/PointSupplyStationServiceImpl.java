package com.shiminzhong.servicemqtt.device.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiminzhong.servicemqtt.device.entity.PointSupplyStation;
import com.shiminzhong.servicemqtt.device.entity.ResponseResult;
import com.shiminzhong.servicemqtt.device.mapper.PointSupplyStationMapper;
import com.shiminzhong.servicemqtt.device.service.PointSupplyStationService;

import com.shiminzhong.servicemqtt.mqtt.MQTTConnect;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class PointSupplyStationServiceImpl extends ServiceImpl<PointSupplyStationMapper, PointSupplyStation> implements PointSupplyStationService {

    @Resource
    private PointSupplyStationMapper pointSupplyStationMapper;

    @Resource
    private MQTTConnect mqttConnect;

    @Override
    public ResponseResult Renew(PointSupplyStation pointSupplyStation){

        //先发布消息
        String theme = "smz/dgz/"+pointSupplyStation.getStandId();
        String msg= "";
        try {
            mqttConnect.sub(theme);
        } catch (MqttException e) {
            return new ResponseResult(400,"失败，未能订阅主题");
        }
        if (Objects.equals(pointSupplyStation.getState(), "0")){
            msg = "启动";
        } else if (Objects.equals(pointSupplyStation.getState(), "2")) {
            msg = "停用";
        }
        try {
            mqttConnect.pub(theme,msg,1);
        } catch (MqttException e) {
            return new ResponseResult(400,"失败，未能发布消息");
        }
        //执行修改
        LambdaUpdateWrapper<PointSupplyStation> wrapper = new LambdaUpdateWrapper<>(); //条件构造器
        wrapper.eq(PointSupplyStation::getStandId,pointSupplyStation.getStandId()).set(PointSupplyStation::getState,pointSupplyStation.getState());
        int result = pointSupplyStationMapper.update(null,wrapper);
        if (result==1){
            return new ResponseResult(200,"成功");
        }
        return new ResponseResult(400,"失败");

    }
}
