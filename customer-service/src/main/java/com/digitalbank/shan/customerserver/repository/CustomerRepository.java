package com.digitalbank.shan.customerserver.repository;

import org.springframework.data.repository.CrudRepository;

import com.digitalbank.shan.customerserver.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
