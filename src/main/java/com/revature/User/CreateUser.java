package com.revature.User;
 //This will select user type by number 1 customer, 2 employee, 3 admin 
public class CreateUser {
	
	private String userName;
	private String password;
	private String address;
	private int typeOfUser;
	private double balance = 0;
	
	public CreateUser() {
		super();
	}

	public CreateUser(String userName, String password, String address, double balance) {
		super();
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(int typeOfUser) {
		this.typeOfUser = typeOfUser;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
