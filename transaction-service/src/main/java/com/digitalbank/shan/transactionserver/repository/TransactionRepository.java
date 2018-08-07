package com.digitalbank.shan.transactionserver.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.digitalbank.shan.transactionserver.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long>{

	List<Transaction> findByFromAccountId(Long accountId);
	List<Transaction> findByToAccountId(Long accountId);

}
