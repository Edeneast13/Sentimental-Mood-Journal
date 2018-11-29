package edeneastapps.sentimentalmoodjournal.application;

import android.app.Application;

import edeneastapps.sentimentalmoodjournal.views.settings.DaggerSettingsComponent;
import edeneastapps.sentimentalmoodjournal.views.settings.SettingsComponent;
import edeneastapps.sentimentalmoodjournal.views.settings.SettingsModule;

public class MainApplication extends Application {
    private SettingsComponent mSettingsComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mSettingsComponent =
                DaggerSettingsComponent.builder()
                        .appModule(new AppModule(this))
                        .settingsModule(new SettingsModule(getApplicationContext()))
                        .build();
    }

    public SettingsComponent getSettingsComponent() {
        return mSettingsComponent;
    }
}
