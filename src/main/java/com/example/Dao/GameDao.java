package com.example.Dao;

import java.util.List;

public interface GameDao {
    Game Selectbykey(int key);

    Game Selectbyname(String name);

    List<Game> SelectAll();

    List<Game> SelectRank();
}
