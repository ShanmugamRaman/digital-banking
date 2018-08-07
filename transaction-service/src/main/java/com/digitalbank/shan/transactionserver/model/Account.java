package com.digitalbank.shan.transactionserver.model;

import java.math.BigDecimal;

public class Account {
	
	private Long accountId;
	private Long customerId;
	private String accountName;
	private BigDecimal currentOutstandingBalance;
	private boolean isActive;
	
	public Account() { }
	
	public Account(Long customerId, String accountName, BigDecimal currentOutstandingBalance) {
		this.setCustomerId(customerId);
		this.accountName = accountName;
		this.currentOutstandingBalance = currentOutstandingBalance;
		this.isActive = true;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public BigDecimal getCurrentOutstandingBalance() {
		return currentOutstandingBalance;
	}

	public void setCurrentOutstandingBalance(BigDecimal currentOutstandingBalance) {
		this.currentOutstandingBalance = currentOutstandingBalance;
	}

	public Long getAccountId() {
		return accountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
