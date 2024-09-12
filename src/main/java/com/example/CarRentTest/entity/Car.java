package com.example.CarRentTest.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CarID;

    @Column(nullable = false)
	String CarType;

    @Column(nullable = false)
	int Price;

    @Column
	Date date;

    @Column(nullable = false)
	int PeopleNub;

    @Column(nullable = false)
	String Car_Status;

    @Column(nullable = false)
	String C_Location;

    @Column
	float Rating;

    @Column
	String imgs;

	public Car() {
		super();
	}

	public Car(int carID, String carType, int price, Date date, int peopleNub, String car_Status, String c_Location,
			float rating, String imgs) {
		super();
		this.CarID = carID;
		this.CarType = carType;
		this.Price = price;
		this.date = date;
		this.PeopleNub = peopleNub;
		this.Car_Status = car_Status;
		this.C_Location = c_Location;
		this.Rating = rating;
		this.imgs = imgs;
	}

	public float getRating() {
		return Rating;
	}

	public void setRating(float rating) {
		Rating = rating;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

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
}
