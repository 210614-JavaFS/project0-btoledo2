package com.revature.repos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.models.SavingAccount;
import com.revature.util.ConnectionUtil;

public class SavingAccountImpl implements SavingAccountDAO {
	public static CustomerDAO customerDao = new CustomerImpl();
	@Override
	public List<SavingAccount> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM saving_account";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<SavingAccount> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				SavingAccount saving = new SavingAccount();
				saving.setSavingId(result.getInt("saving_id"));
				saving.setBalance(result.getDouble("balance"));
				int customerID= result.getInt("customer_id");
				if(customerID!=0) {
					Customer customer =  customerDao.findCustomer(customerID);
					saving.setCustomer(customer);
				}
				list.add(saving);
			}			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
