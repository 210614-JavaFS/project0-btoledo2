package com.revature.repos;

import java.util.List;

import com.revature.models.CheckingAccount;

public interface CheckingAccountDAO {
	public List<CheckingAccount> findAll();
}
