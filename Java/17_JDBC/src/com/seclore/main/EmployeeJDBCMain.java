package com.seclore.main;

import java.util.List;

import com.seclore.dao.EmployeeDAO;
import com.seclore.pojo.Employee;
import com.seclore.util.IOUtil;

public class EmployeeJDBCMain {

	public static void main(String[] args) {
		IOUtil io = new IOUtil();
		EmployeeDAO employeeDao = new EmployeeDAO();
		int choice;
		boolean continueChoice;
		Employee employee;

		do {
			System.out.println("Employee Management System");
			System.out.println();

			System.out.println("Menu:");
			System.out.println("1. Get All Employees");
			System.out.println("2. Add Employee");
			System.out.println("3. Get Employee");
			System.out.println("4. Update Employee");
			System.out.println("5. Delete Employee");

			choice = io.getInt("Enter Choice: ");

			switch (choice) {
			case 1:
				System.out.println();
				System.out.println("Employees:");
				List<Employee> employees = employeeDao.getAllEmployees();
				for (Employee emp : employees) {
					System.out.println(emp);
				}
				break;
			case 2:
				System.out.println();
				System.out.println("Enter employee details:");

				employee = new Employee(io.getInt("Id: "), io.getString("Name: "), io.getDouble("Salary: "));
				if (employeeDao.addNewEmployee(employee)) {
					System.out.println("Employee added successfully.");
				} else {
					System.out.println("Cannot add employee.");
				}

				break;
			case 3:
				System.out.println();

				employee = employeeDao.getEmployeeById(io.getInt("Id: "));
				
				if (employee != null) {
					System.out.println(employee);
				} else {
					System.out.println("Cannot find employee.");
				}

				break;
			case 4:
				System.out.println();
				System.out.println("Enter employee details:");

				employee = new Employee(io.getInt("Id: "), io.getString("Name: "), io.getDouble("Salary: "));
				if (employeeDao.updateEmployee(employee)) {
					System.out.println("Employee updated successfully.");
				} else {
					System.out.println("Cannot update employee.");
				}

				break;
			case 5:
				System.out.println();

				if (employeeDao.deleteEmployeeById(io.getInt("Id: "))) {
					System.out.println("Employee deleted successfully.");
				} else {
					System.out.println("Cannot delete employee.");
				}

				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}

			continueChoice = io.getBoolean("Do you want to continue? [Y/n] ");
		} while (continueChoice);
	}

}
