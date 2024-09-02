package com.example.CarRentTest.reactcontro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CarRentTest.vo.CarVo;

@RestController
@RequestMapping("/carrent")
public class MyController {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
    
	@GetMapping("/test_api")
    public String testApi() {
        return "API連接成功";
    }

    @PostMapping("/searchPlace")
    public List<Map<String, Object>> searchPlace(@RequestBody Map<String, String> request) {
        String chplace = request.get("chplace");
        String chdate = request.get("chdate");
        String redate = request.get("redate");

        String sqlStr = "SELECT * FROM car WHERE C_Location LIKE ? AND Car_Status LIKE 'unuse' AND Date IS NULL";
        return jdbcTemplate.queryForList(sqlStr, chplace);
    }
    
    @PostMapping("/order2")//方法1
    public Map<String, Object> processOrder2(@RequestBody Map<String, String> request) {
        Integer selectedCarId = Integer.parseInt(request.get("selectedCar"));
        String chdate = request.get("chdate");
        String redate = request.get("redate");

        // 使用 SQL 查詢選擇的車輛數據
        String sql = "SELECT * FROM car WHERE CarID = ?";
        Map<String, Object> selectedCar = jdbcTemplate.queryForMap(sql, selectedCarId);

        // 返回查詢結果和日期
        Map<String, Object> response = new HashMap<>();
        response.put("selectedCar", selectedCar);
        response.put("chdate", chdate);
        response.put("redate", redate);

        return response; // 直接返回結果，作為 JSON 響應
    }
    /*
    @PostMapping("/order2")//方法2
    public ResponseEntity<Map<String, Object>> processOrder2(@RequestBody Map<String, String> request) {
        String selectedCarId = request.get("selectedCar");
        String chdate = request.get("chdate");
        String redate = request.get("redate");

        String sql = "SELECT * FROM car WHERE CarID = ?";
        CarVo selectedCar = jdbcTemplate.queryForObject(sql, new Object[]{selectedCarId},
                new BeanPropertyRowMapper<>(CarVo.class));

        Map<String, Object> response = new HashMap<>();
        response.put("selectedCar", selectedCar);
        response.put("chdate", chdate);
        response.put("redate", redate);

        return ResponseEntity.ok(response);
    }
    */
 // 你可以在这里添加更多的API
}
