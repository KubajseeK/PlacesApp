package sk.itsovy.kutka.placesapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PlaceRepository {

    private PlacesDao placesDao;
    private LiveData<List<Place>> places;

    PlaceRepository(Application application) {
        PlacesDatabase database = PlacesDatabase.getDatabase(application);
        placesDao = database.placesDao();

        places = placesDao.getAllPlaces();
    }

    LiveData<List<Place>> getAllPlaces() {
        return places;
    }

    void insert(Place place) {
        PlacesDatabase.databaseWriteExecutor.execute(() -> {
            placesDao.insert(place);
        });
    }

    void delete(Place place) {
        PlacesDatabase.databaseWriteExecutor.execute(() -> {
            placesDao.deletePlace(place);
        });
    }
}
