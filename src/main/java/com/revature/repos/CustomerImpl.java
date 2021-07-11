package com.revature.repos;

import java.sql.Connection;
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

	@Override
	public Customer findCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCustomer(Customer customer) {
	
		return false;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

}
