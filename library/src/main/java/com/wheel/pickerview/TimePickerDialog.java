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

import com.wheel.pickerview.wheel.WheelView;

/**
 * Created by jzxiang on 16/4/19.
 */
public class TimePickerDialog extends DialogFragment {
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
        WheelView yearWheelView = (WheelView) view.findViewById(R.id.year);
        WheelView monthWheelView = (WheelView) view.findViewById(R.id.month);
        WheelView dayWheelView = (WheelView) view.findViewById(R.id.day);
        WheelView hourWheelView = (WheelView) view.findViewById(R.id.hour);
        WheelView minuteWheelView = (WheelView) view.findViewById(R.id.minute);


        return view;
    }

    public enum Type {
        ALL, YEAR_MONTH_DAY, HOURS_MINS, MONTH_DAY_HOUR_MIN, YEAR_MONTH
    }// 五种选择模式，年月日时分，年月日，时分，月日时分，年月
}
