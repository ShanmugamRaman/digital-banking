package com.digitalbank.shan.transactionserver.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbank.shan.transactionserver.exception.InvalidInputException;
import com.digitalbank.shan.transactionserver.model.Transaction;
import com.digitalbank.shan.transactionserver.service.TransactionService;

@RestController
@RequestMapping("/v1.0/transactions")
public class TransactionController {

	final static Logger logger = LogManager.getLogger();
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/{transactionId}")
	public List<Transaction> getTransactionById(@PathVariable("transactionId") final Long transactionId) {
		logger.info("Execution in getTransactionById()");
		return transactionService.getTransactionsById(transactionId);
	}
	
	@GetMapping()
	public List<Transaction> getTransactionByAccountId(@QueryParam("accountId") final Long accountId) {
		logger.info("Execution in getTransactionByAccountId(); accountId : "+accountId);
		if(accountId != null)
			return transactionService.getTransactionsByAccountId(accountId);
		return null;
	}
	
	/** Achieves 
	 * 		-> Withdrawal of account; fromCustomer is customer-id; toCustomer is system-id-for-encashment
	 * 		-> Deposit of account; fromCustomer is system-id-for-deposit; toCustomer is customer-id
	 * 		-> Transfer b/w 2 customers
	 * @throws InvalidInputException 
	 */
	@PostMapping()
	public ResponseEntity<Transaction> addTransactions(@RequestBody @Valid final Transaction transaction, BindingResult bindingResult) throws InvalidInputException{
		logger.info("Execution in addTransactions()");
		if(bindingResult.hasErrors()) {
			StringBuffer resultant = new StringBuffer();
			bindingResult.getFieldErrors().stream().forEach(error -> {
				System.out.println(error.toString());
				resultant.append(" ").append(error.toString());	
			});
			throw new InvalidInputException(resultant.toString().trim());
		}else {
			return transactionService.addTransaction(transaction);
		}
		
	}
	
}
