package com.backbase.kalah.exception;

/**
 * Created by pradeep on 11/7/16.
 */
public class InvalidMoveException extends Exception {

    public InvalidMoveException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
