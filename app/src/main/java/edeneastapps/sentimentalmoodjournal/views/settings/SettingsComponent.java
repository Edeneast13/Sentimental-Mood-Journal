package edeneastapps.sentimentalmoodjournal.views.settings;

import javax.inject.Singleton;

import dagger.Component;
import edeneastapps.sentimentalmoodjournal.views.editentry.EditActivity;
import edeneastapps.sentimentalmoodjournal.views.entrydetail.EntryDetailActivity;
import edeneastapps.sentimentalmoodjournal.views.newentry.NewEntryActivity;
import edeneastapps.sentimentalmoodjournal.application.AppModule;
import edeneastapps.sentimentalmoodjournal.views.dashboard.DashboardActivity;

@Singleton
@Component(modules = {AppModule.class, SettingsModule.class})
public interface SettingsComponent {
    void inject(SettingsActivity activity);
    void inject(DashboardActivity activity);
    void inject(NewEntryActivity activity);
    void inject(EditActivity activity);
    void inject(EntryDetailActivity activity);
}
