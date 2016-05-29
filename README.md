##### Welcome to follow me on GitHub
GitHub: https://github.com/JZXiang

---

[中文版文档](https://github.com/JZXiang/TimePickerDialog/blob/master/README-cn.md)
# TimerPickerDialog
An Android time picker library.  
It's easy to use.  
Supports five types.(YEAR_MONTH,YEAR_MONTH_DAY,MONTH_DAY_HOUR_MINUTE,YEAR_MONTH_DAY_HOUR_MINUTE,HOUR_MINUTE)  
It can set the minimum millseconds.  

[Download APK](https://github.com/JZXiang/PickerView/raw/master/sample-debug.apk)

## gradle, latest version:
```java
   compile 'com.jzxiang.pickerview:TimePickerDialog:0.9.2'
```
## Demo picture
![](https://github.com/JZXiang/PickerView/raw/master/preview/timepickerdialog_demo.gif)

## An example configuration
```java
    mDialogAll = new TimePickerDialog.Builder()
                    .setCallBack(this)
                    .setCancelStringId("cancel")
                    .setSureStringId("sure")
                    .setTitleStringId("TimePicker")
                    .setCyclic(false)
                    .setMinMillseconds(System.currentTimeMillis())
                    .setCurrentMillseconds(System.currentTimeMillis())
                    .setThemeColor(getResources().getColor(R.color.timepicker_dialog_bg))
                    .setType(Type.ALL)
                    .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                    .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                    .setWheelItemTextSize(12)
                    .build();
```
## Thanks
[android-wheel](https://github.com/maarek/android-wheel)

