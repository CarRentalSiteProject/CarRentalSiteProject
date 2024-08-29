package com.example.CarRentTest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class loginMemberDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validateLogin(String username, String password) {
        String sql = "SELECT COUNT(*) FROM member WHERE Username = ? AND Password = ?";
        
        // 使用 JdbcTemplate 进行查询并获取匹配的行数
        int count = jdbcTemplate.queryForObject(sql, new Object[]{username, password}, Integer.class);

        // 如果返回的 count 大于 0，说明有匹配的用户
        return count > 0;
    }
}
