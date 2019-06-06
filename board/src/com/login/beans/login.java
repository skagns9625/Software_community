package com.login.beans;

public class login {	
	//사용자 아이디
	private String Id;
	
	//사용자 비밀번호
	private String Password;
	
	//사용자 이름
	private String name;
	
	//사용자 학번
	private String Snum;	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSnum() {
		return Snum;
	}
	public void setEmail(String Snum) {
		this.Snum = Snum;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
}
