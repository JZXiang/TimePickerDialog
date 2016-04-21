package com.wheel.pickerview.view;

import android.content.Context;
import android.view.View;

import com.wheel.pickerview.R;
import com.wheel.pickerview.adapters.NumericWheelAdapter;
import com.wheel.pickerview.data.PickerController;
import com.wheel.pickerview.data.Type;
import com.wheel.pickerview.utils.PickerContants;
import com.wheel.pickerview.utils.Utils;
import com.wheel.pickerview.wheel.OnWheelChangedListener;
import com.wheel.pickerview.wheel.WheelView;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/20.
 */
public class TimeWheel {
    private View mView;
    private Type mType;
    private Context mContext;

    private WheelView year, month, day, hour, minute;
    private NumericWheelAdapter mYearAdapter, mMonthAdapter, mDayAdapter, mHourAdapter, mMinuteAdapter;

    private int mTextSize;
    private int mTextColor;

    private PickerController mPickerController;
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

    public TimeWheel(PickerController pickerController, View view) {
        this(pickerController, view, PickerContants.DEFAULT_TYPE);
    }

    public TimeWheel(PickerController pickerController, View view, Type type) {
        mPickerController = pickerController;
        mView = view;
        mType = type;
        mContext = view.getContext();

//        mTextSize = mContext.getResources().getDimensionPixelSize(R.dimen.picker_default_text_size);
        mTextSize = 10;
        mTextColor = mContext.getResources().getColor(R.color.picker_default_text_color);

        initialize();
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
        initialize();
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
        initialize();
    }

    public void initialize() {
        initView();
        initYear();
        initMonth();
        initDay();
        initHour();
        initMinute();
    }

    void initView() {
        year = (WheelView) mView.findViewById(R.id.year);
        month = (WheelView) mView.findViewById(R.id.month);
        day = (WheelView) mView.findViewById(R.id.day);
        hour = (WheelView) mView.findViewById(R.id.hour);
        minute = (WheelView) mView.findViewById(R.id.minute);

        switch (mType) {
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

        month.addChangingListener(monthListener);
        day.addChangingListener(dayListener);
        year.addChangingListener(yearListener);
    }

    void initYear() {
        int minYear = mPickerController.getMinYear();
        int maxYear = mPickerController.getMaxYear();
        mYearAdapter = new NumericWheelAdapter(mContext, minYear, maxYear, PickerContants.FORMAT, PickerContants.YEAR);
        mYearAdapter.setTextColor(mTextColor);
        mYearAdapter.setTextSize(mTextSize);
        mYearAdapter.setTextSize(mTextSize);
        year.setViewAdapter(mYearAdapter);
        year.setCurrentItem(mPickerController.getDefaultCalendar().year - minYear);
    }

    void initMonth() {
        updateMonths();
        int curYear = getCurrentYear();
        int minMonth = mPickerController.getMinMonth(curYear);
        month.setCurrentItem(mPickerController.getDefaultCalendar().month - minMonth);
    }

    void initDay() {
        updateDays();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();

        int minDay = mPickerController.getMinDay(curYear, curMonth);
        day.setCurrentItem(mPickerController.getDefaultCalendar().day - minDay);
    }

    void initHour() {
        updateHours();
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();

        int minHour = mPickerController.getMinHour(curYear, curMonth, curDay);
        hour.setCurrentItem(mPickerController.getDefaultCalendar().hour - minHour);
    }

    void initMinute() {
        int minMinute = mPickerController.getMinMinute();
        int maxMinute = mPickerController.getMaxMinute();
        mMinuteAdapter = new NumericWheelAdapter(mContext, minMinute, maxMinute, PickerContants.FORMAT, PickerContants.MINUTE);
        mMinuteAdapter.setTextSize(mTextSize);
        mMinuteAdapter.setTextColor(mTextColor);
        minute.setViewAdapter(mMinuteAdapter);
    }

    void updateMonths() {
        if (month.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int minMonth = mPickerController.getMinMonth(curYear);
        int maxMonth = mPickerController.getMaxMonth();
        mMonthAdapter = new NumericWheelAdapter(mContext, minMonth, maxMonth, PickerContants.FORMAT, PickerContants.MONTH);
        mMonthAdapter.setTextSize(mTextSize);
        mMonthAdapter.setTextColor(mTextColor);
        month.setViewAdapter(mMonthAdapter);

        if (mPickerController.isMinYear(curYear)) {
            month.setCurrentItem(0, true);
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

        int maxDay = mPickerController.getMaxDay(curYear, curMonth);
        int minDay = mPickerController.getMinDay(curYear, curMonth);
        mDayAdapter = new NumericWheelAdapter(mContext, minDay, maxDay, PickerContants.FORMAT, PickerContants.DAY);
        mDayAdapter.setTextColor(mTextColor);
        mDayAdapter.setTextSize(mTextSize);
        day.setViewAdapter(mDayAdapter);

        if (mPickerController.isMinMonth(curYear, curMonth)) {
            day.setCurrentItem(0, true);
        }
    }

    void updateHours() {
        if (hour.getVisibility() == View.GONE)
            return;

        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();

        int minHour = mPickerController.getMinHour(curYear, curMonth, curDay);
        int maxHour = mPickerController.getMaxHour();

        mHourAdapter = new NumericWheelAdapter(mContext, minHour, maxHour, PickerContants.FORMAT, PickerContants.HOUR);
        mHourAdapter.setTextSize(mTextSize);
        mHourAdapter.setTextColor(mTextColor);
        hour.setViewAdapter(mHourAdapter);

        if (mPickerController.isMinDay(curYear, curMonth, curDay))
            hour.setCurrentItem(0, true);
    }

    public int getCurrentYear() {
        return year.getCurrentItem() + mPickerController.getMinYear();
    }

    public int getCurrentMonth() {
        int curYear = getCurrentYear();
        return month.getCurrentItem() + +mPickerController.getMinMonth(curYear);
    }

    public int getCurrentDay() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        return day.getCurrentItem() + mPickerController.getMinDay(curYear, curMonth);
    }

    public int getCurrentHour() {
        int curYear = getCurrentYear();
        int curMonth = getCurrentMonth();
        int curDay = getCurrentDay();
        return hour.getCurrentItem() + mPickerController.getMinHour(curYear, curMonth, curDay);
    }

    public int getCurrentMinute() {
        return minute.getCurrentItem() + mPickerController.getMinMinute();
    }


}
