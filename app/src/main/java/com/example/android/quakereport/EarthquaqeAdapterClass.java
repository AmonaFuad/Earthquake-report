package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An {@link EarthquaqeAdapterClass} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link EarthquakeCustomClass} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */

public class EarthquaqeAdapterClass extends ArrayAdapter <EarthquakeCustomClass> {

    /**
     * Constructs a new {@link EarthquaqeAdapterClass}.
     *
     * @param context        of the app
     * @param earthquakeInfo is the list of earthquakes, which is the data source of the adapter
     */
    public EarthquaqeAdapterClass(Context context, ArrayList <EarthquakeCustomClass> earthquakeInfo) {
        super(context, 0, earthquakeInfo);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            listItemView = inflater.inflate(R.layout.custom_list, parent, false);
        }
        // Find the earthquake at the given position in the list of earthquakes
        EarthquakeCustomClass getInfo = getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnituedTextView = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        // Display the magnitude of the current earthquake in that TextView
        magnituedTextView.setText(getInfo.getmagnitude());

        // Find the TextView with view ID location
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_text_view);
        // Display the location of the current earthquake in that TextView
        locationTextView.setText(getInfo.getlocation());

      // Find the TextView with view ID date
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date_text_view);
        // Display the date of the current earthquake in that TextView
        dateTextView.setText(getInfo.getMdate());

        return listItemView;
    }
}
