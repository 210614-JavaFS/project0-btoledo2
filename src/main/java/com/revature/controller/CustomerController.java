package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.Driver;
import com.revature.models.CheckingAccount;
import com.revature.models.Customer;
import com.revature.models.SavingAccount;
import com.revature.repos.CustomerDAO;
import com.revature.services.CheckingAccountService;
import com.revature.services.CustomerService;
import com.revature.services.SavingAccountService;

public class CustomerController {

	private static Logger log = (Logger) LoggerFactory.getLogger(Driver.class);
	private static CustomerService customerService = new CustomerService();
	private static SavingAccountService savingService = new SavingAccountService();
	private static CheckingAccountService checkingService = new CheckingAccountService();
	private static Scanner scan = new Scanner(System.in);
	
	public void customerMenu() {
		boolean inCustomerMenu = true;
		System.out.println("Welcome Customers");

		while (inCustomerMenu) { // switch case
			System.out.println("Select an Option");
			System.out.println("1. Create User");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			String input = scan.nextLine();
			if (input.equals("1")) {
				createUser();
			} else if (input.equals("2")) {
				int id = customerLogin();
				if(id < 0) {System.out.println("Cannot login in");}
				else {System.out.println("Here");
					customerSubMenu(id);}
			} else if (input.equals("3")) {
				inCustomerMenu = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
		
	}
	
	
	
	// Done
	public int customerLogin() {
		int id= -1;
		String userName = null;
		String temp;
		String tempPswd;
		String password;
		List<Customer> customer = customerService.getAllCustomer();
		boolean exit = false;
		boolean exitpswd= false;
		boolean leave = false;
		
		while(!leave) {
			while(!exit) {
				System.out.println("Please enter your Username.");
				userName = scan.nextLine();
				for(Customer e :customer) {
					temp = e.getUserName();
					if(temp.equals(userName)) {exit = true;}
				}
				if(exit == false) {
					System.out.println("Username does not exist. \n" + "Type 'y' to try again or 'n' to leave login");
					log.warn("User enter an incorrect username or username does not exist.");
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
				for(Customer e :customer) {
					temp = e.getUserName();
					tempPswd = e.getPassword(); 
					if(tempPswd.equals(password) && temp.equals(userName)) {
						exitpswd = true;
						leave = true;
						log.info("User enter correct username and password login into customer sub menu.");
						id = e.getCustomerID();
					}
							
				}
				if(exitpswd == false) {
					System.out.println("Password does not match Username. \n" + "Type 'y' to try again or 'n' to leave login");
					log.warn("User type incorrect password or password does not match this username? ");
					password = scan.nextLine();
					if(password.equals("n")) {
						exitpswd = true;
						leave = true;
					}
				}
			}
		}
		return id;
	}
	// Done but maybe need to change later on 
	public void createUser() {
		boolean exit = false;
		String userName = null;
		boolean notFound = false;
		String temp;
		List<Customer> customers = customerService.getAllCustomer();
		
		System.out.println("Please enter your First Name");
		String firstName = scan.nextLine();
		System.out.println("Please enter you last Name");
		String lastName = scan.nextLine();
		System.out.println("Please enter your Username.");
		while(!exit) {
			notFound = false;
			userName = scan.nextLine();
			for(Customer e :customers) {
				temp = e.getUserName();
				if(temp.equals(userName)) {
					notFound = true;
					System.out.println("Username exist already try another Username.");
					log.warn("User enter an already existing username.");}
			}
			if(notFound == false) {exit = true;}
			
		}
		System.out.println("Please Enter Password");
		String password = scan.nextLine();
		exit = false;
		while (!exit) {
			System.out.println("Please ReEnter Password:");
			temp = scan.nextLine();

			if (password.equals(temp)) {
				exit = true;
			} else {
				System.out.println("password did not match");
				log.warn("User enter incorrect passowrd.");
			}
		}
		
		System.out.println("Please enter you Addres.");
		String address = scan.nextLine();
		Customer customer = new Customer(firstName,lastName,userName,password,address);
		if(customerService.addCustome(customer)) {
			System.out.println("You user account was being add.");
			log.info("User Create an account.");
		}else {
			System.out.println("User name is not valid, try again.");
			log.warn("User enter the valid inputs.");
		}
		
	}
	// needs work
	public void customerSubMenu(int id){
		log.info("User enter customer sub menu.");
		Customer customer = customerService.getCustomer(id);
		boolean customerOptions = true;
		
		while (customerOptions) { // Done
			System.out.println( "Hello ,"+ customer.getFirstName());
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
				if(customer.isHasChecking() && customer.isHasChecking()) {
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
	
	}
	// Done
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
	/*TODO LIST:
	 * 1. Transfer subMenu
	 * 2. Transfer From saving to checking and vice versa like withdraw but with extra steps
	 * 3. show balances
	 * 4.
	 * 5.
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
}
