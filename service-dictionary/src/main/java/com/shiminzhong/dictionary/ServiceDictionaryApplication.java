package com.shiminzhong.dictionary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.shiminzhong.dictionary.mapper")
public class ServiceDictionaryApplication {

    public static void main(String[] args) {

       ConfigurableApplicationContext run = SpringApplication.run(ServiceDictionaryApplication.class, args);

    }

}
