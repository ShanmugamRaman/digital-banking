package com.digitalbank.shan.customermanagementserver.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.digitalbank.shan.customermanagementserver.model.Account;


@FeignClient("account-service")
public interface AccountFeignService {

	@GetMapping("/accounts?customerId={customerId}")
	public List<Account> getAccountsByCustomerId(@PathVariable("customerId") final Long customerId);
	
	
}
