package com.backbase.kalah.constant;

/**
 * Created by pradeep on 11/7/16.
 */
public enum Status {


    IN_PROGRESS("Game in Progress"),
    PLAYER_ONE_WIN("Player 1 Wins"),
    ITS_A_TIE("It's a Tie"),
    PLAYER_TWO_WIN("Player 2 Wins");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
