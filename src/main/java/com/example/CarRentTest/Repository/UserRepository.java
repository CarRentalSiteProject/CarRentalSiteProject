package com.example.CarRentTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CarRentTest.vo.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    User findByEmail(String email);
    User findByPhone(String phone);
}
