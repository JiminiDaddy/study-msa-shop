package com.chpark.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 2:11 AM
 */

@EnableDiscoveryClient
@SpringBootApplication
public class MsaProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaProductApplication.class, args);
    }
}
