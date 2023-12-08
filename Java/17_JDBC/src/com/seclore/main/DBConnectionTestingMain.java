package com.seclore.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.seclore.factory.ConnectionFactory;

public class DBConnectionTestingMain {

	public static void main(String[] args) {
		Connection connection = ConnectionFactory.getConnection();

		if (connection == null) {
			return;
		}
		
		System.out.println("Connected.");

		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection.");
			System.out.println(e.getMessage());
		}

	}

}
