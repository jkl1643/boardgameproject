package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

// Socket_Handler : 소켓이 생성될때나 사라질때, 사용될 때 작동되는 클래스
@Component
public class Socket_Handler  extends TextWebSocketHandler {
    //private final ObjectMapper objectMapper;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
      //  Chat_Message chatMessage = objectMapper.readValue(msg,Chat_Message.class)
        System.out.println(message.getPayload());
        session.sendMessage(new TextMessage(" EEECHO : " + message));
    }// handleTextMessage : 메시지를 수신시 실행

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("소켓 실행");
        super.afterConnectionEstablished(session); // 부모 실행



    }// afterConnectionEstablished : 웹 소켓 연결시 실행

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {



        super.afterConnectionClosed(session, status); // 부모 실행
        System.out.println("소켓 종료");
    }// afterConnectionClosed : 웹 소켓 close시 실행
}
