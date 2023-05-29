package com.shiminzhong.servicevod.mqtt;




import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;



/**
 * MQTT回调函数
 *
 * @author 史明忠
 * @since 2023/5/10
 */
@Slf4j
@Component
public class InitCallback implements MqttCallback {

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
  public void messageArrived(String topic, MqttMessage message) {}
}
