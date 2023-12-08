package com.seclore.dao;

import java.util.Set;

import com.seclore.pojo.Employee;

public class EmployeeHashSetDAO {
	private Set<Employee> employeeList;

	public EmployeeHashSetDAO(Set<Employee> employeeList) {
		super();
		this.employeeList = employeeList;
	}
	
	public void addNewEmployee(Employee employee) {
		employeeList.add(employee);
	}
	
	public Set<Employee> getAllEmployees() {
		return employeeList;
	}
}
