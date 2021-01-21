package sk.itsovy.kutka.placesapp;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlacesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);

    @Query("SELECT * FROM places")
    LiveData<List<Place>> getAllPlaces();

    @Delete
    void deletePlace(Place place);

    @Query("Delete from places where name =:name")
    void deletePlace(String name);

    @Query("Delete from places")
    void deleteAll();

}
