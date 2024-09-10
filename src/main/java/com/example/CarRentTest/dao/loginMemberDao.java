package com.example.CarRentTest.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.CarRentTest.vo.MemberVo;

@Repository
public class loginMemberDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean validateLogin(String username, String password) {
         String sql = "SELECT COUNT(*) FROM members WHERE (email = ? OR phone = ?) AND Password = ?";
        //String sql = "SELECT COUNT(*) FROM members WHERE name = ? AND password = ?";
        
        // 使用 JdbcTemplate 进行查询并获取匹配的行数
        //int count = jdbcTemplate.queryForObject(sql, new Object[]{username, password}, Integer.class);

     // 確保為所有佔位符提供正確的參數
        int count = jdbcTemplate.queryForObject(sql, new Object[]{username, username, password}, Integer.class);
        
        // 如果返回的 count 大于 0，说明有匹配的用户
        return count > 0;
    }
    
    //登入後生成資料json檔供membership使用
    public MemberVo findByUsername(String username) {
        String sql = "SELECT name, age, gender, address FROM members WHERE email = ? OR phone = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{username, username}, new MemberVoRowMapper());
    }
    
    public class MemberVoRowMapper implements RowMapper<MemberVo> {
        @Override
        public MemberVo mapRow(ResultSet rs, int rowNum) throws SQLException {
            MemberVo member = new MemberVo();
            member.setName(rs.getString("name"));
            member.setAge(rs.getInt("age"));
            member.setGender(rs.getString("gender"));
            member.setAddress(rs.getString("address"));
            return member;
        }
    }


}
