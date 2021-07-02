package com.revature.User;
 //This will select user type by number 1 customer, 2 employee, 3 admin 
public class CreateUser {
	
	private String userName;
	private int typeOfUser;
	
	public CreateUser() {
		super();
	}

	public CreateUser(String userName, int typeOfUser) {
		super();
		this.userName = userName;
		this.typeOfUser = typeOfUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTypeOfUser() {
		return typeOfUser;
	}

	public void setTypeOfUser(int typeOfUser) {
		this.typeOfUser = typeOfUser;
	}
	
}
