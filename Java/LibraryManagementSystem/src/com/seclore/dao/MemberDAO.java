package com.seclore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seclore.pojo.Member;

public class MemberDAO {
	private Connection connection;
	private PreparedStatement preparedStatement;
	private int count;
	private ResultSet resultSet;
	private String query;

	public MemberDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean addNewMember(Member member) {
		if (connection == null) {
			return false;
		}

		query = "INSERT INTO members ([name], [type]) VALUES (?, ?); SELECT SCOPE_IDENTITY();";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, member.getName());
			preparedStatement.setString(2, member.getType());

			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return false;
			}
			member.setCode(resultSet.getInt(1));
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return false;
		}
	}

	public Member getMemberByCode(int memberCode) {
		if (connection == null) {
			return null;
		}

		query = "SELECT * FROM members WHERE code=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, memberCode);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				return null;
			}

			Member member = new Member(resultSet.getInt("code"), resultSet.getString("name"),
					resultSet.getString("type"));
			return member;

		} catch (SQLException e) {
			return null;
		}
	}

	public List<Member> getAllMembers() {
		if (connection == null) {
			return null;
		}

		query = "SELECT * FROM members";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			List<Member> members = new ArrayList<Member>();

			while (resultSet.next()) {
				members.add(
						new Member(resultSet.getInt("code"), resultSet.getString("name"), resultSet.getString("type")));
			}
			return members;
		} catch (SQLException e) {
			return null;
		}
	}

	public boolean updateMember(Member member) {
		if (connection == null) {
			return false;
		}

		query = "UPDATE members SET name=? WHERE code=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, member.getName());
			preparedStatement.setInt(2, member.getCode());

			count = preparedStatement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean deleteMember(Member member) {
		if (connection == null) {
			return false;
		}

		query = "DELETE FROM members WHERE code=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, member.getCode());

			count = preparedStatement.executeUpdate();
			return count > 0;
		} catch (SQLException e) {
			return false;
		}
	}
}
