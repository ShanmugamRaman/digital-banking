package com.digitalbank.shan.customerserver.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbank.shan.customerserver.exception.InvalidInputException;
import com.digitalbank.shan.customerserver.model.Customer;
import com.digitalbank.shan.customerserver.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/v1.0/customers")
public class CustomerController {

	static final Logger logger = LogManager.getLogger();
	
	@Autowired
	private CustomerService customerService;

	@GetMapping()
	@HystrixCommand(fallbackMethod = "fallbackGetAllCustomers")
	public List<Customer> getAllCustomers() {
		logger.info("Execution in : getAllCustomers()");
		return customerService.findAllActiveCustomers();
	}

	public List<Customer> fallbackGetAllCustomers() {
		logger.error("Execution in : fallbackGetAllCustomers()");
		return new ArrayList<Customer>();
	}
	
	@GetMapping("/{customerId}")
	@HystrixCommand(fallbackMethod = "fallbackGetCustomerById")
	public Customer getCustomerById(@PathVariable("customerId") final Long customerId) {
		logger.info("Execution in : getCustomerById() : customerId = "+customerId);
		return customerService.findCustomerById(customerId);
	}
	
	public Customer fallbackGetCustomerById( Long customerId) {
		logger.error("Execution in : fallbackGetCustomerById(); customerId = "+customerId);
		return new Customer();
	}

	@PostMapping()
	public ResponseEntity<Customer> addCustomer(@RequestBody final @Valid Customer customer,
			BindingResult bindingResult) throws InvalidInputException {
		if(bindingResult.hasErrors()) {
			StringBuffer resultant = new StringBuffer();
			bindingResult.getFieldErrors().stream().forEach(error -> {
				resultant.append(" ").append(error.toString());	
			});
			throw new InvalidInputException(resultant.toString().trim());
		}else {
			return customerService.addCustomer(customer);
		}
		
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity updateCustomer(@RequestBody final @Valid Customer customer,
			BindingResult bindingResult) throws InvalidInputException{
		if(bindingResult.hasErrors()) {
			StringBuffer resultant = new StringBuffer();
			bindingResult.getFieldErrors().stream().forEach(error -> 
				resultant.append(" ").append(error.toString())	
			);
			throw new InvalidInputException(resultant.toString().trim());
		}else {
			if(isActiveCustomer(customer.getCustomerId())) {
				return customerService.addCustomer(customer);
			}else {
				return new ResponseEntity(HttpStatus.NOT_MODIFIED);
			}
		}
	}
	
	private boolean isActiveCustomer(Long customerId) {
		return customerService.isActiveCustomer(customerId);
	}
	

}
