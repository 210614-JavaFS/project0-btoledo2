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
}
