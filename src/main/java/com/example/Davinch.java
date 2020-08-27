package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class Card
{
    private String value;
    private String color;
    private int isopen;

    public Card(String value, String color) {
        this.value = value;
        this.color = color;
        isopen = 1;
        //////////////////////////////////////////// 완성시 0으로 수정하기
    }

    public String getValue() { return value; }
    public String getColor() { return color; }
    public int getIsopen() { return isopen; }
    public void setIsopen(int isopen) { this.isopen = isopen; }
}

class PlayerDeck
{
    private List<Card> Deck;

    public void add(Card card)
    {
        Deck.add(card);
    }

    public boolean check(int num, String value )
    {
        Card card = Deck.get(num);
        if(card.getValue().equals(value))
            return true;
        else
            return false;
    }

    PlayerDeck() {
       Deck = new ArrayList<Card>();
    }

    public List<Card> getDeck() { return Deck; }
}

public class Davinch {
    private int turn;
    private List<Card> Deck;
    private List<Integer> players;
    private HashMap<Integer, PlayerDeck> pDeck;
    private int maxplayer = 4;

    public Davinch() {
        this.turn = -1;
        this.players = new ArrayList<Integer>();
        Deck = new ArrayList<Card>();

        for(int i = 0; i < 12; i ++)
        {
            Deck.add(new Card(String.valueOf(i), "Black"));
            Deck.add(new Card(String.valueOf(i), "White"));
        }
        Deck.add(new Card("-", "Black"));
        Deck.add(new Card("-", "White"));
    }

    public boolean join(int userid)
    {
        players.add(userid);
        return true;
    }

    public boolean start()
    {
        for(int key : players)
            pDeck.put(key, new PlayerDeck());

        // 인원에 따라 패를 랜덤으로 주는 부분
        int p = 0;

        if (players.size() > 3) p = 3;
        else if (players.size() > 1) p = 4;

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        for(int i = 0; i < p; i++)
        {
            for(int key: players)
            {

                int r;
                Card card;
                do{
                    r = rand.nextInt(Deck.size());
                }while((card = Deck.get(r)).getValue().equals("-"));
                pDeck.get(key).add(card);
                Deck.remove(card);
            }
        }
        // 카드를 랜덤 배분한다.


        turn = rand.nextInt(players.size());
        //시작 하는 사람은 랜덤으로 정해진다 .

        return true;
    }
    // 플레이어의 수는 유동적이다
    // 각 플레이어의 패 값을 14개씩을 가진다.
    // 맥스 플레이어 값이 필요하다
    // 현재 플레이어 인원수가 필요하다.
    // 현재 상태에 대한 값이 필요하다
    // 현재상태가 웨이팅이면 시작버튼만 기능,
    // 현재상태가 시작이면 모든 변수들을 초기화 하고 시작을 준비한다.


    //1. 기본 변수들 정하기
    //2. 시작 까지만 만들기
    //3. jsp 단의 완성. 모달 창등의 완성
    //4. 나머지 자바단 완성.






//다빈치 코드
//카드 0 ~ 11, -  ( 2쌍 )
//
//0. 설정
//랜덤으로 배정 ( 2~3인 4개 4인 3개) ( 조커의 경우는 다시 ) ( 같은수의 경우 검정색을 왼쪽으로 )
//
//1. 플레이어 턴
//- 남은 패를 클릭시 패를 하나 가져오도록  ( 랜덤 )
//- 조커의 경우는 위치를 지정하도록 아니면 자동으로 정렬 ( 5초, 고정 시간을 둬야 조커를 안들킴 )
//- 상대의 타일을 클릭 가능하게 , 클릭하고 번호를 지정하기 (모달창 띄워서 ?, 3초정도 결과 로그나오게)
//- 맞췄을땐 상대 블럭을 보이게.
//- 틀렸을땐 내가 가져온 블럭을 보이게.
//- 모달창으로 다시 선택하기와 턴 종료버튼이 나오고 틀렸을땐 다시선택하기 버튼 비활성화
//- 턴 종료
//
//
//
//변수
//플레이어 번호
//각 플레이어의 패 세트 ( 총 14개로 )
//{ 패 번호, 공개여부 }
//가운데 패
//현재 플레이어 수
//턴 페이즈 = 세션.


}
