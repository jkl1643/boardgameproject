package com.example;

public class MemoRegisterRequest {
	private Long id;
	private String email;	
	private String year;
	private String month;
	private String day;
	private String memo;
	private String savePath;
	private String saveImagePath;
	
	
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getSaveImagePath() {
		return saveImagePath;
	}
	public void setSaveImagePath(String saveImagePath) {
		this.saveImagePath = saveImagePath;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getYear() {
		return year;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
