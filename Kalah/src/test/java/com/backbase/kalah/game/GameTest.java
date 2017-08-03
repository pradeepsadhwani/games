package com.backbase.kalah.game;

import com.backbase.kalah.bean.Board;
import com.backbase.kalah.exception.InvalidMoveException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by pradeep on 11/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testKalahContext.xml")
public class GameTest {
    Game game;
    Board board;

    public Game getGame() {
        return game;
    }

    @Autowired
    public void setGame(Game game) {
        this.game = game;
    }

    public Board getBoard() {
        return board;
    }

    @Autowired
    public void setBoard(Board board) {
        this.board = board;
    }

    @Before
    public void init() {
    }

    @Test
    public void testSow() {
        try {
            game.sow(board.getRow1().getPits().get(0).getPitId());
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(board.getRow1().getPits().get(0).getNumberOfStone(), 0);
    }

    @Test
    public void testReset() {
        game.reset();
        Assert.assertEquals(board.getRow1().getPits().get(0).getNumberOfStone(), 6);

    }
}
