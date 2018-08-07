package com.digitalbank.shan.accountserver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digitalbank.shan.accountserver.model.Account;
import com.digitalbank.shan.accountserver.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<>();
		accountRepository.findAll().forEach(account -> accounts.add(account));
		return accounts;
	}

	public Account getAccountByAccountId(Long accountId) {
		Optional<Account> account = accountRepository.findById(accountId);
		
		if(account.isPresent()) {
			return account.get();
		}
		else {
			return null;
		}
	}

	public List<Account> getAccountsByCustomerId(Long customerId) {
		return accountRepository.findByCustomerId(customerId);
	}

	public ResponseEntity<Account> addAccount(@Valid Account account) {
		accountRepository.save(account);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	public boolean isActiveAccount(Long accountId) {
		return getAccountByAccountId(accountId) != null;
	}

	public ResponseEntity<Account> closeAccount(Long accountId) {
		Account account = getAccountByAccountId(accountId);
		account.setActive(false);
		addAccount(account);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
