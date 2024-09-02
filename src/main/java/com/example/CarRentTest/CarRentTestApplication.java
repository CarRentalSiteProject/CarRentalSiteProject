package com.example.CarRentTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//運行@WebServlet
@SpringBootApplication
public class CarRentTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRentTestApplication.class, args);
	}

}
