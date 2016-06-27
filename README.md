##### Welcome to follow me on GitHub
GitHub: https://github.com/JZXiang

---

[中文版文档](https://github.com/JZXiang/TimePickerDialog/blob/master/README-cn.md)
# TimePickerDialog
An Android time picker library.  
Easy to use.  
Support five types.   
Able to set the minimun and maximum millseconds. 

[ChangeLog](https://github.com/JZXiang/TimePickerDialog/blob/master/change_log.md)  
[Download APK](https://github.com/JZXiang/PickerView/raw/master/sample-debug.apk)

## gradle, latest version:
```java
   compile 'com.jzxiang.pickerview:TimePickerDialog:1.0.0'
```
## Demo picture
![](https://github.com/JZXiang/PickerView/raw/master/preview/timepickerdialog_demo.gif)

## An example configuration
```java
                    mDialogAll = new TimePickerDialog.Builder()
                                    .setCallBack(this)
                                    .setCancelStringId("Cancel")
                                    .setSureStringId("Sure")
                                    .setTitleStringId("TimePicker")
                                    .setYearText("Year")
                                    .setMonthText("Month")
                                    .setDayText("Day")
                                    .setHourText("Hour")
                                    .setMinuteText("Minute")
                                    .setCyclic(false)
                                    .setMinMillseconds(System.currentTimeMillis())
                                    .setMaxMillseconds(System.currentTimeMillis() + tenYears)
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

License
-------

    Copyright 2016 JZXiang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

