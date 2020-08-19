package com.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import javax.websocket.OnMessage;
import java.util.HashMap;

@Component
public class dbqudfufhandler extends TextWebSocketHandler {
    int i = 0;

    HashMap<String, WebSocketSession> user = new HashMap<>();

    //user = new HashMap<>();
    //@Autowired
    //private ObjectMapper objectMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        //String nick = (String) httpsession.getAttribute("idid"); //세션에 있는값 가져오기
        //System.out.println("소켓 실행222222 : " + session.getId() + " / " + nick);
        super.afterConnectionEstablished(session); // 부모 실행

        i++;
        user.put(session.getId(), session);

    }// afterConnectionEstablished : 웹 소켓 연결시 실행

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        System.out.println("메세지온거 = " + msg);

        session.sendMessage(new TextMessage("d")); //본인에게 되돌아옴
        for (WebSocketSession wss : user.values()) {
            wss.sendMessage(new TextMessage("1"));
        }
    }//메세지

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        //String nick = (String) httpsession.getAttribute("idid");
        super.afterConnectionClosed(session, status); // 부모 실행
        user.remove(session.getId(), session);
        System.out.println("소켓 종료");
    }// afterConnectionClosed : 웹 소켓 close시 실행
}
