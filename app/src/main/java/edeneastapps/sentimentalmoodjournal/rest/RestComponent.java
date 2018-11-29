package edeneastapps.sentimentalmoodjournal.rest;

import javax.inject.Singleton;

import dagger.Component;
import edeneastapps.sentimentalmoodjournal.application.AppModule;
import edeneastapps.sentimentalmoodjournal.views.newentry.NewEntryActivity;

@Singleton
@Component(modules = {AppModule.class, RestModule.class})
public interface RestComponent {
    void inject(NewEntryActivity activity);
}
