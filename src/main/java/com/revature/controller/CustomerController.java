package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Customer;
import com.revature.repos.CustomerDAO;
import com.revature.services.CustomerService;

public class CustomerController {

	private static CustomerService customerService = new CustomerService();
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
				else {System.out.println("Hello Customer id:" + id);}
			} else if (input.equals("3")) {
				inCustomerMenu = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
		
	}
	
	
	
	
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
						id = e.getCustomerID();
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
		
		return id;
	}
	
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
					System.out.println("Username exist already try another Username.");}
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
			}
		}
		
		System.out.println("Please enter you Addres.");
		String address = scan.nextLine();
		Customer customer = new Customer(firstName,lastName,userName,password,address);
		if(customerService.addCustome(customer)) {
			System.out.println("You user account was being add.");
		}else {
			System.out.println("USer name is not valid, try again.");
		}
		
	}
	
	
	
	
	/*TODO LIST:
	 * 1. Make add new user item
	 * 2. after login open up another menu for customer
	 * 3.
	 * 4.
	 * 5.
	 * 
	 * 
	 * 
	 */
	
	
	
	
	
	
	
}
