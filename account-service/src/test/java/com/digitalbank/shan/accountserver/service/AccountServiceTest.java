package com.digitalbank.shan.accountserver.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.digitalbank.shan.accountserver.model.Account;
import com.digitalbank.shan.accountserver.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	@Mock
	private AccountRepository accountRepository;
	
	@InjectMocks
	private AccountService accountService;
	
	@Test
	public void testFetchAllAccounts() {
		
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account(1L, "Account 1", new BigDecimal(0.00)));
		
		when(accountRepository.findAll()).thenReturn(accounts);
		List<Account> returnedAccounts = accountService.getAllAccounts();
		
		assertTrue(returnedAccounts.get(0).equals(accounts.get(0)));
	}
	
	@Test
	public void testFetchAccountsById() {
		
		List<Account> accounts = new ArrayList<>();
		accounts.add(new Account(1L, "Account 1", new BigDecimal(0.00)));
		accounts.add(new Account(1L, "Account 2", new BigDecimal(0.00)));
		
		when(accountRepository.findAll()).thenReturn(accounts);
		List<Account> returnedAccounts = accountService.getAllAccounts();
		
		assertTrue(returnedAccounts.get(0).equals(accounts.get(0)));
	}
}
