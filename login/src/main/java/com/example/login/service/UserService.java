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
     public User registerUser(String username, String password,  int age, String gender, String email, String licenseNub, String address, String phone) {
         // 檢查用戶名是否已存在
         if (userRepository.findByUsername(username) != null) {
             throw new RuntimeException("用戶名已存在");
         }
         // 創建新的 User 實例
         String encodedPassword = passwordEncoder.encode(password);
         User newUser = new User(username, encodedPassword,  age , gender , email , licenseNub , address , phone);
    return userRepository.save(newUser);
     }

     public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return userRepository.save(user);
        }
        return null;
    }

    //利用username作為登入帳號
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    //利用emailOrphone作為登入帳號
    public Optional<User> findByEmailOrPhone(String emailOrphone) {
       User user = userRepository.findByEmail(emailOrphone);
       if(user == null){
        user = userRepository.findByPhone(emailOrphone);
       }
       return Optional.ofNullable(user);
    }

    public void logoutUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.save(user);
        }
    }
}
