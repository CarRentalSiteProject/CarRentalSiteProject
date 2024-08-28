package com.example.login.controller;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.JWT.JwtService;
import com.example.login.model.LoginRequest;
import com.example.login.model.User;
import com.example.login.service.UserService;

// 標記這是一個控制器
@Controller
public class SignupController {

    // 創建日誌記錄器，用於記錄控制器的操作
    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    // 注入用戶服務，用於處理用戶相關的業務邏輯
    @Autowired
    private UserService userService;

    // 注入認證管理器，用於處理用戶認證
    @Autowired
    private AuthenticationManager authenticationManager;

    // 注入密碼編碼器，用於加密和驗證密碼
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 注入 JWT 服務，用於生成和驗證 JWT token
    @Autowired
    private JwtService jwtService;

    // 處理 GET 請求 "/signup"，返回註冊頁面
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    // 處理 POST 請求 "/signup"，處理用戶註冊
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User signupRequest) {
        try {
            // 註冊新用戶
            User user = userService.registerUser(signupRequest.getUsername(), signupRequest.getPassword());
            // 生成 JWT token
            String token = jwtService.generateToken(new HashMap<>(), user);
            return ResponseEntity.ok(new AuthResponse(token, user.getUsername()));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 處理 GET 請求 "/login"，返回登錄頁面
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // 處理 POST 請求 "/login"，處理用戶登錄
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 查找用戶
            Optional<User> userOptional = userService.findByUsername(loginRequest.getUsername());
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("登入失敗：用戶不存在");
            }
            User user = userOptional.get();

            // 驗證密碼
            boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            if (!passwordMatch) {
                return ResponseEntity.badRequest().body("登入失敗：密碼不正確");
            }

            // 進行認證
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // 生成 JWT token
            String token = jwtService.generateToken(new HashMap<>(), user);
            return ResponseEntity.ok(new AuthResponse(token, user.getUsername()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("登入失敗：" + e.getMessage());
        }
    }

    // 處理 GET 請求 "/index"，返回首頁
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // 處理 GET 請求 "/menu"，返回菜單頁面
    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    // 處理 GET 請求 "/api/validate-token"，驗證 JWT token
    @GetMapping("/api/validate-token")
    public ResponseEntity<?> validateToken() {
        // 如果請求能到達這裡，說明 JWT 過濾器已經驗證了 token
        return ResponseEntity.ok().build();
    }

    // 處理 GET 請求 "/test"，返回測試頁面
    @GetMapping("/test")
    public String test() {
        return "test";
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


