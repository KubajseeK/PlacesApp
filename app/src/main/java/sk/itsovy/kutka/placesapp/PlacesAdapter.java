package sk.itsovy.kutka.placesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {

    private List<Place> cachedPlaces;
    private OnPlaceClickListener mOnPlaceClickListener;

    public PlacesAdapter(OnPlaceClickListener mOnPlaceClickListener) {
        this.mOnPlaceClickListener = mOnPlaceClickListener;
    }

    public PlacesAdapter() {
    }

    public void setCachedPlaces(List<Place> cachedPlaces) {
        this.cachedPlaces = cachedPlaces;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlacesAdapter.PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemLayout = inflater.inflate(R.layout.item_layout, parent, false);
        return new PlaceViewHolder(itemLayout, mOnPlaceClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.PlaceViewHolder holder, int position) {
        holder.bind(cachedPlaces.get(position));

    }

    @Override
    public int getItemCount() {
        if (cachedPlaces == null) {
            return 0;
        }
        return cachedPlaces.size();
    }

    public static class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        OnPlaceClickListener onPlaceClickListener;

        public PlaceViewHolder(@NonNull View itemView, OnPlaceClickListener onPlaceClickListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewPlace);
            this.onPlaceClickListener = onPlaceClickListener;

            itemView.setOnClickListener(this);
        }



        public void bind(Place place) {
            textView.setText(place.getName());

        }

        @Override
        public void onClick(View v) {
            onPlaceClickListener.onClick(getAdapterPosition());

        }
    }
}
