package edeneastapps.sentimentalmoodjournal;

public class Utils {
    public static int returnSentimentRangeColor(float score){
        if (score >= .80f){
            return R.color.blue500;
        }
        else if(score >= .60f && score < .80f){
            return R.color.lightblue500;
        }
        else if(score >= .40f && score < .60f){
            return R.color.cyan500;
        }
        else if(score >= .20f && score < .40f){
            return R.color.teal500;
        }
        else if(score >= 0f && score < .20f){
            return R.color.green500;
        }
        else if(score >= (-.20f) && score < 0f){
            return R.color.lightgreen500;
        }
        else if(score >= (-.40f) && score < (-.20f)){
            return R.color.lime500;
        }
        else if(score >= (-.60f) && score < (-40f)){
            return R.color.yellow400;
        }
        else if(score >= (-.80f) && score < (-.60f)){
            return R.color.amber500;
        }
        else{
            return R.color.orange500;
        }
    }
}
