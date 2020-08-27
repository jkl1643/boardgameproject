package custom_asking;

import java.time.LocalDateTime;

import com.example.WrongIdPasswordException;

public class Custom {
	private Long count;
	private String title; // 문의 제목
	private String content; // 문의 내용
	//private String name;
	private int number; // 닉네임
	//private String email;
	private LocalDateTime registerDateTime;
	
	public Custom(String title, String content, LocalDateTime regDateTime,
			 int number) {
		this.title = title;
		this.content = content;
		this.number = number;
		//this.name = name;
		//this.email = email;
		this.registerDateTime = regDateTime;
	}
	
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public LocalDateTime getRegisterDateTime() {
		return registerDateTime;
	}

	public void setRegisterDateTime(LocalDateTime registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	
	public void changeTitle(String title) {
		this.title = title;
	}
	
	public void changeContent(String content) {
		this.content = content;
	}
	
	
}
