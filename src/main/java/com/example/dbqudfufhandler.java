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
    @Autowired
    private ObjectMapper objectMapper;


    int i = 0;

    int winner = -1;  // 승자 인덱스 + 1, 승리한 유저 멤버 아이디 불러오기 => userId[winner-1]
    Long[] userId = new Long[2];  // 유저 멤버 아이디


    int winnerStack = 0;
    int[] winnerScore=  new int[2];  // 승자점수


    int roundcounter=26;

    HashMap<String, WebSocketSession> user = new HashMap<>();
    String[] name=  new String[2];// 유저 세션명






    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        String nick = (String) httpsession.getAttribute("idid");
        System.out.println("소켓 실행222222 : " + session.getId() + " / " + nick);
        super.afterConnectionEstablished(session); // 부모 실행

            name[i] = session.getId();
            i++;
            user.put(session.getId(), session);

    }// afterConnectionEstablished : 웹 소켓 연결시 실행


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();

        Dice chatMessage = objectMapper.readValue(msg, Dice.class);

        System.out.println("메세지온거 = " + msg);



        switch (chatMessage.getCmd()) {



            case "start":


                if(i>=2) {
                    chatMessage.setPlayer(1);
                    String sendMessage = objectMapper.writeValueAsString(chatMessage);
                    WebSocketSession wss = user.get(name[0]);
                    wss.sendMessage(new TextMessage(sendMessage));
                    chatMessage.setPlayer(2);
                    sendMessage = objectMapper.writeValueAsString(chatMessage);
                    wss = user.get(name[1]);
                    wss.sendMessage(new TextMessage(sendMessage));
                    chatMessage.setPlayer(0);
                }
                break;


            case "check":
                int result = chatMessage.getSelecto();
                if(result!=-1)
                    userId[chatMessage.getPlayer()-1]= new Long(result);
                else
                    ;//접근 거부, 연결 해제


                chatMessage.setPlayer(0);
                break;


            case "roll":
                break;

            case "record":
                if(chatMessage.getAces()==-1)
                    chatMessage.setAces(0);

                if(chatMessage.getTwos()==-1)
                    chatMessage.setTwos(0);

                if(chatMessage.getThrees()==-1)
                    chatMessage.setThrees(0);

                if(chatMessage.getFours()==-1)
                    chatMessage.setFours(0);

                if(chatMessage.getFives()==-1)
                    chatMessage.setFives(0);

                if(chatMessage.getSixes()==-1)
                    chatMessage.setSixes(0);

                if(chatMessage.getThree_Of_A_Kind()==-1)
                    chatMessage.setThree_Of_A_Kind(0);

                if(chatMessage.getFour_Of_A_Kind()==-1)
                    chatMessage.setFour_Of_A_Kind(0);

                if(chatMessage.getFull_House()==-1)
                    chatMessage.setFull_House(0);

                if(chatMessage.getSmall_Straight()==-1)
                    chatMessage.setSmall_Straight(0);

                if(chatMessage.getLarge_Straight()==-1)
                    chatMessage.setLarge_Straight(0);

                if(chatMessage.getChance()==-1)
                    chatMessage.setChance(0);

                if(chatMessage.getYahtzee()==-1)
                    chatMessage.setYahtzee(0);

                if(chatMessage.getBonus()==-1)
                    chatMessage.setBonus(0);

                roundcounter--;

                break;


            case "end":

                winnerScore[(chatMessage.getPlayer())-1]=chatMessage.getSelecto();
                winnerStack++;
                chatMessage.setPlayer(0);



                if(winnerStack==2){
                    if(winnerScore[0]>winnerScore[1])
                        winner=1;
                    else
                        winner=2;


                    chatMessage.setCmd("alert");
                    chatMessage.setSelecto(winner);
                    String sendMessage = objectMapper.writeValueAsString(chatMessage);
                    for(int i = 0 ; i<2 ; i++) {
                        WebSocketSession wss = user.get(name[i]);
                        wss.sendMessage(new TextMessage(sendMessage));
                    }

                }
                break;


            default:
                System.out.println("정의 되지 않은 타입 : " + chatMessage.getCmd());
                break;
        }

        String sendMessage = objectMapper.writeValueAsString(chatMessage); //보낼매새지


        System.out.println("서버에서 보냄 : " + sendMessage);


        if(chatMessage.getPlayer()!=0) {
            if (userId[chatMessage.getPlayer() - 1] != null)
                System.out.println("허용유저");
            else
                System.out.println("미허용유저");
        }

        if(chatMessage.getPlayer()==1){
            WebSocketSession wss = user.get(name[1]);
            wss.sendMessage(new TextMessage(sendMessage));
       }
       else if(chatMessage.getPlayer()==2) {
           WebSocketSession wss = user.get(name[0]);
           wss.sendMessage(new TextMessage(sendMessage));
       }

        if(roundcounter==0){
            chatMessage.setCmd("end");
            sendMessage = objectMapper.writeValueAsString(chatMessage);
            for(int i = 0 ; i<2 ; i++) {
                WebSocketSession wss = user.get(name[i]);
                wss.sendMessage(new TextMessage(sendMessage));
            }
            roundcounter++;
        }


    }// handleTextMessage : 메시지를 수신시 실행


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        //String nick = (String) httpsession.getAttribute("idid");
        super.afterConnectionClosed(session, status); // 부모 실행
        user.remove(session.getId(), session);
        System.out.println("소켓 종료");
    }// afterConnectionClosed : 웹 소켓 close시 실행
}
