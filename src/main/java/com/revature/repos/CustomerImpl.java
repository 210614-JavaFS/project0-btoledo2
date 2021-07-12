package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.util.ConnectionUtil;

public class CustomerImpl implements CustomerDAO {
	
	private static EmployeeDAO employeeDao = new EmployeeImpl();
	// works
	@Override
	public List<Customer> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM customer";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Customer> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(result.getInt("customer_id"));
				customer.setFirstName(result.getString("first_name"));
				customer.setLastName(result.getString("last_name"));
				customer.setUserName(result.getString("login_name"));
				customer.setPassword(result.getString("pswd"));
				customer.setAddress(result.getString("address"));
				customer.setHasSaving(result.getBoolean("has_saving"));
				customer.setHasChecking(result.getBoolean("has_checking"));
				int employeeID= result.getInt("employee_id");
				if(employeeID!=0) {
					Employee employee =  employeeDao.findEmployee(employeeID);
					customer.setEmployee(employee);
				}
				list.add(customer);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// works 
	@Override
	public Customer findCustomer(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM customer WHERE customer_id = ?";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);	
			ResultSet result = statement.executeQuery();
			
			Customer customer = new Customer();
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				customer.setCustomerID(result.getInt("customer_id"));
				customer.setFirstName(result.getString("first_name"));
				customer.setLastName(result.getString("last_name"));
				customer.setUserName(result.getString("login_name"));
				customer.setPassword(result.getString("pswd"));
				customer.setAddress(result.getString("address"));
				customer.setHasSaving(result.getBoolean("has_saving"));
				customer.setHasChecking(result.getBoolean("has_checking"));
				int employeeID= result.getInt("employee_id");
				if(employeeID!=0) {
					Employee employee =  employeeDao.findEmployee(employeeID);
					customer.setEmployee(employee);
				}
			}
			return customer;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	// works
	@Override
	public boolean addCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO customer(first_name, last_name,login_name,pswd,address, employee_id)\r\n"
					+ "	VALUES (?, ?,?,?,?, 1);";
			int index = 0;
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++index, customer.getFirstName());
			statement.setString(++index, customer.getLastName());
			statement.setString(++index, customer.getUserName());
			statement.setString(++index, customer.getPassword());
			statement.setString(++index, customer.getAddress());
			
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}
    // need testing
	@Override
	public boolean createSaving(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO saving_account(balance, customer_id)\r\n"
					+ " SELECT 0, ? FROM customer WHERE customer_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(1, id);
			statement.setInt(2, id);
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}
	// need testing
	@Override
	public boolean createChecking(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO checking_account(balance, customer_id)\r\n"
					+ " SELECT 0, ? FROM customer WHERE customer_id = ?;";
			int index = 0;
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(++index, id);
			statement.setInt(++index, id);
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateSaving(int id, double amount) {


		return false;
	}

	@Override
	public boolean updateChecking(int id, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showBalance(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean transferMoney(int id) {
		// TODO Auto-generated method stub
		return false;
	}



}
