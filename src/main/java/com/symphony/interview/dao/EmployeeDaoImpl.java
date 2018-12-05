package com.symphony.interview.dao;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.symphony.interview.dto.Employee;
import com.symphony.interview.dao.EmployeeDao;

public class EmployeeDaoImpl implements EmployeeDao {
	
	private static final Logger s_logger = LogManager.getLogger(EmployeeDaoImpl.class);
	
	private String jdbcDriver;  
	private String dbUrl;

	private String dbUser;
	private String dbPassword;
	
	private static final String SELECT_EMP_QUERY="select EMP_ID, FIRST_NAME, LAST_NAME, DEPT_NAME from EMPLOYEE";
	private static final String INSERT_EMP_QUERY="insert into EMPLOYEE (FIRST_NAME, LAST_NAME, DEPT_NAME) values (?,?,?)"; //EMP_ID is a auto increased int
	private static final String UPDATE_EMP_QUERY="update EMPLOYEE set FIRST_NAME=?, LAST_NAME=?, DEPT_NAME=? WHERE emp_id=?";
	private static final String DELETE_EMP_QUERY="delete from EMPLOYEE where EMP_ID=?";
	
	public List<Employee> getEmployees() {
		List<Employee> employeesList = new ArrayList<>();
		try {
			Class.forName(jdbcDriver);
			Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMP_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Employee emp = new Employee();
				emp.setEmpId(resultSet.getString("EMP_ID"));
				emp.setFirstName(resultSet.getString("FIRST_NAME"));
				emp.setLastName(resultSet.getString("LAST_NAME"));
				emp.setDeptName(resultSet.getString("DEPT_NAME"));
				employeesList.add(emp);
			}
			
		} catch (Exception e) {
			s_logger.info(e.toString());
		}
		return employeesList;
	}

	public boolean addEmployee(Employee employee) {
		if(employee != null) {
			try {
				Class.forName(jdbcDriver);
				Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMP_QUERY);
				preparedStatement.setString(1, employee.getFirstName());
				preparedStatement.setString(2, employee.getLastName());
				preparedStatement.setString(3, employee.getDeptName());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return true;
			} catch (Exception e) {
				s_logger.info(e.toString());
				return false;
			}
		}
		return false;
	}

	public boolean updateEmployee(Employee employee) {
		if(employee != null) {
			try {
				Class.forName(jdbcDriver);
				Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMP_QUERY);
				preparedStatement.setString(1, employee.getFirstName());
				preparedStatement.setString(2, employee.getLastName());
				preparedStatement.setString(3, employee.getDeptName());
				preparedStatement.setInt(4, Integer.parseInt(employee.getEmpId()));
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return true;
			} catch (Exception e) {
				s_logger.info(e.toString());
				return false;
			}
		}
		return false;
	}

	public boolean deleteEmployee(String empId) {
		if(empId != null) {
			try {
				Class.forName(jdbcDriver);
				Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMP_QUERY);
				preparedStatement.setInt(1, Integer.parseInt(empId));
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return true;
			} catch (Exception e) {
				s_logger.info(e.toString());
			}
		}
		return false;
	}
	
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}
	
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
}
