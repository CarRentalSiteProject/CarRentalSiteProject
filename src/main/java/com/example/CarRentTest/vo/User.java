package com.example.CarRentTest.vo;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "members") // 假設你的數據庫表名為 "users"
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String gender;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String licenseNub;

    @Column(nullable = false)
    private String address;

    @Column(unique = true)
    private String phone;

    // 構造函數
    public User() {}

    public User(String username, String password,  int age, String gender, String email, String licenseNub, String address, String phone) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.licenseNub = licenseNub;
        this.address = address;
        this.phone = phone;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean checkPassword(String rawPassword) {
        return this.password.equals(rawPassword);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    //signup

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseNub() {
        return licenseNub;
    }

    public void setLicenseNub(String licenseNub) {      
        this.licenseNub = licenseNub;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailOrPhone() {
        return email != null ? email : phone;
    }


}

