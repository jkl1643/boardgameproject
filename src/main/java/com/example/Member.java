package com.example;

import java.util.Date;

public class Member {
	private Long id;
	private String email;
	private String password;
	private String nickname;
	private int gamelog;
	private Date registerDate;

	public Member(String email, String password, String nickname, int gamelog, Date registerDate) {
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.gamelog = gamelog;
		this.registerDate = registerDate;
	}
	public Date getRegisterDate() {
		return registerDate;
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
	public String getNickname() {
		return nickname;
	}
	
	//수명 추가
	public void setNickname(String nickname) {
			this.nickname = nickname;
	}
	public int getGamelog() {
		return gamelog;
	}
	public void setGamelog(int gamelog) {
		this.gamelog = gamelog;
	}
	public void changePassword(String oldPassword, String newPassword) {
		if(!password.equals(oldPassword)) {//저장되있는거   , 지금입력한거
			System.out.println("password = " + password);
			System.out.println("oldPassword = " + oldPassword);
			throw new WrongIdPasswordException();
		}
		this.password = newPassword;
	}	
	public void changeNickname(String nickname) {
		this.nickname = nickname;
	}

	public void checkLogin(String email, String inputPassword) {
		if(!password.equals(inputPassword)) {
			throw new WrongIdPasswordException();
		}
		System.out.println(email + "님 로그인 되었습니다.");
	}	
	public void checkPassword(String inputemail, String inputPassword) {
		System.out.println("password = " + password + "inputPassword = " + inputPassword);
		if(!password.equals(inputPassword)) {
			throw new WrongIdPasswordException();
		}
		
		if(!MemberLogin.loginEmail.equals(inputemail)) {
			throw new NotMatchException();
		} else {
			System.out.println("else = " + MemberLogin.loginEmail + ", " + inputemail);
		}
	}
}
