package com.revature.services;

import java.util.List;

import com.revature.models.SavingAccount;
import com.revature.repos.SavingAccountDAO;
import com.revature.repos.SavingAccountImpl;

public class SavingAccountService {
	
	private static SavingAccountDAO savingAccountDao = new SavingAccountImpl();
	
	public List<SavingAccount> getAllSaving(){
		return savingAccountDao.findAll();
	}
	
}
