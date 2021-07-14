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

public class EmployeeImpl implements EmployeeDAO {

	@Override
	public List<Employee> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM employee";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Employee> list = new ArrayList<>();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(result.getInt("employee_id"));
				employee.setEmpRole(result.getString("emp_role"));
				employee.setFirstName(result.getString("first_name"));
				employee.setLastName(result.getString("last_name"));
				employee.setUserName(result.getString("login_name"));
				employee.setPassword(result.getString("pswd"));

				list.add(employee);
			}
			
			return list;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer findCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployee(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM employee WHERE employee_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			//This is where SQL injection is checked for.
			statement.setInt(1, id);
			
			ResultSet result = statement.executeQuery();
			
			Employee employee = new Employee();
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				employee.setEmployeeID(result.getInt("employee_id"));
				employee.setEmpRole(result.getString("emp_role"));
				employee.setFirstName(result.getString("first_name"));
				employee.setLastName(result.getString("last_name"));
				employee.setUserName(result.getString("login_name"));
				employee.setPassword(result.getString("pswd"));
			}
			
			return employee;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// Done
	@Override
	public boolean showAccountBalance() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT customer.customer_id , customer.first_name  , customer.last_name , saving_account.balance , checking_account.balance\r\n "
					+ "FROM customer LEFT JOIN saving_account ON saving_account.customer_id = customer.customer_id \r\n"
					+ "LEFT JOIN checking_account ON checking_account.customer_id = customer.customer_id;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			String show = "";
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			//System.out.println("First Name - Last Name  - Saving Balance -  Checking Balance");
			while(result.next()) {
				show += "Customer ID:  ";
				show += result.getInt(1);
				show += " First Name: ";
				show += result.getString(2);
				show += " Last Name: ";
				show += result.getString(3);
				show += " Saving Account Balance: ";
				show += result.getString(4);
				show += " Checking Account Balance: ";
				show += result.getString(5);
				System.out.println(show);
				show = "";
			}
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
    // Done
	@Override
	public boolean showAccountInfo() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT customer.customer_id  , customer.HAS_SAVING , customer.HAS_CHECKING , saving_account.balance , checking_account.balance\r\n"
					+ "FROM customer \r\n"
					+ "LEFT JOIN saving_account ON saving_account.customer_id = customer.customer_id \r\n"
					+ "LEFT JOIN checking_account ON checking_account.customer_id = customer.customer_id ;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			String show = "";
			String temp = "";// to check null
			boolean hasSaving = false;
			boolean hasChecking = false;
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			//System.out.println("First Name - Last Name  - Saving Balance -  Checking Balance");
			while(result.next()) {
				show += "Customer ID: ";
				show += result.getString(1);
				hasSaving = result.getBoolean(2);
				hasChecking = result.getBoolean(3);
				temp += result.getString(4);
				if(hasSaving == true && !temp.equals("null")) {
					show += " Saving account is Active ";
				}else if(hasSaving == false && !temp.equals("null")) {
					show += " Saving account is waiting on Approval ";
				}else if(hasSaving == false && temp.equals("null")) {
					show += " Saving account does not Exist. ";
				}
				temp = "";
				temp += result.getString(5);
				if(hasChecking == true && !temp.equals("null")) {
					show += " Checking account is Active ";
				}else if(hasChecking == false && !temp.equals("null")) {
					show += " Checking account is waiting on Approval ";
				}else if(hasChecking == false && temp.equals("null")) {
					show += " Checking account does not Exist. ";
				}
				System.out.println(show);
				show = "";
				temp = "";
			}
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
    // Done 
	@Override
	public boolean showPersonalInfo() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM customer";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			String show = "";
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				show +="";
				result.getInt("customer_id");
				show +="First Name: ";
				show += result.getString("first_name");
				show +=" Last Name: ";
				show += result.getString("last_name");
				show +=" Username: ";
				show += result.getString("login_name");
				result.getString("pswd");
				show +=" Street Address: ";
				show += result.getString("address");
				result.getBoolean("has_saving");
				result.getBoolean("has_checking");
				System.out.println(show);
				show = "";
			}
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Testing
	@Override 
	public boolean denyCheckingAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM checking_account \r\n"
					+ "WHERE CUSTOMER_ID  = ?; ";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(1, id);
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}

	// Testing
	@Override
	public boolean denySavingAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM saving_account \r\n"
					+ "WHERE CUSTOMER_ID  = ?; ";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(1, id);
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}

	// Testing
	@Override
	public boolean approveSavingAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE CUSTOMER \r\n"
					+ "SET HAS_SAVING = TRUE \r\n"
					+ "WHERE customer_id = ?; ";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(1, id);
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	// Testing

	@Override
	public boolean approveCheckingAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE CUSTOMER \r\n"
					+ "SET HAS_CHECKING = true\r\n"
					+ "WHERE customer_id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(1, id);
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Testing
	@Override
	public boolean removeUSer(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM CUSTOMER \r\n"
					+ "WHERE CUSTOMER_ID =?; ";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setDouble(1, id);
			statement.execute();
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return false;
	}

}
