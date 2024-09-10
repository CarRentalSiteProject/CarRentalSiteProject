package com.example.CarRentTest.Service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.CarRentTest.vo.User;
import com.example.CarRentTest.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrphoneOrusername) throws UsernameNotFoundException {
        logger.info("嘗試加載用戶：" + emailOrphoneOrusername);
        User user = userRepository.findByEmail(emailOrphoneOrusername);
        
        if (user == null) {
            logger.info("通過電子郵件未找到用戶，嘗試通過電話號碼查找");
            user = userRepository.findByPhone(emailOrphoneOrusername);
        }
        if (user == null) {
            user = userRepository.findByUsername(emailOrphoneOrusername);
        }
        if(user == null){
            logger.warn("用戶不存在：" + emailOrphoneOrusername);
            throw new UsernameNotFoundException("用戶不存在：" + emailOrphoneOrusername);
        }
        
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password("{noop}" + user.getPassword())
                .build();
    }
}
