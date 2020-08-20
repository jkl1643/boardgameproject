package com.example;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.xml.soap.Text;
import java.io.IOException;
import java.util.*;


// Room_Server : 만들어진 방들을 저장하고 관리하는 클래스
public class Main_Server {
    @Autowired
    private ObjectMapper objectMapper;
    private HashMap<String, String> User_nick;
    private HashMap<String, WebSocketSession> User_list;
    private HashMap<String, Room> Room_list;

    Main_Server() {
        Room_list = new HashMap<String, Room>(); //방목록
        User_nick = new HashMap<>();            //유저닉으로 아이디찾기
        User_list = new HashMap<>();            //아이디로 소켓찾기
    }

    public void connectuser(String nick, WebSocketSession user) {
        if (User_nick.get(nick) != null) {
            User_list.remove(User_nick.get(nick));
            User_nick.remove(nick);
        }
        User_list.put(user.getId(), user);
        User_nick.put(nick, user.getId());
    }

    public void disconnectuser(String nick)
    {
        User_list.remove(User_nick.get(nick));
        User_nick.remove(nick);
    }


//    방관련
    public int create(String name, String game, String pw)
    {
        Room room = new Room(name, game, pw);
        Room_list.put(room.getID(), room);

        Chat_Message d = new Chat_Message();

        d.setType("create");
        String js = "";

        try {
            js = objectMapper.writeValueAsString(d);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(Room_list.size());
        for(WebSocketSession wss : User_list.values())
        {
            try {
                wss.sendMessage(new TextMessage(js));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Room_list.size();
    }

    public void select(String roomID, String pw, String nick)
    {
        for(Room room : Room_list.values())
        {
            if(room.getID().equals(roomID))
                room.join(nick, pw);
        }

    }

    public HashMap<String, Room> getRoom_list() { return Room_list; }
    public void setRoom_list(HashMap<String, Room> Room_list) { this.Room_list = Room_list; }
    public HashMap<String, WebSocketSession> getUser_list() { return User_list; }
    public void setUser_list(HashMap<String, WebSocketSession> user_list) { User_list = user_list; }
    public HashMap<String, String> getUser_nick() { return User_nick; }
    public void setUser_nick(HashMap<String, String> user_nick) { User_nick = user_nick; }
}
