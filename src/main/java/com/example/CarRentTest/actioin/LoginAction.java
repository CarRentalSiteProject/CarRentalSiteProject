package com.example.CarRentTest.actioin;

// 導入必要的類和接口
import java.util.HashMap;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import com.example.CarRentTest.dao.loginMemberDao;
import com.example.CarRentTest.vo.MemberVo;
=======
import com.example.CarRentTest.JWT.JwtService;
import com.example.CarRentTest.dao.LoginRequest;
import com.example.CarRentTest.vo.User;
import com.example.CarRentTest.Service.UserService;
>>>>>>> 220f89ebeab77998460cca10ea8f3b27048f12a5

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

// 標記這是一個控制器
@RestController
@RequestMapping("/api")
public class LoginAction {

    // 創建日誌記錄器，用於記錄控制器的操作
    private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);

    // 注入用戶服務，用於處理用戶相關的業務邏輯
    @Autowired
    private UserService userService;

    // 注入認證管理器，用於處理用戶認證
    @Autowired
    private AuthenticationManager authenticationManager;

    // 注入 JWT 服務，用於生成和驗證 JWT token
    @Autowired
    private JwtService jwtService;

    // 處理 GET 請求 "/login"，返回登錄頁面
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 處理 POST 請求 "/login"，處理用戶登錄
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        logger.info("Received login request for user: {}", loginRequest.getemailOrphone());
        try {
            // 查找用戶
            Optional<User> userOptional = userService.findByEmailOrPhone(loginRequest.getemailOrphone());
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("登入失敗：用戶不存在");
            }
            User user = userOptional.get();

<<<<<<< HEAD
        Map<String, Object> response = new HashMap<>();
        if (memberDao.validateLogin(name, password)) {
        	// 查詢會員資訊
            MemberVo member = memberDao.findByUsername(name);
            response.put("success", true);
            response.put("memberInfo", member);
        } else {
            response.put("success", false);
            response.put("message", "Incorrect username or password");
=======
            // 驗證密碼
            if (!loginRequest.getPassword().equals(user.getPassword())) {
                return ResponseEntity.badRequest().body("登入失敗：密碼不正確");
            }

            // 進行認證
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getemailOrphone(), loginRequest.getPassword())
            );

            // 生成 JWT token
            String token = jwtService.generateToken(user, user.getUsername(),user.getEmailOrPhone());

            //設置JWT token到cookie
            Cookie cookie = new Cookie("jwt", token);
            cookie.setPath("/");
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);

            // 直接從 token 中提取 username
            String username = jwtService.extractUsername(token);

            return ResponseEntity.ok(new AuthResponse(token, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("登入失敗：" + e.getMessage());
>>>>>>> 220f89ebeab77998460cca10ea8f3b27048f12a5
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok().body("登出成功");
    }
    // 處理 GET 請求 "/api/validate-token"，驗證 JWT token
    @GetMapping("/api/validate-token")
    public ResponseEntity<?> validateToken() {
        // 如果請求能到達這裡，說明 JWT 過濾器已經驗證了 token
        return ResponseEntity.ok().build();
    }


    // 內部類，用於返回 JWT token 和用戶名
    private static class AuthResponse {
        private String token;
        private String username;

        public AuthResponse(String token, String username) {
            this.token = token;
            this.username = username;
        }

        public String getToken() {
            return token;
        }

        public String getUsername() {
            return username;
        }
    }
}

