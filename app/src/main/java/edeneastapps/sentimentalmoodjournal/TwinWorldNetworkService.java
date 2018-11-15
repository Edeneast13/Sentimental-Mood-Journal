package edeneastapps.sentimentalmoodjournal;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TwinWorldNetworkService extends IntentService {

    public TwinWorldNetworkService() {
        super("TwinWorld Network Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            Entry data = intent.getParcelableExtra("entry");
            postSentiment(data);
        }
    }

    void postSentiment(Entry entry){
        TwinWorldInterface restService = TwinWorldClient.getClient().create(TwinWorldInterface.class);
        restService.postSentiment(entry.getContent()).enqueue(new Callback<TwinWorldSentimentResult>() {
            @Override
            public void onResponse(Call<TwinWorldSentimentResult> call, Response<TwinWorldSentimentResult> response) {
                if (response.isSuccessful()){
                    updateDatabase(entry, response.body());
                }
            }

            @Override
            public void onFailure(Call<TwinWorldSentimentResult> call, Throwable t) {

            }
        });
    }

    void updateDatabase(Entry entry, TwinWorldSentimentResult result){
        entry.setSentimentRatio(result.getRatio());
        entry.setSentimentScore(result.getScore());
        entry.setSentimentType(result.getType());
        new EntryViewModel(getApplication()).updateEntry(entry);
    }
}
