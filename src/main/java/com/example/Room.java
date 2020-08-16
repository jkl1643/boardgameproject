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
    private String status;
    private Set<String> usernicks;
    private int player;
    private int maxplayer;

    Room(String name, String game, String password)
    {
        this.ID = UUID.randomUUID().toString();
        this.name = name;
        this.game = game;
        this.usernicks = new HashSet<>();
        this.password = password;
        this.status = "Waiting";
        switch (game)
        {
            case "Yahtzee":
                maxplayer = 2;
                break;
            default:
                maxplayer = 1;
        }
    }

    public boolean join(String nick, String pw)
    {

        if (!password.equals(pw))
        {
            System.out.println("미일치 : " + pw + " / " + password);
            return false;
        }

        usernicks.add(nick);
        player = usernicks.size();
        return true;
    }
    public void exit(String nick)
    {
        usernicks.remove(nick);
        player = usernicks.size();
    }
    public String getStatus() { return status; }
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
    public Set<String> getUsers() { return usernicks; }
    public void setUsers(Set<String> users) { this.usernicks = users; }
    public void setPlayer(int player) { this.player = player; }
    public void setMaxplayer(int maxplayer) { this.maxplayer = maxplayer; }
}
