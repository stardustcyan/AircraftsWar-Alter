package edu.hitsz.dao;

import java.util.List;

public interface PlayerDAO {
    void getPlayerList();
    void addPlayer(String playerName, int score);
    void deletePlayer(int playerIndex);

    void printPlayerList();
    void savePlayerList();
}
