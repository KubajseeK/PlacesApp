package sk.itsovy.kutka.placesapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Place.class}, version = 1, exportSchema = false)
public abstract class PlacesDatabase extends RoomDatabase {

    public abstract PlacesDao placesDao();

    private static volatile PlacesDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PlacesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PlacesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlacesDatabase.class, "places_database")
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                PlacesDao dao = INSTANCE.placesDao();
                dao.deleteAll();

                Place p1 = new Place();
                p1.setName("Hell");
                p1.setLatitude(39.26450);
                p1.setLongitude(26.27770);
                dao.insert(p1);

            });
        }
    };
}
