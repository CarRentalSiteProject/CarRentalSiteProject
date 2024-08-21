package com.tcg.action;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;


import vo.CarVo;
public class TestConn {
	private AppConfig appconfig;
    public  TestConn(AppConfig appConfig) {
	
        this.appconfig = appConfig;
    }
    public List<CarVo> getCarByName(String carType) throws SQLException{
        String sqlStr = "select * from car where CarType like ? and Car_Status like 'unuse';";
        List<CarVo> cars = new ArrayList<>();
            try(Connection conn = appconfig.getConnection()){
            	PreparedStatement st = conn.prepareStatement(sqlStr);
                st.setString(1,"%" + carType + "%"); // '%' + A '%' 包括 A 的
                ResultSet rs = st.executeQuery();
                while (rs.next()){                
                	cars.add(
                        new CarVo(
                        rs.getInt("CarID"),
                        rs.getString("CarType"),
                        rs.getInt("Price"),
                        rs.getDate("Date"),
                        rs.getInt("PeopleNub"),
                        rs.getString("Car_Status"),
                        rs.getString("C_Location")
                        )
                    );
                }
        }catch (SQLException e) {
			e.printStackTrace();
		}
            return cars;
    }
}
