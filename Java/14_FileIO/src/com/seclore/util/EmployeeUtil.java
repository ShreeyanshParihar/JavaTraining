package com.seclore.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.seclore.pojo.Employee;

public class EmployeeUtil {
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	public boolean employeeSerialization(File file, Employee employee) {
		try {
			objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(employee);
			return true;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return false;
	}
	
	public Employee employeeDeserialization(File file) {
		try {
			objectInputStream = new ObjectInputStream(new FileInputStream(file));
			return (Employee) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				objectInputStream.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
}
