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
    private int carID;

    @Column(nullable = false)
	String carType;

    @Column(nullable = false)
	int price;

    @Column
	String date;

    @Column(nullable = false)
	int peopleNub;

    @Column(nullable = false)
	String car_Status;

    @Column(nullable = false)
	String c_Location;

    @Column
	float Rating;

    @Column
	String imgs;

	public Car() {
		super();
	}

	public Car(int carID, String carType, int price, String date, int peopleNub, String car_Status, String c_Location,
			float rating, String imgs) {
		super();
		this.carID = carID;
		this.carType = carType;
		this.price = price;
		this.date = date;
		this.peopleNub = peopleNub;
		this.car_Status = car_Status;
		this.c_Location = c_Location;
		this.Rating = rating;
		this.imgs = imgs;
	}

	public float getRating() {
		return this.Rating;
	}

	public void setRating(float rating) {
		this.Rating = rating;
	}

	public String getImgs() {
		return this.imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public int getcarID() {
		return this.carID;
	}

	public void setcarID(int carID) {
		this.carID = carID;
	}

	public String getcarType() {
		return this.carType;
	}

	public void setcarType(String carType) {
		this.carType = carType;
	}

	public int getprice() {
		return this.price;
	}

	public void setprice(int price) {
		this.price = price;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getpeopleNub() {
		return this.peopleNub;
	}

	public void setpeopleNub(int peopleNub) {
		this.peopleNub = peopleNub;
	}

	public String getcar_Status() {
		return this.car_Status;
	}

	public void setcar_Status(String car_Status) {
		this.car_Status = car_Status;
	}

	public String getc_Location() {
		return this.c_Location;
	}

	public void setc_Location(String c_Location) {
		this.c_Location = c_Location;
	}
}
