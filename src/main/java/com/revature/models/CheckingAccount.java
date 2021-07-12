package com.revature.models;

public class CheckingAccount {
	private int checkingId;
	private double balance;
	private Customer customer;
	public CheckingAccount(double balance, Customer customer) {
		super();
		this.balance = balance;
		this.customer = customer;
	}
	public CheckingAccount() {
		super();
	}
	public int getCheckingId() {
		return checkingId;
	}
	public void setCheckingId(int checkingId) {
		this.checkingId = checkingId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + checkingId;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
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
		CheckingAccount other = (CheckingAccount) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (checkingId != other.checkingId)
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CheckingAccount [checkingId=" + checkingId + ", balance=" + balance + ", customer=" + customer + "]";
	}
	
	
	
	
	

}
