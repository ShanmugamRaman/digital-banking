package com.digitalbank.shan.customerserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digitalbank.shan.customerserver.model.Customer;
import com.digitalbank.shan.customerserver.repository.CustomerRepository;

@Service
public class CustomerService {
	
	static final Logger logger = LogManager.getLogger();

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> findAllActiveCustomers() {

		List<Customer> activeCustomers = new ArrayList<>();

		customerRepository.findAll().forEach(customer -> {
			if (customer.isActive()) {
				activeCustomers.add(customer);
			}
		});

		return activeCustomers;
	}

	public Customer findCustomerById(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if(customer.isPresent()) {
			return customer.get();
		}else {
			logger.info("No customer with customerId : "+customerId+" found");
			return null;
		}
		
	}

	public ResponseEntity addCustomer(Customer customer) {
		customerRepository.save(customer);
		return new ResponseEntity(customer, HttpStatus.CREATED);
	}

	public boolean isActiveCustomer(Long customerId) {
		return customerRepository.existsById(customerId);
	}

}
