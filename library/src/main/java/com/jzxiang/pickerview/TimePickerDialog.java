package com.jzxiang.pickerview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jzxiang.pickerview.config.PickerConfig;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.data.WheelCalendar;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.jzxiang.pickerview.view.TimeWheel;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/19.
 */
public class TimePickerDialog extends DialogFragment implements View.OnClickListener {
    PickerConfig mPickerConfig;
    IController mIController;
    private TimeWheel mTimeWheel;

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

    @Override
    public void onResume() {
        super.onResume();
        int height = getResources().getDimensionPixelSize(R.dimen.picker_height);

        Window window = getDialog().getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, height);//Here!
        window.setGravity(Gravity.BOTTOM);
    }

    private void initialize(PickerConfig pickerConfig) {
        mPickerConfig = pickerConfig;
        mIController = new ControllerImpl(pickerConfig);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog_NoTitle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(initView());
        return dialog;
    }

    View initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.date_layout, null);
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
