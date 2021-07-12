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
	
	
}
