package com.example.CarRentTest.actioin;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ecpay.payment.integration.*;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.exception.EcpayException;

//webservlet 宣告路由時，不用分號 ; 結尾
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AllInOne allInOne;
    
    @Autowired
	private JdbcTemplate jdbcTemplate;

    @Override
    public void init() throws ServletException{
        super.init();
        allInOne = new AllInOne("");
    }
//測試連線get
    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        AioCheckOutALL obj = new AioCheckOutALL();
        //代入商家ID，但payment_conf.xml已有設定
        //obj.setMerchantID("");zz
        //String tno = req.getParameter("tno");//訂單編號
        String total = req.getParameter("total");//訂單金額
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//時間格式設定
	    String dateTime = LocalDateTime.now().format(formatter);//生成當日時間
	    String orderNumber = generateOrderNumber();
        obj.setMerchantTradeNo(orderNumber);
        obj.setMerchantTradeDate(dateTime);
        obj.setTotalAmount(total);
        obj.setTradeDesc("CarRentSystem");
        obj.setItemName("車型方案");
        //設定在金流介面，使用者點選返回商家頁面的連結
        obj.setReturnURL("http://localhost:8080/loc_day");//首頁暫定
        //交易完成後，回傳交易結果的連結
        obj.setOrderResultURL("http://localhost:8080/paymentResult");

        String form = "";
        try{
            form = allInOne.aioCheckOut(obj, null);
        }catch(EcpayException e){
            e.printStackTrace();
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(form);
    }
    //流程post
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();//留著適用多請求共享數據
        int memberID=1;//假設客戶ID
        Integer carId = Integer.parseInt(req.getParameter("carId"));//接收Carid預定從資料庫要求資料
        String chdateStr = req.getParameter("chdate");//預定轉入資料庫紀錄Car,Order_detail
        String redateStr = req.getParameter("redate");
        
        DateTimeFormatter formatreq = DateTimeFormatter.ofPattern("yyyy-MM-dd");//日期轉換格式並計算所需數值
        LocalDate chdate = LocalDate.parse(chdateStr, formatreq);
        LocalDate redate = LocalDate.parse(redateStr, formatreq);
        int daysBetween = (int) ChronoUnit.DAYS.between(chdate, redate);//計算天數轉入資輛庫Order_detail
        
        String sql = "SELECT * FROM car WHERE CarID like ? And Car_Status like 'unuse'";
        Map data = jdbcTemplate.queryForMap(sql, carId);//從Carid取得資料並確認是否為unuse
        
        String cartype = (String) data.get("CarType");//獲取車型，預定轉入資料庫紀錄Order,Order_detail
        String carplace = (String) data.get("C_Location");//獲取地點，預定轉入資料庫紀錄Order,Order_detail
        Integer price = (Integer) data.get("Price");      
        String odPrice = String.valueOf(price);//訂單價格並轉為字串，預定轉金流
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");//時間格式設定
	    String dateTime = LocalDateTime.now().format(formatter);//生成訂單時間
	    String orderNumber = generateOrderNumber();//生成訂單編號"日期時間+亂碼"
	    BigDecimal odnub = new BigDecimal(orderNumber);//將訂單編號轉為固定值，轉入資料庫Order,Order_detail
	    
        String sqlup = "UPDATE car set Car_Status = 'use', Date = ? where CarID like ?";//更新Car狀態為use
        String sqlodtail ="insert into order_detail (Detail_ID, CarType, Price, Date, MemberID, Days, Location) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";//新增訂單細節
        String sqlod ="insert into `order` (MemberID,Detail_ID,Od_Status) "
	    		+ "VALUES (?, ?, ?)";//新增訂單與訂單狀態
        try {
        		//更新Car
			jdbcTemplate.update(sqlup,chdateStr,carId);
			//更新Order_detail
			jdbcTemplate.update(sqlodtail,odnub,cartype,price,chdateStr,memberID,daysBetween,carplace);
			//更新Order
			jdbcTemplate.update(sqlod,memberID,odnub,"renting");
			System.out.println("新增成功，可確認資料庫");
		} catch (DataAccessException e) {
			e.printStackTrace();
			System.out.println("資料數值或jdbcTemplate設定有誤");
		}

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
        obj.setReturnURL("http://localhost:8080/loc_day");//首頁暫定
        //交易完成後，回傳交易結果的連結
        obj.setOrderResultURL("http://localhost:8080/paymentResult");

        String form = "";
        try {
            form = allInOne.aioCheckOut(obj, null);
        } catch (EcpayException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(form);
    }
    
    //生成訂單編號的方法
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
}
