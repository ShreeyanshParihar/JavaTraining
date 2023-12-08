package com.seclore.main;

import com.seclore.pojo.Employee;
import com.seclore.util.EmployeeUtil;

public class EmployeeMain {
	public static void main(String[] args) {
		System.out.println("main start");
		Employee employee = new Employee(101, "Divy Jain", 1000);
		EmployeeUtil employeeUtil = new EmployeeUtil();
		
		System.out.println(employee);
		employeeUtil.changeEmployee(employee);
		System.out.println(employee);
		System.out.println("main end");
	}
}
