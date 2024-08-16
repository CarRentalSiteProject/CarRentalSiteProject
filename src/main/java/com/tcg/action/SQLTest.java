package com.tcg.action;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLTest {
	public static Connection getConnection() {
		Connection connect = null;
		try {
			//load MySQL JDBC Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/member", "root", "382466");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connect;
	}
}
