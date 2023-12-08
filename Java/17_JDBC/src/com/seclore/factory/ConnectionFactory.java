package com.seclore.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL;
	private static final String DRIVER;

	static {
		URL = "jdbc:sqlserver://localhost:1433;databaseName=TrainingDB;integratedSecurity=true";
		DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find driver class.");
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println("Cannot connect to DB Server.");
			System.out.println(e.getMessage());
		}
		return connection;
	}

	public static boolean closeConnection(Connection connection) {
		try {
			if (connection != null)
				connection.close();
			return true;
		} catch (SQLException e) {
			System.out.println("Could not close connection.");
			System.out.println(e.getMessage());
		}
		return false;
	}
}
