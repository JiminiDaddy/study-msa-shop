package com.chpark.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/10
 * Time : 1:43 AM
 */

@EnableDiscoveryClient
@SpringBootApplication
public class MsaMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaMemberApplication.class, args);
    }
}
