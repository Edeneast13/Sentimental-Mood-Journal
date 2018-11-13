package edeneastapps.sentimentalmoodjournal;

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

    EntryViewModel(@NonNull Application application) {
        super(application);
        mEntryDao = EntryDatabase.getDatabase(application).entryDao();
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    LiveData<List<Entry>> getAllEntries(){
        return mEntryDao.findAll();
    }

    LiveData<List<Entry>> getByDateCreated(String dateStamp){return mEntryDao.findByDateCreated(dateStamp);}

    void addEntry(final Entry entry){
        mExecutorService.execute(() -> mEntryDao.insertEntry(entry));
    }

    void deleteEntry(final Entry entry){
        mExecutorService.execute(() -> mEntryDao.deleteEntry(entry));
    }

    void getEntryByTitle(final String title){
        mExecutorService.execute(() -> mEntryDao.fetchEntry(title));
    }

    void updateEntry(final Entry entry){
        mExecutorService.execute(() -> mEntryDao.updateEntry(entry));
    }
}
