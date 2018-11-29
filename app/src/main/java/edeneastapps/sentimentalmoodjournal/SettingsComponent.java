package edeneastapps.sentimentalmoodjournal;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SettingsModule.class})
public interface SettingsComponent {
    void inject(SettingsActivity activity);
    void inject(DashboardActivity activity);
    void inject(NewEntryActivity activity);
    void inject(EditActivity activity);
    void inject(EntryDetailActivity activity);
}
