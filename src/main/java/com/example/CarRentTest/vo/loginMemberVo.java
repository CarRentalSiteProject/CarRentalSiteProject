package com.example.CarRentTest.vo;

public class loginMemberVo {
	
	int MemberID;
	String email;
	String phone;
	String password;
	public int getMemberID() {
		return MemberID;
	}
	public void setMemberID(int memberID) {
		MemberID = memberID;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		
		// 決定使用 email 還是 phone 作為 Username 顯示
	    String username = (email != null && !email.isEmpty()) ? email : phone;
		
		return "MemberVo [MemberID=" + MemberID + ", Username=" + username + ", Password=" + password
				+ "]";
	}

}
