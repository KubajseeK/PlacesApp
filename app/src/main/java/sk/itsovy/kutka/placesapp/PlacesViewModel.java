package sk.itsovy.kutka.placesapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PlacesViewModel extends ViewModel {

    private PlaceRepository repository;
    private LiveData<List<Place>> places;

    public PlacesViewModel() {
        super();
    }

    public PlacesViewModel(@NonNull Application application) {
        super();
          repository = new PlaceRepository(application);
          places = repository.getAllPlaces();
    }

    LiveData<List<Place>> getPlaces() {
        return places;
    }

    public void insert(Place place) {
        repository.insert(place);
    }
    public void delete(Place place) {
        repository.delete(place);
    }
}
