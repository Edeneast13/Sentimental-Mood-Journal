package edeneastapps.sentimentalmoodjournal.rest.emotionapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmotionApiClient {
    private static final String BASE_URL = "https://twinword-emotion-analysis-v1.p.mashape.com/";
    private static Retrofit sRetrofit = null;

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
