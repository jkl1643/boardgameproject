package MyGameRecord;

import java.time.LocalDateTime;

public class MyGameRecord {
   
	private Long gamerecord_number;
    private int total;
    private int win;
    private int draw;
    private int lose;
    private int game_number;
    private int member_number;
//	private LocalDateTime registerDateTime;
//  private String nickname;
    public MyGameRecord(int total, int win,
                        int draw, int lose) {
      //  this.nickname = nickname;
        this.total = total;
        this.win = win;
        this.draw = draw;
        this.lose = lose;

    }

    public Long getGamerecord_number() {
		return gamerecord_number;
	}
	public void setGamerecord_number(Long gamerecord_number) {
		this.gamerecord_number = gamerecord_number;
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
	public int getGame_number() {
		return game_number;
	}
	public void setGame_number(int game_number) {
		this.game_number = game_number;
	}
	public int getMember_number() {
		return member_number;
	}
	public void setMember_number(int member_number) {
		this.member_number = member_number;
	}

  


}