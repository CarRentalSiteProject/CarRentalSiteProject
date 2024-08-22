package fortestDBconn;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

import vo.CarVo;


@Controller
public class CarCart {
	@GetMapping("/cart")
	public String inputcart() {
		return "cart";
	}/*
	@PostMapping("/carCart")
	public String tryconn(
			@RequestParam(value="fordate", required=true) String date,
			@RequestParam(value="forcar", required=false) String car,Model m) {
		System.out.println("id :"+date);
		System.out.println("Car :"+car);
		return "cart";
	}*/
	
	@PostMapping("/carCart")
	public String trycart(
			@RequestParam(value="fordate", required=true) String date,
			@RequestParam(value="forcar", required=false) String car,Model m
			){
		System.out.println("id :"+date);
		System.out.println("Car :"+car);
		CarDB(car);
		return "cart";
	}
	public void CarDB(String car) {
		Properties props = new Properties();
		String path = "D:\\teamproject\\CarRentalSiteProject\\res\\db.properties"; // 絕對路徑

        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        } catch (IOException e) {
        	System.out.println("查詢錯誤CC" + e.getMessage() );
        	e.printStackTrace();
            // 處理文件加載錯誤，例如記錄日誌或返回錯誤視圖
        }
		try {
			DatabaseConnection dcn = new DatabaseConnection(props);
			DataConn dconn = new DataConn(dcn);
			List<CarVo> cvo = dconn.getCarByName(car);
			if(!cvo.isEmpty()) {
				
				for(CarVo carData:cvo) {
					System.out.println("ID :"+carData.getCarID());
					System.out.println("CarType :"+carData.getCarType());
					System.out.println("Price :"+carData.getPrice());
					System.out.println("Date :"+carData.getDate());
					System.out.println("PeopleNub :"+carData.getPeopleNub());
					System.out.println("Car_Status :"+carData.getCar_Status());
					System.out.println("C_Location :"+carData.getC_Location());
				}
			}else{
                System.out.println("找無相對應資料");

            }
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
