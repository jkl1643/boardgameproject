package com.example;

import com.example.Dao.*;

import java.util.HashMap;
import java.util.List;

public class DBcontroller {
    private GameDao gameDao;

    public DBcontroller(GameDao gameDao)
    {
        this.gameDao = gameDao;
    }
    public Game Selectbykey(int key) { return gameDao.Selectbykey(key); }
    public void Buygame(Purchase buy) { gameDao.Buygame(buy); }
    public List<Game> GameRank_list() { return gameDao.SelectRank(); }
    public List<Integer> GameCount_list() { return gameDao.SelectRankCount(); }
    public Integer keyBynick(String nick) { return gameDao.keybynick(nick); }
    public Integer Checkingbuy(HashMap keyset) { return gameDao.Checkingbuy(keyset); }
    public List<Game> Game_mylist(int member_number) { return gameDao.Mygamelist(member_number); }
}
