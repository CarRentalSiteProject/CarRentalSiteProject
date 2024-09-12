package com.example.CarRentTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarRentTest.entity.*;

@Repository
public interface CarDao extends JpaRepository<Car, Long>{
}
