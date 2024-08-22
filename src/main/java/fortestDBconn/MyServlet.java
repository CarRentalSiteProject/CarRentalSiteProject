package fortestDBconn;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.CarVo;
@WebServlet("/myServlet")
public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (
            Connection conn = DBC.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM car")) {

           while (rs.next()) {
               CarVo vo = new CarVo();
               vo.setCarID(rs.getInt("CarID"));
               vo.setCarType(rs.getString("CarType"));
               vo.setPrice(rs.getInt("Price"));
               vo.setDate(rs.getDate("Date"));
               vo.setPeopleNub(rs.getInt("PeopleNub"));
               vo.setCar_Status(rs.getString("Car_Status"));
               vo.setC_Location(rs.getString("C_Location"));
			   conn.close();
           }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Database connection problem.", e);
        }request.getRequestDispatcher("/getServertest.jsp").forward(request, response);
     }
}