package com.jzxiang.pickerview.view;

import android.content.Context;
import android.view.View;
import com.jzxiang.pickerview.IController;
import com.jzxiang.pickerview.R;
import com.jzxiang.pickerview.adapters.NumericWheelAdapter;
import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.utils.PickerContants;
import com.jzxiang.pickerview.utils.Utils;
import com.jzxiang.pickerview.wheel.OnWheelChangedListener;
import com.jzxiang.pickerview.wheel.WheelView;
import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/20.
 */
public class TimeWheel {
    Context mContext;

    WheelView year, month, day, hour, minute;
    NumericWheelAdapter mYearAdapter, mMonthAdapter, mDayAdapter, mHourAdapter, mMinuteAdapter;

    IController mIController;
    PickerConfig mPickerConfig;

    OnWheelChangedListener yearListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateMonths();
        }
    };
    OnWheelChangedListener monthListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateDays();
        }
    };
    OnWheelChangedListener dayListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            updateHours();
        }
    };


    public TimeWheel(IController iController, View view, PickerConfig pickerConfig) {
        mIController = iController;
        mPickerConfig = pickerConfig;
        mContext = view.getContext();
        initialize(view);
    }


    public void initialize(View view) {
        initView(view);
        initYear();
        initMonth();
        initDay();
        initHour();
        initMinute();
    }


    void initView(View view) {
        year = (WheelView) view.findViewById(R.id.year);
        month = (WheelView) view.findViewById(R.id.month);
        day = (WheelView) view.findViewById(R.id.day);
        hour = (WheelView) view.findViewById(R.id.hour);
        minute = (WheelView) view.findViewById(R.id.minute);

        switch (mPickerConfig.mType) {
            case ALL:

                break;
            case YEAR_MONTH_DAY:
                Utils.hideViews(hour, minute);
                break;
            case YEAR_MONTH:
                Utils.hideViews(day, hour, minute);
                break;
            case MONTH_DAY_HOUR_MIN:
                Utils.hideViews(year);
                break;
            case HOURS_MINS:
                Utils.hideViews(year, month, day);
                break;
        }

        year.addChangingListener(yearListener);
        year.addChangingListener(monthListener);
        year.addChangingListener(dayListener);
        month.addChangingListener(monthListener);
        month.addChangingListener(dayListener);
        day.addChangingListener(dayListener);
    }

    void initYear() {
        int minYear = mIController.getMinYear();
        int maxYear = mIController.getMaxYear();

        mYearAdapter = new NumericWheelAdapter(mContext, minYear, maxYear, PickerContants.FORMAT, mPickerConfig.mYear);
        mYearAdapter.setConfig(mPickerConfig);
        year.setViewAdapter(mYearAdapter);
        year.setCurrentItem(mIController.getDefaultCalendar().year - minYear);
    }

    void initMonth() {
        updateMonths();
        int curYear = getCurrentYear();
        int minMonth = mIController.getMinMonth(curYear);
        month.setCurrentItem(mIController.getDefaultCalendar().month - minMonth);
        month.setCyclic(mPickerConfig.cyclic);
    }

    void initDay() {
        updateDays();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();

        int minDay = mIController.getMinDay(curYear, curMonth);
        day.setCurrentItem(mIController.getDefaultCalendar().day - minDay);
        day.setCyclic(mPickerConfig.cyclic);
    }

    void initHour() {
        updateHours();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();

        int minHour = mIController.getMinHour(curYear, curMonth, curDay);
        hour.setCurrentItem(mIController.getDefaultCalendar().hour - minHour);
        hour.setCyclic(mPickerConfig.cyclic);
    }

    void initMinute() {
        int minMinute = mIController.getMinMinute();
        int maxMinute = mIController.getMaxMinute();
        mMinuteAdapter = new NumericWheelAdapter(mContext, minMinute, maxMinute, PickerContants.FORMAT, mPickerConfig.mMinute);
        mMinuteAdapter.setConfig(mPickerConfig);
        minute.setCyclic(mPickerConfig.cyclic);
        minute.setViewAdapter(mMinuteAdapter);
    }

    void updateMonths() {
        if (month.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int minMonth = mIController.getMinMonth(curYear);
        int maxMonth = mIController.getMaxMonth();
        mMonthAdapter = new NumericWheelAdapter(mContext, minMonth, maxMonth, PickerContants.FORMAT, mPickerConfig.mMonth);
        mMonthAdapter.setConfig(mPickerConfig);
        month.setViewAdapter(mMonthAdapter);

        if (mIController.isMinYear(curYear)) {
            month.setCurrentItem(0, false);
        }
    }

    void updateDays() {
        if (day.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year.getCurrentItem());
        calendar.set(Calendar.MONTH, curMonth);

        int maxDay = mIController.getMaxDay(curYear, curMonth);
        int minDay = mIController.getMinDay(curYear, curMonth);
        mDayAdapter = new NumericWheelAdapter(mContext, minDay, maxDay, PickerContants.FORMAT, mPickerConfig.mDay);
        mDayAdapter.setConfig(mPickerConfig);
        day.setViewAdapter(mDayAdapter);

        if (mIController.isMinMonth(curYear, curMonth)) {
            day.setCurrentItem(0, true);
        }

        int dayCount = mDayAdapter.getItemsCount();
        if (day.getCurrentItem() >= dayCount) {
            day.setCurrentItem(dayCount - 1, true);
        }
    }

    void updateHours() {
        if (hour.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();

        int minHour = mIController.getMinHour(curYear, curMonth, curDay);
        int maxHour = mIController.getMaxHour();

        mHourAdapter = new NumericWheelAdapter(mContext, minHour, maxHour, PickerContants.FORMAT, mPickerConfig.mHour);
        mHourAdapter.setConfig(mPickerConfig);
        hour.setViewAdapter(mHourAdapter);

        if (mIController.isMinDay(curYear, curMonth, curDay))
            hour.setCurrentItem(0, false);
    }

    public int getCurrentYear() {
        return year.getCurrentItem() + mIController.getMinYear();
    }

    public int getCurrentMonth() {
        int curYear = getCurrentYear();
        return month.getCurrentItem() + +mIController.getMinMonth(curYear);
    }

    public int getCurrentDay() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        return day.getCurrentItem() + mIController.getMinDay(curYear, curMonth);
    }

    public int getCurrentHour() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        return hour.getCurrentItem() + mIController.getMinHour(curYear, curMonth, curDay);
    }

    public int getCurrentMinute() {
        return minute.getCurrentItem() + mIController.getMinMinute();
    }


}
