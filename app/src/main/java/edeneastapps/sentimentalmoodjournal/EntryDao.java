package edeneastapps.sentimentalmoodjournal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EntryDao {
    @Insert
    void insertEntry(Entry entry);

    @Query("SELECT * FROM entries WHERE title = :title")
    Entry fetchEntry(String title);

    @Query("SELECT * FROM entries")
    LiveData<List<Entry>> findAll();

    @Update
    void updateEntry(Entry entry);

    @Delete
    void deleteEntry(Entry entry);
}
