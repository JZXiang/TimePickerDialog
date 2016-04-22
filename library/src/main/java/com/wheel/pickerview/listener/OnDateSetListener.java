package com.wheel.pickerview.listener;

import com.wheel.pickerview.TimePickerDialog;

import java.util.Calendar;

/**
 * Created by jzxiang on 16/4/20.
 */
public interface OnDateSetListener {

    void onDateSet(TimePickerDialog timePickerView, Calendar calendar);
}
