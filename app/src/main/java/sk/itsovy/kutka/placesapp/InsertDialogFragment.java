package sk.itsovy.kutka.placesapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class InsertDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.insert_dialog_view, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Insert Place")
                .setView(layout)
                .setPositiveButton("Insert", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        ViewModelProvider provider = new ViewModelProvider(requireActivity());
                        PlacesViewModel viewModel = provider.get(PlacesViewModel.class);

                        EditText nameEditText = layout.findViewById(R.id.insert_name);
                        EditText latEditText = layout.findViewById(R.id.insert_latitude);
                        EditText longEditText = layout.findViewById(R.id.insert_longitude);

                        Place place = new Place();
                        place.setName(nameEditText.getText().toString());
                        place.setLatitude(Double.parseDouble(latEditText.getText().toString()));
                        place.setLongitude(Double.parseDouble(longEditText.getText().toString()));
                        viewModel.insert(place);


                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
