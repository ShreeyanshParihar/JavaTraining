package com.seclore.main;

import java.util.TreeSet;

import com.seclore.dao.EmployeeHashSetDAO;
import com.seclore.pojo.Employee;
import com.seclore.util.EmployeeIdComparator;

public class TreeSetMain {

	public static void main(String[] args) {
		System.out.println("Creating employees...");
		Employee divy = new Employee(101, "Divy", 1000);
		Employee vivek = new Employee(102, "Vivek", 2000);
		Employee seema = new Employee(103, "Seema", 1000);
		Employee reema = new Employee(104, "Reema", 3000);
		
		EmployeeHashSetDAO dao = new EmployeeHashSetDAO(new TreeSet<Employee>(new EmployeeIdComparator()));

		System.out.println("-----------------------");
		System.out.println("Adding employees...");
		dao.addNewEmployee(divy);
		dao.addNewEmployee(seema);
		dao.addNewEmployee(reema);
		dao.addNewEmployee(vivek);

		System.out.println("-----------------------");
		System.out.println("Printing employees...");
		for (Employee employee : dao.getAllEmployees()) {
			System.out.println(employee);
		}
	}

}
