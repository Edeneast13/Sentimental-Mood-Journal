package edeneastapps.sentimentalmoodjournal;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Entry.class}, version = 1, exportSchema = false)
public abstract class EntryDatabase extends RoomDatabase {
    public abstract EntryDao entryDao();

    private static volatile EntryDatabase INSTANCE;

    static EntryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EntryDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EntryDatabase.class, "entry_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
