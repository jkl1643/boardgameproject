package MyGameRecord;

public class MyGameRecord {
   
	private Long gamerecord_number;
    private int gamerecord_total;
    private int gamerecord_win;
    private int gamerecord_draw;
    private int gamerecord_lose;
    private int game_number;
    private Long member_number;
    public MyGameRecord(int gamerecord_total, int gamerecord_win,
						int gamerecord_draw, int gamerecord_lose, int game_number, Long member_number) {
        this.gamerecord_total = gamerecord_total;
        this.gamerecord_win = gamerecord_win;
        this.gamerecord_draw = gamerecord_draw;
        this.gamerecord_lose = gamerecord_lose;
        this.game_number = game_number;
        this.member_number = member_number;
    }

    public Long getGamerecord_number() {
		return gamerecord_number;
	}
	public void setGamerecord_number(Long gamerecord_number) {
		this.gamerecord_number = gamerecord_number;
	}
	public int getTotal() {
		return gamerecord_total;
	}
	public void setTotal(int gamerecord_total) {
		this.gamerecord_total = gamerecord_total;
	}
	public int getWin() {
		return gamerecord_win;
	}
	public void setWin(int gamerecord_win) {
		this.gamerecord_win = gamerecord_win;
	}
	public int getDraw() {
		return gamerecord_draw;
	}
	public void setDraw(int gamerecord_draw) {
		this.gamerecord_draw = gamerecord_draw;
	}
	public int getLose() {
		return gamerecord_lose;
	}
	public void setLose(int gamerecord_lose) {
		this.gamerecord_lose = gamerecord_lose;
	}
	public int getGame_number() {
		return game_number;
	}
	public void setGame_number(int game_number) {
		this.game_number = game_number;
	}
	public Long getMember_number() {
		return member_number;
	}
	public void setMember_number(Long member_number) {
		this.member_number = member_number;
	}

	public void changeTotal(int gamerecord_total) {
		this.gamerecord_total = gamerecord_total;;
	}
	
	public void changeWin(int gamerecord_win) {
		this.gamerecord_win = gamerecord_win;
	}

	public void changeLose(int gamerecord_lose) {
		this.gamerecord_lose = gamerecord_lose;
	}
	
	public void changeDraw(int gamerecord_draw) {
		this.gamerecord_draw = gamerecord_draw;
	}
}