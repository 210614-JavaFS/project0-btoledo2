package com.revature.repos;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDAO {
	
	public List<Customer> findAll();
	
	public Customer findCustomer(int id);

	public boolean addCustomer(Customer customer); // make sure it works first
	
	public boolean createSaving(int id);
	
	public boolean createChecking(int id);
	
	public boolean updateSaving(int id, double amount);
	
	public boolean updateChecking(int id, double amount);
	
	public boolean showBalance(int id);
	
	//public boolean transferMoney(int id);
	
	
	/*TODO LIST:
	 * 1. Create User: public boolean addCustomer(Customer customer);
	 * 2. make saving and checking account 
	 * 3. deposit to saving or checking
	 * 4. see balance in both accounts
	 * 5. withdraw money
	 * 6.transfer between saving and checking and vice versa
	 */
	
}
