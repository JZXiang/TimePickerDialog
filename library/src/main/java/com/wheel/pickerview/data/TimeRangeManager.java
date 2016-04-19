package com.wheel.pickerview.data;


/**
 * Created by jzxiang on 16/4/19.
 */
public class TimeRangeManager {


    private WheelCalendar min;

    public TimeRangeManager(long minMillseconds) {
        min = new WheelCalendar(minMillseconds);
    }

    public WheelCalendar getMinCalendar() {
        return min;
    }

    /**
     * isShowAllMonths [year]
     * isShowAllDays [year,month]
     * isSHowAllHours [year,month,day]
     *
     * @param date date length must less than three
     * @return isShowALl
     */
    public boolean isShowAll(int... date) {
        if (date.length > 3)
            throw new RuntimeException("date length must less than 3");

        if (min.isNoRange())
            return true;

        switch (date.length) {
            case 1:
                return isShowAllMonth(date);
            case 2:
                return isShowAllDay(date);
            case 3:
                return isShowAllHours(date);
        }

        return false;
    }

    /**
     * @param date [year]
     * @return
     */
    private boolean isShowAllMonth(int... date) {
        return date[0] != min.year;
    }

    /**
     * @param date [year,month]
     * @return
     */
    private boolean isShowAllDay(int... date) {
        return !(date[0] == min.year && date[1] == min.month);
    }

    private boolean isShowAllHours(int... date) {
        return !(date[0] == min.year && date[1] == min.month && date[2] == min.day);
    }


}
