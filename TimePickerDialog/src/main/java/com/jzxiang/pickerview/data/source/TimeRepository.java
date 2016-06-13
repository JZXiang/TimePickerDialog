package com.jzxiang.pickerview.data.source;

import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.data.WheelCalendar;
import com.jzxiang.pickerview.utils.PickerContants;
import com.jzxiang.pickerview.utils.Utils;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/6/13.
 */
public class TimeRepository implements TimeDataSource {
    PickerConfig mPickerConfig;
    WheelCalendar mCalendarMin, mCalendarMax;

    boolean mIsMinNoRange, mIsMaxNoRange;

    public TimeRepository(PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;
        mCalendarMin = pickerConfig.mMinCalendar;
        mCalendarMax = pickerConfig.mMaxCalendar;

        mIsMinNoRange = mCalendarMin.isNoRange();
        mIsMaxNoRange = mCalendarMax.isNoRange();
    }

    @Override
    public int getMinYear() {
        if (mIsMinNoRange)
            return PickerContants.DEFAULT_MIN_YEAR;
        else
            return mCalendarMin.year;
    }

    @Override
    public int getMaxYear() {
        if (mIsMaxNoRange)
            return getMinYear() + PickerContants.YEAR_COUNT;

        return mCalendarMax.year;
    }

    @Override
    public int getMinMonth(int year) {
        if (!mIsMinNoRange && Utils.isTimeEquals(mCalendarMin, year))
            return mCalendarMin.month;

        return PickerContants.MIN_MONTH;
    }

    @Override
    public int getMaxMonth(int year) {
        if (!mIsMaxNoRange && Utils.isTimeEquals(mCalendarMax, year))
            return mCalendarMax.month;

        return PickerContants.MAX_MONTH;
    }

    @Override
    public int getMinDay(int year, int month) {
        if (!mIsMinNoRange && Utils.isTimeEquals(mCalendarMin, year, month))
            return mCalendarMin.day;

        return PickerContants.MIN_DAY;
    }

    @Override
    public int getMaxDay(int year, int month) {
        if (!mIsMaxNoRange && Utils.isTimeEquals(mCalendarMax, year, month))
            return mCalendarMax.day;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month - 1);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getMinHour(int year, int month, int day) {
        if (!mIsMinNoRange && Utils.isTimeEquals(mCalendarMin, year, month, day))
            return mCalendarMin.hour;
        else
            return PickerContants.MIN_HOUR;
    }

    @Override
    public int getMaxHour(int year, int month, int day) {
        if (!mIsMaxNoRange && Utils.isTimeEquals(mCalendarMax, year, month, day))
            return mCalendarMax.hour;

        return PickerContants.MAX_HOUR;
    }

    @Override
    public int getMinMinute(int year, int month, int day, int hour) {
        if (!mIsMinNoRange && Utils.isTimeEquals(mCalendarMin, year, month, day, hour))
            return mCalendarMin.minute + 1;
        else
            return PickerContants.MIN_MINUTE;
    }

    @Override
    public int getMaxMinute(int year, int month, int day, int hour) {
        if (!mIsMaxNoRange && Utils.isTimeEquals(mCalendarMax, year, month, day, hour))
            return mCalendarMax.minute;

        return PickerContants.MAX_MINUTE;
    }

    @Override
    public boolean isMinYear(int year) {
        return Utils.isTimeEquals(mCalendarMin, year);
    }

    @Override
    public boolean isMinMonth(int year, int month) {
        return Utils.isTimeEquals(mCalendarMin, year, month);
    }

    @Override
    public boolean isMinDay(int year, int month, int day) {
        return Utils.isTimeEquals(mCalendarMin, year, month, day);
    }

    @Override
    public boolean isMinHour(int year, int month, int day, int hour) {
        return Utils.isTimeEquals(mCalendarMin, year, month, day, hour);
    }


    @Override
    public WheelCalendar getDefaultCalendar() {
        return mPickerConfig.mCurrentCalendar;
    }
}
