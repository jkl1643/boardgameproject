package com.example;

import org.springframework.web.socket.WebSocketSession;

import java.util.*;


// Room_Server : 만들어진 방들을 저장하고 관리하는 클래스
public class Room_Server {

    private List<Room> Room_list;

    public List<Room> getRoom_list() { return Room_list; }
    public void setRoom_list(List<Room> room_list) { Room_list = room_list; }

    Room_Server()
    {
       Room_list = new ArrayList<Room>();

    }

    public int create(String name)
    {
        Room room = new Room(name);
        Room_list.add(room);
        System.out.println(Room_list.size());
        return Room_list.size();
    }

    public void select(String name, WebSocketSession user)
    {
        for(Room room : Room_list)
        {
            if(room.getName().equals(name))
                room.join(user);
        }
    }
}
