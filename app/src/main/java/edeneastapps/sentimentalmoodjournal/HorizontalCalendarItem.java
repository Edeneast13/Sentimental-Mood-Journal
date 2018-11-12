package edeneastapps.sentimentalmoodjournal;

import android.graphics.Color;

public class HorizontalCalendarItem {
    String day;
    String month;
    Color backgroundColor;

    public HorizontalCalendarItem(String day, String month, Color backgroundColor) {
        this.day = day;
        this.month = month;
        this.backgroundColor = backgroundColor;
    }
}
