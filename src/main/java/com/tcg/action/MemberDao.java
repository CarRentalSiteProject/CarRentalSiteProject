package com.tcg.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MemberDao {

	void getMemberByID(int mbID) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connect = SQLConnection.getConnection();
			preparedStatement = connect.prepareStatement("select * from member where getMemberByID=?");
			// 從1開始
			preparedStatement.setInt(1, mbID);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getString(3);
				int id = resultSet.getInt("MemberID");
				String name = resultSet.getString("Username");
				String password = resultSet.getString("Password");

				System.out.println("ID: " + id);
				System.out.println("Name: " + name);
				System.out.println("Name: " + password);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connect != null) {
					connect.close();
				}
			} catch (SQLException e) {

			}
		}
	}

	public boolean validateLogin(String username, String password) {
		Connection connect = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean isValid = false;
		try {
			connect = SQLConnection.getConnection();
			preparedStatement = connect.prepareStatement("select * from member where Username=? and Password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				isValid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connect != null) {
					connect.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return isValid;
	}
}

//	Map<String, String> data = new HashMap<>();
//	public MemberDao() {
//		data.put("tom", "1234");
//		data.put("bob", "5678");
//		data.put("mary", "2234");
//	}
//	public String get(String key) {
//		return data.get(key);
//	}
