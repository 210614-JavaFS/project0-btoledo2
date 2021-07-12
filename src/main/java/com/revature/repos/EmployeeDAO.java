package com.revature.repos;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;

public interface EmployeeDAO {
	
	public List<Employee> findAll();
	
	public Employee findEmployee(int id);
	
	public Customer findCustomer(int id);
	
	//public boolean updateCustomer(Customer customer);
	
	
	/* TODO: Employee                                 
	 *  1. login page - will use the same page but will differ by employee role so two menus
	 *  2. approve checking or saving so update customer hasSaving and hasChecks
	 *  3. Show account info : Customer ID userName Password
	 *  4. show account balances : Customer ID saving account and Checking account
	 *  5. personal information : FistName LastName Address
	 *
	 */
	
	/* TODO: Admin                                
	 *  1. login page - will use the same but will differ by employee role so two menus
	 *  2. delete accounts "delete users" or "delete accounts e.g saving or checking"
	 *  3. show 
	 *  4.
	 *  5.
	 *
	 */
	
	
	
	

}
