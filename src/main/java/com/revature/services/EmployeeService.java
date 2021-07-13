package com.revature.services;

import java.util.List;

import com.revature.models.Employee;
import com.revature.repos.EmployeeDAO;
import com.revature.repos.EmployeeImpl;

public class EmployeeService {
	
	private static EmployeeDAO employeeDao = new EmployeeImpl();
	
	public List<Employee> getAllEmployees(){
		return employeeDao.findAll();
	}
	
	public Employee getEmployee(int id) {
			return employeeDao.findEmployee(id);
		
	}
	
	public boolean showAccountBalance() {
		return employeeDao.showAccountBalance();
	}
	
	public boolean showPersonalInfo() {
		return employeeDao.showPersonalInfo();
	} 
	
	public boolean customerAccountStatus() {
		return employeeDao.showAccountInfo();
	}
	
	public boolean approveSaving(int id) {
		return employeeDao.approveSavingAccount(id);
	}
	
	public boolean approveChecking(int id) {
		return employeeDao.approveCheckingAccount(id);
	}
	
	public boolean denySaving(int id) {
		return employeeDao.denySavingAccount(id);
	}
	
	public boolean denyChecking(int id) {
		return employeeDao.denyCheckingAccount(id);
	}
	
	
	
	
}
