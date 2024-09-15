package com.example.CarRentTest.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.CarRentTest.vo.MemberVo;


@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	
	@Override
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setStatus(HttpServletResponse.SC_OK);
	}


	
    private static final long serialVersionUID = 1L;

    private static final String INSERT_MEMBER_SQL = "INSERT INTO members (age, gender, name, email, licenseNub, address, phone , password) VALUES (?, ?, ?, ?, ?, ?, ? , ?)";

    private JdbcTemplate jdbcTemplate;

    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        jdbcTemplate = context.getBean(JdbcTemplate.class);
    }

    public void addMember(MemberVo member) {
        jdbcTemplate.update(INSERT_MEMBER_SQL, 
            member.getAge(),
            member.getGender(),
            member.getName(),
            member.getEmail(),
            member.getLicenseNub(),
            member.getAddress(),
            member.getPhone(),
            member.getPassword()
        );
    }
    
    private static final String CHECK_EMAIL_PHONE_SQL = "SELECT COUNT(*) FROM members WHERE email = ? OR phone = ?";

    public boolean isEmailOrPhoneExists(String email, String phone) {
        Integer count = jdbcTemplate.queryForObject(CHECK_EMAIL_PHONE_SQL, new Object[]{email, phone}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type");
	    response.setHeader("Access-Control-Allow-Credentials", "true");
    	
    	try {
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String licenseNub = request.getParameter("licenseNub");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            
         // 檢查 Email 或 Phone 是否已存在
            if (isEmailOrPhoneExists(email, phone)) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write("{\"error\":\"您已註冊帳號\"}");
                return;
            }

            MemberVo member = new MemberVo();
            member.setAge(age);
            member.setGender(gender);
            member.setName(name);
            member.setEmail(email);
            member.setLicenseNub(licenseNub);
            member.setAddress(address);
            member.setPhone(phone);
            member.setPassword(password);

            addMember(member);

            // 成功後返回狀態碼 200 並回傳 JSON 格式的響應
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Signup successful\"}");
        } catch (Exception e) {
        	e.printStackTrace();
            // 發生錯誤時返回狀態碼 400 並回傳錯誤訊息
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"Invalid input\"}");
        }
    }

}


