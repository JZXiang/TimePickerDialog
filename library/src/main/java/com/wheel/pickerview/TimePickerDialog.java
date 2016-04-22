package com.wheel.pickerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.wheel.pickerview.data.PickerController;
import com.wheel.pickerview.data.Type;
import com.wheel.pickerview.data.WheelCalendar;
import com.wheel.pickerview.listener.OnDateSetListener;
import com.wheel.pickerview.utils.PickerContants;
import com.wheel.pickerview.utils.Utils;
import com.wheel.pickerview.utils.ViewController;
import com.wheel.pickerview.view.TimeWheel;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/19.
 */
public class TimePickerDialog extends DialogFragment implements PickerController, View.OnClickListener {
    private OnDateSetListener mCallBack;
    private TimeWheel mTimeWheel;
    private TextView mTvCancel, mTvTitle, mTvSure;
    private String mTitle;

    private ViewController mViewController;

    private WheelCalendar mCalendarMin, mCalendarCurrent;
    private Type mType;

    public static TimePickerDialog newIntance(OnDateSetListener onDateSetListener) {
        return newIntance(onDateSetListener, Calendar.getInstance(), 0, PickerContants.DEFAULT_TYPE);
    }

    public static TimePickerDialog newIntance(OnDateSetListener onDateSetListener, Type type) {
        return newIntance(onDateSetListener, Calendar.getInstance(), 0, type);
    }

    public static TimePickerDialog newIntance(OnDateSetListener onDateSetListener, Calendar cllendar, Type type) {
        return newIntance(onDateSetListener, cllendar, 0, type);
    }

    public static TimePickerDialog newIntance(OnDateSetListener onDateSetListener, Calendar calendar, long minMillSeconds, Type type) {
        TimePickerDialog timePickerDialog = new TimePickerDialog();
        timePickerDialog.initialize(onDateSetListener, calendar, minMillSeconds, type);
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
        View view = inflater.inflate(R.layout.date_layout, container, false);
        mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(this);
        mTvSure = (TextView) view.findViewById(R.id.tv_sure);
        mTvSure.setOnClickListener(this);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);

        if (!TextUtils.isEmpty(mTitle)) {
            mTvTitle.setText(mTitle);
        }

        mTimeWheel = new TimeWheel(this, view, mType);
        return view;
    }


    private void initialize(OnDateSetListener onDateSetListener, Calendar defaultCalendar, long minMillseconds, Type type) {
        mType = type;
        mCallBack = onDateSetListener;
        mCalendarMin = new WheelCalendar(minMillseconds);
        mCalendarCurrent = new WheelCalendar(defaultCalendar.getTimeInMillis());
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
        calendar.set(Calendar.MONTH, month - 1);

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
    public boolean isNoRange() {
        return mCalendarMin.isNoRange();
    }

    @Override
    public WheelCalendar getDefaultCalendar() {
        return mCalendarCurrent;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        manager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                .add(android.R.id.content, this)
                .commit();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        initViewController();
        if (mViewController != null)
            mViewController.show();
    }

    void initViewController() {
        if (mViewController == null && getActivity() != null) {
            mViewController = new ViewController(getActivity());
            mViewController.setShaderClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    public void setTitleMessage(String title) {
        mTitle = title;
    }

    public void setTitleMessage(int title) {
        mTitle = getContext().getString(title);
    }

    @Override
    public void dismiss() {
        if (mViewController != null)
            mViewController.dismiss();
        getFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                .remove(this)
                .commit();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            dismiss();
        } else if (i == R.id.tv_sure) {
            sureClicked();
        }
    }

    void sureClicked() {
        if (mCallBack != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, mTimeWheel.getCurrentYear());
            calendar.set(Calendar.MONTH, mTimeWheel.getCurrentMonth());
            calendar.set(Calendar.DAY_OF_MONTH, mTimeWheel.getCurrentDay());
            calendar.set(Calendar.HOUR_OF_DAY, mTimeWheel.getCurrentHour());
            calendar.set(Calendar.MINUTE, mTimeWheel.getCurrentMinute());
            mCallBack.onDateSet(this, calendar);
        }
        dismiss();
    }


}
