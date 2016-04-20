package com.wheel.pickerview.data;

import com.wheel.pickerview.utils.PickerContants;
import com.wheel.pickerview.utils.Utils;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/19.
 */
public class DataController implements PickerController {

    private WheelCalendar mCalendarMin;

    public DataController(long minMilliseconds) {
        mCalendarMin = new WheelCalendar(minMilliseconds);
    }

    @Override
    public int getMinYear() {
        if (isNoRange())
            return PickerContants.DEFAULT_MIN_YEAR;
        else
            return mCalendarMin.year;
    }

    @Override
    public int getMaxYear() {
        return getMinYear() + PickerContants.YEAR_COUNT;
    }

    @Override
    public int getMinMonth(int year) {
        if (!isNoRange() && Utils.isTimeEquals(mCalendarMin, year))
            return mCalendarMin.month;
        else
            return PickerContants.MIN_MONTH;
    }

    @Override
    public int getMaxMonth() {
        return PickerContants.MAX_MONTH;
    }

    @Override
    public int getMinDay(int year, int month) {
        if (!isNoRange() && Utils.isTimeEquals(mCalendarMin, year, month))
            return mCalendarMin.day;
        else
            return PickerContants.MIN_DAY;
    }

    @Override
    public int getMaxDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getMinHour(int year, int month, int day) {
        if (!isNoRange() && Utils.isTimeEquals(mCalendarMin, year, month, day))
            return mCalendarMin.hour + 1;
        else
            return PickerContants.MIN_HOUR;
    }

    @Override
    public int getMaxHour() {
        return PickerContants.MAX_HOUR;
    }

    @Override
    public int getMinMinute() {
        return PickerContants.MIN_MINUTE;
    }

    @Override
    public int getMaxMinute() {
        return PickerContants.MAX_MINUTE;
    }

    @Override
    public boolean isNoRange() {
        return mCalendarMin.isNoRange();
    }
}
