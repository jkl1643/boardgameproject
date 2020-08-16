package com.example;

import com.example.Dao.*;

import java.util.List;

public class DBcontroller {
    private GameDao gameDao;

    public DBcontroller(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    public List<Game> GameRank_list() {
        return gameDao.SelectRank();
    }
}
