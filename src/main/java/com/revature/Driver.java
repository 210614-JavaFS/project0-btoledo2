package com.revature;

import java.util.Scanner;

import com.revature.Controller.MenuController;
import com.revature.User.CreateUser;


public class Driver extends MenuController {
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
	   boolean inBank = true;
	   String input = "";
		while(inBank) { // switch case
			System.out.println("1. Create Account");
		    System.out.println("2. Login");
		    System.out.println("3. Exit");
		    input = scan.nextLine();
		    switch(input) {
		    case "1":	    	
		    	CreateUser user = newUser();
		    	System.out.println(user.getAddress());
		    	break;
		    case "2":
		    	break;	    		
		    case "3":
		    	inBank = false;
		    	break;
		    default:
		    	System.out.println("Invalid Selection.");
		    	break;
		    }    
		}
	}

}
