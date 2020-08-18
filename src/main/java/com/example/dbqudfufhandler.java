package com.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import javax.websocket.OnMessage;

@Component
public class dbqudfufhandler extends TextWebSocketHandler {
    int i = 0;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        String nick = (String) httpsession.getAttribute("idid");
        System.out.println("소켓 실행222222 : " + session.getId() + " / " + nick);
        super.afterConnectionEstablished(session); // 부모 실행
        i++;

    }// afterConnectionEstablished : 웹 소켓 연결시 실행

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        System.out.println("메세지온거 = " + msg);
    }

    @OnMessage
    public void handleMessage(String message){
        System.out.println("메세지온거 = " + message);
    }
}
