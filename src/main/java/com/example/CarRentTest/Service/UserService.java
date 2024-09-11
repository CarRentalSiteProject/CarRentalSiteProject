package com.example.CarRentTest.Service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CarRentTest.Repository.UserRepository;
import com.example.CarRentTest.vo.User;
@Service
public class UserService {
     // 自動注入 UserRepository 的實例
     @Autowired
     private UserRepository userRepository;

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
       if(user == null){
        user = userRepository.findByUsername(emailOrphone);
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

