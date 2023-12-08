package com.seclore.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.seclore.pojo.Employee;
import com.seclore.util.EmployeeNameComparator;

public class CollectionsUtilMain {
	public static void main(String[] args) {
		Employee divy = new Employee(101, "Divya", 1000);
		Employee vivek = new Employee(102, "Vivek", 2000);
		Employee seema = new Employee(103, "Seema", 4000);
		Employee reema = new Employee(104, "Reema", 3000);

		List<Employee> employees = new ArrayList<Employee>();
		Collections.addAll(employees, divy, vivek, seema, reema);

		Iterator<Employee> employeeIter = employees.iterator();
		while (employeeIter.hasNext())
			System.out.println(employeeIter.next());

		Collections.sort(employees, new EmployeeNameComparator());
		System.out.println(employees);

		System.out.println(Collections.binarySearch(employees, seema, new EmployeeNameComparator()));
	}
}