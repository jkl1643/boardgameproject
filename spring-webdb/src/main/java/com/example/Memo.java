package com.example;

public class Memo {
	private Long id;
	private String email;
	private String year;
	private String month;
	private String day;
	private String memo;
	private String saveImagePath;

	public Memo(String email, String year, String month, String day, String memo, String saveImagePath) {
		this.email = email;
		this.year = year;
		this.month = month;
		this.day = day;
		this.memo = memo;
		this.saveImagePath = saveImagePath;
	}
	public String getSaveImagePath() {
		return saveImagePath;
	}
	public String getEmail() {
		return email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void changeMemo(String newMemo) {
		this.memo = newMemo;
	}	
	public void changePath(String newSaveImagePath) {
		this.saveImagePath = newSaveImagePath;
	}
}
