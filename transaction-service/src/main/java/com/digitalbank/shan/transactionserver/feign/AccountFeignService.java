package com.digitalbank.shan.transactionserver.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.digitalbank.shan.transactionserver.model.Account;

@FeignClient(name = "account-server")
public interface AccountFeignService {
	
	@GetMapping("/accounts/{accountId}")
	public Account getAccountByAccountId(@PathVariable("accountId") final Long accountId);
	
	@PutMapping("/accounts/{accountId}")
	public ResponseEntity<Account> updateAccount(@RequestBody final @Valid Account account);

}
