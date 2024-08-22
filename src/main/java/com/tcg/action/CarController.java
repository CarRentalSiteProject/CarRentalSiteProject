package com.tcg.action;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.CarVo;

@Controller
public class CarController {
    @Autowired
    private CarDAO carDAO;
    
    @GetMapping("/intputanything")
    public String intpage() {
    	return "cart";
    }

    @GetMapping("/x")
    public String showCarsPage(Model model) {
        List<CarVo> cars = carDAO.getAllCars();
        model.addAttribute("cars", cars);
        return "cars"; 
    }
    @PostMapping("/carcart")
    public String getCarByName(
            @RequestParam("forcar") String carType,
            Model m) throws SQLException {
        
        // 調用服務層方法來查詢車型
        List<CarVo> car = carDAO.getCarByName(carType);
        
        // 將結果添加到模型中
        m.addAttribute("carList", car);
        
        // 返回 JSP 頁面名稱
        return "respCar";
    }

}