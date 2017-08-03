package com.backbase.kalah.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by pradeep on 11/3/16.
 */
public class Row {
    private Long rowId;
    private List<Pit> pits;
    private static AtomicLong nextId = new AtomicLong();


    public Row() {
        this.rowId = nextId.incrementAndGet();
        pits = new ArrayList<Pit>();
    }

    public List<Pit> getPits() {
        return pits;
    }


    public Long getRowId() {
        return rowId;
    }

    public Pit getPitById(Long pitId) {
        for (Pit pit : pits) {
            if (pitId != null && pit.getPitId().equals(pitId)) {
                return pit;
            }
        }
        return null;
    }

    public Integer getIndexOfPit(Long pitId) {
        for (int i = 0; i < pits.size(); i++) {
            if (pitId != null && pits.get(i).getPitId().equals(pitId)) {
                return i;
            }
        }
        return null;
    }

    public Pit getOppositePit(List<Pit> oppositePits, Long currentPitId) {
        int index = getIndexOfPit(currentPitId);
        return oppositePits.get(index);
    }

    public boolean isEmpty() {
        int allStones = 0;
        for (Pit pit : pits) {
            allStones = allStones + pit.getNumberOfStone();
        }
        if (allStones == 0) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public int getTotalNumberOfStones(){
        int allStones = 0;
        for (Pit pit : pits) {
            allStones = allStones + pit.getNumberOfStone();
        }
        return allStones;
    }
}
