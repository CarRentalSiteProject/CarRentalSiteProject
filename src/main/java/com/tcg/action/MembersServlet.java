package com.tcg.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vo.MemberVo;

@WebServlet("/member")
public class MembersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws Exception {
        String jdbcURL = "jdbc:mysql://localhost:3306/car";
        String jdbcUsername = "sharon";
        String jdbcPassword = "f_x/Csf7.wzewz]/";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<MemberVo> members = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                MemberVo member = new MemberVo();
                member.setMemberID(resultSet.getInt("memberID"));
                member.setAge(resultSet.getInt("age"));
                member.setGender(resultSet.getString("gender"));
                member.setName(resultSet.getString("name"));
                member.setEmail(resultSet.getString("email"));
                member.setLicenseNub(resultSet.getString("licenseNub"));
                member.setAddress(resultSet.getString("address"));
                member.setPhone(resultSet.getString("phone"));
                member.setLogin(resultSet.getBoolean("login"));
                members.add(member);
            }

            request.setAttribute("members", members);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database connection failed: " + e.getMessage());
        }

        request.getRequestDispatcher("Member.jsp").forward(request, response);
    }

}
