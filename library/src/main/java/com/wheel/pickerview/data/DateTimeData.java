package com.wheel.pickerview.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzxiang on 16/4/19.
 */
public class DateTimeData {

    public static final String[] MONTHS = new String[12];
    public static final String YEAR = "年";
    public static final String MONTH = "月";
    public static final String DAY = "日";
    public static final String HOUR = "时";
    public static final String MINUTE = "分";
    private static final int DEFAULT_MIN_YEAR = 2015;
    private static final int YEAR_COUNT = 50;
    private static final int DEFAULT_MAX_YEAR = DEFAULT_MIN_YEAR + YEAR_COUNT;
    private TimeRangeManager mTimeRangeManager;
    private WheelCalendar mCalendarMin;

    protected DateTimeData(long minMilliseconds) {
        initData(minMilliseconds);
    }


    void initData(long minMilliseconds) {

        mTimeRangeManager = new TimeRangeManager(minMilliseconds);
        mCalendarMin = mTimeRangeManager.getMinCalendar();

        for (int i = 0; i < MONTHS.length; i++) {
            MONTHS[i] = String.valueOf(i + 1) + MONTH;
        }
    }


    public String[] getYearsData() {
        int minYear = mCalendarMin.year;
        List<String> list = new ArrayList<>();
        for (int i = minYear; i <= minYear + YEAR_COUNT; i++) {
            list.add(minYear + YEAR);
        }
        return (String[]) list.toArray();
    }


    public String[] getMonthsData(int currentYear) {
        if (mTimeRangeManager.isShowAll(currentYear))
            return MONTHS;

        int minMonth = mCalendarMin.month + 1;
        String[] months = new String[MONTHS.length - minMonth];
        System.arraycopy(MONTHS, minMonth - 1, months, 0, months.length);
        return months;

    }

//    /**
//     * get the days data
//     *
//     * @param date date length must less than 2
//     * @return
//     */
//    public int getDaysData(int... date) {
//        if (mTimeRangeManager.isShowAll(date))
//            return
//        int minYear = mCalendarMin.get(Calendar.YEAR);
//        int minMonth = mCalendarMin.get(Cal);

//    }


}
