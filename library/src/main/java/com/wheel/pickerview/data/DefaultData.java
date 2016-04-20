package com.wheel.pickerview.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by jzxiang on 16/4/20.
 */
public class DefaultData {

    public static final int DEFAULT_MIN_YEAR = 2015;
    public static final int YEAR_COUNT = 50;

    public static final String[] YEARS = new String[YEAR_COUNT];
    public static final String[] MONTHS = new String[12];
    public static final String[] HOURS = new String[24];
    public static final String[] MINUTES = new String[60];


    public static final String YEAR = "年";
    public static final String MONTH = "月";
    public static final String DAY = "日";
    public static final String HOUR = "时";
    public static final String MINUTE = "分";
    public static final String FORMAT = "%02d";

    private static DefaultData defaultData;

    private DefaultData() {
        initYears();
        initMonths();
        initHours();
        initMinutes();
    }

    public static DefaultData getIntance() {
        if (defaultData == null) {
            defaultData = new DefaultData();
        }
        return defaultData;
    }

    /**
     * @param params [type,year,month]
     * @return
     */
    public String[] getDefaultData(int... params) {
        switch (params[0]) {
            case Calendar.YEAR:
                return YEARS;
            case Calendar.MONTH:
                return MONTHS;
            case Calendar.DAY_OF_MONTH:
                return getDays(params[1], params[2]);
            case Calendar.HOUR_OF_DAY:
                return HOURS;
            case Calendar.MINUTE:
                return MINUTES;
        }
        return null;
    }


    void initYears() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < YEAR_COUNT; i++) {
            YEARS[i] = Integer.toString(DEFAULT_MIN_YEAR + i) + YEAR;
        }
    }

    void initMonths() {
        for (int i = 0; i < MONTHS.length; i++) {
            MONTHS[i] = String.format(FORMAT, i + 1) + MONTH;
        }
    }

    void initHours() {
        for (int i = 0; i < HOURS.length; i++) {
            HOURS[i] = String.format(FORMAT, i) + HOUR;
        }
    }

    void initMinutes() {
        for (int i = 0; i < MINUTES.length; i++) {
            MINUTES[i] = String.format(FORMAT, i) + MINUTE;
        }
    }


    String[] getDays(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String[] days = new String[maxDay];
        for (int i = 0; i < maxDay; i++) {
            days[i] = String.format(FORMAT, (i + 1)) + DAY;
        }
        return days;
    }


}
