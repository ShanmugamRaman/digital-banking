package com.digitalbank.shan.customermanagementserver.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.digitalbank.shan.customermanagementserver.model.Customer;

@FeignClient("customer-service")
@Service
public interface CustomerFeignService {

	@GetMapping("/customers/{customerId}")
	public Customer getCustomerById(@PathVariable("customerId") final Long customerId);

}
