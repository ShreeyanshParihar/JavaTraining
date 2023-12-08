package com.seclore.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.seclore.pojo.BookCopy;
import com.seclore.pojo.BookIssue;
import com.seclore.pojo.Member;

public class BookIssueDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private int count;
	private ResultSet resultSet;
	private String query;

	public BookIssueDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean addBookIssue(BookCopy bookCopy, Member member) {
		if (connection == null) {
			return false;
		}

		try {
			// Check if book is issuable
			if (!bookCopy.isIssuable()) {
				System.out.println("Not issuable");
				return false;
			}

			// Check if book is not already issued
			query = "SELECT * FROM issues WHERE status='returned' AND book_code=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bookCopy.getCode());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println("Already issued");
				return false;
			}

			// Check if person hasn't crossed issueing limit
			query = "SELECT COUNT(*) FROM issues WHERE status='issued' AND member_code=?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, member.getCode());
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			count = resultSet.getInt(1);
			if (count >= member.getIssueLimit()) {
				System.out.println("Exceeded issue limit");
				return false;
			}

			LocalDate today = LocalDate.now();

			// Insert into issues
			query = "INSERT INTO issues (member_code, book_code, date_of_issue, date_of_return, status) VALUES (?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, member.getCode());
			preparedStatement.setInt(2, bookCopy.getCode());
			preparedStatement.setDate(3, Date.valueOf(today));
			preparedStatement.setDate(4, Date.valueOf(today.plusDays(member.getIssuePeriod())));
			preparedStatement.setString(5, "issued");
			count = preparedStatement.executeUpdate();

			if (count <= 0) {
				return false;
			}

			return true;

		} catch (SQLException e) {
			return false;
		}
	}

	public BookIssue parseResultToBookIssue(ResultSet resultSet) throws SQLException {
		BookCopy bookCopy = (new BookCopyDAO(connection)).getBookCopyByCode(resultSet.getInt("book_code"));
		Member member = (new MemberDAO(connection)).getMemberByCode(resultSet.getInt("member_code"));
		return new BookIssue(resultSet.getInt("code"), bookCopy, member, resultSet.getDate("date_of_issue"),
				resultSet.getDate("date_of_return"), resultSet.getString("status"));
	}

	public List<BookIssue> getBookIssuesByMember(Member member) {
		if (connection == null) {
			return null;
		}

		query = "SELECT * FROM issues where member_code=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, member.getCode());
			resultSet = preparedStatement.executeQuery();

			List<BookIssue> bookCopies = new ArrayList<BookIssue>();

			while (resultSet.next()) {
				bookCopies.add(parseResultToBookIssue(resultSet));
			}
			return bookCopies;
		} catch (SQLException e) {
			return null;
		}
	}

	public BookIssue getBookIssueByCode(int code) {
		if (connection == null) {
			return null;
		}
		query = "SELECT * FROM issues WHERE code=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, code);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return parseResultToBookIssue(resultSet);
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean returnIssuedBook(BookIssue bookIssue) {
		if (connection == null) {
			return false;
		}

		query = "UPDATE issues SET status='returned' WHERE code=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, bookIssue.getCode());

			count = preparedStatement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	public List<BookIssue> getBookIssuesByStatus(String status) {
		if (connection == null) {
			return null;
		}
		query = "SELECT * FROM issues WHERE status=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, status);
			resultSet = preparedStatement.executeQuery();
			List<BookIssue> bookIssues = new ArrayList<BookIssue>();
			while (resultSet.next()) {
				bookIssues.add(parseResultToBookIssue(resultSet));
			}
			return bookIssues;
		} catch (SQLException e) {
			return null;
		}
	}
}
