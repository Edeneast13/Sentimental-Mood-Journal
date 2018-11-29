package edeneastapps.sentimentalmoodjournal.rest.emotionapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmotionScores {
    @SerializedName("joy")
    @Expose
    private Double joy;
    @SerializedName("sadness")
    @Expose
    private Double sadness;
    @SerializedName("surprise")
    @Expose
    private Double surprise;
    @SerializedName("fear")
    @Expose
    private Double fear;
    @SerializedName("anger")
    @Expose
    private Double anger;
    @SerializedName("disgust")
    @Expose
    private Double disgust;

    public Double getJoy() {
        return joy;
    }

    public void setJoy(Double joy) {
        this.joy = joy;
    }

    public Double getSadness() {
        return sadness;
    }

    public void setSadness(Double sadness) {
        this.sadness = sadness;
    }

    public Double getSurprise() {
        return surprise;
    }

    public void setSurprise(Double surprise) {
        this.surprise = surprise;
    }

    public Double getFear() {
        return fear;
    }

    public void setFear(Double fear) {
        this.fear = fear;
    }

    public Double getAnger() {
        return anger;
    }

    public void setAnger(Double anger) {
        this.anger = anger;
    }

    public Double getDisgust() {
        return disgust;
    }

    public void setDisgust(Double disgust) {
        this.disgust = disgust;
    }
}
