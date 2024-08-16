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
	
	private static final String jdbcurl = "jdbc:mysql://localhost:3306/member";
    private static final String id = "root";
    private static final String paswd = "382466";
    
    public MemberDao() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public String get(String key) {
        String value = null;
        String query = "SELECT password FROM member WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(jdbcurl, id, paswd);
             PreparedStatement statement = connection.prepareStatement(query)) {
            
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    value = resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return value;
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

}
