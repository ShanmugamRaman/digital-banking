package com.digitalbank.shan.accountserver.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbank.shan.accountserver.exception.InvalidInputException;
import com.digitalbank.shan.accountserver.model.Account;
import com.digitalbank.shan.accountserver.service.AccountService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/v1.0/accounts")
public class AccountController {
	
	final static Logger logger = LogManager.getLogger();

	@Autowired
	private AccountService accountService;
	
	@GetMapping()
	@HystrixCommand(fallbackMethod="fallbackGetAllAccounts")
	public List<Account> getAllAccounts(@RequestParam("customerId") final Long customerId){
		logger.info("Execution in getAllAccounts()");
		if(customerId == null) {
			return accountService.getAllAccounts();
		}
		return accountService.getAccountsByCustomerId(customerId);
	}
	
	public List<Account> fallbackGetAllAccounts(@RequestParam("customerId") final Long customerId){
		logger.error("Execution in fallbackGetAllAccounts()");
		return null;
	}
	
	@GetMapping("/{accountId}")
	@HystrixCommand(fallbackMethod="fallbackGetAccountById")
	public Account getAccountById(@PathVariable("accountId") final Long accountId) {
		logger.info("Execution in getAccountById() : "+accountId);
		return accountService.getAccountByAccountId(accountId);
	}
	
	public Account fallbackGetAccountById(@PathVariable("accountId") final Long accountId) {
		logger.error("Execution in fallbackGetAccountById() : "+accountId);
		return null;
	}
	
	/*@GetMapping("?customerId={customerId}")
	public List<Account> getAccountsByCustomerId(@QueryParam("customerId") final Long customerId){
		return accountService.getAccountsByCustomerId(customerId);
		//return getAllAccounts().stream().filter(account -> account.getCustomerId().equals(customerId)).collect(Collectors.toList());
	}*/
	
	@PostMapping()
	public ResponseEntity<Account> addAccount(@RequestBody final @Valid Account account,
			BindingResult bindingResult) throws InvalidInputException {
		if(bindingResult.hasErrors()) {
			StringBuffer resultant = new StringBuffer();
			bindingResult.getFieldErrors().stream().forEach(error -> {
				resultant.append(" ").append(error.toString());	
			});
			throw new InvalidInputException(resultant.toString().trim());
		}else {
			return accountService.addAccount(account);
		}
		
	}
	
	@PutMapping("/{accountId}")
	public ResponseEntity<Account> updateAccount(@RequestBody final @Valid Account account, @PathVariable("accountId") Long accountId,
			BindingResult bindingResult) throws InvalidInputException{
		if(bindingResult.hasErrors()) {
			StringBuffer resultant = new StringBuffer();
			bindingResult.getFieldErrors().stream().forEach(error -> {
				resultant.append(" ").append(error.toString());	
			});
			throw new InvalidInputException(resultant.toString().trim());
		}else {
			if(isActiveAccount(accountId)) {
				return accountService.addAccount(account);
			}else {
				return new ResponseEntity<Account>(HttpStatus.NOT_MODIFIED);
			}
		}
	}

	@PutMapping("/{accountId}/closeAccount")
	public ResponseEntity<Account> closeAccount(@PathVariable("accountId") Long accountId) throws InvalidInputException{
	
		if(isActiveAccount(accountId)) {
			return accountService.closeAccount(accountId);
		}else {
			throw new InvalidInputException("Account is either closed or not available");
		}
	
	}
	
	private boolean isActiveAccount(Long accountId) {
		return accountService.isActiveAccount(accountId);
	}
	
}
