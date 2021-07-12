package com.revature.services;

import java.util.List;

import com.revature.models.Customer;
import com.revature.repos.CustomerDAO;
import com.revature.repos.CustomerImpl;

public class CustomerService {
	
	private static CustomerDAO customerDao = new CustomerImpl();
	
	public List<Customer> getAllCustomer(){
		return customerDao.findAll();
	}
	
	public Customer getCustomer(int id) {
			return customerDao.findCustomer(id);
	}
	
	public boolean addCustome(Customer customer) {		
		return customerDao.addCustomer(customer);
	}

	public boolean addSavingAccount(int id) {
		return customerDao.createSaving(id);
	}
	
	public boolean addCheckingAccount(int id) {
		return customerDao.createChecking(id);
	}
	
	
}
