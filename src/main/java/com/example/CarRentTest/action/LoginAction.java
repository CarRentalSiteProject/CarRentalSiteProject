package com.example.CarRentTest.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRentTest.JWT.JwtService;
import com.example.CarRentTest.dao.LoginRequest;
import com.example.CarRentTest.vo.User;
import com.example.CarRentTest.Service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

@RestController
@RequestMapping("/api")
public class LoginAction {

    private static final Logger logger = LoggerFactory.getLogger(LoginAction.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        logger.info("收到用戶登入請求：{}", loginRequest.getemailOrphone());
        try {
            Optional<User> userOptional = userService.findByEmailOrPhone(loginRequest.getemailOrphone());
            if (userOptional.isEmpty()) {
                return ResponseEntity.badRequest().body("登入失敗：用戶不存在");
            }
            User user = userOptional.get();

            if (!loginRequest.getPassword().equals(user.getPassword())) {
                return ResponseEntity.badRequest().body("登入失敗：密碼不正確");
            }

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getemailOrphone(), loginRequest.getPassword())
            );

            String token = jwtService.generateToken(user, user.getUsername(),user.getEmailOrPhone());

            Cookie cookie = new Cookie("jwt", token);
            cookie.setPath("/");
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);

            String username = jwtService.extractUsername(token);

            return ResponseEntity.ok(new AuthResponse(token, username));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("登入失敗：" + e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "jwt", defaultValue = "") String token, HttpServletResponse response) {
        if (!token.isEmpty()) {
            try {
                String username = jwtService.extractUsername(token);
                Optional<User> userOptional = userService.findByEmailOrPhone(username);

                if (userOptional.isPresent() && jwtService.isTokenValid(token, userOptional.get())) {
                    Cookie cookie = new Cookie("jwt", null);
                    cookie.setMaxAge(0);
                    cookie.setHttpOnly(true);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return ResponseEntity.ok().body("登出成功");
                }
            } catch (Exception e) {
                Cookie cookie = new Cookie("jwt", null);
                cookie.setMaxAge(0);
                cookie.setHttpOnly(true);
                cookie.setPath("/");
                response.addCookie(cookie);
                return ResponseEntity.ok().body("登出成功");
            }
        }
        return ResponseEntity.status(401).body("未登入");
    }

    @GetMapping("/checkLoginStatus")
    public ResponseEntity<?> checkLoginStatus(@CookieValue(value = "jwt", defaultValue = "") String token) {
        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("未登入");
        }

        try {
            String username = jwtService.extractUsername(token);
            Optional<User> userOptional = userService.findByEmailOrPhone(username);

            if (userOptional.isPresent() && jwtService.isTokenValid(token, userOptional.get())) {
                return ResponseEntity.ok().body("已登入");
            } else {
                return ResponseEntity.status(401).body("未登入或登入已過期");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("未登入或登入已過期");
        }
    }

    @GetMapping("/membership")
    public ResponseEntity<?> getMembershipInfo(@RequestHeader(value = "Authorization", defaultValue = "") String authorizationHeader) {
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : "";

        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("未登入");
        }

        try {
            String username = jwtService.extractUsername(token);
            Optional<User> userOptional = userService.findByEmailOrPhone(username);

            if (userOptional.isPresent() && jwtService.isTokenValid(token, userOptional.get())) {
                User user = userOptional.get();
                return ResponseEntity.ok(Map.of(
                    "name", user.getUsername(),
                    "gender", user.getGender(),
                    "address", user.getAddress(),
                    "age", user.getAge()
                ));
            } else {
                return ResponseEntity.status(401).body("未登入或登入已過期");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("未登入或登入已過期");
        }
    }

    @PutMapping("/updateinfo")
    public ResponseEntity<?> updateMembershipInfo(
            @RequestBody User userDto, 
            @RequestHeader("Authorization") String authorizationHeader,
            HttpServletResponse response) {

        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : "";

        if (token.isEmpty()) {
            return ResponseEntity.status(401).body("未登入");
        }

        try {
            String username = jwtService.extractUsername(token);
            Optional<User> userOptional = userService.findByEmailOrPhone(username);

            if (userOptional.isPresent() && jwtService.isTokenValid(token, userOptional.get())) {
                User existingUser = userOptional.get();

                boolean isUsernameUpdated = !existingUser.getUsername().equals(userDto.getUsername());

                updateUserFields(existingUser, userDto);
                userService.save(existingUser);
                
                // 清除舊的 cookie
                Cookie oldCookie = new Cookie("jwt", null);
                oldCookie.setMaxAge(0);
                oldCookie.setHttpOnly(true);
                oldCookie.setPath("/");
                response.addCookie(oldCookie);

                // 生成新的 JWT token
                String newToken = jwtService.generateToken(existingUser, existingUser.getUsername(), existingUser.getEmailOrPhone());
                Cookie newCookie = new Cookie("jwt", newToken);
                newCookie.setPath("/");
                newCookie.setMaxAge(60 * 60); // 1 hour
                response.addCookie(newCookie);

                if (isUsernameUpdated) {
                    return ResponseEntity.ok(Collections.singletonMap("logoutRequired", true));
                } else {
                    return ResponseEntity.ok(Collections.singletonMap("logoutRequired", false));
                }
            } else {
                return ResponseEntity.status(401).body("未登入或登入已過期");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("更新用戶資料時發生錯誤");
        }
    }

    private void updateUserFields(User existingUser, User userDto) {
        if (userDto.getUsername() != null && !userDto.getUsername().isEmpty()) {
            existingUser.setUsername(userDto.getUsername());
        }

        if (userDto.getAge() != null) {
            existingUser.setAge(userDto.getAge());
        }

        if (userDto.getGender() != null) {
            existingUser.setGender(userDto.getGender());
        }

        if (userDto.getAddress() != null) {
            existingUser.setAddress(userDto.getAddress());
        }
    }

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
