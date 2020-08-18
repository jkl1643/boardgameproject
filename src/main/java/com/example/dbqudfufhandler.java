package com.example;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;

@Component
public class dbqudfufhandler extends TextWebSocketHandler {
    int i = 0;
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
        Dice dice = new Dice();
        String a = String.valueOf(i);
        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        httpsession.setAttribute("a", a);
        System.out.println("핸들러 메세지수신할때 = " + a);
        Chat_Message Message = new Chat_Message();
    }
}
