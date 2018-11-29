package edeneastapps.sentimentalmoodjournal;

import android.app.Application;

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
