package com.digitalbank.shan.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class GatewayServerApplication {

	public static void main(String[] args) {
		
		/*http://localhost:8888/api/account-service/v1.0/accounts
		http://localhost:9001/v1.0/accounts
		http://localhost:8888/api/customer-service/v1.0/customers/*/
		SpringApplication.run(GatewayServerApplication.class, args);
	}
}
