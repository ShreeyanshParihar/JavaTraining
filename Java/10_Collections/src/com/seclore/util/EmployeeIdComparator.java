package com.seclore.util;

import java.util.Comparator;

import com.seclore.pojo.Employee;

public class EmployeeIdComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee employee1, Employee employee2) {
		return employee1.getId() - employee2.getId();
	}

}
