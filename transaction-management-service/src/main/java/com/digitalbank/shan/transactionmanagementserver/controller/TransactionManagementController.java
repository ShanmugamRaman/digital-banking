package com.digitalbank.shan.transactionmanagementserver.controller;

import java.util.Date;
import java.util.List;

import javax.ws.rs.QueryParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbank.shan.transactionmanagementserver.model.Transaction;
import com.digitalbank.shan.transactionmanagementserver.service.TransactionManagementService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/v1.0/transactionManagement")
public class TransactionManagementController {

	Logger logger = LogManager.getLogger();
	
	@Autowired
	private TransactionManagementService transactionMgmtService;

	@GetMapping("/accounts/{accountId}/transactions/startDate/{startDate}/endDate/{endDate}")
	@HystrixCommand(fallbackMethod = "fallbackGetTransactionsByAccountId")
	public List<Transaction> getTransactionsByAccountId(@PathVariable("accountId") final Long accountId,
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate,
			@QueryParam("offset") int offset, 
			@QueryParam("size") int size) {
		logger.info("Execution in getTransactionsByAccountId");
		return transactionMgmtService.getFilteredTransactions(accountId, startDate, endDate, offset, size);

	}
	
	public List<Transaction> fallbackGetTransactionsByAccountId(@PathVariable("accountId") final Long accountId,
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate,
			@QueryParam("offset") int offset, 
			@QueryParam("size") int size) {
		logger.info("Execution in fallbackGetTransactionsByAccountId");
		return null;
		
	}

}
