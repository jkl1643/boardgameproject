package com.example;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// Room : 게임 방, 방에 접속해있는 유저들의 정보나 방의 정보들을 관리.
public class Room {

    private String ID;
    private String name;
    private Set<WebSocketSession> users;

    Room(String name)
    {
        this.ID = UUID.randomUUID().toString();
        this.name = name;
        this.users = new HashSet<>();
    }

    public void join(WebSocketSession user)
    {
        users.add(user);

    }

    public void exit(WebSocketSession user)
    {
        users.remove(user);
    }
    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
