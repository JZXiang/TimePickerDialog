package com.wheel.pickerview.data;

import com.wheel.pickerview.wheel.ItemsRange;

/**
 * Created by jzxiang on 16/4/19.
 */
public class TimeRange extends ItemsRange {

    private int last;

    public TimeRange(int first, int last) {
        this.last = last;
    }

    @Override
    public int getCount() {
        return getLast() - getFirst() + 1;
    }

    @Override
    public int getLast() {
        return last;
    }

    @Override
    public boolean contains(int index) {
        if (getFirst() == 0 && getLast() == 0)
            return true;

        if (getFirst() == 0) {
            return index <= getLast();
        }

        if (getLast() == 0) {
            return index >= getFirst();
        }
        return index <= getLast() && index >= getFirst();
    }
}
