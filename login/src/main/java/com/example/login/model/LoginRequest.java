package com.example.login.model;

    
public class LoginRequest {
    private String emailOrphone;
    private String password;

    // 構造函數
    public LoginRequest() {}

    public LoginRequest(String emailOrphone, String password) {
        this.emailOrphone = emailOrphone;
        this.password = password;
    }
    

    // Getter 和 Setter
    public String getemailOrphone() {
        return emailOrphone;
    }

    public void setUsername(String emailOrphone) {
        this.emailOrphone = emailOrphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}