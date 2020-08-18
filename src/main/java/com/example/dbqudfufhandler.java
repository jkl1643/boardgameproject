package com.example;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;

@Component
public class dbqudfufhandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        String nick = (String) httpsession.getAttribute("idid");
        System.out.println("소켓 실행222222 : " + session.getId() + " / " + nick);
    }// afterConnectionEstablished : 웹 소켓 연결시 실행
}
