package com.digitalbank.shan.transactionmanagementserver.feign;

import java.util.List;

import javax.ws.rs.QueryParam;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.digitalbank.shan.transactionmanagementserver.model.Transaction;

@FeignClient("transaction-server")
public interface TransactionFeignService {
	
	@GetMapping("/transactions?accountId={accountId}")
	public List<Transaction> getTransactionByAccountId(@QueryParam("accountId") final Long accountId);

}
