package com.symphony.interview.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.symphony.interview.dto.Employee;
import com.symphony.interview.dao.EmployeeDao;
import com.symphony.interview.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	public EmployeeDao employeeDao;
	
	public List<Employee> getEmployees() {
		return employeeDao.getEmployees();
	}

	public boolean addEmployee(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	public boolean updateEmployee(Employee employee) {
		return employeeDao.updateEmployee(employee);
	}

	public boolean deleteEmployee(String empId) {
		return employeeDao.deleteEmployee(empId);
	}
}
