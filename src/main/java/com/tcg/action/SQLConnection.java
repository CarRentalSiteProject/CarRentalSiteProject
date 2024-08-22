package com.tcg.action;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
	public static Connection getConnection() {
		Connection connect = null;
		try {
			//load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/member", "Feng", "TEgF.0gwaDsotV-b");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}
}
