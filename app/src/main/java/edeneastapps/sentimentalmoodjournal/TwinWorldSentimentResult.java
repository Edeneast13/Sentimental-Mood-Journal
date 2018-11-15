package edeneastapps.sentimentalmoodjournal;

import com.google.gson.annotations.SerializedName;

public class TwinWorldSentimentResult {
    @SerializedName("type")
    private String type;
    @SerializedName("score")
    private String score;
    @SerializedName("ratio")
    private String ratio;

    public String getType() {
        return type;
    }

    public String getScore() {
        return score;
    }

    public String getRatio() {
        return ratio;
    }
}
