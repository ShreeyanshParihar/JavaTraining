package com.seclore.main;

import java.util.ArrayList;

import com.seclore.dao.EmployeeDAO;
import com.seclore.pojo.Employee;

public class ArrayListMain {

	public static void main(String[] args) {
		Employee divy = new Employee(101, "Divy", 1000);
		Employee vivek = new Employee(102, "Vivek", 2000);
		Employee seema = new Employee(103, "Seema", 4000);
		Employee reema = new Employee(104, "Reema", 3000);

		EmployeeDAO dao = new EmployeeDAO(new ArrayList<Employee>());
		dao.addNewEmployee(divy);
		dao.addNewEmployee(seema);
		dao.addNewEmployee(reema);
		dao.addNewEmployee(vivek);

		System.out.println("Employees:");
		for (Employee employee : dao.getAllEmployees()) {
			System.out.println(employee);
		}

//		Collections.sort(dao.getAllEmployees(), new EmployeeIdComparator());
//
//		System.out.println("---------------");
//		System.out.println("Id Sorted Employees:");
//		for (Employee employee : dao.getAllEmployees()) {
//			System.out.println(employee);
//		}
//
//		System.out.println("---------------");
//		Collections.sort(dao.getAllEmployees(), new EmployeeNameComparator());
//
//		System.out.println("Name Sorted Employees:");
//		for (Employee employee : dao.getAllEmployees()) {
//			System.out.println(employee);
//		}
//
//		System.out.println("---------------");
//		Collections.sort(dao.getAllEmployees(), new EmployeeSalaryComparator());
//
//		System.out.println("Salary Sorted Employees:");
//		for (Employee employee : dao.getAllEmployees()) {
//			System.out.println(employee);
//		}

		System.out.println("---------------");
		System.out.println("Employee 102: " + dao.getEmployee(102));

		System.out.println("---------------");
		dao.updateEmployeeName(101, "Divya Jain");
		dao.updateEmployeeSalary(104, 5000);
		System.out.println("Updates Employees:");
		for (Employee employee : dao.getAllEmployees()) {
			System.out.println(employee);
		}

		System.out.println("---------------");
		dao.deleteEmployee(103);

		System.out.println("Deleted 103:");
		for (Employee employee : dao.getAllEmployees()) {
			System.out.println(employee);
		}

	}

}
