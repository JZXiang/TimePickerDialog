package com.jzxiang.pickerview.config;

import android.content.Context;
import android.text.TextUtils;

import com.jzxiang.pickerview.R;
import com.jzxiang.pickerview.data.Type;

/**
 * Created by jzxiang on 16/5/15.
 */
public class DefaultConfig {
    public static final Type TYPE = Type.ALL;
    public static final int COLOR = 0XFFE60012;

    public static String CANCEL = "";
    public static String SURE = "";
    public static String TITLE = "";

    public static String YEAR = "";
    public static String MONTH = "";
    public static String DAY = "";
    public static String HOUR = "";
    public static String MINUTE = "";

    public static final int TOOLBAR_TV_COLOR = 0xFFFFFFFF;
    public static final int TV_NORMAL_COLOR = 0xFF999999;
    public static final int TV_SELECTOR_COLOR = 0XFF404040;
    public static final int TV_SIZE = 12;
    public static final boolean CYCLIC = true;

    public static void init(Context context) {
        if (!TextUtils.isEmpty(CANCEL))
            return;

        CANCEL = context.getString(R.string.picker_cancel);
        SURE = context.getString(R.string.picker_sure);
        TITLE = context.getString(R.string.picker_title);

        YEAR = context.getString(R.string.picker_year);
        MONTH = context.getString(R.string.picker_month);
        DAY = context.getString(R.string.picker_day);
        HOUR = context.getString(R.string.picker_hour);
        MINUTE = context.getString(R.string.picker_minute);
    }

}
