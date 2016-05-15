package com.wheel.pickerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.wheel.pickerview.config.PickerConfig;
import com.wheel.pickerview.data.Type;
import com.wheel.pickerview.data.WheelCalendar;
import com.wheel.pickerview.listener.OnDateSetListener;
import com.wheel.pickerview.utils.ViewController;
import com.wheel.pickerview.view.TimeWheel;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/19.
 */
public class TimePickerDialog extends DialogFragment implements View.OnClickListener {
    PickerConfig mPickerConfig;
    IController mIController;
    private TimeWheel mTimeWheel;
    private ViewController mViewController;

    private static TimePickerDialog newIntance(PickerConfig pickerConfig) {
        TimePickerDialog timePickerDialog = new TimePickerDialog();
        timePickerDialog.initialize(pickerConfig);
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
        TextView cancel = (TextView) view.findViewById(R.id.tv_cancel);
        cancel.setOnClickListener(this);
        TextView sure = (TextView) view.findViewById(R.id.tv_sure);
        sure.setOnClickListener(this);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        View toolbar = view.findViewById(R.id.toolbar);

        title.setText(mPickerConfig.mTitleStringId);
        cancel.setText(mPickerConfig.mCancelStringId);
        sure.setText(mPickerConfig.mSureStringId);
        toolbar.setBackgroundColor(mPickerConfig.mThemeColor);

        mTimeWheel = new TimeWheel(mIController, view, mPickerConfig);
        return view;
    }


    private void initialize(PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;
        mIController = new ControllerImpl(pickerConfig);
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
        if (mPickerConfig.mCallBack != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, mTimeWheel.getCurrentYear());
            calendar.set(Calendar.MONTH, mTimeWheel.getCurrentMonth());
            calendar.set(Calendar.DAY_OF_MONTH, mTimeWheel.getCurrentDay());
            calendar.set(Calendar.HOUR_OF_DAY, mTimeWheel.getCurrentHour());
            calendar.set(Calendar.MINUTE, mTimeWheel.getCurrentMinute());
            mPickerConfig.mCallBack.onDateSet(this, calendar);
        }
        dismiss();
    }

    public static class Builder {
        PickerConfig mPickerConfig;

        public Builder() {
            mPickerConfig = new PickerConfig();
        }

        public Builder setType(Type type) {
            mPickerConfig.mType = type;
            return this;
        }

        public Builder setThemeColor(int color) {
            mPickerConfig.mThemeColor = color;
            return this;
        }

        public Builder setCancelStringId(@StringRes int id) {
            mPickerConfig.mCancelStringId = id;
            return this;
        }

        public Builder setSureStringId(@StringRes int id) {
            mPickerConfig.mSureStringId = id;
            return this;
        }

        public Builder setTitleStringId(@StringRes int id) {
            mPickerConfig.mTitleStringId = id;
            return this;
        }

        public Builder setToolBarTextColorId(@ColorRes int id) {
            mPickerConfig.mToolBarTVColorId = id;
            return this;
        }

        public Builder setWheelItemTextNormalColorId(@ColorRes int id) {
            mPickerConfig.mWheelTVNormalColorId = id;
            return this;
        }

        public Builder setWheelItemTextSelectorColorId(@ColorRes int id) {
            mPickerConfig.mWheelTVSelectorColorId = id;
            return this;
        }

        public Builder setWheelItemTextSize(int size) {
            mPickerConfig.mWheelTVSize = size;
            return this;
        }

        public Builder setCyclic(boolean cyclic) {
            mPickerConfig.cyclic = cyclic;
            return this;
        }

        public Builder setMinMillseconds(long millseconds) {
            mPickerConfig.mMinCalendar = new WheelCalendar(millseconds);
            return this;
        }

        public Builder setSelectorMillseconds(long millseconds) {
            mPickerConfig.mCurrentCalendar = new WheelCalendar(millseconds);
            return this;
        }

        public Builder setCallBack(OnDateSetListener listener) {
            mPickerConfig.mCallBack = listener;
            return this;
        }

        public TimePickerDialog build() {
            return newIntance(mPickerConfig);
        }

    }


}
