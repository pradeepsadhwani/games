package com.backbase.kalah.game;

import com.backbase.kalah.bean.*;
import com.backbase.kalah.constant.KalahConstant;
import com.backbase.kalah.constant.Status;
import com.backbase.kalah.exception.InvalidMoveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pradeep on 11/7/16.
 */
@Service
public class Game {
    @Autowired
    private Board board;
    private Player player1, player2;
    private int turn;
    private String result;
    private Map<Player, Row> playerRowMap;
    private Map<Player, Kalah> playerKalahMap;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Map<Player, Kalah> getPlayerKalahMap() {
        return playerKalahMap;
    }

    public void setPlayerKalahMap(Map<Player, Kalah> playerKalahMap) {
        this.playerKalahMap = playerKalahMap;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<Player, Row> getPlayerRowMap() {
        return playerRowMap;
    }

    public void setPlayerRowMap(Map<Player, Row> playerRowMap) {
        this.playerRowMap = playerRowMap;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    @PostConstruct
    public void init() {
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
        ;
        this.playerRowMap = new HashMap<Player, Row>(2);
        this.playerKalahMap = new HashMap<Player, Kalah>(2);

        this.turn = 1;
        this.result = Status.IN_PROGRESS.getStatus();

        initializePlayerRowMap();
        initializePlayerKalahMap();
    }

    private void initializePlayerRowMap() {
        playerRowMap.put(player1, board.getRow1());
        playerRowMap.put(player2, board.getRow2());
    }

    private void initializePlayerKalahMap() {
        playerKalahMap.put(player1, board.getKalah1());
        playerKalahMap.put(player2, board.getKalah2());
    }

    public void changeTurn() {
        if (turn == 1) {
            turn = 2;
        } else {
            turn = 1;
        }
    }

    public Player getActivePlayer() {
        if (turn == 1) {
            return player1;
        } else {
            return player2;
        }
    }

    private Player getOppositePlayer() {
        if (turn != 1) {
            return player1;
        } else {
            return player2;
        }
    }

    public void reset() {
        init();
        board.initializeBoard();
    }

    public void sow(Long pitId) throws InvalidMoveException {
        Player player = getActivePlayer();
        Player oppositePlayer = getOppositePlayer();
        Row row = playerRowMap.get(player);
        Row oppositeRow = playerRowMap.get(oppositePlayer);
        Kalah kalah = playerKalahMap.get(player);
        Kalah oppositeKalah = playerKalahMap.get(oppositePlayer);
        List<Pit> pits = row.getPits();
        List<Pit> oppositePits = oppositeRow.getPits();

        boolean playAgain = Boolean.FALSE;

        Integer pitIndex = row.getIndexOfPit(pitId);
        if (pitIndex == null || pits.get(pitIndex).isEmpty()) {
            throw new InvalidMoveException("You have selected an Invalid PIT");
        }

        int stonesToSow = pits.get(pitIndex).getNumberOfStone();
        pits.get(pitIndex).removeStones(stonesToSow);
        while (stonesToSow > 0) {
            for (int i = pitIndex + 1; stonesToSow > 0 && i < pits.size(); i++) {
                stonesToSow--;

                if (stonesToSow == 0 && pits.get(i).isEmpty()) {
                    //add stone in kalah/store
                    kalah.addStones(1);
                    //add opposite pit stones in kalah
                    int noOfStones = oppositeRow.getPits().get((KalahConstant.NO_OF_PITS_PER_ROW - 1) - i).getNumberOfStone();
                    oppositeRow.getPits().get((KalahConstant.NO_OF_PITS_PER_ROW - 1) - i).removeStones(noOfStones);
                    kalah.addStones(noOfStones);
                    continue;
                }
                pits.get(i).addStones(1);
            }
            pitIndex = -1;//to reset the pointer where distribution started
            if (stonesToSow > 0) {
                stonesToSow--;
                kalah.addStones(1);
                if (stonesToSow == 0) {
                    playAgain = Boolean.TRUE;
                }
            }

            for (int i = 0; stonesToSow > 0 && i < oppositePits.size(); i++) {
                stonesToSow--;
                oppositePits.get(i).addStones(1);
            }
        }

        if (stonesToSow == 0 && (oppositeRow.isEmpty() || row.isEmpty())) {
            if (oppositeRow.isEmpty()) {
                int noOfStones = 0;
                for (Pit pit : pits) {
                    noOfStones = noOfStones + pit.getNumberOfStone();
                    pit.removeStones(pit.getNumberOfStone());
                }
                kalah.addStones(noOfStones);
            } else if (row.isEmpty()) {
                int noOfStones = 0;
                for (Pit pit : oppositePits) {
                    noOfStones = noOfStones + pit.getNumberOfStone();
                    pit.removeStones(pit.getNumberOfStone());
                }
                oppositeKalah.addStones(noOfStones);
            }
            if (kalah.getNumberOfStone() > oppositeKalah.getNumberOfStone()) {
                this.result = Status.PLAYER_ONE_WIN.getStatus();
            } else if (kalah.getNumberOfStone() > oppositeKalah.getNumberOfStone()) {
                this.result = Status.PLAYER_TWO_WIN.getStatus();
            } else if (kalah.getNumberOfStone() == oppositeKalah.getNumberOfStone()) {
                this.result = Status.ITS_A_TIE.getStatus();

            }
        }

        if (!playAgain)
            changeTurn();
    }

}
