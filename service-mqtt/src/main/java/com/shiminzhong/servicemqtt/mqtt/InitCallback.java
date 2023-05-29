package com.shiminzhong.servicemqtt.mqtt;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.shiminzhong.servicemqtt.device.entity.*;
import com.shiminzhong.servicemqtt.device.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;


/**
 * MQTT回调函数
 *
 * @author 史明忠
 * @since 2023/5/10
 */
@Slf4j
@Component
public class InitCallback implements MqttCallback {

  @Resource
  private DeviceMapper deviceMapper;

  @Resource
  private RegionMapper regionMapper;
  @Resource
  private MessageMapper messageMapper;

  @Resource
  private StatisticsMapper statisticsMapper;

  @Resource
  private PointSupplyStationMapper pointSupplyStationMapper;


  /**
   * MQTT 断开连接会执行此方法
   */
  @Override
  public void connectionLost(Throwable cause) {
    log.error(cause.getMessage(), cause);
  }

  /**
   * publish发布成功后会执行到这里
   */
  @Override
  public void deliveryComplete(IMqttDeliveryToken token) {
  }

  /**
   * subscribe订阅后得到的消息会执行到这里
   */
  @Override
  public void messageArrived(String topic, MqttMessage message) {
    log.info("[{}] : {}", topic, new String(message.getPayload()));
    String string = new String(message.getPayload(), StandardCharsets.UTF_8);
    JSONObject fromJson = JSONObject.parseObject(string);
    String stand_id = fromJson.getString("stand_id");
    String state = fromJson.getString("state");
    String error = fromJson.getString("error");
    String site = fromJson.getString("site");
    float used = Float.parseFloat(fromJson.getString("used"));
    String storageTankState = fromJson.getString("storageTankState");
    String flowmeterState = fromJson.getString("flowmeterState");
    String storageTankError = fromJson.getString("storageTankError");
    String flowmeterError = fromJson.getString("flowmeterError");
    fromJson.remove("stand_id");
    fromJson.remove("state");
    fromJson.remove("error");
    fromJson.remove("site");
    fromJson.remove("storageTankState");
    fromJson.remove("flowmeterState");
    fromJson.remove("storageTankError");
    fromJson.remove("flowmeterError");
    fromJson.remove("used");

    //站点状态异常时推送消息
    if (Objects.equals(state, "1")){
      LocalDateTime dateTime = LocalDateTime.now();
      Message message1 = new Message();
      message1.setSiteId(stand_id);
      message1.setName("系统");
      message1.setSite(site);
      message1.setAccountId("admin");
      message1.setContent(error);
      message1.setType("设备异常");
      message1.setTime(dateTime);
      messageMapper.insert(message1);
    }


    LambdaUpdateWrapper<PointSupplyStation>wrapperRegion = new LambdaUpdateWrapper<>();
    LambdaUpdateWrapper<Region>wrapperRegion1 = new LambdaUpdateWrapper<>();
    LambdaUpdateWrapper<Region>wrapperRegion2 = new LambdaUpdateWrapper<>();
    LambdaUpdateWrapper<Statistics>wrapperRegion3 = new LambdaUpdateWrapper<>();

    //更新站点表error信息
    wrapperRegion.eq(PointSupplyStation::getStandId, stand_id).set(PointSupplyStation::getState,state).set(PointSupplyStation::getError,error);
    int conclusion = pointSupplyStationMapper.update(null,wrapperRegion);

    //更区域表error和状态信息
    wrapperRegion1.eq(Region::getStandId, stand_id).eq(Region::getEquipmentArea,"storageTank").set(Region::getState,storageTankState).set(Region::getError,storageTankError);
    int conclusion1 = regionMapper.update(null,wrapperRegion1);

    wrapperRegion2.eq(Region::getStandId, stand_id).eq(Region::getEquipmentArea,"flowmeter").set(Region::getState,flowmeterState).set(Region::getError,flowmeterError);
    int conclusion2 = regionMapper.update(null,wrapperRegion2);

    //更新统计表信息
    wrapperRegion3.eq(Statistics::getStandId, stand_id).setSql("used=used+"+used).setSql("turnover=turnover+"+used*2.68);
    int conclusion3 = statisticsMapper.update(null,wrapperRegion3);

    //更新设备参数信息
    for(Map.Entry<String, Object> entry : fromJson.entrySet()) {
//      if (!Objects.equals(entry.getKey(), "stand_id")){
        JSONObject fromJson1 = fromJson.getJSONObject(entry.getKey());
        for (Map.Entry<String, Object> entry1 : fromJson1.entrySet()) {
            switch (entry1.getKey()){
              case "state":
                LambdaUpdateWrapper<Device>wrapper1 = new LambdaUpdateWrapper<>();
                wrapper1.eq(Device::getStand_id, stand_id).eq(Device::getDevice_name,entry.getKey()).set(Device::getState,entry1.getValue());
                int result = deviceMapper.update(null,wrapper1);
                break;
              case "price":
                LambdaUpdateWrapper<Device>wrapper2 = new LambdaUpdateWrapper<>();
                wrapper2.eq(Device::getStand_id, stand_id).eq(Device::getDevice_name,entry.getKey()).set(Device::getPrice,entry1.getValue());
                int result2 = deviceMapper.update(null,wrapper2);
                break;
            }
        }
//      }
    }
  }
}
