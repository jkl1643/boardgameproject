package com.example;



import org.springframework.web.socket.WebSocketSession;

import java.util.*;


// Room_Server : 만들어진 방들을 저장하고 관리하는 클래스
public class Main_Server {
    private HashMap<String, WebSocketSession> User_list = new HashMap<>();
    private HashMap<String, Room> Room_list;

    Main_Server() {
        Room_list = new HashMap<String, Room>();

    }

    public int create(String name, String game, String pw) {
        Room room = new Room(name, game, pw);
        Room_list.put(room.getID(), room);

        System.out.println(Room_list.size());

        return Room_list.size();
    }

    public void select(String roomID, String pw, WebSocketSession user) {
        for (Room room : Room_list.values()) {
            if (room.getID().equals(roomID))
                room.join(user, pw);
        }
    }

    public HashMap<String, Room> getRoom_list() { return Room_list; }
    public void setRoom_list(HashMap<String, Room> Room_list) { this.Room_list = Room_list; }
    public HashMap<String, WebSocketSession> getUser_list() { return User_list; }
    public void setUser_list(HashMap<String, WebSocketSession> user_list) { User_list = user_list; }
}
