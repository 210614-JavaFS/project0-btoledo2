package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Employee;
import com.revature.services.EmployeeService;

public class EmployeeController {

	private static EmployeeService employeeService = new EmployeeService();
	private static Scanner scan = new Scanner(System.in);
	
	public void empMenu() {
		System.out.println("Hello Employee \n"
				+ "1) See all employees \n"
				+ "2) Login. \n"
				+ "3) return to previous menu.");
		
		String response = scan.nextLine();
		
		switch(response) {
			case "1":
				showEmployees();
				break;
			case "2":
				String role = employeeLogin();
				System.out.println("Employee Role:  " + role);
				break;
			default:
				return;
		}

	}
	
	
	
	public void showEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		
		for(Employee E:employees) {
			System.out.println(E);
		}
	}
	
	
	public String employeeLogin() {
		String role = "no";
		String userName = null;
		String temp;
		String tempPswd;
		String password;
		List<Employee> employees = employeeService.getAllEmployees();
		boolean exit = false;
		boolean exitpswd= false;
		boolean leave = false;
		
		while(!leave) {
			while(!exit) {
				System.out.println("Please enter your Username.");
				userName = scan.nextLine();
				for(Employee e :employees) {
					temp = e.getUserName();
					if(temp.equals(userName)) {exit = true;}
				}
				if(exit == false) {
					System.out.println("Username does not exist. \n" + "Type 'y' to try again or 'n' to leave login");
					userName = scan.nextLine();
					System.out.println(userName);
					if(userName.equals("n")) {
						exit = true;
						leave = true;
						exitpswd = true;
					}
				}
			}
			while(!exitpswd) {
				System.out.println("Please enter your Password.");
				password = scan.nextLine();
				for(Employee e :employees) {
					temp = e.getUserName();
					tempPswd = e.getPassword(); 
					if(tempPswd.equals(password) && temp.equals(userName)) {
						exitpswd = true;
						leave = true;
						role = e.getEmpRole();
					}
							
				}
				if(exit == false) {
					System.out.println("Password does not match Username. \n" + "Type 'y' to try again or 'n' to leave login");
					password = scan.nextLine();
					if(password.equals("n")) {
						exitpswd = true;
						leave = true;
					}
				}
			}
		}
		
		return role;
	}
	
	
	
	//TODO: need two menues one for regular and one for admin 
	// both will have different selections
	
	
	
	
	
	
	
	
	
}
