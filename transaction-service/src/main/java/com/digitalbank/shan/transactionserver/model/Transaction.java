package com.digitalbank.shan.transactionserver.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.digitalbank.shan.transactionserver.constant.TransactionStatus;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	
	@NotNull(message = "From account should not be empty")
	private Long fromAccountId;
	@NotNull(message = "To account should not be empty")
	private Long toAccountId;
	@NotNull(message = "Date should not be empty")
	private Date dateOfTransaction;
	@NotNull(message = "Transaction Status should not be empty")
	private TransactionStatus transactionStatus;
	@NotNull(message = "Amount should not be empty")
	private BigDecimal amountTransacted;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}

	public Transaction(Long transactionId, Long fromAccountId, Long toAccountId, Date dateOfTransaction,
			TransactionStatus transactionStatus, BigDecimal amountTransacted) {
		this.transactionId = transactionId;
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.dateOfTransaction = dateOfTransaction;
		this.transactionStatus = transactionStatus;
		this.amountTransacted = amountTransacted;
	}
	
	public Long getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(Long fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public Long getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(Long toAccountId) {
		this.toAccountId = toAccountId;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public BigDecimal getAmountTransacted() {
		return amountTransacted;
	}

	public void setAmountTransacted(BigDecimal amountTransacted) {
		this.amountTransacted = amountTransacted;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountTransacted == null) ? 0 : amountTransacted.hashCode());
		result = prime * result + ((dateOfTransaction == null) ? 0 : dateOfTransaction.hashCode());
		result = prime * result + ((fromAccountId == null) ? 0 : fromAccountId.hashCode());
		result = prime * result + ((toAccountId == null) ? 0 : toAccountId.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((transactionStatus == null) ? 0 : transactionStatus.hashCode());
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
		Transaction other = (Transaction) obj;
		if (amountTransacted == null) {
			if (other.amountTransacted != null)
				return false;
		} else if (!amountTransacted.equals(other.amountTransacted))
			return false;
		if (dateOfTransaction == null) {
			if (other.dateOfTransaction != null)
				return false;
		} else if (!dateOfTransaction.equals(other.dateOfTransaction))
			return false;
		if (fromAccountId == null) {
			if (other.fromAccountId != null)
				return false;
		} else if (!fromAccountId.equals(other.fromAccountId))
			return false;
		if (toAccountId == null) {
			if (other.toAccountId != null)
				return false;
		} else if (!toAccountId.equals(other.toAccountId))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (transactionStatus != other.transactionStatus)
			return false;
		return true;
	}
	
	

}
