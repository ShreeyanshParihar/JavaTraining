package com.seclore.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_DEFAULT)
public class Employee {
	@JsonProperty("id")
	private int employeeId;

	private String name;

	private String[] position;

	private List<String> skills;

	private Map<String, Double> salary;

	public Employee() {
		super();
	}

	public Employee(int employeeId, String name, String[] position, List<String> skills, Map<String, Double> salary) {
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.position = position;
		this.skills = skills;
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getPosition() {
		return position;
	}

	public void setPosition(String[] position) {
		this.position = position;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public Map<String, Double> getSalary() {
		return salary;
	}

	public void setSalary(Map<String, Double> salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", name=" + name + ", position=" + Arrays.toString(position)
				+ ", skills=" + skills + ", salary=" + salary + "]";
	}

}
