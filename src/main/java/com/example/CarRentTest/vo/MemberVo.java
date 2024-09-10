package com.example.CarRentTest.vo;

public class MemberVo {
int memberID;
int age;
String gender;
String password ;
String name;
String email;
String licenseNub;
String address;
String phone;
Boolean login;
public int getMemberID() {
	return memberID;
}
public void setMemberID(int memberID) {
	this.memberID = memberID;
}
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
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
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
public Boolean getLogin() {
	return login;
}
public void setLogin(Boolean login) {
	this.login = login;
}

<<<<<<< HEAD
@Override
public String toString() {
	
	// 決定使用 email 還是 phone 作為 Username 顯示
    String username = (email != null && !email.isEmpty()) ? email : phone;
	
	return "MemberVo [MemberID=" + memberID + ", Username=" + username + ", Password=" + password
			+ "]";
}

}
=======
}
>>>>>>> 220f89ebeab77998460cca10ea8f3b27048f12a5
