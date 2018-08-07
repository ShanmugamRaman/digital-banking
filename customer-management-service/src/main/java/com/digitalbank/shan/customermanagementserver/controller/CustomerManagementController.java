package com.digitalbank.shan.customermanagementserver.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbank.shan.customermanagementserver.feign.AccountFeignService;
import com.digitalbank.shan.customermanagementserver.feign.CustomerFeignService;
import com.digitalbank.shan.customermanagementserver.model.Account;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/v1.0/customerManagement")
public class CustomerManagementController {
	
	Logger logger = LogManager.getLogger();
	
	@Autowired
	private CustomerFeignService customerService;
	
	@Autowired
	private AccountFeignService accountService;
	
	@GetMapping("/customers/{customerId}/accounts")
	@HystrixCommand(fallbackMethod = "fallbackGetAccountsByCustomerId")
	public List<Account> getAccountsByCustomerId(@PathVariable("customerId") final Long customerId){
		
		logger.info("Execution in getAccountsByCustomerId method; customerId : "+customerId);
		if(customerService.getCustomerById(customerId) != null) {
			return accountService.getAccountsByCustomerId(customerId);
		}
		return null;
	}

	public List<Account> fallbackGetAccountsByCustomerId(@PathVariable("customerId") final Long customerId){
		logger.info("Execution in fallbackGetAccountsByCustomerId method; customerId : "+customerId);
		return null;
	}
	
}
