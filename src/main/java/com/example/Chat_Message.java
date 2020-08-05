package com.example;

import java.time.LocalDate;

public class Chat_Message {

    private String ID;
    private String writer;
    private String message;
    private LocalDate date;

    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }
    public String getWriter() { return writer; }
    public void setWriter(String writer) { this.writer = writer; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}

