package edeneastapps.sentimentalmoodjournal;

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
}
