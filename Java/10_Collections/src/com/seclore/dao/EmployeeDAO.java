package com.seclore.dao;

import java.util.List;

import com.seclore.pojo.Employee;

public class EmployeeDAO {
	private List<Employee> employeeList;

	public EmployeeDAO(List<Employee> employeeList) {
		super();
		this.employeeList = employeeList;
	}

	public List<Employee> getAllEmployees() {
		return employeeList;
	}

	public void addNewEmployee(Employee employee) {
		employeeList.add(employee);
	}

	public Employee getEmployee(int id) {
		for (Employee employee : employeeList) {
			if (employee.getId()==id)
				return employee;
		}
		return null;
	}
	
	public void deleteEmployee(int id) {
		employeeList.removeIf((employee) -> employee.getId() == id);
	}
	
	public void updateEmployeeName(int id, String name) {
		for (Employee employee : employeeList) {
			if (employee.getId()==id)
				employee.setName(name);
		}
	}
	
	public void updateEmployeeSalary(int id, double salary) {
		for (Employee employee : employeeList) {
			if (employee.getId()==id)
				employee.setSalary(salary);
		}
	}
}
