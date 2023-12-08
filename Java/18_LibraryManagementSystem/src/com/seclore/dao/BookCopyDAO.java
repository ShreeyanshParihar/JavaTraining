package com.seclore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seclore.pojo.Book;
import com.seclore.pojo.BookCopy;

public class BookCopyDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private int count;
	private ResultSet resultSet;
	private String query;

	public BookCopyDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean addNewBookCopies(Book book, int numberOfBooks, int numberOfIssuableBooks) {
		if (connection == null) {
			return false;
		}

		try {
			BookDAO bookDAO = new BookDAO(connection);
			if (bookDAO.getBookBySerialNumber(book.getSerialNumber()) == null) {
				return false;
			}

			query = "INSERT INTO book_copies (serial_number, issuable) VALUES (?, ?)";
			preparedStatement = connection.prepareStatement(query);
			for (int i = 0; i < numberOfBooks; i++) {
				preparedStatement.setInt(1, book.getSerialNumber());
				preparedStatement.setBoolean(2, i < numberOfIssuableBooks);
				if (preparedStatement.executeUpdate() <= 0) {
					return false;
				}
			}

			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	private BookCopy parseResultToBookCopy(ResultSet resultSet) throws SQLException {
		Book book = BookDAO.parseResultToBook(resultSet);
		BookCopy bookCopy = new BookCopy(resultSet.getInt("code"), book, resultSet.getBoolean("issuable"));
		return bookCopy;
	}

	public BookCopy getBookCopyByCode(int code) {
		if (connection == null) {
			return null;
		}

		query = "SELECT books.serial_number, books.name, books.author, book_copies.code, book_copies.issuable\n"
				+ "FROM book_copies JOIN books\n" + "    ON book_copies.serial_number = books.serial_number\n"
				+ "WHERE book_copies.code=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, code);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}

			return parseResultToBookCopy(resultSet);

		} catch (SQLException e) {
			return null;
		}
	}

	public List<BookCopy> getAllBookCopies() {
		if (connection == null) {
			return null;
		}

		query = "SELECT books.serial_number, books.name, books.author, book_copies.code, book_copies.issuable\n"
				+ "FROM book_copies JOIN books\n" + "    ON book_copies.serial_number = books.serial_number";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			List<BookCopy> bookCopies = new ArrayList<BookCopy>();

			while (resultSet.next()) {
				bookCopies.add(parseResultToBookCopy(resultSet));
			}
			return bookCopies;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<BookCopy> getAllBookCopiesByBook(Book book) {
		if (connection == null) {
			return null;
		}
		query = "SELECT books.serial_number, books.name, books.author, book_copies.code, book_copies.issuable\n"
				+ "FROM book_copies JOIN books\n" + "    ON book_copies.serial_number = books.serial_number\n"
				+ "WHERE book_copies.serial_number=?";

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, book.getSerialNumber());
			resultSet = preparedStatement.executeQuery();
			List<BookCopy> bookCopies = new ArrayList<BookCopy>();
			while (resultSet.next()) {
				bookCopies.add(parseResultToBookCopy(resultSet));
			}
			return bookCopies;
		} catch (SQLException e) {
			return null;
		}
	}

	public BookCopy getIssueableBookCopy(Book book) {
		if (connection == null) {
			return null;
		}
		query = "SELECT books.serial_number, books.name, books.author, book_copies.code, book_copies.issuable\n"
				+ "FROM book_copies JOIN books\n" + "    ON book_copies.serial_number = books.serial_number\n"
				+ "WHERE book_copies.serial_number=? AND book_copies.issuable=1 AND\n"
				+ "book_copies.code NOT IN (SELECT book_code FROM issues WHERE status='issued')";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, book.getSerialNumber());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return parseResultToBookCopy(resultSet);
			}
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean updateBookCopy(BookCopy bookCopy) {
		if (connection == null) {
			return false;
		}

		query = "UPDATE book_copies SET issuable=? WHERE code=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, bookCopy.isIssuable());
			preparedStatement.setInt(2, bookCopy.getCode());

			count = preparedStatement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			return false;
		}
	}

}
