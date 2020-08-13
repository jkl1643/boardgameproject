package com.example;

import java.time.LocalDate;


public class Chat_Message {

    private String roomID = "none";
    private String writer = "none";
    private String message = "none";
    private String type = "none"; // 나중에 Enum 으로 수정


    public String getRoomID() { return roomID; }
    public void setRoomID(String roomID) { this.roomID = roomID; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }

}

