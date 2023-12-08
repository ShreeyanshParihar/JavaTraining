package com.seclore.main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seclore.pojo.Employee;

public class EmployeeJacksonMain {

	public static void main(String[] args) {
		Employee divy = new Employee(101, "Divya Jain", new String[] { "Developer", "Designer" },
				Arrays.asList("Java", "Python"), new HashMap<String, Double>() {
					private static final long serialVersionUID = -5083642761916496070L;

					{
						put("2010", Double.valueOf(1200));
						put("2011", Double.valueOf(1300));
						put("2012", Double.valueOf(1500));
					}
				});

		ObjectMapper mapper = new ObjectMapper();

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("divy.json"), divy);
			System.out.println("Serialization successful.");
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(divy));
		} catch (IOException e) {
			e.printStackTrace();
		}

		divy.setEmployeeId(0);
		divy.setSalary(null);
		divy.setPosition(null);

		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("divyNulled.json"), divy);
			System.out.println("Serialization successful.");
			System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(divy));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
