package com.example.CarRentTest.vo;

public class loginMemberVo {
	
	int MemberID;
	String Username;
	String Password;
	public int getMemberID() {
		return MemberID;
	}
	public void setMemberID(int memberID) {
		MemberID = memberID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "MemberVo [MemberID=" + MemberID + ", Username=" + Username + ", Password=" + Password
				+ "]";
	}

}
