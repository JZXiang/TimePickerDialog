package com.wheel.pickerview.view;

import android.content.Context;
import android.view.View;

import com.wheel.pickerview.R;
import com.wheel.pickerview.TimePickerDialog;
import com.wheel.pickerview.adapters.NumericWheelAdapter;
import com.wheel.pickerview.data.DataController;
import com.wheel.pickerview.utils.PickerContants;
import com.wheel.pickerview.utils.Utils;
import com.wheel.pickerview.wheel.WheelView;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/20.
 */
public class TimeWheel {
    private DataController mDataController;
    private View mView;
    private TimePickerDialog.Type mType;
    private Context mContext;

    private WheelView year, month, day, hour, minute;

    public TimeWheel(DataController dataController, View view) {
        this(dataController, view, PickerContants.DEFAULT_TYPE);
    }

    public TimeWheel(DataController dataController, View view, TimePickerDialog.Type type) {
        mDataController = dataController;
        mView = view;
        mType = type;
        mContext = view.getContext();
    }

    void init() {

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

        int minYear = mDataController.getMinYear();
        int maxYear = mDataController.getMaxYear();
        year.setViewAdapter(new NumericWheelAdapter(mContext, minYear, maxYear, PickerContants.FORMAT, PickerContants.YEAR));

    }

    public void setCurrentTiem(Calendar calendar) {

    }


}
