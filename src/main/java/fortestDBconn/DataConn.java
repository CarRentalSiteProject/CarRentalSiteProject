package fortestDBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CarVo;

public class DataConn {
	private DatabaseConnection databaseConnection;
	public DataConn(DatabaseConnection databaseConnection) {
		this.databaseConnection=databaseConnection;
	}
	public List<CarVo> getCarByName(String carType) throws SQLException{
        String sqlStr = "select * from car where CarType like ? and Car_Status like 'unuse';";
        List<CarVo> cars = new ArrayList<>();
            try(Connection conn = databaseConnection.getConnection()){
            	PreparedStatement st = conn.prepareStatement(sqlStr);
                st.setString(1,"%" + carType + "%"); // '%' + A '%' 包括 A 的
                ResultSet rs = st.executeQuery(sqlStr);
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
        	System.out.println("查詢錯誤DCN" + e.getMessage() );
			e.printStackTrace();
		}
            return cars;
    }
}
