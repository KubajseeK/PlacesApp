package sk.itsovy.kutka.placesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnPlaceClickListener {


    static ArrayList<String> places = new ArrayList<>();
    static ArrayList<LatLng> locations = new ArrayList<>();
    PlacesViewModel placesViewModel = new PlacesViewModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExtendedFloatingActionButton fab = findViewById(R.id.extended_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessFabClick();
            }
        });



        ArrayList<String> latitudes = new ArrayList<>();
        ArrayList<String> longitudes = new ArrayList<>();


        if (places.size() > 0 && latitudes.size() > 0 && longitudes.size() > 0) {
            if (places.size() == latitudes.size() && places.size() == longitudes.size()) {
                for (int i = 0; i < latitudes.size(); i++) {
                    locations.add(new LatLng(Double.parseDouble(latitudes.get(i)), Double.parseDouble(longitudes.get(i))));
                }
            }
        } else {
            places.add("1");
            places.add("2");
            places.add("3");
            locations.add(new LatLng(0, 0));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        PlacesAdapter adapter = new PlacesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        PlacesViewModel placesViewModel = new ViewModelProvider(this, new MyViewModelFactory(this.getApplication(), "my awesome param")).get(PlacesViewModel.class);
        placesViewModel.getPlaces().observe(this, places -> adapter.setCachedPlaces(places));




    }

    private void ProcessFabClick() {

        DialogFragment insertDialogFragment = new InsertDialogFragment();
        insertDialogFragment.show(getSupportFragmentManager(), "insert");
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}