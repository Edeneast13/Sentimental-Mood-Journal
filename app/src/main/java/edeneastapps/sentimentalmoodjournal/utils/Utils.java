package edeneastapps.sentimentalmoodjournal.utils;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;

import java.util.List;

import edeneastapps.sentimentalmoodjournal.R;
import edeneastapps.sentimentalmoodjournal.database.entry.Entry;

public class Utils {
    public static int returnSentimentRangeColor(float score){
        if (score >= .08f){
            return R.color.blue500;
        }
        else if(score >= .06f && score < .08f){
            return R.color.lightblue500;
        }
        else if(score >= .04f && score < .06f){
            return R.color.cyan500;
        }
        else if(score >= .02f && score < .04f){
            return R.color.teal500;
        }
        else if(score >= 0f && score < .02f){
            return R.color.green500;
        }
        else if(score >= (-.02f) && score < 0f){
            return R.color.lightgreen500;
        }
        else if(score >= (-.04f) && score < (-.02f)){
            return R.color.lime500;
        }
        else if(score >= (-.06f) && score < (-04f)){
            return R.color.yellow400;
        }
        else if(score >= (-.08f) && score < (-.06f)){
            return R.color.amber500;
        }
        else{
            return R.color.orange500;
        }
    }

    public static float returnSentimentScoreAverage(List<Float> scores){
        float average = 0;
        for (int i = 0; i < scores.size(); i++) {
            average = average + scores.get(i);
        }
        return average / scores.size();
    }

    public static void configCardLayout(Context context, View layout, int color){
        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(25);
        shape.setColor(context.getResources().getColor(color));
        layout.setBackground(shape);
        layout.setElevation(4);
    }

    public static int returnMoodIconWhite(int mood){
        int id = 0;
        switch (mood){
            case 0:
                id = R.mipmap.ic_angry_icon_white;
                break;
            case 1:
                id = R.mipmap.ic_sad_emoji_white;
                break;
            case 2:
                id = R.mipmap.ic_confused_emoji_white;
                break;
            case 3:
                id = R.mipmap.ic_okay_emoji_white;
                break;
            case 4:
                id = R.mipmap.ic_happy_emoji_white;
                break;
        }
        return id;
    }

    /**
     * toggle between gone and visible
     * @param view
     */
    public static void toggleViewVisibility(View view){
        if(view.getVisibility() == View.GONE){
            view.setVisibility(View.VISIBLE);
        }
        else{
            view.setVisibility(View.GONE);
        }
    }

    /**
     * formant of mm/dd/yyyy
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static String returnStringDate(int month, int day, int year){
        String date = month + "/" + day + "/" + year;
        return date;
    }

    public static int calculateSentimentProgress(Entry entry){
        float score = Float.parseFloat(entry.getSentimentScore());
        float progress;
        if (score < 0){
            progress = score * (-100);
        }
        else{
            progress = score * (100);
        }
        return Math.round(progress);
    }
}
