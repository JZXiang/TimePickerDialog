##### 欢迎关注我
GitHub: https://github.com/JZXiang
# TimePickerDialog

Android时间选择器，支持年月日时分，年月日，年月，月日时分，时分格式，可以设置最小时间（精确到分）  

[APK下载](https://github.com/JZXiang/PickerView/raw/master/sample-debug.apk)


## 使用gradle 依赖:
```java
   compile 'com.jzxiang.pickerview:TimePickerDialog:0.9.2'
```
## Demo 图片
![](https://github.com/JZXiang/PickerView/raw/master/preview/timepickerdialog_demo.gif)

## 示例配置
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
## 感谢
[android-wheel](https://github.com/maarek/android-wheel)