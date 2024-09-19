package com.example.CarRentTest.actioin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@RestController
@RequestMapping("car")
public class CarAction {
	
	@Autowired
    private EntityManager entityManager;

    @PostMapping("queryPage")
    public Map<String, Object> queryPage(@RequestBody Map<String, Object> requestParams) {
        int page = (int) requestParams.getOrDefault("page", 0);
        int size = (int) requestParams.getOrDefault("size", 9);
        String chplace = (String) requestParams.get("chplace");
        String chdate = (String) requestParams.get("chdate");
        String redate = (String) requestParams.get("redate");
        String sortBy = (String) requestParams.getOrDefault("sortBy", "price");
        String direction = (String) requestParams.getOrDefault("direction", "desc");
        
        List<String> allowedSortByFields = Arrays.asList("price", "brand", "passengers");
        if (!allowedSortByFields.contains(sortBy)) {
            sortBy = "price"; // default sorting field
        }
        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            direction = "desc"; // default direction
        }

        // SQL query
        String sqlStr = "SELECT * FROM car WHERE C_Location LIKE :chplace AND Car_Status = 'unuse' AND (Date IS NULL OR Date BETWEEN :chdate AND :redate)"
        		+ "ORDER BY " + sortBy + " " + direction;

        // Pagination
        int offset = page * size;

        // Create query and set parameters
        Query query = entityManager.createNativeQuery(sqlStr, Car.class);
        query.setParameter("chplace", "%" + chplace + "%");
        query.setParameter("chdate", chdate);
        query.setParameter("redate", redate);

        // Apply pagination
        query.setFirstResult(offset);
        query.setMaxResults(size);

        // Execute query
        List<Car> cars = query.getResultList();
        
        // Modified count query to get the total number of items
        String countSqlStr = "SELECT COUNT(*) FROM car WHERE C_Location LIKE :chplace AND Car_Status = 'unuse' AND (Date IS NULL OR Date BETWEEN :chdate AND :redate)";
        Query countQuery = entityManager.createNativeQuery(countSqlStr);
        countQuery.setParameter("chplace", "%" + chplace + "%");
        countQuery.setParameter("chdate", chdate);
        countQuery.setParameter("redate", redate);

        // Retrieve the total number of items
        Long totalItems = ((Number) countQuery.getSingleResult()).longValue();
        int totalPages = (int) Math.ceil((double) totalItems / size);

        // Prepare response
        Map<String, Object> rs = new HashMap<>();
        rs.put("success", true);
        rs.put("cars", cars);
        rs.put("currentPage", page);
        rs.put("totalItems", totalItems);
        rs.put("totalPages", totalPages);

        return rs;
    }

}
