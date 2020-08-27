package com.example.Dao;

public class Game {
    private int game_number;
    private String game_name;
    private String game_info;
    private String game_rule;
    private String game_image;

    public Game(int game_number, String game_name, String game_info, String game_rule, String game_image) {
        this.game_name = game_name;
        this.game_info = game_info;
        this.game_rule = game_rule;
        this.game_image = game_image;
    }

    public int getGame_number() { return game_number; }
    public String getGame_name() { return game_name; }
    public String getGame_info() { return game_info; }
    public String getGame_rule() { return game_rule; }
    public String getGame_image() { return game_image; }

    public void setGame_number(int game_number) { this.game_number = game_number; }
    public void setGame_name(String game_name) { this.game_name = game_name; }
    public void setGame_info(String game_info) { this.game_info = game_info; }
    public void setGame_rule(String game_rule) { this.game_rule = game_rule; }
    public void setGame_image(String game_image) { this.game_image = game_image; }
}
