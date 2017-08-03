package com.backbase.kalah.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pradeep on 11/3/16.
 */
public class Kalah {
    private Long kalahId;
    private List<Stone> stones;

    private static AtomicLong nextId = new AtomicLong();


    public Kalah() {
        this.kalahId = nextId.incrementAndGet();
        stones = new ArrayList<Stone>();
    }

    public Long getKalahId() {
        return kalahId;
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
