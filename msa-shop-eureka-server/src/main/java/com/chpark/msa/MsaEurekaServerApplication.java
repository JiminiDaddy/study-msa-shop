package com.chpark.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by Choen-hee Park
 * User : chpark
 * Date : 2020/11/09
 * Time : 12:53 AM
 */
@EnableEurekaServer
@SpringBootApplication
public class MsaEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsaEurekaServerApplication.class, args);
    }
}
