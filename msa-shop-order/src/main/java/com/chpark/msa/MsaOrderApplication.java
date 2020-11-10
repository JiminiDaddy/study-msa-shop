package com.chpark.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/09
 * Time : 1:24 AM
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MsaOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaOrderApplication.class, args);
    }
}
