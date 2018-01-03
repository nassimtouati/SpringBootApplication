package com.touati.org.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.touati.org.model.Department;
import com.touati.org.model.Employee;

public class EmployeeDTO {

	private String firstName;
	private String lastName;
	private Department department;
	
	
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	
	
	
	

	}

	

