package com.seclore.util;

import java.util.Comparator;

import com.seclore.pojo.Employee;

public class EmployeeSalaryComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee employee1, Employee employee2) {
		return (int) (employee1.getSalary() - employee2.getSalary());
	}

}
