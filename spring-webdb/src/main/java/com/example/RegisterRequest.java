package com.example;

import java.util.Date;

public class RegisterRequest {
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	private String tel;
	private String address;
	private String year;
	private String month;
	private String day;
	private String memo;
	private Date registerDate;
	private String savePath;
	
	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getYear() {
		return year;
	}
	public String getMonth() {
		return month;
	}
	public String getDay() {
		return day;
	}
	public String getMemo() {
		return memo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}	
	
	public void setYear(String year) {
		this.year = year;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public boolean isPasswordEqualToConfirmPassword() {
		return password.equals(confirmPassword);
	}
	public boolean isMemoExist() {
		return memo != null; //true면 비어있는거, false면 이미 적혀잇는거
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
}
