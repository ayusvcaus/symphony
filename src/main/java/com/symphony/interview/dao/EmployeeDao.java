package com.symphony.interview.dao;

import java.util.List;
import com.symphony.interview.dto.Employee;

public interface EmployeeDao {
	
	public List<Employee> getEmployees();

	public boolean addEmployee(Employee employee);

	public boolean updateEmployee(Employee employee);

	public boolean deleteEmployee(String empId);
}
