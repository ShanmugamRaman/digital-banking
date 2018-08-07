package com.digitalbank.shan.transactionmanagementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.digitalbank.shan.transactionmanagementserver.feign")
public class TransactionManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionManagementServerApplication.class, args);
	}
}
