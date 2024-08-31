package com.example.CarRentTest.actioin;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CarRentTest.vo.CarVo;

@Controller
public class WebAction {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//連接首頁輸入日期與地點
	@GetMapping("loc_day")
	public String loc_day() {
		return "LoC_Day";
	}
	//測試金流連接用
	@GetMapping("cashtest")
	public String cashtest() {
		return "forcashtest";
	}
	//依地點與時間為NULL(目前沒訂單)篩選
	@PostMapping("searchPlace")
	public String searchPlace(@RequestParam("chplace") String chplace, 
            @RequestParam("chdate") String chdate, @RequestParam("redate") String redate,
            Model model) {
		String sqlStr = "select * from car where C_Location like ? And Car_Status like 'unuse' And Date IS NULL;";
		List<Map<String, Object>> dataList = jdbcTemplate.queryForList(sqlStr,chplace);
		model.addAttribute("cars", dataList);
	    model.addAttribute("chdate", chdate);
	    model.addAttribute("redate", redate);
	    
		return "menu";
	}
	//依據地點與時間為(NULL OR 前訂單加3個月)
	@PostMapping("searchPlace2")
	public String searchPlace2(@RequestParam("chplace") String chplace, 
            @RequestParam("chdate") String chdate, 
            Model model) {
		LocalDate selectedDate = LocalDate.parse(chdate);
	    LocalDate minDate = selectedDate.minusMonths(3);
	    String minDateStr = minDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String sqlStr = "select * from car where C_Location like ? And Car_Status like 'unuse' And (Date >= ? OR Date IS NULL);";
		List<Map<String, Object>> dataList = jdbcTemplate.queryForList(sqlStr,chplace,minDateStr);
		model.addAttribute("cars", dataList);
	    model.addAttribute("chdate", chdate);
	    
		return "menu";
	}
	//測試訂單編碼與接收值
	@PostMapping("/order")
	public String processOrder(@RequestParam("selectedCar") Integer selectedCarId, Model model) {
	    String sql = "SELECT * FROM car WHERE CarID = ?";
	    CarVo selectedCar = jdbcTemplate.queryForObject(sql, new Object[] {selectedCarId},
        		new BeanPropertyRowMapper<CarVo>(CarVo.class));
	    String orderNumber = generateOrderNumber();
	
	    model.addAttribute("selectedCar", selectedCar);
	    model.addAttribute("orderNumber", orderNumber);
	    
	    return "rentOrder";  
	}
	//訂單呈現
	@PostMapping("/order2")
	public String processOrder2(@RequestParam("selectedCar") Integer selectedCarId,
			@RequestParam("chdate") String chdate, @RequestParam("redate") String redate,
			Model model) {
	    String sql = "SELECT * FROM car WHERE CarID = ?";
	    CarVo selectedCar = jdbcTemplate.queryForObject(sql, new Object[] {selectedCarId},
        		new BeanPropertyRowMapper<CarVo>(CarVo.class));
	    
	    model.addAttribute("selectedCar", selectedCar);
	    model.addAttribute("chdate", chdate);
	    model.addAttribute("redate", redate);
	    return "rentOrder";
	}
	//生成訂單編號
	private String generateOrderNumber() {
	    // 獲取當前日期和時間
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
	    String dateTime = LocalDateTime.now().format(formatter);
	    
	    // 生成六位數的隨機字母和數字
	    String randomCode = generateRandomCode(3);
	    
	    // 組合訂單號碼
	    return dateTime + randomCode;
	}
	//生成亂碼
	private String generateRandomCode(int length) {
	    String characters = "0123456789";
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < length; i++) {
	        sb.append(characters.charAt(random.nextInt(characters.length())));
	    }
	    return sb.toString();
	}
}
