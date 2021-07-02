package com.revature.AccountInfo;
// you will have an customer, employee, admin class but this will be the parent
public class Account {
	private String user;
	private String accountType;
	
	public Account() {
		super();
	}
	
	public Account(String user, String accountType) {
		super();
		this.user = user;
		this.accountType = accountType;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}
