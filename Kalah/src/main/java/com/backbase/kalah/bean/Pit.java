package com.backbase.kalah.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pradeep on 11/3/16.
 */
public class Pit {
    private Long pitId;
    private List<Stone> stones;
    private static AtomicLong nextId = new AtomicLong();

    public List<Stone> getStones() {
        return stones;
    }

    public void setStones(List<Stone> stones) {
        this.stones = stones;
    }

    public static AtomicLong getNextId() {
        return nextId;
    }

    public Pit() {
        this.pitId = nextId.incrementAndGet();
    }

    public Long getPitId() {
        return pitId;
    }

    public int getNumberOfStone() {
        return stones.size();
    }

    public boolean isEmpty() {
        return stones.isEmpty();
    }

    public void addStones(int numberOfStones) {
        for (int i = 0; i < numberOfStones; i++) {
            Stone stone = new Stone();
            stones.add(stone);
        }
    }

    public void removeStones(int numberOfStones) {
        for (int i = 0; i < numberOfStones; i++) {
            stones.remove(stones.size() - 1);
        }
    }

}
