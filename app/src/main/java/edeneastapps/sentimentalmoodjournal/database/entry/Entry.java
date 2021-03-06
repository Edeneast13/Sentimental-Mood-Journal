package edeneastapps.sentimentalmoodjournal.database.entry;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "entries")
public class Entry implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String timestamp;
    private String datestamp;
    private String content;
    private String title;
    private int mood;
    private String sentimentType;
    private String sentimentScore;
    private String sentimentRatio;
    private int sentimentColor;
    private Double emotionJoy;
    private Double emotionSadness;
    private Double emotionSurprise;
    private Double emotionFear;
    private Double emotionAnger;
    private Double emotionDisgust;

    public Entry(){ }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDatestamp(String datestamp) {
        this.datestamp = datestamp;
    }

    public String getDatestamp() {
        return datestamp;
    }

    public String getSentimentType() {
        return sentimentType;
    }

    public void setSentimentType(String sentimentType) {
        this.sentimentType = sentimentType;
    }

    public String getSentimentScore() {
        return sentimentScore;
    }

    public void setSentimentScore(String sentimentScore) {
        this.sentimentScore = sentimentScore;
    }

    public String getSentimentRatio() {
        return sentimentRatio;
    }

    public void setSentimentRatio(String sentimentRatio) {
        this.sentimentRatio = sentimentRatio;
    }

    public int getSentimentColor() {
        return sentimentColor;
    }

    public void setSentimentColor(int sentimentColor) {
        this.sentimentColor = sentimentColor;
    }

    public Double getEmotionJoy() {
        return emotionJoy;
    }

    public void setEmotionJoy(Double emotionJoy) {
        this.emotionJoy = emotionJoy;
    }

    public Double getEmotionSadness() {
        return emotionSadness;
    }

    public void setEmotionSadness(Double emotionSadness) {
        this.emotionSadness = emotionSadness;
    }

    public Double getEmotionSurprise() {
        return emotionSurprise;
    }

    public void setEmotionSurprise(Double emotionSurprise) {
        this.emotionSurprise = emotionSurprise;
    }

    public Double getEmotionFear() {
        return emotionFear;
    }

    public void setEmotionFear(Double emotionFear) {
        this.emotionFear = emotionFear;
    }

    public Double getEmotionAnger() {
        return emotionAnger;
    }

    public void setEmotionAnger(Double emotionAnger) {
        this.emotionAnger = emotionAnger;
    }

    public Double getEmotionDisgust() {
        return emotionDisgust;
    }

    public void setEmotionDisgust(Double emotionDisgust) {
        this.emotionDisgust = emotionDisgust;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(timestamp);
        parcel.writeString(datestamp);
        parcel.writeString(content);
        parcel.writeString(title);
        parcel.writeInt(mood);
        parcel.writeString(sentimentType);
        parcel.writeString(sentimentScore);
        parcel.writeString(sentimentRatio);
        parcel.writeInt(sentimentColor);
        parcel.writeDouble(emotionJoy);
        parcel.writeDouble(emotionSadness);
        parcel.writeDouble(emotionSurprise);
        parcel.writeDouble(emotionFear);
        parcel.writeDouble(emotionAnger);
        parcel.writeDouble(emotionDisgust);
    }

    /**
     * creates a parcelable object
     */
    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>(){
        public Entry createFromParcel(Parcel parcel){
            return new Entry(parcel);
        }
        public Entry[] newArray(int size){
            return new Entry[size];
        }
    };

    /**
     * retrieves packaged parcelable content
     */
    private Entry(Parcel parcel){
        id = parcel.readInt();
        timestamp = parcel.readString();
        datestamp = parcel.readString();
        content = parcel.readString();
        title = parcel.readString();
        mood = parcel.readInt();
        sentimentType = parcel.readString();
        sentimentScore = parcel.readString();
        sentimentRatio = parcel.readString();
        sentimentColor = parcel.readInt();
        emotionJoy = parcel.readDouble();
        emotionSadness = parcel.readDouble();
        emotionSurprise = parcel.readDouble();
        emotionFear = parcel.readDouble();
        emotionAnger = parcel.readDouble();
        emotionDisgust = parcel.readDouble();
    }
}
