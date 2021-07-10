package com.revature;

//import java.sql.Connection;
//import java.sql.SQLException;
import java.util.Scanner;

import com.revature.controller.EmployeeController;
import com.revature.controller.MenuController;
//import com.revature.models.Customer;
//import com.revature.util.ConnectionUtil;


public class Driver extends MenuController {
	private static EmployeeController employeeController = new EmployeeController();
	
	
	
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
	   boolean inBank = true;
	   String input = "";
		while(inBank) { // switch case
			System.out.println("1. Customer");
		    System.out.println("2. Employee");
		    System.out.println("3. Exit");
		    input = scan.nextLine();
		    switch(input) {
		    case "1":
		    	//TODO 
		    	// 1 - create customer 
		    	// 2 - login
		    	break;
		    case "2":
		    	employeeController.empMenu();
		    	break;	    		
		    case "3":
		    	// exit statement 
		    	
		    	inBank = false;
		    	break;
		    default:
		    	System.out.println("Invalid Selection.");
		    	break;
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
