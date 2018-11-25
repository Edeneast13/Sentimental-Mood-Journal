package edeneastapps.sentimentalmoodjournal;

public class HorizontalCalendarProperties {
    private int currentMonth;
    private int monthAtEnd;
    private int monthAtStart;
    private int currentYear;
    private int visibleYear;

    HorizontalCalendarProperties(int currentMonth, int currentYear) {
        this.currentMonth = currentMonth;
        this.currentYear = currentYear;
        this.monthAtEnd = currentMonth;
        this.monthAtStart = currentMonth;
        this.visibleYear = currentYear;
    }

    public int getMonthAtEnd() {
        return monthAtEnd;
    }

    public void setMonthAtEnd(int monthAtEnd) {
        this.monthAtEnd = monthAtEnd;
    }

    public int getMonthAtStart() {
        return monthAtStart;
    }

    public void setMonthAtStart(int monthAtStart) {
        this.monthAtStart = monthAtStart;
    }

    public int getVisibleYear() {
        return visibleYear;
    }

    public void setVisibleYear(int visibleYear) {
        this.visibleYear = visibleYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }
}