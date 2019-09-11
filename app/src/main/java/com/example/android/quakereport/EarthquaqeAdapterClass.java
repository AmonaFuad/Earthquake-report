package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
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
        TextView magnituedTextView = listItemView.findViewById(R.id.magnitude_text_view);
        // Display the magnitude of the current earthquake in that TextView
        magnituedTextView.setText(getInfo.getmagnitude());

        // Find the TextView with view ID location
        TextView firstLocationTextView = listItemView.findViewById(R.id.firstLocation_text_view);
        // Display the location of the current earthquake in that TextView
        String currentLocation = getInfo.getlocation();
        String substr = "of";
        String before = currentLocation.substring(0, currentLocation.indexOf(substr) + 2);
        String[] separatedString1 = currentLocation.split("of");

        firstLocationTextView.setText(before);

        // Find the TextView with view ID location
        TextView secondLocationTextView = listItemView.findViewById(R.id.secondLocation_text_view);
        // Display the location of the current earthquake in that TextView
        currentLocation = getInfo.getlocation();
        String[] separatedString2 = currentLocation.split("of");

        secondLocationTextView.setText(separatedString2[1]);


        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(getInfo.getTimeInMilliseconds());
      // Find the TextView with view ID date
        TextView dateTextView = listItemView.findViewById(R.id.date_text_view);
        // Display the date of the current earthquake in that TextView
        String formattedDate = formatDate(dateObject);
        dateTextView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeTextView = listItemView.findViewById(R.id.time_text_view);
// Display the date of the current earthquake in that TextView
        String formatedTime = formatTime(dateObject);
        timeTextView.setText(formatedTime);


        return listItemView;
    }
}
