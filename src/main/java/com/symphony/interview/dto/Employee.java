package com.symphony.interview.dto;

import java.util.Objects;

import java.io.Serializable;

import com.google.gson.Gson;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private String empId;

	private String firstName;
	
	private String lastName;
	
	private String deptName;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(empId, firstName, lastName, deptName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Employee)) {
			return false;
		}
		Employee other = (Employee) obj;
		if (empId == null) {
			if (other.getEmpId() != null) {
				return false;
			}
		} else if (!empId.equals(other.getEmpId())) {
			return false;
		}		
		if (firstName == null) {
			if (other.getFirstName() != null) {
				return false;
			}
		} else if (!firstName.equals(other.getFirstName())) {
			return false;
		}
		if (lastName == null) {
			if (other.getLastName() != null) {
				return false;
			}
		} else if (!lastName.equals(other.getLastName())) {
			return false;
		}
		if (deptName == null) {
			if (other.getDeptName() != null) {
				return false;
			}
		} else if (!deptName.equals(other.getDeptName())) {
			return false;
		}		
		return true;
	} 
	
}
