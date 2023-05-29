package com.shiminzhong.servicemqtt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan({"com.shiminzhong.servicemqtt.device.mapper"})
@EnableFeignClients
public class ServiceMqttApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMqttApplication.class, args);
    }

}
