package com.example.CarRentTest.reactcontro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import ecpay.payment.integration.AllInOne;

import jakarta.servlet.http.*;

import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.exception.EcpayException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.example.CarRentTest.JWT.JwtService;

@RestController
@RequestMapping("/carrent")
public class MyController {
	private AllInOne allInOne;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
    public MyController() {
        this.allInOne = new AllInOne("");
    }
    
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
    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody Map<String, String> request) {
    	/*
    		int memberID=2;//假設客戶ID
    		*/
    		Integer carId = Integer.parseInt(request.get("carId") !=null ? request.get("carId") :null);
        String chdateStr = request.get("chdate") != null ? request.get("chdate") :null;
        String redateStr = request.get("redate") != null ? request.get("redate") :null;
        
        
        DateTimeFormatter formatreq = DateTimeFormatter.ofPattern("yyyy-MM-dd");//日期轉換格式並計算所需數值
        LocalDate chdate = LocalDate.parse(chdateStr, formatreq);
        LocalDate redate = LocalDate.parse(redateStr, formatreq);
        int daysBetween = (int) ChronoUnit.DAYS.between(chdate, redate);//計算天數差
        int findays = daysBetween+1;//將天數(含當天)轉入資輛庫Order_detail
        String sql = "SELECT * FROM car WHERE CarID like ? And Car_Status like 'unuse'";
        Map data = jdbcTemplate.queryForMap(sql, carId);//從Carid取得資料並確認是否為unuse
        /*
        String cartype = (String) data.get("CarType");//獲取車型，預定轉入資料庫紀錄Order,Order_detail
        String carplace = (String) data.get("C_Location");//獲取地點，預定轉入資料庫紀錄Order,Order_detail
        */
        Integer price = (Integer) data.get("Price");
        int finprice = price*findays;//最終訂單價格為單價*天數
        String odPrice = String.valueOf(finprice);//最終訂單價格轉為字串，預定轉金流
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//時間格式設定
	    String dateTime = LocalDateTime.now().format(formatter);//生成訂單時間
	    String orderNumber = generateOrderNumber();//生成訂單編號"日期時間+亂碼"
	    /*
	    BigDecimal odnub = new BigDecimal(orderNumber);//將訂單編號轉為固定值，轉入資料庫Order,Order_detail
	    */
	    
	    /*
        String sqlup = "UPDATE car set Car_Status = 'use', Date = ? where CarID like ?";//更新Car狀態為use
        String sqlodtail ="insert into order_detail (Detail_ID, CarType, Price, Date, MemberID, Days, Location) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";//新增訂單細節
        String sqlod ="insert into `order` (MemberID,Detail_ID,Od_Status) "
	    		+ "VALUES (?, ?, ?)";//新增訂單與訂單狀態
        try {
        		//更新Car
			jdbcTemplate.update(sqlup,chdateStr,carId);
			//更新Order_detail
			jdbcTemplate.update(sqlodtail,odnub,cartype,finprice,chdateStr,memberID,findays,carplace);
			//更新Order
			jdbcTemplate.update(sqlod,memberID,odnub,"renting");
			System.out.println("新增成功，可確認資料庫");
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("資料數值或jdbcTemplate設定有誤");
		}*/

        AioCheckOutALL obj = new AioCheckOutALL();
        //代入商家ID，但payment_conf.xml已有設定
        //obj.setMerchantID("");
        //String tno = "NNTO" + System.currentTimeMillis(); // 隨機生成一個交易編號
        obj.setMerchantTradeNo(orderNumber);//訂單編號
        obj.setMerchantTradeDate(dateTime);//訂單時間
        obj.setTotalAmount(odPrice); //訂單價格
        obj.setTradeDesc("CarRent Payment");
        obj.setItemName("CarRent OrderDetail");
        //設定在金流界面，使用者點選返回商家頁面時的連結
        obj.setReturnURL("http://localhost:3000/");//首頁暫定
        //交易完成後，回傳交易結果的連結
        obj.setOrderResultURL("http://localhost:3000/carrent/paymentResult");//不設回傳訂單值會直接出現交易成功的頁面
        obj.setClientBackURL("http://localhost:3000/"); //設定回首頁之按鈕   	
        String form = "";
        try {
            form = allInOne.aioCheckOut(obj, null);
        } catch (EcpayException e) {
            e.printStackTrace();
        }
        
        // 返回表单 HTML
        return ResponseEntity.ok().body(form);
    }
    
    private String generateOrderNumber() {
	    // 獲取當前日期和時間
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	    String dateTime = LocalDateTime.now().format(formatter);
	    
	    // 生成六位數的隨機字母和數字
	    String randomCode = generateRandomCode(6);
	    
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
	/*
	@PostMapping("/paymentResult")//僅能回報字串，react無法解析
    public ResponseEntity<Map<String, String>> handlePaymentResult(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap<>();
        
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            resultMap.put(paramName, paramValue);
        }
        System.out.println(resultMap);
        return ResponseEntity.ok(resultMap);
    }
    */
	
	
	 @PostMapping("/paymentResult")
	    public void handlePaymentResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        Map<String, String> resultMap = new HashMap<>();
	        Enumeration<String> parameterNames = request.getParameterNames();
	        while (parameterNames.hasMoreElements()) {
	            String paramName = parameterNames.nextElement();
	            String paramValue = request.getParameter(paramName);
	            resultMap.put(paramName, paramValue);
	        }
	        // Json格式
	        String jsonData = new ObjectMapper().writeValueAsString(resultMap);

	        // 加密Json
	        String encodedData = Base64.getEncoder().encodeToString(jsonData.getBytes());

	        // 將網頁輸出帶入加密的Json形成get攜帶資料
	        String redirectUrl = "http://localhost:3000/carrent/paymentResult?data=" + URLEncoder.encode(encodedData, "UTF-8");

	        // Redirect to React page
	        response.sendRedirect(redirectUrl);
	    }

	    @GetMapping("/paymentResultPage")
	    public Map<String, String> paymentResultPage(
	    		@RequestParam String data,
	    		@RequestParam String odmsg) {
	        try {
	        		System.out.println("Data: " + data);
	        		System.out.println("Odmsg: " + odmsg);
	            // Decode the Base64 encoded string
	            byte[] decodedBytes = Base64.getDecoder().decode(data);
	            String decodedString = new String(decodedBytes);

	            // Parse the JSON string back to a Map
	            ObjectMapper objectMapper = new ObjectMapper();
	            Map<String, String> resultMap = objectMapper.readValue(decodedString, new TypeReference<Map<String, String>>() {});
	            Map<String, String> odmsgMap = objectMapper.readValue(odmsg, new TypeReference<Map<String, String>>() {});
	            resultMap.putAll(odmsgMap);
	            String chdateStr = (String)odmsgMap.get("chdate");//得取車日
	            String redateStr  = (String)odmsgMap.get("redate");//得還車日
	            String carIdst  = (String)odmsgMap.get("carId");//得CarId
	            String mcTradeNO = (String)resultMap.get("MerchantTradeNo");//得訂單編號
		        String tradeprice = (String)resultMap.get("TradeAmt");//轉換訂單金額
		        Integer carId = Integer.parseInt(carIdst);//轉換carID
		        BigDecimal odnub = new BigDecimal(mcTradeNO);//轉換訂單編號
		        Integer finprice = Integer.parseInt(tradeprice);//轉換金額
		        int memberID=2;//假設客戶ID
		        
		        DateTimeFormatter formatreq = DateTimeFormatter.ofPattern("yyyy-MM-dd");//日期轉換格式並計算所需數值
		        LocalDate chdate = LocalDate.parse(chdateStr, formatreq);
		        LocalDate redate = LocalDate.parse(redateStr, formatreq);
		        int daysBetween = (int) ChronoUnit.DAYS.between(chdate, redate);//計算天數差
		        int findays = daysBetween+1;//將天數(含當天)轉入資輛庫Order_detail
		        String sql = "SELECT * FROM car WHERE CarID like ?";
		        List<Map<String, Object>> carDataList = jdbcTemplate.queryForList(sql, carId);//從Carid取得資料並確認是否為unuse

		        if (carDataList.isEmpty()) {
		            // No car found with the given ID and 'unuse' status
		            System.out.println("No available car found with ID: " + carId);
		            return Collections.singletonMap("error", "No available car found");
		        }

		        Map<String, Object> cardata = carDataList.get(0);
		        String cartype = (String) cardata.get("CarType");//獲取車型，預定轉入資料庫紀錄Order,Order_detail
		        resultMap.put("carType", cartype);
		        String carplace = (String) cardata.get("C_Location");//獲取地點，預定轉入資料庫紀錄Order,Order_detail
		        resultMap.put("carPlace", carplace);
		        String sqlup = "UPDATE car set Car_Status = 'use', Date = ? where CarID like ?";//更新Car狀態為use
		        String sqlodtail ="insert into order_detail (Detail_ID, CarType, Price, Date, MemberID, Days, Location) "
			    		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";//新增訂單細節
		        String sqlod ="insert into `order` (MemberID,Detail_ID,Od_Status) "
			    		+ "VALUES (?, ?, ?)";//新增訂單與訂單狀態
		        try {
		        		//更新Car
					jdbcTemplate.update(sqlup,chdateStr,carId);
					//更新Order_detail
					jdbcTemplate.update(sqlodtail,odnub,cartype,finprice,chdateStr,memberID,findays,carplace);
					//更新Order
					jdbcTemplate.update(sqlod,memberID,odnub,"renting");
					System.out.println("新增成功，可確認資料庫");
				} catch (DataAccessException e) {
					e.printStackTrace();
					System.out.println("資料數值或jdbcTemplate設定有誤");
				}
		        
	            return resultMap;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return Collections.singletonMap("error", "Failed to process payment result");
	        }
	    }

 /*@PostMapping("/paymentResult")
    public void handlePaymentResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            resultMap.put(paramName, paramValue);
        }

        // 將 resultMap 轉換為 JSON 字符串
        String jsonData = new ObjectMapper().writeValueAsString(resultMap);

        // 加密 JSON 字符串
        String encryptedData = encrypt(jsonData);

        // 構建重定向 URL
        String redirectUrl = "http://localhost:3000/carrent/paymentResultPage?data=" 
                           + URLEncoder.encode(encryptedData, "UTF-8");

        // 重定向到 React 頁面
        response.sendRedirect(redirectUrl);
    }*/
	    /*
	     * @GetMapping("/decryptPaymentResult")
    public Map<String, String> decryptPaymentResult(@RequestParam String data) throws Exception {
        String decryptedJson = decrypt(data);
        return new ObjectMapper().readValue(decryptedJson, new TypeReference<Map<String, String>>() {});
    }
	     * */
	    //加密與解密
	    private static final String SECRET_KEY = "X5a2D6g845e9s8E3"; // 16, 24, or 32 bytes key
	    private String encrypt(String data) throws Exception {//加密AES
	        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
	        return Base64.getEncoder().encodeToString(encryptedBytes);
	    }
    private String decrypt(String encryptedData) throws Exception {//解密AES
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedBytes);
    }

    @PostMapping("forOrder")
    public Map<String, Object> questForOrder(@RequestBody Map<String, String> request){
    		Integer mbID = Integer.parseInt(request.get("memberID"));
    		System.out.println("MemberID = "+mbID);
    		String sqlmb = "SELECT * FROM members WHERE memberID like ? AND login =1";//得到用戶資料
    		String sqlod = "SELECT * FROM `order` WHERE MemberID like ?";
    		Map<String, Object> response = new HashMap<>();
    		
    		try {
    	        Map<String, Object> mbData = jdbcTemplate.queryForMap(sqlmb, mbID);
    	        List<Map<String, Object>> odData = jdbcTemplate.queryForList(sqlod, mbID);
    	        List<Map<String, String>> stringOdData = new ArrayList<>();

    	        for (Map<String, Object> map : odData) {
    	            // 創建一個新的 Map 來存放轉換後的值
    	            Map<String, String> stringMap = new HashMap<>();
    	            for (Map.Entry<String, Object> entry : map.entrySet()) {
    	                if (entry.getValue() instanceof BigDecimal) {
    	                    stringMap.put(entry.getKey(), ((BigDecimal) entry.getValue()).toPlainString()); // 避免科學記數法
    	                } else {
    	                    stringMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
    	                }
    	            }
    	            // 將轉換後的 Map 加入到結果列表中
    	            stringOdData.add(stringMap);
    	        }
    	        String mbname = (String) mbData.get("name");
    	        System.out.println(odData);
    	        response.put("mbodDataStr", stringOdData);
    	        response.put("mbName", mbname);
    	    } catch (EmptyResultDataAccessException e) {
    	        response.put("error", "No data found for the given MemberID");
    	    }

    	    return response;
    }

    
    @PostMapping("forOrderDetail")
    public Map<String, Object> questForOrderDetail(@RequestBody Map<String, String> request) {
        Integer odID = Integer.parseInt(request.get("od_ID"));
        System.out.println(odID);        
        String sqlod = "SELECT * FROM `order` WHERE OrderID like ?";
        String sqloddetail = "SELECT * FROM order_detail WHERE Detail_ID = ?";
        String sqlmb = "SELECT * FROM members WHERE memberID like ? AND login =1";
        
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, Object> selectedOrder = jdbcTemplate.queryForMap(sqlod, odID);
            int mdID = (int)selectedOrder.get("memberID");
            Map<String, Object> mbData = jdbcTemplate.queryForMap(sqlmb, mdID);
            String mbname = (String) mbData.get("name");
            // 檢查是否為 BigDecimal 並避免使用科學記數法
            BigDecimal detailID = (BigDecimal) selectedOrder.get("Detail_ID");
            if (detailID != null) {
                String detailIDStr = detailID.toPlainString(); // 確保大數字的精度
                Map<String, Object> selectedDetail = jdbcTemplate.queryForMap(sqloddetail, detailIDStr);

                // 將 selectedDetail 轉換為字串
                Map<String, String> stringDetailMap = new HashMap<>();
                for (Map.Entry<String, Object> entry : selectedDetail.entrySet()) {
                    stringDetailMap.put(entry.getKey(), entry.getValue() != null ? entry.getValue().toString() : null);
                }
                response.put("mbName", mbname);
                response.put("orderDetail", stringDetailMap);
            } else {
                response.put("error", "No Detail_ID found for the given order.");
            }

        } catch (EmptyResultDataAccessException e) {
            response.put("error", "No order detail found for the given ID");
        }

        System.out.println(response);
        return response;
    }

 // 你可以在这里添加更多的API
}
