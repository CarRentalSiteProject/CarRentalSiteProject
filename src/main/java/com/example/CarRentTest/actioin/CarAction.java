package com.example.CarRentTest.actioin;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRentTest.dao.*;
import com.example.CarRentTest.entity.*;

@RestController
@RequestMapping("car")
public class CarAction {
	
	@Autowired
	CarDao carDao;
	
    @GetMapping("insert")
    public Map insert() {
        Map rs = new HashMap();
        Car car = new Car();
        car.setCarType("Hyundai");
        car.setPrice(2010);
        car.setPeopleNub(5);
        car.setCar_Status("unuse");
        car.setC_Location("Taipei");
        car = carDao.save(car);
        rs.put("success", true);
        rs.put("id", car.getCarID());
        
        return rs;
    }
}
