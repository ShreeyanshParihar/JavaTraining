package com.seclore.util;

import java.util.Comparator;

import com.seclore.pojo.Employee;

public class EmployeeNameComparator implements Comparator<Employee> {

	@Override
	public int compare(Employee employee1, Employee employee2) {
		return employee1.getName().compareTo(employee2.getName());
	}

}
