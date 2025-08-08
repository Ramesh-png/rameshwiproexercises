package com.wipro.basic;

public class Customer {
	
	String customerName;
	String phoneNumber;
	String emailId;
	String address;
	
	public Customer(
			String customerName,
			String phoneNumber,
			String emailId,
			String address
			){
		
			this.customerName=customerName;
			this.phoneNumber=phoneNumber;
			this.emailId=emailId;
			this.address=address;
		}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", phoneNumber=" + phoneNumber + ", emailId=" + emailId
				+ ", address=" + address + "]";
	}
	
	 
}
