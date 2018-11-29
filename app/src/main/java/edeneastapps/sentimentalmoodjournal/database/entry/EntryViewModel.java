package edeneastapps.sentimentalmoodjournal.database.entry;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EntryViewModel extends AndroidViewModel {

    private EntryDao mEntryDao;
    private ExecutorService mExecutorService;

    public EntryViewModel(@NonNull Application application) {
        super(application);
        mEntryDao = EntryDatabase.getDatabase(application).entryDao();
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Entry>> getAllEntries(){
        return mEntryDao.findAll();
    }

    public LiveData<List<Entry>> getByDateCreated(String dateStamp){return mEntryDao.findByDateCreated(dateStamp);}

    public void addEntry(final Entry entry){
        mExecutorService.execute(() -> mEntryDao.insertEntry(entry));
    }

    public void deleteEntry(final Entry entry){
        mExecutorService.execute(() -> mEntryDao.deleteEntry(entry));
    }

    public void getEntryByTitle(final String title){
        mExecutorService.execute(() -> mEntryDao.fetchEntry(title));
    }

    public void updateEntry(final Entry entry){
        mExecutorService.execute(() -> mEntryDao.updateEntry(entry));
    }

    public void updateSentimentById(final Entry entry){
        mExecutorService.execute(() -> mEntryDao.updateSentimentById(entry.getId(), entry.getSentimentScore(), entry.getSentimentRatio(), entry.getSentimentType()));
    }
}
