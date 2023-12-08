package com.seclore.main;

import java.util.Scanner;

import com.seclore.pojo.Employee;

public class ArrayMain {

	public static void main(String[] args) {
		int[] numbers = new int[5];
		int sum = 0;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter values of array: ");
		for (int i = 0; i < numbers.length; i++) {
			System.out.print("	" + i + ": ");
			numbers[i] = scanner.nextInt();
			scanner.nextLine();
		}

		System.out.println("---------------------");
		System.out.print("The array you entered is ");
		for (int number : numbers) {
			System.out.print(number + " ");
			sum += number;
		}
		System.out.println("\nThe sum of numbers is " + sum);

		System.out.println("---------------------");
		System.out.println("---------------------");

		Employee[] employees = new Employee[5];

		System.out.println("Enter employees: ");
		for (int i = 0; i < employees.length; i++) {
			employees[i] = new Employee();
			employees[i].setId(101 + i);
			System.out.println("Employee " + (101 + i));
			System.out.print("	Enter name: ");
			employees[i].setName(scanner.nextLine());
			System.out.print("	Enter salary: ");
			employees[i].setSalary(scanner.nextDouble());
			scanner.nextLine();
		}

		System.out.println("---------------------");
		System.out.println("The employees you entered are:");
		for (Employee employee : employees) {
			System.out.println(employee);
		}

		scanner.close();
	}

}
