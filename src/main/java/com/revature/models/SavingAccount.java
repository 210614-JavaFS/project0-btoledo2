package com.revature.models;

public class SavingAccount {
	private int savingId;
	private double balance;
	private Customer customer;
	public SavingAccount(double balance, Customer customer) {
		super();
		this.balance = balance;
		this.customer = customer;
	}
	public SavingAccount() {
		super();
	}
	public int getSavingId() {
		return savingId;
	}
	public void setSavingId(int savingId) {
		this.savingId = savingId;
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
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + savingId;
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
		SavingAccount other = (SavingAccount) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (savingId != other.savingId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SavingAccount [savingId=" + savingId + ", balance=" + balance + ", customer=" + customer + "]";
	}
	
	
	
	
}
