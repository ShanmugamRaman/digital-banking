package com.digitalbank.shan.transactionserver.model;

public class Customer {

	private long customerId;
	private String customerName;
	private String contactNumber;
	private String emailAddress;
	private boolean isActive;
	
	public Customer() { }
	
	public Customer(String customerName, String contactNumber, String emailAddress, boolean isActive) {
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.emailAddress = emailAddress;
		this.isActive = isActive;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public long getCustomerId() {
		return customerId;
	}
	
	
}
