package com.backbase.kalah.bean;

import org.springframework.stereotype.Component;

/**
 * Created by pradeep on 11/7/16.
 */
public class Player {
    private String playerName;

    public Player() {
    }
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}