package edeneastapps.sentimentalmoodjournal.views.dashboard.horizontalcalendar;

public class HorizontalCalendarItem {
    private int day;
    private int month;
    private int backgroundColor;
    private int year;

    public HorizontalCalendarItem(int day, int month, int backgroundColor, int year) {
        this.day = day;
        this.month = month;
        this.backgroundColor = backgroundColor;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getYear() {
        return year;
    }
}
