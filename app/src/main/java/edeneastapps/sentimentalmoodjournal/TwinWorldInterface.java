package edeneastapps.sentimentalmoodjournal;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TwinWorldInterface {
    @FormUrlEncoded
    @Headers({
            "X-Mashape-Key: w4TbkiaJeLmshSxWdN9gVGRy1Bgyp19LkD7jsnFVL4mcxU13C5",
            "Content-Type: application/x-www-form-urlencoded",
            "Accept: application/json"})
    @POST("analyze/")
    Call<TwinWorldSentimentResult> postSentiment(@Field("text") String text);
}
