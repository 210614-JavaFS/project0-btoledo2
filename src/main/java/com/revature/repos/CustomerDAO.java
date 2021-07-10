package com.revature.repos;

import java.util.List;

import com.revature.models.Customer;

public interface CustomerDAO {
	
	public List<Customer> findAll();
	
	public Customer findCustomer(int id);

	public boolean addCustomer(Customer customer); // make sure it works first
	
	public boolean updateCustomer(Customer customer);
	
	
	
}
