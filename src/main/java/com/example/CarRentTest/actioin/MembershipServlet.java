package com.example.CarRentTest.actioin;

import java.io.IOException;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/membership")
public class MembershipServlet extends HttpServlet {

    private JdbcTemplate jdbcTemplate;

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        jdbcTemplate = context.getBean(JdbcTemplate.class);
    }

    // 根據 email 或 phone 查詢會員資料
    private static final String FETCH_MEMBER_BY_EMAIL_SQL = "SELECT name, age, gender, address FROM members WHERE email = ?";
    private static final String FETCH_MEMBER_BY_PHONE_SQL = "SELECT name, age, gender, address FROM members WHERE phone = ?";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 從 session 中獲取 username
        String username = request.getSession().getAttribute("username").toString();

        // 判斷 username 是 email 還是 phone 並查詢資料
        Map<String, Object> member;
        if (username.contains("@")) { // 假設有 '@' 則為 email
            member = jdbcTemplate.queryForMap(FETCH_MEMBER_BY_EMAIL_SQL, username);
        } else { // 否則視為 phone
            member = jdbcTemplate.queryForMap(FETCH_MEMBER_BY_PHONE_SQL, username);
        }

        // 返回會員資料給前端，使用 JSON 格式
        response.setContentType("application/json");
        response.getWriter().write(new ObjectMapper().writeValueAsString(member));
    }
}
