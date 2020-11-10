package com.chpark.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/09
 * Time : 1:14 AM
 */
@EnableZuulProxy            // TODO ㅇㅓ떤기능인지?
@EnableDiscoveryClient      // TODO
@SpringBootApplication
public class MsaApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaApiGatewayApplication.class, args);
    }
}
