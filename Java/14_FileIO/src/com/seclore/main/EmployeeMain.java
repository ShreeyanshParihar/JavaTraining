package com.seclore.main;

import java.io.File;

import com.seclore.pojo.Employee;
import com.seclore.util.EmployeeUtil;

public class EmployeeMain {

	public static void main(String[] args) {
		File file = new File("serial.txt");
		
		Employee employee = new Employee(1, "Divy", 1000);
		EmployeeUtil employeeUtil = new EmployeeUtil();
		System.out.println(employeeUtil.employeeSerialization(file, employee) ? "Employee serialized."
				: "Failed to serialize employee.");

	
		employee = employeeUtil.employeeDeserialization(file);
		System.out.println(employee);
	}

}
