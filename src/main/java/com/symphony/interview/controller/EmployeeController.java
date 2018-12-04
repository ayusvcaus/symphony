package com.symphony.interview.controller;

import java.util.List;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.symphony.interview.dto.Employee;
import com.symphony.interview.service.EmployeeService;

@Controller
@RequestMapping("/v1")
public class EmployeeController {
	
	private static final Logger s_logger = LogManager.getLogger(EmployeeController.class);

	@Autowired
	public EmployeeService employeeService;

	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees() throws Exception {
		s_logger.info("inside reading...");
		List<Employee> employeeList = employeeService.getEmployees();
		s_logger.info("employees : [" + employeeList.toString() + "]");
		s_logger.info("employees list size: " + employeeList.size());
		if (employeeList==null) {
		    return new ResponseEntity<List<Employee>>(Collections.EMPTY_LIST, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) throws Exception {
		s_logger.info("inside add...");
		boolean success = employeeService.addEmployee(employee);
		s_logger.info("Success? : "+success);
		if (!success) {
			return new ResponseEntity<String>("addEmployee " + employee.getFirstName() + " failed", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<String>("addEmployee " + employee.getFirstName() + " succeded", HttpStatus.OK);
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) throws Exception {
		s_logger.info("inside update...");				
		boolean success = employeeService.updateEmployee(employee);
		s_logger.info("Success? : "+success);
		if (!success) {
			return new ResponseEntity<String>("updateEmployee " + employee.getEmpId() + " failed", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<String>("updateEmployee " + employee.getEmpId() + " succeded", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteEmployee(@RequestBody String empId) throws Exception {
		s_logger.info("inside delete...");
		boolean success = employeeService.deleteEmployee(empId);
		s_logger.info("Success? : "+success);
		if (!success) {
			return new ResponseEntity<String>("deleteEmployee " + empId + " failed", HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		return new ResponseEntity<String>("deleteEmployee " + empId + " succeded", HttpStatus.OK);
	}
}
