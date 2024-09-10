package com.example.CarRentTest.actioin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRentTest.dao.loginMemberDao;
import com.example.CarRentTest.vo.MemberVo;

@RestController
@RequestMapping("/api")
public class LoginAction {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private loginMemberDao memberDao;
	
	@GetMapping("testdb")
	public String testdb() {
		System.out.println("SQLConnect...");
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			// Perform database operations
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "Logintest";
	}
	
	@GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("mes", "Login");
        return "Logintest";
    }

	
//	@PostMapping("/login")
//    public String checkIDPaswd(
//            @RequestParam(value = "username", required = false) String username,
//            @RequestParam(value = "password", required = false) String password,
//            Model model) {
//        System.out.println("checkIDPaswd...");
//        System.out.println("username: " + username);
//        System.out.println("password: " + password);
//
//        if (memberDao.validateLogin(username, password)) {
//            model.addAttribute("username", username);
//            return "index";
//        } else {
//            model.addAttribute("mes", "帳號密碼錯誤");
//            return "Login";
//        }
//    }
	
	@PostMapping("/login")
    public Map<String, Object> checkIDPaswd(
            @RequestBody Map<String, String> credentials) {
        String name = credentials.get("name");
        String password = credentials.get("password");

        Map<String, Object> response = new HashMap<>();
        if (memberDao.validateLogin(name, password)) {
        	// 查詢會員資訊
            MemberVo member = memberDao.findByUsername(name);
            response.put("success", true);
            response.put("memberInfo", member);
        } else {
            response.put("success", false);
            response.put("message", "Incorrect username or password");
        }
        return response;
    }

}
