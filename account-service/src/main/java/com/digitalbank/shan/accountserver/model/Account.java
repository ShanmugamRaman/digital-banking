package com.digitalbank.shan.accountserver.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;
	@NotNull(message = "Customer Id is mandatory!!")
	private Long customerId;
	
	@NotNull
	@Size(min =3, max =10, message = "Account Name is mandatory!!")
	private String accountName;
	@NotNull(message = "Outstanding Balance is mandatory!!")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((currentOutstandingBalance == null) ? 0 : currentOutstandingBalance.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (currentOutstandingBalance == null) {
			if (other.currentOutstandingBalance != null)
				return false;
		} else if (!currentOutstandingBalance.equals(other.currentOutstandingBalance))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (isActive != other.isActive)
			return false;
		return true;
	}

}
