# ChangeLog

### 0.9.3
* Add some functions of set the time unit.  
An example configuration
``` java
    mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setTitleStringId("TimePicker")
                .setYearText("Year")
                .setMonthText("Month")
                .setDayText("Day")
                .setHourText("Hour")
                .setMinuteText("Minute")
```
