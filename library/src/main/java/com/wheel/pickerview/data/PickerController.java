package com.wheel.pickerview.data;


/**
 * Created by jzxiang on 16/4/20.
 */
public interface PickerController {

    int getMinYear();

    int getMaxYear();

    int getMinMonth(int currentYear);

    int getMaxMonth();

    int getMinDay(int year, int month);

    int getMaxDay(int year, int month);

    int getMinHour(int year, int month, int day);

    int getMaxHour();

    int getMinMinute();

    int getMaxMinute();

    boolean isMinYear(int year);

    boolean isMinMonth(int year, int month);

    boolean isMinDay(int year, int month, int day);

    boolean isNoRange();

    WheelCalendar getDefaultCalendar();
}
