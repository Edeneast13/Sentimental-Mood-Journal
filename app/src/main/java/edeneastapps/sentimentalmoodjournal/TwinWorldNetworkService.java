package edeneastapps.sentimentalmoodjournal;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TwinWorldNetworkService extends IntentService {

    final static String RESPONSE_SUCCESS = "response_success";

    public TwinWorldNetworkService() {
        super("TwinWorld Network Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            Entry data = intent.getParcelableExtra("entry");
            Log.e("Entry ID:: ", data.getId() + "");
            postSentiment(data);
        }
    }

    void postSentiment(Entry entry){
        TwinWorldInterface restService = TwinWorldClient.getClient().create(TwinWorldInterface.class);
        restService.postSentiment(entry.getContent()).enqueue(new Callback<TwinWorldSentimentResult>() {
            @Override
            public void onResponse(@NonNull Call<TwinWorldSentimentResult> call, @NonNull Response<TwinWorldSentimentResult> response) {
                if (response.isSuccessful()){
                    entry.setSentimentType(response.body().getType());
                    entry.setSentimentScore(response.body().getScore());
                    entry.setSentimentRatio(response.body().getRatio());
                    saveEntry(entry);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TwinWorldSentimentResult> call, @NonNull Throwable t) {

            }
        });
    }

    void saveEntry(Entry entry){
        new EntryViewModel(getApplication()).addEntry(entry);
        notifyUI();
    }

    private void notifyUI(){
        Intent intent = new Intent();
        intent.setAction(RESPONSE_SUCCESS);
        sendBroadcast(intent);
    }
}
