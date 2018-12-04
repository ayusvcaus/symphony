package com.symphony.interview.service;

import java.util.List;

import com.symphony.interview.dto.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployees();

	public boolean addEmployee(Employee employee);

	public boolean updateEmployee(Employee employee);

	public boolean deleteEmployee(String empId);
}
