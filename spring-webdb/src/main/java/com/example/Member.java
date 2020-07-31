package com.example;

import java.time.LocalDateTime;
import java.util.Date;

public class Member {
	private Long id;
	private String email;
	private String password;
	private String name;
	private String tel;
	private String address;
	private String year;
	private String month;
	private String day;
	private String memo;
	private String savePath;	
	private Date registerDate;

	public Member(String email, String password, String name, String tel, String address, Date registerDate) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.registerDate = registerDate;
	}		
	public Date getRegisterDate() {
		return registerDate;
	}
	public String getSavePath() {
		return savePath;
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
	public String getAddress() {
		return address;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public void changePassword(String oldPassword, String newPassword) {
		if(!password.equals(oldPassword)) {//저장되있는거   , 지금입력한거
			System.out.println("password = " + password);
			System.out.println("oldPassword = " + oldPassword);
			throw new WrongIdPasswordException();
		}
		this.password = newPassword;
	}	
	public void changeName(String name) {
		this.name = name;
	}	
	public void changeTel(String tel) {
		this.tel = tel;
	}	
	public void changeAddress(String address) {
		this.address = address;
	}	
	public void checkLogin(String email, String inputPassword) {
		if(!password.equals(inputPassword))
			throw new WrongIdPasswordException();
		System.out.println(email + "님 로그인 되었습니다.");
	}	
	public void checkPassword(String inputemail, String inputPassword) {
		System.out.println("password = " + password + "inputPassword = " + inputPassword);
		if(!password.equals(inputPassword))
			throw new WrongIdPasswordException();
		
		if(!MemberLogin.loginEmail.equals(inputemail)) {
			throw new NotMatchException();
		} else
			System.out.println("else = " + MemberLogin.loginEmail + ", " + inputemail);
	}
}
