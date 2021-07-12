package com.revature.services;

import java.util.List;

import com.revature.models.CheckingAccount;
import com.revature.repos.CheckingAccountDAO;
import com.revature.repos.CheckingAccountImpl;

public class CheckingAccountService {
	
	private static CheckingAccountDAO checkingAccountDao = new CheckingAccountImpl();

	public List<CheckingAccount> getAllChecking(){		
		return checkingAccountDao.findAll();
	}
	
}
