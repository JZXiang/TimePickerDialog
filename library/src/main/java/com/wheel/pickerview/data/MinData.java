package com.wheel.pickerview.data;


/**
 * Created by jzxiang on 16/4/20.
 */
public class MinData {

    static final int YEAR_COUNT = DefaultData.YEAR_COUNT;

    WheelCalendar mMinCalendar;

    public MinData(WheelCalendar wheelCalendar) {
        mMinCalendar = wheelCalendar;
    }


    String[] getYears() {
        String[] years = new String[YEAR_COUNT];
        for (int i = 0; i <= YEAR_COUNT; i++) {
            years[i] = Integer.toString(mMinCalendar.year + i) + DefaultData.YEAR;
        }
        return years;
    }


}
