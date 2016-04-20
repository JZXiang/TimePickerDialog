package com.wheel.pickerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.wheel.pickerview.data.DataController;
import com.wheel.pickerview.listener.DateSetListener;
import com.wheel.pickerview.utils.PickerContants;
import com.wheel.pickerview.view.TimeWheel;
import com.wheel.pickerview.wheel.WheelView;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/19.
 */
public class TimePickerDialog extends DialogFragment {
    private DataController mDataController;
    private DateSetListener mCallBack;
    private TimeWheel mTimeWheel;

    public static TimePickerDialog newIntance(DateSetListener dateSetListener) {
        return newIntance(dateSetListener, Calendar.getInstance(), 0, PickerContants.DEFAULT_TYPE);
    }

    public static TimePickerDialog newIntance(DateSetListener dateSetListener, Type type) {
        return newIntance(dateSetListener, Calendar.getInstance(), 0, type);
    }

    public static TimePickerDialog newIntance(DateSetListener dateSetListener, Calendar cllendar, Type type) {
        return newIntance(dateSetListener, cllendar, 0, type);
    }

    public static TimePickerDialog newIntance(DateSetListener dateSetListener, Calendar calendar, long minMillSeconds, Type type) {
        TimePickerDialog timePickerDialog = new TimePickerDialog();
        timePickerDialog.initialize(dateSetListener, calendar, minMillSeconds);
        return timePickerDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.date_layout, container, false);
        WheelView year = (WheelView) view.findViewById(R.id.year);
        WheelView month = (WheelView) view.findViewById(R.id.month);
        WheelView day = (WheelView) view.findViewById(R.id.day);
        WheelView hour = (WheelView) view.findViewById(R.id.hour);
        WheelView minute = (WheelView) view.findViewById(R.id.minute);

        return view;
    }

    private void initialize(DateSetListener dateSetListener, Calendar calendar, long minMillseconds) {
        mCallBack = dateSetListener;
        mDataController = new DataController(minMillseconds);
    }


    public enum Type {
        ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN, Type, YEAR_MONTH
    }// 五种选择模式，年月日时分，年月日，时分，月日时分，年月


}
