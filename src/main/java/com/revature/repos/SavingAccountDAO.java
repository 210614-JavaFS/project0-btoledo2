package com.revature.repos;

import java.util.List;

import com.revature.models.SavingAccount;

public interface SavingAccountDAO {
	
	public List<SavingAccount> findAll();
	
	public SavingAccount findSavingAccount(int id);
}
