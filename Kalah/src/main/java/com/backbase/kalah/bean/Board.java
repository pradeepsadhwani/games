package com.backbase.kalah.bean;

import com.backbase.kalah.constant.KalahConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pradeep on 11/3/16.
 */
@Component
public class Board {
    private Long boardId;
    private static AtomicLong nextId = new AtomicLong();
    Kalah kalah1, kalah2;
    Row row1, row2;

    public Board() {
        System.out.println("Board Initialized");
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public static AtomicLong getNextId() {
        return nextId;
    }

    public static void setNextId(AtomicLong nextId) {
        Board.nextId = nextId;
    }

    public Kalah getKalah1() {
        return kalah1;
    }

    public void setKalah1(Kalah kalah1) {
        this.kalah1 = kalah1;
    }

    public Kalah getKalah2() {
        return kalah2;
    }

    public void setKalah2(Kalah kalah2) {
        this.kalah2 = kalah2;
    }

    public Row getRow1() {
        return row1;
    }

    public void setRow1(Row row1) {
        this.row1 = row1;
    }

    public Row getRow2() {
        return row2;
    }

    public void setRow2(Row row2) {
        this.row2 = row2;
    }

    public Long getBoardId() {
        return boardId;
    }

    @PostConstruct
    public void initializeBoard() {
        this.boardId = nextId.incrementAndGet();
        kalah1 = new Kalah();
        kalah2 = new Kalah();
        this.row1 = new Row();
        this.row2 = new Row();
        initializeRow(row1);
        initializeRow(row2);
    }

    private void initializeRow(Row row){
        for (int j = 0; j < KalahConstant.NO_OF_PITS_PER_ROW; j++) {
            List<Stone> stones = new ArrayList<Stone>();
            Pit pit = new Pit();
            for (int k = 0; k < KalahConstant.NO_OF_STONES_PER_PIT; k++) {
                Stone stone = new Stone();
                stones.add(stone);
            }
            pit.setStones(stones);
            row.getPits().add(pit);
        }
    }
}
