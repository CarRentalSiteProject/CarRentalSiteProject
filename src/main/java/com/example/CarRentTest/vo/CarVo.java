package com.example.CarRentTest.vo; 

import java.sql.Date;

public class CarVo {
	
	int CarID;
	String CarType;
	int Price;
	Date date;
	int PeopleNub;
	String Car_Status;
	String C_Location;
	public int getCarID() {
		return CarID;
	}
	public void setCarID(int carID) {
		CarID = carID;
	}
	public String getCarType() {
		return CarType;
	}
	public void setCarType(String carType) {
		CarType = carType;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPeopleNub() {
		return PeopleNub;
	}
	public void setPeopleNub(int peopleNub) {
		PeopleNub = peopleNub;
	}
	public String getCar_Status() {
		return Car_Status;
	}
	public void setCar_Status(String car_Status) {
		Car_Status = car_Status;
	}
	public String getC_Location() {
		return C_Location;
	}
	public void setC_Location(String c_Location) {
		C_Location = c_Location;
	}
	public CarVo() {}
	public CarVo (int carID,String carType,int price,Date date,int peopleNub,String car_Status,String c_Location) {
		this.CarID=carID;
		this.CarType=carType;
		this.Price=price;
		this.date=date;
		this.PeopleNub=peopleNub;
		this.Car_Status=car_Status;
		this.C_Location=c_Location;
	}
}
