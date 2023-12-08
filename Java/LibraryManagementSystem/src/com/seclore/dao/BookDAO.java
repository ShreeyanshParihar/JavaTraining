package com.seclore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seclore.pojo.Book;

public class BookDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private int count;
	private ResultSet resultSet;
	private String query;

	public BookDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean addNewBook(Book book) {
		if (connection == null) {
			return false;
		}

		query = "INSERT INTO books VALUES (?, ?); SELECT SCOPE_IDENTITY();";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, book.getName());
			preparedStatement.setString(2, book.getAuthor());

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}
			book.setSerialNumber(resultSet.getInt(1));
			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	public static Book parseResultToBook(ResultSet resultSet) throws SQLException {
		return new Book(resultSet.getInt("serial_number"), resultSet.getString("name"), resultSet.getString("author"));
	}

	public Book getBookBySerialNumber(int serialNumber) {
		if (connection == null) {
			return null;
		}

		query = "SELECT * FROM books WHERE serial_number=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, serialNumber);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			return parseResultToBook(resultSet);

		} catch (SQLException e) {
			return null;
		}
	}

	public List<Book> getAllBooks() {
		if (connection == null) {
			return null;
		}

		query = "SELECT serial_number, name, author FROM books";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			List<Book> books = new ArrayList<Book>();

			while (resultSet.next()) {
				books.add(parseResultToBook(resultSet));
			}
			return books;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean deleteBook(Book book) {
		if (connection == null) {
			return false;
		}

		query = "DELETE FROM books WHERE serial_number=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, book.getSerialNumber());

			count = preparedStatement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			return false;
		}
	}
}
