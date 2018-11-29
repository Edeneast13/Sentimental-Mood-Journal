package edeneastapps.sentimentalmoodjournal.application;

import android.app.Application;

import edeneastapps.sentimentalmoodjournal.rest.DaggerRestComponent;
import edeneastapps.sentimentalmoodjournal.rest.RestComponent;
import edeneastapps.sentimentalmoodjournal.rest.RestModule;
import edeneastapps.sentimentalmoodjournal.views.settings.DaggerSettingsComponent;
import edeneastapps.sentimentalmoodjournal.views.settings.SettingsComponent;
import edeneastapps.sentimentalmoodjournal.views.settings.SettingsModule;

public class MainApplication extends Application {
    private SettingsComponent mSettingsComponent;
    private RestComponent mRestComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mSettingsComponent =
                DaggerSettingsComponent.builder()
                        .appModule(new AppModule(this))
                        .settingsModule(new SettingsModule(getApplicationContext()))
                        .build();

        String emotionBaseUrl = "https://twinword-emotion-analysis-v1.p.mashape.com/";
        String sentimentBaseUrl = "https://twinword-sentiment-analysis.p.mashape.com/";
        mRestComponent =
                DaggerRestComponent.builder()
                        .appModule(new AppModule(this))
                        .restModule(new RestModule(sentimentBaseUrl, emotionBaseUrl))
                        .build();
    }

    public SettingsComponent getSettingsComponent() {
        return mSettingsComponent;
    }

    public RestComponent getRestComponent() {
        return mRestComponent;
    }
}
