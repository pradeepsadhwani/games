package com.backbase.kalah.web.vo;

import com.backbase.kalah.bean.Kalah;
import com.backbase.kalah.bean.Pit;
import com.backbase.kalah.bean.Player;
import com.backbase.kalah.game.Game;

import java.util.List;

/**
 * Created by f553457 on 11/7/16.
 */
public class GameViewModel {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Kalah kalah1;
    private Kalah kalah2;
    private List<Pit> pits1;
    private List<Pit> pits2;
    private String result;


    public GameViewModel(Game game) {
        kalah1 = game.getBoard().getKalah1();
        kalah2 = game.getBoard().getKalah2();
        player1 = game.getPlayer1();
        player2 = game.getPlayer2();
        pits1 = game.getPlayerRowMap().get(player1).getPits();
        pits2 = game.getPlayerRowMap().get(player2).getPits();
        currentPlayer = game.getActivePlayer();
        result = game.getResult();
    }

    public String getPlayer1Name() {
        return player1.getPlayerName();
    }

    public String getResult() {
        return result;
    }

    public String getPlayer2Name() {
        return player2.getPlayerName();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getKalah1Stones() {
        return kalah1.getNumberOfStone();
    }

    public int getKalah2Stones() {
        return kalah2.getNumberOfStone();
    }

    public List<Pit> getPits1() {
        return pits1;
    }

    public List<Pit> getPits2() {
        return pits2;
    }

    public boolean isPlayer1Enabled() {
        return currentPlayer.equals(player1);
    }

    public boolean isPlayer2Enabled() {
        return currentPlayer.equals(player2);
    }
}

