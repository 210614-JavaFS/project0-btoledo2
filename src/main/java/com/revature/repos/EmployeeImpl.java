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



}
