package com.symphony.interview.controller;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.google.gson.Gson;

import com.symphony.interview.dto.Employee;
import com.symphony.interview.service.EmployeeService;
import com.symphony.interview.controller.EmployeeController;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore( {"javax.management.*"})
public class EmployeeControllerTest {
	
	private static String ctrl_base_path = "/v1";
	
	@InjectMocks
	@Autowired
	private static EmployeeController controller;

	@Mock
	private static EmployeeService employeeService;


	private MockMvc mockMvc;
	
	private Employee empObj;
	private Employee empObj2;

	 @Before
	 public void initMockMvc() throws Exception { 
	     MockitoAnnotations.initMocks(this);
	     mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	     
         empObj=new Employee();
         empObj.setEmpId("123");
         empObj.setFirstName("Tony");
         empObj.setLastName("Brown");
         empObj.setDeptName("Engineering");
         
         empObj2=new Employee();
         empObj2.setEmpId("567");
         empObj2.setFirstName("Jack");
         empObj2.setLastName("Smith");
         empObj2.setDeptName("Finance");
         
	 }

	 @Test
	 public void testGetEmployees() throws Exception {
	      // Success
          
          List<Employee> employeeList=new ArrayList<>();
          
	      when(employeeService.getEmployees()).thenReturn(employeeList);
	      
	      mockMvc.perform(get(ctrl_base_path + "/getEmployees")
	    		  .accept(MediaType.APPLICATION_JSON))
		          .andDo(print())
		          .andExpect(status().isOk())
		          .andExpect(content().string("[]"));
          
          employeeList.add(empObj);
          employeeList.add(empObj2);
	      
          when(employeeService.getEmployees()).thenReturn(employeeList);
          mockMvc.perform(get(ctrl_base_path + "/getEmployees")
	    		  .accept(MediaType.APPLICATION_JSON))
		          .andDo(print())
		          .andExpect(status().isOk())
		          .andExpect(content().string("[{\"empId\":\"123\",\"firstName\":\"Tony\",\"lastName\":\"Brown\",\"deptName\":\"Engineering\"},{\"empId\":\"567\",\"firstName\":\"Jack\",\"lastName\":\"Smith\",\"deptName\":\"Finance\"}]"));
	 }
	 
	 @Test
	 public void testAddEmployee() throws Exception {
		 	     
	     empObj.setEmpId(null);

		 when(employeeService.addEmployee(empObj)).thenReturn(true);
		 
		 mockMvc.perform(post(ctrl_base_path + "/addEmployee")
				  .content(new Gson().toJson(empObj))
				  .contentType(MediaType.APPLICATION_JSON)
	    		  .accept(MediaType.APPLICATION_JSON))
		          .andDo(print())
		          .andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testUpdateEmployee() throws Exception {	

		 when(employeeService.updateEmployee(empObj)).thenReturn(true);
		 
		 mockMvc.perform(put(ctrl_base_path + "/updateEmployee")
				  .content(new Gson().toJson(empObj))
				  .contentType(MediaType.APPLICATION_JSON)
	    		  .accept(MediaType.APPLICATION_JSON))
		          .andDo(print())
		          .andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testDeleteEmployee() throws Exception {	

		 when(employeeService.deleteEmployee(empObj.getEmpId())).thenReturn(true);
		 
		 mockMvc.perform(delete(ctrl_base_path + "/deleteEmployee")
				  .content(empObj.getEmpId())
				  .contentType(MediaType.APPLICATION_JSON)
	    		  .accept(MediaType.APPLICATION_JSON))
		          .andDo(print())
		          .andExpect(status().isOk());
	 }	 
}
