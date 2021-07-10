package com.revature.models;
 //This will select user type by number 1 customer, 2 employee, 3 admin 
public class Customer {
	
	private int customerID;
	private String firstName;
	private String lastName;
	private String userName; // login
	private String password;
	private String address; // going to keep it simple 
	private boolean hasSaving;
	private boolean hasChecking;
	private int employeeID; // links customer to employee many to one 
	
	public Customer(int customerID, String firstName, String lastName, String userName, String password, String address,
			boolean hasSaving, boolean hasChecking, int employeeID) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.hasSaving = hasSaving;
		this.hasChecking = hasChecking;
		this.employeeID = employeeID;
	}

	public Customer() {
		super();
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public void setPassword(String pasword) {
		this.password = pasword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isHasSaving() {
		return hasSaving;
	}

	public void setHasSaving(boolean hasSaving) {
		this.hasSaving = hasSaving;
	}

	public boolean isHasChecking() {
		return hasChecking;
	}

	public void setHasChecking(boolean hasChecking) {
		this.hasChecking = hasChecking;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + customerID;
		result = prime * result + employeeID;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (hasChecking ? 1231 : 1237);
		result = prime * result + (hasSaving ? 1231 : 1237);
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (customerID != other.customerID)
			return false;
		if (employeeID != other.employeeID)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (hasChecking != other.hasChecking)
			return false;
		if (hasSaving != other.hasSaving)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", fistName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", pasword=" + password + ", address=" + address + ", hasSaving="
				+ hasSaving + ", hasChecking=" + hasChecking + ", employeeID=" + employeeID + "]";
	}
	
	
	
	
	
	
	
}
