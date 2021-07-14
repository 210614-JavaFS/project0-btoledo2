package com.revature;

//import java.sql.Connection;
//import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.CustomerController;
import com.revature.controller.EmployeeController;
import com.revature.controller.MenuController;
//import com.revature.models.Customer;
//import com.revature.util.ConnectionUtil;


public class Driver extends MenuController {
	private static EmployeeController employeeController = new EmployeeController();
	private static CustomerController customerController = new CustomerController();
	private static Logger log = (Logger) LoggerFactory.getLogger(Driver.class);
	
	public static Scanner scan = new Scanner(System.in);


	public static void main(String[] args) {
		boolean inBank = true;
		System.out.println("Welcome to The Bank Application.");

		while (inBank) { // switch case
			log.info("Bank Application Started");
			System.out.println("Select an Option");
			System.out.println("1. Customer Options");
			System.out.println("2. Employee Options");
			System.out.println("3. Exit");
			String input = scan.nextLine();
			if (input.equals("1")) {
				customerController.customerMenu();
			} else if (input.equals("2")) {
				employeeController.empMenu();

			} else if (input.equals("3")) {
				System.out.println("Leaving Bank");
				inBank = false;
			} else {
				System.out.println("Not a valid selection try again.");
			}

		}
	}
	
	
	// Connection test Successful
//	public static void main(String[] args) {
//	try(Connection conn = ConnectionUtil.getConnection()){
//		System.out.println("Connection Successful!");
//	}catch(SQLException e) {
//		e.printStackTrace();
//	}
//}
	
	
	
}
