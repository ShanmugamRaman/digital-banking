package com.digitalbank.shan.customermanagementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.digitalbank.shan.customermanagementserver.feign")
@EnableHystrix
public class CustomerManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagementServerApplication.class, args);
	}
}
