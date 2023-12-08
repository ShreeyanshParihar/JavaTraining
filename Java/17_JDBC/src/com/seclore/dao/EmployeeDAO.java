package com.seclore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seclore.factory.ConnectionFactory;
import com.seclore.pojo.Employee;

public class EmployeeDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private int count;
	private ResultSet resultSet;
	private String query;

	public boolean addNewEmployee(Employee employee) {
		connection = ConnectionFactory.getConnection();

		if (connection == null) {
			return false;
		}

		query = "INSERT INTO employee_master VALUES (?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, employee.getId());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setDouble(3, employee.getSalary());

			count = preparedStatement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			return false;
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

	public Employee getEmployeeById(int employeeId) {
		connection = ConnectionFactory.getConnection();

		if (connection == null) {
			return null;
		}

		query = "SELECT * FROM employee_master WHERE employee_id=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, employeeId);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}
			
			Employee employee = new Employee(resultSet.getInt("employee_id"), resultSet.getString("name"),
					resultSet.getDouble("salary"));
			return employee;

		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

	public List<Employee> getAllEmployees() {
		connection = ConnectionFactory.getConnection();

		if (connection == null) {
			return null;
		}

		query = "SELECT * FROM employee_master";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			List<Employee> employees = new ArrayList<Employee>();

			while (resultSet.next()) {
				employees.add(new Employee(resultSet.getInt("employee_id"), resultSet.getString("name"),
						resultSet.getDouble("salary")));
			}
			return employees;
		} catch (SQLException e) {
			return null;
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

	public boolean updateEmployee(Employee employee) {
		connection = ConnectionFactory.getConnection();

		if (connection == null) {
			return false;
		}

		query = "UPDATE employee_master SET name=?, salary=? WHERE employee_id=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setDouble(2, employee.getSalary());
			preparedStatement.setInt(3, employee.getId());

			count = preparedStatement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			return false;
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

	public boolean deleteEmployeeById(int employeeId) {
		connection = ConnectionFactory.getConnection();

		if (connection == null) {
			return false;
		}

		query = "DELETE FROM employee_master WHERE employee_id=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, employeeId);

			count = preparedStatement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			return false;
		} finally {
			ConnectionFactory.closeConnection(connection);
		}
	}

}
