package com.example;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// Room : 게임 방, 방에 접속해있는 유저들의 정보나 방의 정보들을 관리.
public class Room {

    private String ID;
    private String name;
    private String password;
    private String game;
    private Set<String> users;
    private int player;
    private int maxplayer;

    Room(String name, String game, String password) {
        this.ID = UUID.randomUUID().toString();
        this.name = name;
        this.game = game;
        this.users = new HashSet<>();
        this.password = password;

        switch (game) {
            case "Yahtzee":
                maxplayer = 2;
                break;
            default:
                maxplayer = 1;
        }
    }

    public boolean join(WebSocketSession user, String pw) {
        System.out.println("pw : " + pw);
        System.out.println("pw2 : " + password);
        if (!password.equals(pw))
            return false;

        users.add(user.getId());
        player = users.size();
        return true;
    }

    public void exit(WebSocketSession user) {
        users.remove(user.getId());
        player = users.size();
    }

    public String getID() { return ID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getGame() { return game; }
    public void setGame(String game) { this.game = game; }
    public int getPlayer() { return player; }
    public int getMaxplayer() { return maxplayer; }
    public void setID(String ID) { this.ID = ID; }
    public Set<String> getUsers() { return users; }
    public void setUsers(Set<String> users) { this.users = users; }
    public void setPlayer(int player) { this.player = player; }
    public void setMaxplayer(int maxplayer) { this.maxplayer = maxplayer; }
}
