package MyGameRecord;

import java.time.LocalDateTime;

public class MyGameRecord {
	private Long count;
	private String nickname;
	private int total; 
	private int win; 
	private int draw; 
	private int lose;
//	private LocalDateTime registerDateTime;
	
	public MyGameRecord(String nickname, int total, int win, 
			int draw, int lose) {
		this.nickname = nickname;
		this.total = total;
		this.win = win;
		this.draw = draw;
		this.lose = lose;
	
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}


	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}
	


	
}
