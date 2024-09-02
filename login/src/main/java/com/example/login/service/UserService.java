package com.example.login.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.login.model.User;
import com.example.login.repository.UserRepository;

@Service
public class UserService {
     // 自動注入 UserRepository 的實例
     @Autowired
     private UserRepository userRepository;

     @Autowired
    private PasswordEncoder passwordEncoder;
 
     // 註冊新用戶的方法
     public User registerUser(String username, String password) {
         // 檢查用戶名是否已存在
         if (userRepository.findByUsername(username) != null) {
             // 如果用戶名已存在，拋出運行時異常
             throw new RuntimeException("用戶名已存在");
         }
         // 創建新的 User 實例
         String encodedPassword = passwordEncoder.encode(password);
         User newUser = new User(username, encodedPassword, true);
    return userRepository.save(newUser);
     }

     public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            user.setLoggedIn(true);
            return userRepository.save(user);
        }
        return null;
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public void logoutUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            user.setLoggedIn(false);
            userRepository.save(user);
        }
    }
}
