package com.example.Dao;

public class Gamerecord {
    private int number;
    private int total;
    private int win;
    private int draw;
    private int lose;
    private int game_number;
    private int member_number;

    public Gamerecord(int number, int total, int win, int draw, int lose, int game_number, int member_number) {
        this.number = number;
        this.total = total;
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.game_number = game_number;
        this.member_number = member_number;
    }

    public void setNumber(int number) { this.number = number; }
    public void setTotal(int total) { this.total = total; }
    public void setWin(int win) { this.win = win; }
    public void setDraw(int draw) { this.draw = draw; }
    public void setLose(int lose) { this.lose = lose; }
    public void setGame_number(int game_number) { this.game_number = game_number; }
    public void setMember_number(int member_number) { this.member_number = member_number; }

    public int getNumber() { return number; }
    public int getTotal() { return total; }
    public int getWin() { return win; }
    public int getDraw() { return draw; }
    public int getLose() { return lose; }
    public int getGame_number() { return game_number; }
    public int getMember_number() { return member_number; }


}
