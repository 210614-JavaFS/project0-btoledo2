package com.revature.Controller;

public class MenuController {
	//This will print out user selections
	public void menuSelection() {
	    System.out.println("1. Create Account");
	    System.out.println("2. Login");
	    System.out.println("3. Deposit money");
	    System.out.println("4. Withdraw money");
	    System.out.println("5. Transfer money");
	    System.out.println("6. Check balance"); // can use a getter to output balance
	    System.out.println("7. Display Account Details");
	    System.out.println("0. to quit: \n"); // exit lets you pick new user (customer,employee, admin roles)
	    System.out.print("Enter Your Choice : ");
	}
	
	public void deposit(String user, int balance, int deposit) {
		balance +=deposit;
	}
	
	public void withdraw(String use, int balance, int withdraw) {
		if((balance - withdraw) < 0) {
			System.out.println("You cannot withdraw more than you account balance");
		}
		else {balance -=withdraw;}
	}
	public void checkBalance(String user, int balance) {
		System.out.println("Total balance: " + balance);
	}
	
	
	
	
	

}
