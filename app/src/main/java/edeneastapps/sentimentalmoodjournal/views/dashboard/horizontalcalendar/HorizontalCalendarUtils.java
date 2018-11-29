package edeneastapps.sentimentalmoodjournal.views.dashboard.horizontalcalendar;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

public class HorizontalCalendarUtils {
    public static int calculateMonthLength(int month){
        int monthLength = 0;
        switch (month){
            case 0: {
                monthLength = 31;
                break;
            }
            case 1: {
                monthLength = 28;
                break;
            }
            case 2: {
                monthLength = 31;
                break;
            }
            case 3: {
                monthLength = 30;
                break;
            }
            case 4: {
                monthLength = 31;
                break;
            }
            case 5: {
                monthLength = 30;
                break;
            }
            case 6: {
                monthLength = 31;
                break;
            }
            case 7: {
                monthLength = 31;
                break;
            }
            case 8: {
                monthLength = 30;
                break;
            }
            case 9: {
                monthLength = 31;
                break;
            }
            case 10: {
                monthLength = 30;
                break;
            }
            case 11: {
                monthLength = 31;
                break;
            }
        }
        return monthLength;
    }

    /**
     * returns month in three letter format, i.e. JAN, FEB, etc...
     * @param month
     * @return
     */
    public static String returnMonthName(int month){
        String monthName = "";
        switch (month){
            case 0: {
                monthName = "Jan";
                break;
            }
            case 1: {
                monthName = "Feb";
                break;
            }
            case 2: {
                monthName = "Mar";
                break;
            }
            case 3: {
                monthName = "Apr";
                break;
            }
            case 4: {
                monthName = "May";
                break;
            }
            case 5: {
                monthName = "Jun";
                break;
            }
            case 6: {
                monthName = "Jul";
                break;
            }
            case 7: {
                monthName = "Aug";
                break;
            }
            case 8: {
                monthName = "Sep";
                break;
            }
            case 9: {
                monthName = "Oct";
                break;
            }
            case 10: {
                monthName = "Nov";
                break;
            }
            case 11: {
                monthName = "Dec";
                break;
            }
        }
        return monthName;
    }

    public static void configCalLayout(Context context, View layout, int color){
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(context.getResources().getColor(color));
        shape.setShape(GradientDrawable.OVAL);
        layout.setBackground(shape);
    }
}
