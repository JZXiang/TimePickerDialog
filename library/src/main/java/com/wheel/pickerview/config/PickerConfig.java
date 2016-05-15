package com.wheel.pickerview.config;

import com.wheel.pickerview.data.Type;
import com.wheel.pickerview.data.WheelCalendar;
import com.wheel.pickerview.listener.OnDateSetListener;


/**
 * Created by jzxiang on 16/5/15.
 */
public class PickerConfig {

    public Type mType = DefaultConfig.TYPE;
    public int mThemeColor = DefaultConfig.COLOR;

    public int mCancelStringId = DefaultConfig.CANCEL_STRING_ID;
    public int mSureStringId = DefaultConfig.SURE_STRING_ID;
    public int mTitleStringId = DefaultConfig.TITLE_STRING_ID;
    public int mToolBarTVColorId = DefaultConfig.TOOLBAR_TV_COLOR_ID;

    public int mWheelTVNormalColorId = DefaultConfig.TV_NORMAL_COLOR_ID;
    public int mWheelTVSelectorColorId = DefaultConfig.TV_SELECTOR_COLOR_ID;
    public int mWheelTVSize = DefaultConfig.TV_SIZE;
    public boolean cyclic = DefaultConfig.CYCLIC;
    /**
     * 显示的最小时间
     */
    public WheelCalendar mMinCalendar = new WheelCalendar(0);
    /**
     * 当前时间
     */
    public WheelCalendar mCurrentCalendar = new WheelCalendar(System.currentTimeMillis());

    public OnDateSetListener mCallBack;
}
