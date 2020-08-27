package com.example;


import com.fasterxml.jackson.databind.ObjectMapper;

import MyGameRecord.MyGameRecord;
import MyGameRecord.MyGameRecordDao;
import MyGameRecord.MyGameRecordWrite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


@Component
public class YahtzeeHandler extends TextWebSocketHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HashMap<Integer, Main_Server> Server_list;

    @Autowired
    private MyGameRecordDao mygamerecorddao;
    
    @Autowired
    private MyGameRecordWrite mygamerecordwrite;

    @Autowired
    private MemberDao memberDao;
    
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setMyGameRecordDao(MyGameRecordDao mygamerecorddao) {
		this.mygamerecorddao = mygamerecorddao;
	}

    public void setMygameRecordWrite(MyGameRecordWrite mygamerecordwrite) {
    	this.mygamerecordwrite = mygamerecordwrite;
    }
    
    String[] nickname = new String[2];
    int i = 0;
    
    String blacklist;
    String getRoomId;
    int getUserId;


    HashMap<String, Integer> index = new HashMap<>();

    HashMap<String, Integer> winnerHash = new HashMap<>();

    HashMap<String, Long[]> userIdHash = new HashMap<>();

    HashMap<String, Integer> winnerStackHash = new HashMap<>();

    HashMap<String, Integer[]> winnerScoreHash = new HashMap<>();

    HashMap<String, Integer> roundcounterHash = new HashMap<>();

    HashMap<String, String[]> nameHash = new HashMap<>();

    HashMap<String, WebSocketSession> user = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        String nick = (String) httpsession.getAttribute("idid");
        System.out.println("소켓 실행 : " + session.getId() + " / " + nick);
        super.afterConnectionEstablished(session); // 부모 실행

        if(i<2) {
            nickname[i] = nick;
        }

        for(int a = 0; a < nickname.length; a++) {
        System.out.println("닉네임은 무엇 : " + nickname[a]);
        }
        i++;
        user.put(session.getId(), session);
    }// afterConnectionEstablished : 웹 소켓 연결시 실행


    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        HttpSession httpsession = (HttpSession) session.getAttributes().get("session");
        Main_Server Server = Server_list.get(1);

        HashMap<String, Room> Room_List = Server.getRoom_list();

        String msg = message.getPayload();
        Dice chatMessage = objectMapper.readValue(msg, Dice.class);

        System.out.println("메세지온거 = " + msg);

        switch (chatMessage.getCmd()) {
            case "start":
                getRoomId = chatMessage.getRoomId();
                getUserId = chatMessage.getSelecto();
                boolean sameId = false;
                int a1 = 0;
                Long[] a2 = null;

                if((index.get(getRoomId) == null) || (userIdHash.get(getRoomId) == null));
                else {
                    a1 = index.get(getRoomId);
                    a2 = userIdHash.get(getRoomId);
                }

                for(int i = 0; i < a1; i++) {
                    if (a2[i] == getUserId) {
                        sameId = true;
                        break;
                    }
                }

                if(sameId || (Room_List.get(getRoomId).getStatus() == "Start")){
                    chatMessage.setCmd("close");
                    String sendMessage = objectMapper.writeValueAsString(chatMessage);
                    WebSocketSession wss = user.get(session.getId());
                    wss.sendMessage(new TextMessage(sendMessage));
                    chatMessage.setPlayer(0);
                    blacklist = session.getId();
                    break;
                }

                if(index.containsKey(getRoomId) == false){
                    index.put(getRoomId, 0);
                    winnerHash.put(getRoomId, -1);
                    userIdHash.put(getRoomId, new Long[2]);
                    winnerStackHash.put(getRoomId, 0);
                    winnerScoreHash.put(getRoomId, new Integer[2]);
                    roundcounterHash.put(getRoomId, 26);
                    nameHash.put(getRoomId, new String[2]);
                }

                int a = index.get(getRoomId);
                a = a + 1;
                index.put(getRoomId, a);

                a = index.get(getRoomId);

                Long[] b = userIdHash.get(getRoomId);
                b[a - 1]= new Long(getUserId);
                userIdHash.put(getRoomId, b);

                String[] c = nameHash.get(getRoomId);
                c[a - 1]= session.getId();
                nameHash.put(getRoomId, c);


                if(index.get(getRoomId) == 2) {
                    chatMessage.setPlayer(1);
                    String sendMessage = objectMapper.writeValueAsString(chatMessage);
                    WebSocketSession wss = user.get(nameHash.get(getRoomId)[0]);
                    wss.sendMessage(new TextMessage(sendMessage));

                    chatMessage.setPlayer(2);
                    sendMessage = objectMapper.writeValueAsString(chatMessage);
                    wss = user.get(nameHash.get(getRoomId)[1]);
                    wss.sendMessage(new TextMessage(sendMessage));

                    chatMessage.setPlayer(0);

                    Room_List.get(getRoomId).setStatus("Start");
                }
                break;

            case "roll":
                getRoomId = chatMessage.getRoomId();
                break;

            case "record":
                getRoomId = chatMessage.getRoomId();
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

                a = roundcounterHash.get(getRoomId);
                a = a-1;
                roundcounterHash.put(getRoomId, a);
                break;

            case "end":
                getRoomId = chatMessage.getRoomId();

                Integer d[] = winnerScoreHash.get(getRoomId);
                d[(chatMessage.getPlayer())-1]=chatMessage.getSelecto();
                winnerScoreHash.put(getRoomId, d);

                a = winnerStackHash.get(getRoomId);
                a = a+1;
                winnerStackHash.put(getRoomId, a);

                chatMessage.setPlayer(0);

                if(winnerStackHash.get(getRoomId)==2) {
                    d = winnerScoreHash.get(getRoomId);
                    if (d[0] > d[1]) {
                        winnerHash.put(getRoomId, 1);
                        System.out.println("승리자 = " + nickname[0]);
                        System.out.println("패배자 = " + nickname[1]);

                        Member member1 = memberDao.selectByEmail(nickname[0]); // 1p
                        Member member2 = memberDao.selectByEmail(nickname[1]); // 2p
                        MyGameRecord mygame1 = mygamerecorddao.selectByMEMNUM(member1.getId());
                        MyGameRecord mygame2 = mygamerecorddao.selectByMEMNUM(member2.getId());

                        if(member1.getId() == mygame1.getMember_number()) // 1p 승리시 업데이트
                        {
                            //1p 업데이트
                            System.out.println("업데이트확인");
                            //mygamerecorddao.update(record);
                            System.out.println("mygame1번호 = " + mygame1.getMember_number());
                            /* total +1 ,
                             * win + 1,
                             * lose + 0
                             * draw + 0
                             */
                            mygame1.changeTotal(mygame1.getTotal()+1);
                            mygame1.changeWin(mygame1.getWin()+1);
                            mygame1.changeDraw(mygame1.getDraw());
                            mygame1.changeLose(mygame1.getLose());
                            System.out.println("mygame1" + mygame1.getTotal());
                            mygamerecorddao.update(mygame1);
                            System.out.println("2");
                            
                   
                        }

                        if(member2.getId() == mygame2.getMember_number()) // 2p 패배시
                        {
                        	//2p 업데이트
                            System.out.println("2업데이트확인");
                           
                            System.out.println("mygame2번호 = " + mygame2.getMember_number());
                            mygame2.changeTotal(mygame2.getTotal()+1);
                            mygame2.changeWin(mygame2.getWin());
                            mygame2.changeDraw(mygame2.getDraw());
                            mygame2.changeLose(mygame2.getLose()+1);
                            System.out.println("mygame2" + mygame2.getTotal());
                            mygamerecorddao.update(mygame2);
                            System.out.println("3");
                        }
                    } else {
                        winnerHash.put(getRoomId, 2);
                        System.out.println("승리자 = " + nickname[1]);
                        System.out.println("패배자 = " + nickname[0]);
                        
                        Member member1 = memberDao.selectByEmail(nickname[0]); // 1p
                        Member member2 = memberDao.selectByEmail(nickname[1]); // 2p
                        MyGameRecord mygame1 = mygamerecorddao.selectByMEMNUM(member1.getId());
                        MyGameRecord mygame2 = mygamerecorddao.selectByMEMNUM(member2.getId());
                        
                        if(member2.getId() == mygame2.getMember_number()) // 2p 승리
                        {
                        	//2p 업데이트 
                            System.out.println("2업데이트확인");
                           
                            System.out.println("mygame2번호 = " + mygame2.getMember_number());
                            mygame2.changeTotal(mygame2.getTotal()+1);
                            mygame2.changeWin(mygame2.getWin()+1);
                            mygame2.changeDraw(mygame2.getDraw());
                            mygame2.changeLose(mygame2.getLose());
                            System.out.println("mygame2" + mygame2.getTotal());
                            mygamerecorddao.update(mygame2);
                            System.out.println("4");
                        }
                        
                        
                        if(member1.getId() == mygame1.getMember_number()) // 1p 패배
                        {
                            //1p 업데이트
                            System.out.println("업데이트확인");
                            //mygamerecorddao.update(record);
                            System.out.println("mygame1번호 = " + mygame1.getMember_number());
                            /* total +1 ,
                             * win + 1,
                             * lose + 0
                             * draw + 0
                             */

                            mygame1.changeTotal(mygame1.getTotal()+1);
                            mygame1.changeWin(mygame1.getWin());
                            mygame1.changeDraw(mygame1.getDraw());
                            mygame1.changeLose(mygame1.getLose()+1);
                            System.out.println("mygame1" + mygame1.getTotal());
                            mygamerecorddao.update(mygame1);
                            System.out.println("5");
                        }
                    }
                }
                chatMessage.setCmd("alert");
                a = winnerHash.get(getRoomId);
                System.out.println("답"+a);
                chatMessage.setSelecto(a);
                String sendMessage = objectMapper.writeValueAsString(chatMessage);
                for(int i = 0 ; i<2 ; i++) {
                    WebSocketSession wss = user.get(nameHash.get(getRoomId)[i]);
                    wss.sendMessage(new TextMessage(sendMessage));
                }
                break;

            case "run":
                getRoomId = chatMessage.getRoomId();

                chatMessage.setCmd("alert");
                if(chatMessage.getPlayer()==1){ //탈주한 플래이어가 1, 2가 이김
                    winnerHash.put(getRoomId, 2);
                    chatMessage.setSelecto(2);
                    sendMessage = objectMapper.writeValueAsString(chatMessage);
                    WebSocketSession wss = user.get(nameHash.get(getRoomId)[1]);
                    wss.sendMessage(new TextMessage(sendMessage));
                    chatMessage.setPlayer(0);

                    //2p 승리
                    /////////////////////////////////////////////////////
                    System.out.println("승리자 = " + nickname[1]);
                    System.out.println("패배자 = " + nickname[0]);
                    
                    
                    Member member1 = memberDao.selectByEmail(nickname[0]); // 1p
                    Member member2 = memberDao.selectByEmail(nickname[1]); // 2p
                    MyGameRecord mygame1 = mygamerecorddao.selectByMEMNUM(member1.getId());
                    MyGameRecord mygame2 = mygamerecorddao.selectByMEMNUM(member2.getId());
                    
                    if(member2.getId() == mygame2.getMember_number()) // 2p 승리
                    {
                    	//2p 업데이트 
                        System.out.println("2업데이트확인");
                       
                        System.out.println("mygame2번호 = " + mygame2.getMember_number());
                        mygame2.changeTotal(mygame2.getTotal()+1);
                        mygame2.changeWin(mygame2.getWin()+1);
                        mygame2.changeDraw(mygame2.getDraw());
                        mygame2.changeLose(mygame2.getLose());
                        System.out.println("mygame2" + mygame2.getTotal());
                        mygamerecorddao.update(mygame2);
                        System.out.println("4");
                    }
                    
                    if(member1.getId() == mygame1.getMember_number()) // 1p 패배
                    {
                        //1p 업데이트
                        System.out.println("업데이트확인");
                        //mygamerecorddao.update(record);
                        System.out.println("mygame1번호 = " + mygame1.getMember_number());
                        /* total +1 ,
                         * win + 1,
                         * lose + 0
                         * draw + 0
                         */

                        mygame1.changeTotal(mygame1.getTotal()+1);
                        mygame1.changeWin(mygame1.getWin());
                        mygame1.changeDraw(mygame1.getDraw());
                        mygame1.changeLose(mygame1.getLose()+1);
                        System.out.println("mygame1" + mygame1.getTotal());
                        mygamerecorddao.update(mygame1);
                        System.out.println("5");
                    }    
                    /////////////////////////////////////////////////////
                } else if(chatMessage.getPlayer()==2){    //2p가 도망, 1이 이김
                    winnerHash.put(getRoomId, 1);
                    chatMessage.setSelecto(1);
                    sendMessage = objectMapper.writeValueAsString(chatMessage);
                    WebSocketSession wss = user.get(nameHash.get(getRoomId)[0]);
                    wss.sendMessage(new TextMessage(sendMessage));
                    chatMessage.setPlayer(0);
                    
                    //1p 승리
                    /////////////////////////////////////////////
                    System.out.println("승리자 = " + nickname[0]);
                    System.out.println("패배자 = " + nickname[1]);

                    Member member1 = memberDao.selectByEmail(nickname[0]); // 1p
                    Member member2 = memberDao.selectByEmail(nickname[1]); // 2p
                    System.out.println("member2 = " + member2.getId());
                    MyGameRecord mygame1 = mygamerecorddao.selectByMEMNUM(member1.getId());
                    MyGameRecord mygame2 = mygamerecorddao.selectByMEMNUM(member2.getId());

                    if(member1.getId() == mygame1.getMember_number()) // 1p 승리시 업데이트
                    {
                        //업데이트
                        System.out.println("업데이트확인");
                        //mygamerecorddao.update(record);
                        System.out.println("mygame1번호 = " + mygame1.getMember_number());
                        /* total +1 ,
                         * win + 1,
                         * lose + 0
                         * draw + 0
                         */

                        mygame1.changeTotal(mygame1.getTotal()+1);
                        mygame1.changeWin(mygame1.getWin()+1);
                        mygame1.changeDraw(mygame1.getDraw());
                        mygame1.changeLose(mygame1.getLose());
                        System.out.println("mygame1 = " + mygame1.getTotal());
                        System.out.println("mygame2 = " + mygame1.getWin());
                        System.out.println("mygame3 = " + mygame1.getDraw());
                        System.out.println("mygame4 = " + mygame1.getLose());
                        System.out.println("mygame5 = " + mygame1.getMember_number());
                        System.out.println("mygame6 = " + mygame1.getGamerecord_number());
                        System.out.println("mygame6 = " + mygame1.getGame_number());
                        mygamerecorddao.update(mygame1);
                        System.out.println("2");
                    }
                  
                    if(member2.getId() == mygame2.getMember_number()) // 2p 패배시
                    {
                    	//2p 업데이트
                        System.out.println("2업데이트확인");
                       
                        System.out.println("mygame2번호 = " + mygame2.getMember_number());
                        mygame2.changeTotal(mygame2.getTotal()+1);
                        mygame2.changeWin(mygame2.getWin());
                        mygame2.changeDraw(mygame2.getDraw());
                        mygame2.changeLose(mygame2.getLose()+1);
                        System.out.println("mygame2" + mygame2.getTotal());
                        mygamerecorddao.update(mygame2);
                        System.out.println("3");
                    }
                    /////////////////////////////////////////////
                }
                break;

            default:
                System.out.println("정의 되지 않은 타입 : " + chatMessage.getCmd());
                break;
        }
        String sendMessage = objectMapper.writeValueAsString(chatMessage); //보낼매새지

        System.out.println("서버에서 보냄 : " + sendMessage);

        if(chatMessage.getPlayer()==1) {
            WebSocketSession wss = user.get(nameHash.get(getRoomId)[1]);
            wss.sendMessage(new TextMessage(sendMessage));
        } else if(chatMessage.getPlayer()==2) {
            WebSocketSession wss = user.get(nameHash.get(getRoomId)[0]);
            wss.sendMessage(new TextMessage(sendMessage));
        }

        if(roundcounterHash.get(getRoomId)==0){
            chatMessage.setCmd("end");
            sendMessage = objectMapper.writeValueAsString(chatMessage);
            for(int i = 0 ; i<2 ; i++) {
                WebSocketSession wss = user.get(nameHash.get(getRoomId)[i]);
                wss.sendMessage(new TextMessage(sendMessage));
            }
            int a = roundcounterHash.get(getRoomId);
            a = a+1;
            roundcounterHash.put(getRoomId, a);
        }
    }// handleTextMessage : 메시지를 수신시 실행

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        Main_Server Server = Server_list.get(1);

        HashMap<String, Room> Room_List = Server.getRoom_list();

        super.afterConnectionClosed(session, status); // 부모 실행
        user.remove(session.getId(), session);
        System.out.println("B실행");
        String sId = session.getId();
        String anothersId = "";
        boolean realUser = false;

        if(sId!=blacklist) {
            realUser = true;
            Set set = nameHash.entrySet();
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry) iterator.next();

                String key = (String) entry.getKey();
                String[] value = (String[]) entry.getValue();

                if (value[0] == sId || value[1] == sId) {
                    if(value[0]==sId){
                        anothersId = value[1];
                        winnerHash.put(key, 2);
                    }
                    else if ( value[1] == sId){
                        anothersId = value[0];
                        winnerHash.put(key, 1);
                    }
                    getRoomId = key;

                    break;
                }
            }
            int a = index.get(getRoomId);
            a--;
            index.put(getRoomId, a);
            System.out.println("줄었다" + a);
            if (a == 0) {
                index.remove(getRoomId);
                winnerHash.remove(getRoomId);
                userIdHash.remove(getRoomId);
                winnerStackHash.remove(getRoomId);
                winnerScoreHash.remove(getRoomId);
                roundcounterHash.remove(getRoomId);
                nameHash.remove(getRoomId);
                Room_List.remove(getRoomId);
                System.out.println("종료");
                realUser=false;
            }
            if(realUser) {
                int f = winnerHash.get(getRoomId);
                String sendMessage = ("{\"cmd\":\"alert\",\"selecto\":" + Integer.toString(f) + "}");

                WebSocketSession wss = user.get(anothersId);
                wss.sendMessage(new TextMessage(sendMessage));
            }
        }
        System.out.println("소켓 종료");
    }// afterConnectionClosed : 웹 소켓 close시 실행
}
