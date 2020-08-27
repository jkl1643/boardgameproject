package com.example;


import com.example.Dao.Game;
import com.example.Dao.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Socket_Handler : 소켓이 생성될때나 사라질때, 사용될 때 작동되는 클래스
@Component
public class Socket_Handler  extends TextWebSocketHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HashMap<Integer, Main_Server> Server_list;



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        String nick = (String) httpsession.getAttribute("idid");
        Main_Server Server = Server_list.get((int) httpsession.getAttribute("Gamenumber"));
        String roomid = (String) httpsession.getAttribute("roomid");

        System.out.println("소켓 실행 : "  + nick + " / " + session.getId() );

        super.afterConnectionEstablished(session); // 부모 실행
        Server.connectuser(nick, session, roomid);
        System.out.println("현재원 : " + Server.getUser_list().size() + " " + Server.getUser_nick().size());

    }// afterConnectionEstablished : 웹 소켓 연결시 실행

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        String msg = message.getPayload();
        Chat_Message chatMessage = objectMapper.readValue(msg, Chat_Message.class);
        String nick = chatMessage.getWriter();
        String value = chatMessage.getMessage();
        Chat_Message Message = new Chat_Message();

        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        Main_Server Server = Server_list.get((int) httpsession.getAttribute("Gamenumber"));

        switch (chatMessage.getType()) {
            case "chat":
                System.out.println("채팅 : " + nick + " / " + value);
                Message.setType("chat");
                Message.setMessage(nick + " : " + value);
                break;
            case "connect":
                System.out.println("채팅 입장 : " + nick);
                Message.setType("chat");
                Message.setMessage(nick + "님이 입장하였습니다.");
                break;
            default:
                System.out.println("정의 되지 않은 타입 : " + chatMessage.getType());
        }
        String sendMessage = objectMapper.writeValueAsString(Message); //보낼매새지

        if (chatMessage.getRoomID().equals("lobby")) {
            for (User user : Server.getUser_list().values()) {
                WebSocketSession wss = user.getWss();
                wss.sendMessage(new TextMessage(sendMessage));
            }
        } else {
            Room room = Server.getRoom_list().get(chatMessage.getRoomID());
            for (String nicks : room.getUsers()) {
                User user = Server.getUser_list().get(Server.getUser_nick().get(nicks));
                WebSocketSession wss = user.getWss();
                try {
                    wss.sendMessage(new TextMessage(sendMessage));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }// handleTextMessage : 메시지를 수신시 실행

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        String nick = (String) httpsession.getAttribute("idid");
        Main_Server Server = Server_list.get((int) httpsession.getAttribute("Gamenumber"));

        Server.disconnectuser(nick);

        super.afterConnectionClosed(session, status); // 부모 실행
        System.out.println("현재원 : " + Server.getUser_list().size() + " " + Server.getUser_nick().size());
        System.out.println("소켓 종료 : " + nick);
    }// afterConnectionClosed : 웹 소켓 close시 실행
}
