package com.example.Dao;

import java.util.HashMap;
import java.util.List;

public interface GameDao {
    Game Selectbykey(int key);
    Game Selectbyname(String name);
    void Buygame(Purchase buy);
    List<Game> SelectAll();
    List<Game> SelectRank();
    List<Integer> SelectRankCount();
    Integer keybynick(String nick);
    Integer Checkingbuy(HashMap keyset);
    List<Game> Mygamelist(int member_number);
    Gamerecord Statbynick(HashMap keyset);
}
