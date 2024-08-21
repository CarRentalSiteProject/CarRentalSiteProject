package com.tcg.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
public class AppConfig {
	private static final String PROPERTIES_FILE = "db.properties";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e){
            System.out.println("專案中的jdbc 驅動程式未尋獲，或者是不存在，請檢查相關路徑");//報錯訊息
            
            //e.printStackTrace();//原生報錯錯誤訊息，通常為測試環境，非正式環境
        }
	}
	public  Connection getConnection() throws SQLException{
	    Properties prop = new Properties();

	        try(InputStream inputStream = AppConfig.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)){
	            if(inputStream == null){
	            System.out.println("參數檔不存在，或檔案為空");
	                return null; //因為要回傳空資源給主類別呼叫此方法時的資源空值處理
	            }
	            //載入參數檔內容
	            prop.load(inputStream);
	        }catch(IOException e){
	            System.out.println("資料庫參數檔讀取失敗");
	            e.printStackTrace();
	        }
	        String url = prop.getProperty("db.url");
	        String id = prop.getProperty("db.user");
	        String password = prop.getProperty("db.password");

	        Connection conn = null;
	        try{
	            conn = DriverManager.getConnection(url, id, password);
	            System.out.println("已成功連線資料庫");
	        }catch(SQLException e){
	            System.out.println("資料庫連線失敗，請檢查連線資訊");
	            e.printStackTrace();
	        }
	        return conn;
	    }
}
