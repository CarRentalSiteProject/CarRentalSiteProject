package com.tcg.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import vo.CarVo;

@WebServlet("/searchCar")
public class CarController extends HttpServlet {
    private TestConn testConn;

    @Override
    public void init() throws ServletException {
        AppConfig appConfig = new AppConfig();
        this.testConn = new TestConn(appConfig);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carType = request.getParameter("forcar");

        try {
            List<CarVo> cars = testConn.getCarByName(carType);
            request.setAttribute("cars", cars);
            request.getRequestDispatcher("respCar.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 始終使用 doGet 來處理 POST 請求
        doGet(request, response);
    }
}