package com.tcg.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vo.CarVo;

@Repository
public class CarDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CarVo> getAllCars() {
        String sql = "SELECT * FROM Car";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CarVo.class));
    }
    public List<CarVo> getCarByName(String carType) throws SQLException{
    		String sqlStr = "select * from car where CarType like ? and Car_Status like 'unuse';";
    		return jdbcTemplate.query(sqlStr,
    		        new Object[] { "%" + carType + "%" }, new BeanPropertyRowMapper<>(CarVo.class));
    }
}