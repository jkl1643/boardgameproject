package com.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;

// Socket_Handler : 소켓이 생성될때나 사라질때, 사용될 때 작동되는 클래스
@Component

public class Socket_Handler  extends TextWebSocketHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Main_Server Server;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        Chat_Message chatMessage = objectMapper.readValue(msg, Chat_Message.class);
        Room room = Server.getRoom_list().get(chatMessage.getRoomID());


        System.out.println(msg);
/*
        for(String key : room.getUsers()) {
            WebSocketSession wss = Server.getUser_list().get(key);
            try {
                wss.sendMessage(new TextMessage(msg));
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
*/
        session.sendMessage(new TextMessage((msg)));
    }// handleTextMessage : 메시지를 수신시 실행

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("소켓 실행");
        super.afterConnectionEstablished(session); // 부모 실행
        Server.getUser_list().put(session.getId(), session);


    }// afterConnectionEstablished : 웹 소켓 연결시 실행

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {


        Server.getUser_list().remove(session.getId());
        super.afterConnectionClosed(session, status); // 부모 실행
        System.out.println("소켓 종료");
    }// afterConnectionClosed : 웹 소켓 close시 실행
}
