package com.seclore.main;

import java.util.HashMap;
import java.util.Map;

import com.seclore.pojo.Employee;

public class HashMapMain {
	public static void main(String[] args) {
		Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();

		employeeMap.put(103, new Employee(103, "Reema", 3000));
		employeeMap.put(101, new Employee(101, "Divy", 2000));
		employeeMap.put(102, new Employee(102, "Vivek", 4000));

		System.out.println(employeeMap);

		System.out.println("--------------");
		for (Integer id : employeeMap.keySet()) {
			System.out.println("Key: " + id + ", Value: " + employeeMap.get(id));
		}
	}
}
