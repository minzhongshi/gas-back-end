package com.shiminzhong.serviceuser;

import io.jsonwebtoken.Claims;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import static com.shiminzhong.serviceuser.utils.JwtUtil.parseJWT;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.shiminzhong.serviceuser.*.mapper")
public class ServiceUserApplication {

    public static void main(String[] args) {

       ConfigurableApplicationContext run = SpringApplication.run(ServiceUserApplication.class, args);
//        Claims claims = null;
//        try {
//            claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5ZjA0NzZkYWI1OTc0ZWJhYTU5NjM1OGYyZjUwZDk5MSIsInN1YiI6IjIwMjMwMjMxMDAwMSIsImlzcyI6InNnIiwiaWF0IjoxNjgzODIwNjk4LCJleHAiOjE2ODM4MjQyOTh9.AWYg1oWFo9t05HV85Dir_0ZC_bqHo33tKf2fmEXDFT4");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        String subject = claims.getSubject();
//        System.out.println(subject);
    }

}
