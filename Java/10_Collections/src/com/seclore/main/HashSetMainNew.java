package com.seclore.main;

import java.util.HashSet;

import com.seclore.dao.EmployeeHashSetDAO;
import com.seclore.pojo.Employee;

public class HashSetMainNew {

	public static void main(String[] args) {
		System.out.println("Creating employees...");
		Employee divy = new Employee(101, "Divy", 1000);
		Employee vivek = new Employee(102, "Vivek", 2000);
		Employee duplicateDivy = new Employee(101, "Divy", 1000);
		Employee higherSalaryDivy = new Employee(101, "Divy", 3000);
		Employee higherSalaryVivek = new Employee(102, "Vivek", 5000);
		
		EmployeeHashSetDAO dao = new EmployeeHashSetDAO(new HashSet<Employee>());

		System.out.println("-----------------------");
		System.out.println("Adding employees...");
		dao.addNewEmployee(divy);
		dao.addNewEmployee(vivek);
		dao.addNewEmployee(duplicateDivy);

		System.out.println("-----------------------");
		System.out.println("Updating employees...");
		dao.getAllEmployees().remove(divy);
		divy.setSalary(3000);
		dao.addNewEmployee(divy);

		System.out.println("-----------------------");
		System.out.println("Printing employees...");
		for (Employee employee : dao.getAllEmployees()) {
			System.out.println(employee);
		}

		System.out.println("-----------------------");
		System.out.println("Adding employees again...");
		dao.addNewEmployee(higherSalaryDivy);

		System.out.println("-----------------------");
		System.out.println("Printing employees...");
		for (Employee employee : dao.getAllEmployees()) {
			System.out.println(employee);
		}

		System.out.println("-----------------------");
		System.out.println("Searching employees...");
		System.out.println("Contains divy? " + dao.getAllEmployees().contains(divy));
		System.out.println("Contains duplicateDivy? " + dao.getAllEmployees().contains(duplicateDivy));
		System.out.println("Contains higherSalaryVivek? " + dao.getAllEmployees().contains(higherSalaryVivek));
	}

}
