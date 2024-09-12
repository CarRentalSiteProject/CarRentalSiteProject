package com.example.CarRentTest.actioin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRentTest.dao.*;
import com.example.CarRentTest.entity.*;
import com.example.CarRentTest.vo.CarVo;

@RestController
@RequestMapping("car")
public class CarAction {
	
	@Autowired
	CarDao carDao;

    @PostMapping("queryPage")
    public Map<String, Object> queryPage(@RequestBody Map<String, Object> requestParams) {
        int page = (int) requestParams.getOrDefault("page", 0); // Default to page 0
        int size = (int) requestParams.getOrDefault("size", 9); // Default page size of 9

        Map<String, Object> rs = new HashMap<>();
        
        Page<Car> pageResult = carDao.findAll(
                PageRequest.of(page, size, Sort.by("price").descending())); // Sort by price, descending
         
        rs.put("success", true);
        rs.put("cars", pageResult.getContent()); // Return the car data
        rs.put("currentPage", pageResult.getNumber()); // Current page number
        rs.put("totalItems", pageResult.getTotalElements()); // Total items
        rs.put("totalPages", pageResult.getTotalPages()); // Total pages
        return rs;
    }
}
