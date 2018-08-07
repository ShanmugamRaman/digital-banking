package com.digitalbank.shan.customerserver.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.digitalbank.shan.customerserver.model.Customer;
import com.digitalbank.shan.customerserver.repository.CustomerRepository;


@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerService customerService;
	
	@Test
	public void testAllActiveCustomers() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer("ABC", "1234", "abc@mn.com", true));
		when(customerRepository.findAll()).thenReturn(customers);
		List<Customer> returnedCustomers = customerService.findAllActiveCustomers();
		assertTrue(returnedCustomers.size() == customers.size());
		
	}
	
	
	@Test
	public void testAllActiveCustomersByPassingInActiveCustomer() {
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer("ABC", "1234", "abc@mn.com", true));
		customers.add(new Customer("XYZ", "1234", "XYZ@mn.com", false));
		
		when(customerRepository.findAll()).thenReturn(customers);
		
		List<Customer> returnedCustomers = customerService.findAllActiveCustomers();
		assertTrue(returnedCustomers.size() == 1);
		
	}
	
	@Test
	public void testFindCustomersByPassingValidCustomerId() {
		when(customerRepository.findById((long) 1)).
		thenReturn(Optional.of(new Customer("ABC", "1234", "abc@mn.com", true)));
		
		Customer returnedCustomers = customerService.findCustomerById((long) 1);
		assertTrue(returnedCustomers.getCustomerName().equals("ABC"));
		
	}
	
	@Test
	public void testFindCustomersByPassingInvalidCustomerId() {
		when(customerRepository.findById((long) 10)).
		thenReturn(Optional.ofNullable(null));
		
		Customer returnedCustomers = customerService.findCustomerById((long) 10);
		assertTrue(returnedCustomers == null);
		
	}
	
	@Test
	public void testAddValidCustomer() {
		Customer customer = new Customer("ABC", "1234", "abc@mn.com", true);
		when(customerRepository.save(customer)).
		thenReturn(customer);
		
		ResponseEntity<Customer> customerResponse 
		= customerService.addCustomer(customer);
		
		assertTrue(customerResponse.getStatusCode().equals(HttpStatus.CREATED) && 
				customerResponse.getBody().getCustomerName().equals("ABC"));
		
	}
	
	@Test
	public void testAddInvalidCustomer() {
		Customer customer = new Customer("", "1234", "abc@mn.com", true);
		when(customerRepository.save(customer)).
		thenReturn(customer);
		
		ResponseEntity<Customer> customerResponse 
		= customerService.addCustomer(customer);
		
		assertTrue(	customerResponse.getBody().getCustomerId() == 0L);
		
	}
}