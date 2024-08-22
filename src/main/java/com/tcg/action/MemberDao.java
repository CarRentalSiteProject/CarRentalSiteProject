package com.tcg.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import vo.MemberVo;

public class MemberDao {
    private String jdbcURL = "jdbc:mysql://localhost:3306/car";
    private String jdbcUsername = "sharon";
    private String jdbcPassword = "f_x/Csf7.wzewz]/";

    private static final String INSERT_MEMBER_SQL = "INSERT INTO members (age, gender, name, email, licenseNub, address, phone , password) VALUES (?, ?, ?, ?, ?, ?, ? , ?)";

    protected Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void addMember(MemberVo member) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MEMBER_SQL)) {
            // 不設置 memberID
            preparedStatement.setInt(1, member.getAge());
            preparedStatement.setString(2, member.getGender());
            preparedStatement.setString(3, member.getName());
            preparedStatement.setString(4, member.getEmail());
            preparedStatement.setString(5, member.getLicenseNub());
            preparedStatement.setString(6, member.getAddress());
            preparedStatement.setString(7, member.getPhone());
            preparedStatement.setString(8, member.getPassword());
            //preparedStatement.setBoolean(8, member.getLogin());
            
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
