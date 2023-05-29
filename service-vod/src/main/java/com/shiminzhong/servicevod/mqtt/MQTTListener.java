package com.shiminzhong.servicevod.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 项目启动 监听主题
 *
 * @author Mr.Qu
 * @since 2020/11/18 0018
 */
@Slf4j
@Component
public class MQTTListener implements ApplicationListener<ContextRefreshedEvent> {

  @Value("root")
  private String username;
  @Value("123456")
  private String password;
  private final MQTTConnect server;
  private final InitCallback initCallback;

  @Autowired
  public MQTTListener(MQTTConnect server, InitCallback initCallback) {
    this.server = server;
    this.initCallback = initCallback;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    try {
      server.setMqttClient(username, password, initCallback);
    } catch (MqttException e) {
      log.error(e.getMessage(), e);
    }
  }
}


