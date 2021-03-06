package com.digitalbank.shan.transactionmanagementserver.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.digitalbank.shan.transactionmanagementserver.feign.TransactionFeignService;
import com.digitalbank.shan.transactionmanagementserver.model.Transaction;

public class TransactionManagementService {
	
	@Autowired
	private TransactionFeignService transactionService;

	public List<Transaction> getFilteredTransactions(Long accountId, Date startDate, Date endDate, int offset,
			int size) {
		
		return transactionService.getTransactionByAccountId(accountId)
				.stream()
				.filter(transaction -> {
					return transaction.getDateOfTransaction().after(startDate);
				})
				.filter(transaction -> {
					return transaction.getDateOfTransaction().before(endDate);
				})
				.skip(offset)
				.limit(size)
				.collect(Collectors.toList());
	
	}

}
