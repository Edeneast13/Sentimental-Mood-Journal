package edeneastapps.sentimentalmoodjournal.rest.emotionapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmotionApiResult {
    @SerializedName("emotions_detected")
    @Expose
    private List<String> emotionsDetected = null;
    @SerializedName("emotion_scores")
    @Expose
    private EmotionScores emotionScores;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("result_code")
    @Expose
    private String resultCode;
    @SerializedName("result_msg")
    @Expose
    private String resultMsg;

    public List<String> getEmotionsDetected() {
        return emotionsDetected;
    }

    public void setEmotionsDetected(List<String> emotionsDetected) {
        this.emotionsDetected = emotionsDetected;
    }

    public EmotionScores getEmotionScores() {
        return emotionScores;
    }

    public void setEmotionScores(EmotionScores emotionScores) {
        this.emotionScores = emotionScores;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
