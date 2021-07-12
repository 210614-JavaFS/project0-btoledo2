package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.CheckingAccount;
import com.revature.models.Customer;
import com.revature.util.ConnectionUtil;

public class CheckingAccountImpl implements CheckingAccountDAO {
	public static CustomerDAO customerDao = new CustomerImpl();
	@Override
	public List<CheckingAccount> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM checking_account";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<CheckingAccount> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				CheckingAccount checking = new CheckingAccount();
				checking.setCheckingId(result.getInt("checking_id"));
				checking.setBalance(result.getDouble("balance"));
				int customerID= result.getInt("customer_id");
				if(customerID!=0) {
					Customer customer =  customerDao.findCustomer(customerID);
					checking.setCustomer(customer);
				}
				list.add(checking);
			}			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public CheckingAccount findCheckingAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * From checking_account WHERE customer_id = ?";
	
			PreparedStatement statement = conn.prepareStatement(sql);		
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			CheckingAccount checking = new CheckingAccount();
			while(result.next()) {
				
				checking.setCheckingId(result.getInt("checking_id"));
				checking.setBalance(result.getDouble("balance"));
				int customerID= result.getInt("customer_id");
				if(customerID!=0) {
					Customer customer =  customerDao.findCustomer(customerID);
					checking.setCustomer(customer);
				}
				
				return checking;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
