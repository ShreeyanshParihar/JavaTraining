package com.seclore.main;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seclore.pojo.Employee;

public class EmployeeJacksonMain {

	public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("divy.json");
		
		try {
			Employee divy = mapper.readValue(file, Employee.class);
			System.out.println(divy);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
