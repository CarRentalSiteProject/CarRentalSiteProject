package com.example.CarRentTest.action;

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
import org.springframework.http.ResponseEntity;
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
//
//@RestController
//@RequestMapping("car")
//public class CarAction {
//	
//	@Autowired
//    private EntityManager entityManager;
//
//    @PostMapping("queryPage")
//    public Map<String, Object> queryPage(@RequestBody Map<String, Object> requestParams) {
//        int page = (int) requestParams.getOrDefault("page", 0);
//        int size = (int) requestParams.getOrDefault("size", 9);
//        String chplace = (String) requestParams.get("chplace");
//        String chdate = (String) requestParams.get("chdate");
//        String redate = (String) requestParams.get("redate");
//        String sortBy = (String) requestParams.getOrDefault("sortBy", "sortByPrice");
//        String direction = (String) requestParams.getOrDefault("direction", "desc");
//        String carType = (String) requestParams.get("type");
//        Integer priceMin = requestParams.get("min") != null ? Integer.parseInt(requestParams.get("min").toString()) : null;
//        Integer priceMax = requestParams.get("max") != null ? Integer.parseInt(requestParams.get("max").toString()) : null;
//        Integer peopleNub = requestParams.get("peopleNub") != null ? Integer.parseInt(requestParams.get("peopleNub").toString()) : null;
//
//        List<String> allowedSortByFields = Arrays.asList("sortByPrice", "type", "min", "peopleNub");
//        if (!allowedSortByFields.contains(sortBy)) {
//            sortBy = "sortByPrice"; // default sorting field
//        }
//        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
//            direction = "desc"; // default direction
//        }
//        
//        // SQL query
//        StringBuffer sqlStr = new StringBuffer(
//        		"SELECT * FROM car WHERE C_Location LIKE :chplace AND Car_Status = 'unuse' AND (Date IS NULL OR Date BETWEEN :chdate AND :redate)"
//        		);
//        if (carType != null && !carType.isEmpty()) {
//            sqlStr.append(" AND CarType LIKE :carType");
//        }
//        if (priceMin != null) {
//            sqlStr.append(" AND Price >= :min");
//        }
//        if (priceMax != null) {
//            sqlStr.append(" AND Price <= :max");
//        }
//        if (peopleNub != null) {
//            sqlStr.append(" AND PeopleNub <= :peopleNub");
//        }
//
//        sqlStr.append("ORDER BY ").append(sortBy).append(" ").append(direction);
//
//        // Pagination
//        int offset = page * size;
//
//        // Create query and set parameters
//        Query query = entityManager.createNativeQuery(sqlStr.toString(), Car.class);
//        query.setParameter("chplace", "%" + chplace + "%");
//        query.setParameter("chdate", chdate);
//        query.setParameter("redate", redate);
//
//        if (carType != null && !carType.isEmpty()) {
//            query.setParameter("carType", "%" + carType + "%");
//        }
//        if (priceMin != null) {
//            query.setParameter("min", priceMin);
//        }
//        if (priceMax != null) {
//            query.setParameter("max", priceMax);
//        }
//        if (peopleNub != null) {
//            query.setParameter("peopleNub", peopleNub);
//        }
//
//        // Apply pagination
//        query.setFirstResult(offset);
//        query.setMaxResults(size);
//
//        // Execute query
//        List<Car> cars = query.getResultList();
//        
//        // Modified count query to get the total number of items
//        String countSqlStr = "SELECT COUNT(*) FROM car WHERE C_Location LIKE :chplace AND Car_Status = 'unuse' AND (Date IS NULL OR Date BETWEEN :chdate AND :redate)";
//        Query countQuery = entityManager.createNativeQuery(countSqlStr);
//        countQuery.setParameter("chplace", "%" + chplace + "%");
//        countQuery.setParameter("chdate", chdate);
//        countQuery.setParameter("redate", redate);
//
//        // Retrieve the total number of items
//        Long totalItems = ((Number) countQuery.getSingleResult()).longValue();
//        int totalPages = (int) Math.ceil((double) totalItems / size);
//
//        // Prepare response
//        Map<String, Object> rs = new HashMap<>();
//        rs.put("success", true);
//        rs.put("cars", cars);
//        rs.put("currentPage", page);
//        rs.put("totalItems", totalItems);
//        rs.put("totalPages", totalPages);
//
//        return rs;
//    }
//
//}
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
        String sortBy = (String) requestParams.getOrDefault("sortBy", "Price");
        String direction = (String) requestParams.getOrDefault("direction", "desc");
        String carType = (String) requestParams.get("carType");

        Integer priceMin = -1;
        Integer priceMax = -1;
        Integer peopleNub = -1;
        
        System.out.println("Received params: " + requestParams);
        System.out.println("max: " + requestParams.get("priceMax"));
        System.out.println("type of param: " + requestParams.get("priceMax"));
        
        try {
            if (Integer.parseInt(requestParams.get("priceMin").toString()) >= 0) {
                priceMin = Integer.parseInt(requestParams.get("priceMin").toString());
            }
            if (Integer.parseInt(requestParams.get("priceMax").toString()) >= 0) {
                priceMax = Integer.parseInt(requestParams.get("priceMax").toString());
            }
            if (Integer.parseInt(requestParams.get("peopleNub").toString()) >= 0) {
                peopleNub = Integer.parseInt(requestParams.get("peopleNub").toString());
            }
        } catch (NumberFormatException e) {
            // Handle parsing exception
        	e.printStackTrace();
        }

        List<String> allowedSortByFields = Arrays.asList("price", "carType", "peopleNub");
        if (!allowedSortByFields.contains(sortBy)) {
            sortBy = "Price"; // default sorting field
        }
        if (!direction.equalsIgnoreCase("asc") && !direction.equalsIgnoreCase("desc")) {
            direction = "desc"; // default direction
        }

        // Build SQL query
        StringBuilder sqlStr = new StringBuilder(
            "SELECT *"
        );
        StringBuilder str = new StringBuilder(
        	" FROM car WHERE c_location LIKE :chplace AND car_status = 'unuse' AND (date IS NULL OR date BETWEEN :chdate AND :redate)"
        );
        if (carType != "null" && !carType.isEmpty()) {
            str.append(" AND CarType LIKE :carType");
        }
        if (priceMin >= 0) {
        	str.append(" AND Price >= :priceMin");
        }
        if (priceMax >= 0) {
        	str.append(" AND Price <= :priceMax");
        }
        if (peopleNub >= 0) {
        	str.append(" AND PeopleNub <= :peopleNub");
        }
        sqlStr.append(str);
        sqlStr.append(" ORDER BY ").append(sortBy).append(" ").append(direction);

        // Pagination
        int offset = page * size;

        // Create query and set parameters
        Query query = entityManager.createNativeQuery(sqlStr.toString(), Car.class);
        query.setParameter("chplace", "%" + chplace + "%");
        query.setParameter("chdate", chdate);
        query.setParameter("redate", redate);

        if (carType != "null" && !carType.isEmpty()) {
            query.setParameter("carType", "%" + carType + "%");
        }
        if (priceMin >= 0) {
            query.setParameter("priceMin", priceMin);
        }
        if (priceMax >= 0) {
            query.setParameter("priceMax", priceMax);
        }
        if (peopleNub >= 0) {
            query.setParameter("peopleNub", peopleNub);
        }

        // Apply pagination
        query.setFirstResult(offset);
        query.setMaxResults(size);

        // Execute query
        List<Car> cars = query.getResultList();

        // Total count query
        StringBuilder countSqlStr = new StringBuilder(
            "SELECT COUNT(*)"
        );
        countSqlStr.append(str);

        Query countQuery = entityManager.createNativeQuery(countSqlStr.toString());
        countQuery.setParameter("chplace", "%" + chplace + "%");
        countQuery.setParameter("chdate", chdate);
        countQuery.setParameter("redate", redate);

        if (carType != "null" && !carType.isEmpty()) {
            countQuery.setParameter("carType", "%" + carType + "%");
        }
        if (priceMin >= 0) {
            countQuery.setParameter("priceMin", priceMin);
        }
        if (priceMax >= 0) {
            countQuery.setParameter("priceMax", priceMax);
        }
        if (peopleNub >= 0) {
            countQuery.setParameter("peopleNub", peopleNub);
        }
        
        System.out.println("sql: " + sqlStr + "\ncnt: " + countSqlStr);

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
