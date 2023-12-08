package com.seclore.util;

import com.seclore.pojo.Employee;

public class EmployeeUtil {
	public void changeEmployee(Employee employee) {
		System.out.println("EmployeeUtil start");
		System.out.println(employee);
		employee.setName(employee.getName().toUpperCase());
		employee.setSalary(employee.getSalary()+1000);
		System.out.println(employee);
		System.out.println("EmployeeUtil end");
	}
}
