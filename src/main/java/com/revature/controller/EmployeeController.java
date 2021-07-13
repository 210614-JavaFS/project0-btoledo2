package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.models.CheckingAccount;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.SavingAccount;
import com.revature.services.CheckingAccountService;
import com.revature.services.CustomerService;
import com.revature.services.EmployeeService;
import com.revature.services.SavingAccountService;

public class EmployeeController {

	private static EmployeeService employeeService = new EmployeeService();
	private static SavingAccountService savingService = new SavingAccountService();
	private static CheckingAccountService checkingService = new CheckingAccountService();
	private static CustomerService customerService = new CustomerService();
	private static Scanner scan = new Scanner(System.in);
	
	public void empMenu() {
		boolean inEmpMenu = true;
		System.out.println("Welcome Employees");

		while (inEmpMenu) { // switch case
			System.out.println("Select an Option");
			System.out.println("1. See all employees");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			String input = scan.nextLine();
			if (input.equals("1")) {
				showEmployees();
			} else if (input.equals("2")) {
				String role = employeeLogin();
				System.out.println("Employee Role:  " + role);
				
				if(role.equals("regular")) {employeeSubMenu();}
				else if(role.equals("admin")) {adminSubMenu();}
				
			} else if (input.equals("3")) {
				inEmpMenu = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
		
	}
	
	
	
	public void showEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		
		for(Employee E:employees) {
			System.out.println(E);
		}
	}
	
	// works
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
					//System.out.println(userName);
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
				if(exitpswd == false) {
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
	
	public void employeeSubMenu() {
		boolean inBank = true;
		while (inBank) { 
			System.out.println("Select an Option");
			System.out.println("1. Show Customers ID and Accounts Status.");
			System.out.println("2. Show Customers Personal Info.");
			System.out.println("3. Show Customers Account Balances.");
			System.out.println("4. Approve Saving Account for one Customer.");
			System.out.println("5. Approve Checking Account for one Customer.");
			System.out.println("6. Exit Menu");
			String input = scan.nextLine();
			if (input.equals("1")) {
				employeeService.customerAccountStatus(); // works
			} else if (input.equals("2")) {
				employeeService.showPersonalInfo(); // works
			}  else if (input.equals("3")) { 
				employeeService.showAccountBalance(); // works
			} else if (input.equals("4")) {
				savingOptions();
			} else if (input.equals("5")) {
				checkingOptions();
			} else if (input.equals("6")) {
				System.out.println("Exiting Menu.");
				inBank = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
	}
	
	public void savingOptions() {
		int id = 0;
		int temp = 0;
		List<Customer> customer = customerService.getAllCustomer();
		List<SavingAccount> savingAccounts = savingService.getAllSaving();
		boolean foundAccount = false;
		boolean hasSaving = false;
		for(Customer c: customer) {
			id = c.getCustomerID();
			hasSaving = c.isHasSaving();
			for(SavingAccount s: savingAccounts) {
				temp = s.getCustomer().getCustomerID();
				if(id == temp) {foundAccount = true;}
			}
			if(hasSaving == true && foundAccount == true) {
				System.out.println("Customer: " + id 
						+" Saving Account has being approved already.");
			} else if(hasSaving == false && foundAccount == true) {
				boolean exit = false;
				String input = "";
				System.out.println("For Customer: " + id
						+ " Would like Approve or Deny Account.");
				while(!exit) {
					System.out.println("Type 'y' to approve or Type 'n' to deny Saving account or Type 'q' for next Customer.");
					input = scan.nextLine();
					if (input.equals("y")) {
						employeeService.approveSaving(id);
						System.out.println("Approve");
						exit =true;
					} else if (input.equals("n")) {
						employeeService.denySaving(id);
						System.out.println("Deny");
						exit = true;
					} else if (input.equals("q")) {
						System.out.println("Next Customer");
						exit = true;
					} else {
						System.out.println("Not a valid selection try again.");
					}	
				}
				}		
			foundAccount = false;
		}
	}
	
	public void checkingOptions() {
		int id = 0;
		int temp = 0;
		List<Customer> customer = customerService.getAllCustomer();
		List<CheckingAccount> checkingAccounts = checkingService.getAllChecking();
		boolean foundAccount = false;
		boolean hasChecking = false;
		for(Customer c: customer) {
			id = c.getCustomerID();
			hasChecking = c.isHasChecking();
			for(CheckingAccount s: checkingAccounts) {
				temp = s.getCustomer().getCustomerID();
				if(id == temp) {foundAccount = true;}
			}
			if(hasChecking == true && foundAccount == true) {
				System.out.println("Customer: " + id 
						+" Checking Account has being approved already.");
			} else if(hasChecking == false && foundAccount == true) {
				boolean exit = false;
				String input = "";
				System.out.println("For Customer: " + id
						+ " Would like Approve or Deny Account.");
				while(!exit) {
					System.out.println("Type 'y' to approve or Type 'n' to deny Checking account or Type 'q' for next Customer.");
					input = scan.nextLine();
					if (input.equals("y")) {
						employeeService.approveChecking(id);
						System.out.println("Approve");
						exit =true;
					} else if (input.equals("n")) {
						employeeService.denyChecking(id);
						System.out.println("Deny");
						exit = true;
					} else if (input.equals("q")) {
						System.out.println("Next Customer");
						exit = true;
					} else {
						System.out.println("Not a valid selection try again.");
					}	
				}
				}		
			foundAccount = false;
		}
	}

	
	
	
	public void adminSubMenu() {
		boolean inBank = true;
		while (inBank) { 
			System.out.println("Select an Option");
			System.out.println("1. Deposit to Saving Account");
			System.out.println("2. Deposit to Checking Account");
			System.out.println("3. Exit");
			String input = scan.nextLine();
			if (input.equals("1")) {
				//depositSaving(id);
			} else if (input.equals("2")) {
				//depositChecking(id);
			} else if (input.equals("3")) {
				System.out.println("Exiting Back.");
				inBank = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
	}
	
	//TODO: need two menues one for regular and one for admin 
	// both will have different selections
	
	
	
	
	
	
	
	
	
}
