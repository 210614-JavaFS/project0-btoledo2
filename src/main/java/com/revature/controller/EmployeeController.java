package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.Driver;
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
	private static Logger log = (Logger) LoggerFactory.getLogger(Driver.class);
	
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
		System.out.println("Hello Admin Select from Employee and Customer Menus");
		while (inBank) { 
			System.out.println("Select an Option");
			System.out.println("1. Employee Menu. ");
			System.out.println("2. Customer Menu.");
			System.out.println("3. TransferBetween accounts. ");
			System.out.println("4. Cancel Account. ");
			System.out.println("5. Exit");
			String input = scan.nextLine();
			if (input.equals("1")) {
				employeeSubMenu();
			} else if (input.equals("2")) {
				customerSubMenu();
			} else if (input.equals("3")) {
				//depositChecking(id);
			} else if (input.equals("4")) {
				//depositChecking(id);
			}else if (input.equals("5")) {
				System.out.println("Exiting Back.");
				inBank = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
	}
	
	// Delete work in progress
	public void removeAccount() {
		List<Customer> customer = customerService.getAllCustomer();
		boolean exit = false;
		int id = 0;
		int temp;
		boolean userID = false;
		while(!exit) {
			System.out.println("Enter A customer ID to Remove. ");
			String customerId = scan.nextLine();
			try {		
				id = Integer.parseInt(customerId);
				for(Customer e :customer) {
					temp = e.getCustomerID();
					if(temp == id) {System.out.println("Customer ID is Valid.");
							userID = true;
							exit = true;;}
				}
				if(userID == false) {
					System.out.println("That is not a valid Customer ID: type 'y' try again or type 'n' to leave customer Menu.");
					customerId = scan.nextLine();
					if(customerId.equals("n")) {exit = true;}
				}
				
				
				
			}
			catch(Exception e) {
				System.out.println("That is not a valid Customer ID: type 'y' try again or type 'n' to leave customer Menu.");
				customerId = scan.nextLine();
				if(customerId.equals("n")) {exit = true;}
				continue;
			}	
		}
		if(userID == true) {
			
		}
		
		
	}
	
	//  code is the same as customer controller but made to work with admin 
	public void customerSubMenu(){
		List<Customer> customer = customerService.getAllCustomer();
		boolean exit = false;
		int id = 0;
		int temp;
		boolean userID = false;
		boolean hasSaving = false;
		boolean hasChecking = false;
		
		while(!exit) {
			System.out.println("Enter A customer ID ");
			String customerId = scan.nextLine();
			try {		
				id = Integer.parseInt(customerId);
				for(Customer e :customer) {
					temp = e.getCustomerID();
					if(temp == id) {System.out.println("Customer ID is Valid.");
							userID = true;
							exit = true;
							hasSaving = e.isHasSaving();
							hasChecking = e.isHasSaving();
							;}
				}
				if(userID == false) {
					System.out.println("That is not a valid Customer ID: type 'y' try again or type 'n' to leave customer Menu.");
					customerId = scan.nextLine();
					if(customerId.equals("n")) {exit = true;}
				}
				
				
				
			}
			catch(Exception e) {
				System.out.println("That is not a valid Customer ID: type 'y' try again or type 'n' to leave customer Menu.");
				customerId = scan.nextLine();
				if(customerId.equals("n")) {exit = true;}
				continue;
			}	
			
		}
		if(userID == true) {
			log.info("Admin enter customer sub menu.");
			boolean customerOptions = true;
			
			while (customerOptions) { // Done
				System.out.println( "Hello, Admin");
				System.out.println("Select an Option");
				System.out.println("1. Create Saving Account");
				System.out.println("2. Create Checking Account");
				System.out.println("3. Deposit"); // sub menu - Done
				System.out.println("4. witdraw"); // sub menu - Done
				System.out.println("5. Transfer"); // sub menu - Done
				System.out.println("6. See Account balance"); // Done but can make look pretty later on 
				System.out.println("7. Exit");
				String input = scan.nextLine();
				if (input.equals("1")) {
					makeCustomerSaving(id);
				} else if (input.equals("2")) {
					makeCustomerChecking(id);
				} else if (input.equals("3")) {
					depositSubMenu(id);
				}else if (input.equals("4")) {
					withdrawSubMenu(id);
				}else if (input.equals("5")) {
					if(hasSaving == true  && hasChecking == true) {
						transferSubMenu(id);
					} else {System.out.println("You dont have access to this option right now.");
						log.info("User does not have an SA or CA or both.");}
				}else if (input.equals("6")) {
						showBalance(id);
				} else if (input.equals("7")) {
					customerOptions = false;
				}
				else {
					System.out.println("Not a valid selection try again.");
				}
	
			}
		}else {System.out.println("Customer id is not valid.");}
	}
	public void makeCustomerSaving(int id) {
		int temp = 0;
		List<SavingAccount> savingAccounts = savingService.getAllSaving();
		boolean foundAccount = false;
		boolean hasSaving = false;
		for(SavingAccount e: savingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				foundAccount = true;
				hasSaving = e.getCustomer().isHasSaving();
				}
		}
		if(foundAccount == false) {
			System.out.println("Saving Account Create waiting on Employee Approve.");
			log.info("Created Saving account.");
			customerService.addSavingAccount(id);
		}else if(foundAccount == true && hasSaving == false) {
			System.out.println("Waiting on Employee to Approve Account.");
		}else if(foundAccount == true && hasSaving == true) {
			System.out.println("You have access to your Saving Account.");
		}
		
		
	}
	// Done
	public void makeCustomerChecking(int id) {
		int temp = 0;
		List<CheckingAccount> checkingAccounts = checkingService.getAllChecking();
		boolean foundAccount = false;
		boolean hasChecking = false;
		for(CheckingAccount e: checkingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				foundAccount = true;
				hasChecking = e.getCustomer().isHasChecking();
				}
		}
		if(foundAccount == false) {
			System.out.println("Checking Account Create waiting on Employee Approve.");
			log.info("Create Checking Account.");
			customerService.addCheckingAccount(id);
		}else if(foundAccount == true && hasChecking == false) {
			System.out.println("Waiting on Employee to Approve Account.");
		}else if(foundAccount == true && hasChecking == true) {
			System.out.println("You have access to your Checking Account.");
		}
	}
	// Done
	public void depositSubMenu(int id) {
		boolean inBank = true;
		while (inBank) { 
			System.out.println("Select an Option");
			System.out.println("1. Deposit to Saving Account");
			System.out.println("2. Deposit to Checking Account");
			System.out.println("3. Exit");
			String input = scan.nextLine();
			if (input.equals("1")) {
				depositSaving(id);
			} else if (input.equals("2")) {
				depositChecking(id);
			} else if (input.equals("3")) {
				System.out.println("Exiting Back.");
				inBank = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
	}
	// Done
	public void depositSaving(int id) {
		int temp = 0;
		double amount = 0;
		List<SavingAccount> savingAccounts = savingService.getAllSaving();
		boolean foundAccount = false;
		boolean hasSaving = false;
		for(SavingAccount e: savingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				foundAccount = true;
				hasSaving = e.getCustomer().isHasSaving();
				amount = e.getBalance();
				}
		}
		if(foundAccount == false) {
			System.out.println("Saving Account does not exist.");
		}else if(foundAccount == true && hasSaving == false) {
			System.out.println("Awaiting on Employee to Approve Account.");
		}else if(foundAccount == true && hasSaving == true) {
			boolean exit = false;
			while(!exit) {
				
				System.out.println("How much do you wish to deposit to your Saving Account?");
				System.out.println("Current Saving Account Balance: "+ amount);
				String depositStr = scan.nextLine();
				double deposit = 0;
				try {
					deposit = Double.parseDouble(depositStr);							
				//customerService.customerSavingAccount(id, deposit);
				}
				catch(Exception e) {
					System.out.println("That is not a valid number: type 'y' try again or type 'n' to leave deposit option");
					depositStr = scan.nextLine();
					if(depositStr.equals("n")) {exit = true;}
					continue;
				}
				if(deposit > 0 ) {
					exit = true;
					amount +=deposit;
					System.out.println("New Balance of Saving Account: " + amount);
					log.info("User deposited: " + deposit + " to their Saving Account new total:" + amount);
					customerService.customerSavingAccount(id, amount);
				}else if (deposit <= 0){
					System.out.println("Cannot deposit 0 or negative number: type 'y' try again or type 'n' to leave deposit option.");
					depositStr = scan.nextLine();
					if(depositStr.equals("n")) {exit = true;}}
			}
		}
		
	}
	// Done
	public void depositChecking(int id) {
		int temp = 0;
		double amount = 0;
		List<CheckingAccount> checkingAccounts = checkingService.getAllChecking();
		boolean foundAccount = false;
		boolean hasChecking = false;
		for(CheckingAccount e: checkingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				foundAccount = true;
				hasChecking = e.getCustomer().isHasChecking();
				amount = e.getBalance();
				}
		}
		if(foundAccount == false) {
			System.out.println("Checking Account does not exist.");
		}else if(foundAccount == true && hasChecking == false) {
			System.out.println("Awaiting on Employee to Approve Account.");
		}else if(foundAccount == true && hasChecking == true) {
			boolean exit = false;
			while(!exit) {
				
				System.out.println("How much do you wish to deposit to your Checking Account?");
				System.out.println("Current Checking Account Balance: "+ amount);
				String depositStr = scan.nextLine();
				double deposit = 0;
				try {
					deposit = Double.parseDouble(depositStr);							
				//customerService.customerSavingAccount(id, deposit);
				}
				catch(Exception e) {
					System.out.println("That is not a valid number: type 'y' try again or type 'n' to leave deposit option");
					depositStr = scan.nextLine();
					if(depositStr.equals("n")) {exit = true;}
					continue;
				}
				if(deposit > 0 ) {
					exit = true;
					amount +=deposit;
					System.out.println("New Balance of Checking Account: " + amount);
					log.info("User deposited: " + deposit + " to their Checking Account new total:" + amount);
					customerService.customerCheckingAccount(id, amount);
				}else if(deposit <= 0) {
					System.out.println("Cannot deposit 0 or negative number: type 'y' try again or type 'n' to leave deposit option.");
					depositStr = scan.nextLine();
					if(depositStr.equals("n")) {exit = true;}}
			}
		}
	
		
	}
	// Done
	public void withdrawSubMenu(int id) {
		boolean inBank = true;
		while (inBank) { 
			System.out.println("Select an Option");
			System.out.println("1. Withdraw from Saving Account");
			System.out.println("2. Withdraw from Checking Account");
			System.out.println("3. Exit");
			String input = scan.nextLine();
			if (input.equals("1")) {
				withdrawSaving(id);
			} else if (input.equals("2")) {
				withdrawChecking(id);
			} else if (input.equals("3")) {
				System.out.println("Exiting Back.");
				inBank = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
	}
	// Done
	public void withdrawSaving(int id) {
		int temp = 0;
		double amount = 0;
		List<SavingAccount> savingAccounts = savingService.getAllSaving();
		boolean foundAccount = false;
		boolean hasSaving = false;
		for(SavingAccount e: savingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				foundAccount = true;
				hasSaving = e.getCustomer().isHasSaving();
				amount = e.getBalance();
				}
		}
		if(foundAccount == false) {
			System.out.println("Saving Account does not exist.");
		}else if(foundAccount == true && hasSaving == false) {
			System.out.println("Awaiting on Employee to Approve Account.");
		}else if(foundAccount == true && hasSaving == true) {
			boolean exit = false;
			while(!exit) {
				
				System.out.println("How much do you wish to withdraw from your Saving Account?");
				System.out.println("Current Saving Account Balance: "+ amount);
				String withdrawStr = scan.nextLine();
				double withdraw = 0;
				try {
					withdraw = Double.parseDouble(withdrawStr);							
				//customerService.customerSavingAccount(id, deposit);
				}
				catch(Exception e) {
					System.out.println("That is not a valid number: type 'y' try again or type 'n' to leave deposit option");
					withdrawStr = scan.nextLine();
					if(withdrawStr.equals("n")) {exit = true;}
					continue;
				}
				if(withdraw > 0 && withdraw <= amount) {
					exit = true;
					amount -= withdraw;
					System.out.println("New Balance of Saving Account: " + amount);
					customerService.customerSavingAccount(id, amount);
				}else if(withdraw <= 0 || withdraw > amount) {
					System.out.println("Cannot withdraw more than your balance or "
							+ "negative numbers: type 'y' try again or type 'n' to leave withdraw option.");
					withdrawStr = scan.nextLine();
					if(withdrawStr.equals("n")) {exit = true;}}
			}
		}
	}
	// Done
	public void withdrawChecking(int id) {
		int temp = 0;
		double amount = 0;
		List<CheckingAccount> checkingAccounts = checkingService.getAllChecking();
		boolean foundAccount = false;
		boolean hasChecking = false;
		for(CheckingAccount e: checkingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				foundAccount = true;
				hasChecking = e.getCustomer().isHasChecking();
				amount = e.getBalance();
				}
		}
		if(foundAccount == false) {
			System.out.println("Checking Account does not exist.");
		}else if(foundAccount == true && hasChecking == false) {
			System.out.println("Awaiting on Employee to Approve Account.");
		}else if(foundAccount == true && hasChecking == true) {
			boolean exit = false;
			while(!exit) {
				
				System.out.println("How much do you wish to withdraw from your Checking Account?");
				System.out.println("Current Checking Account Balance: "+ amount);
				String withdrawStr = scan.nextLine();
				double withdraw = 0;
				try {
					withdraw = Double.parseDouble(withdrawStr);							
				//customerService.customerSavingAccount(id, deposit);
				}
				catch(Exception e) {
					System.out.println("That is not a valid number: type 'y' try again or type 'n' to leave deposit option");
					withdrawStr = scan.nextLine();
					if(withdrawStr.equals("n")) {exit = true;}
					continue;
				}
				if(withdraw > 0 && withdraw <= amount ) {
					exit = true;
					amount -=withdraw;
					System.out.println("New Balance of Checking Account: " + amount);
					customerService.customerCheckingAccount(id, amount);
				}else if (withdraw <= 0 || withdraw > amount){
					System.out.println("Cannot withdraw more than your balance or "
							+ "negative numbers: type 'y' try again or type 'n' to leave withdraw option.");
					withdrawStr = scan.nextLine();
					if(withdrawStr.equals("n")) {exit = true;}}
			}// end of while loop
		}		
	}
	// Done
	public void transferSubMenu(int id) {
		boolean inBank = true;
		while (inBank) { 
			System.out.println("Select an Option");
			System.out.println("1. Transfer from Saving to Checking Account.");
			System.out.println("2. Transfer From Checking to Saving Account.");
			System.out.println("3. Exit");
			String input = scan.nextLine();
			if (input.equals("1")) {
				transferSAToCA(id);
			} else if (input.equals("2")) {
				transferCAToSA(id);
			} else if (input.equals("3")) {
				System.out.println("Exiting Back.");
				inBank = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}
				
		}
	}
	
	public void transferSAToCA(int id) {
		int temp = 0;
		double amountSaving = 0;
		double amountChecking = 0;
		List<CheckingAccount> checkingAccounts = checkingService.getAllChecking();
		List<SavingAccount> savingAccounts = savingService.getAllSaving();
		boolean savingFound = false;
		boolean checkingFound = false;
		for(SavingAccount e: savingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				savingFound = true;
				amountSaving = e.getBalance();
				}
		}
		for(CheckingAccount e: checkingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				checkingFound = true;
				amountChecking = e.getBalance();
				}
		}
		if (savingFound == true && checkingFound == true) {
			boolean exit = false;
			while(!exit) {
				
				System.out.println("How much do you wish to transfer from your Saving Account to your Checking Account?");
				System.out.println("Current Checking Account Balance: " + amountChecking);
				System.out.println("Current Saving Account Balance: " + amountSaving);
				String withdrawStr = scan.nextLine();
				double withdraw = 0;
				try {
					withdraw = Double.parseDouble(withdrawStr);							
				//customerService.customerSavingAccount(id, deposit);
				}
				catch(Exception e) {
					System.out.println("That is not a valid number: type 'y' try again or type 'n' to leave deposit option");
					withdrawStr = scan.nextLine();
					if(withdrawStr.equals("n")) {exit = true;}
					continue;
				}
				if(withdraw > 0 && withdraw <= amountSaving ) {
					exit = true;
					amountChecking +=withdraw;
					amountSaving -= withdraw;
					System.out.println("New Checking Account Balance: " + amountChecking);
					System.out.println("New Saving Account Balance: " + amountSaving);
					customerService.customerSavingAccount(id, amountSaving);
					customerService.customerCheckingAccount(id, amountChecking);
				}else if (withdraw <= 0 || withdraw > amountChecking){
					System.out.println("Cannot transfer more than your balance or "
							+ "negative numbers: type 'y' try again or type 'n' to leave transfer option.");
					withdrawStr = scan.nextLine();
					if(withdrawStr.equals("n")) {exit = true;}}
			
			}// end of while loop 
		
		}else {System.out.println("You dont have Access wait for Employee.");}
		
		
	}
	
	public void transferCAToSA(int id) {
		int temp = 0;
		double amountSaving = 0;
		double amountChecking = 0;
		List<CheckingAccount> checkingAccounts = checkingService.getAllChecking();
		List<SavingAccount> savingAccounts = savingService.getAllSaving();
		boolean savingFound = false;
		boolean checkingFound = false;
		
		for(SavingAccount e: savingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				savingFound = true;
				amountSaving = e.getBalance();
				}
		}
		for(CheckingAccount e: checkingAccounts) {
			temp = e.getCustomer().getCustomerID();
			if(temp == id) {
				checkingFound = true;
				amountChecking = e.getBalance();
				}
		}
		if (savingFound == true && checkingFound == true) {
			boolean exit = false;
			while(!exit) {
				
				System.out.println("How much do you wish to transfer from your Checking Account to your Saving Account?");
				System.out.println("Current Checking Account Balance: " + amountChecking);
				System.out.println("Current Saving Account Balance: " + amountSaving);
				String withdrawStr = scan.nextLine();
				double withdraw = 0;
				try {
					withdraw = Double.parseDouble(withdrawStr);							
				//customerService.customerSavingAccount(id, deposit);
				}
				catch(Exception e) {
					System.out.println("That is not a valid number: type 'y' try again or type 'n' to leave deposit option");
					withdrawStr = scan.nextLine();
					if(withdrawStr.equals("n")) {exit = true;}
					continue;
				}
				if(withdraw > 0 && withdraw <= amountChecking ) {
					exit = true;
					amountChecking -=withdraw;
					amountSaving += withdraw;
					System.out.println("New Checking Account Balance: " + amountChecking);
					System.out.println("New Saving Account Balance: " + amountSaving);
					customerService.customerSavingAccount(id, amountSaving);
					customerService.customerCheckingAccount(id, amountChecking);
				}else if (withdraw <= 0 || withdraw > amountChecking){
					System.out.println("Cannot transfer more than your balance or "
							+ "negative numbers: type 'y' try again or type 'n' to leave transfer option.");
					withdrawStr = scan.nextLine();
					if(withdrawStr.equals("n")) {exit = true;}}
			
			}// end of while loop 
		
		}else {System.out.println("You dont have Access wait for Employee.");}
		
		
	};
	
	public void showBalance(int id) {
		customerService.showBalance(id);		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//TODO: need two menues one for regular and one for admin 
	// both will have different selections
	
	
	
	
	
	
	
	
	
}
