package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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

    private static final String LOCATION_SEPARATE = "of";
    public static int MmagnitudeColor;


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

    // return the decimal format in magnitude (i.e. " 0:0")
    private String formatMagnitude(double magnitudeNumber) {
        DecimalFormat formatter = new DecimalFormat("0.0");
        return formatter.format(magnitudeNumber);

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
        final EarthquakeCustomClass getInfo = getItem(position);

        // Find the TextView with view ID magnitude
        TextView magnituedTextView = listItemView.findViewById(R.id.magnitude_text_view);

        /*
 Display the magnitude of the current earthquake in that TextView
  * formatMagnitude  method take double data type and return string
  *
*/
        double magnitudeValue = getInfo.getmagnitude();
        String formattedMagnitude = formatMagnitude(magnitudeValue);

        magnituedTextView.setText(formattedMagnitude);

        // Find the TextView to display the offset location with view ID location_offset
        TextView firstLocationTextView = listItemView.findViewById(R.id.location_offset);
        // Find the TextView to display the primary location with view ID primary_location
        TextView secondLocationTextView = listItemView.findViewById(R.id.primary_location);
        //define string to get the original location from earthQuakeCustom class
        String original_location = getInfo.getlocation();
        // define string to store first location on it
        String offset_location;
        // define string to store second location on it

        String primary_locatin;
        //make if conditin to check if the location has offset , if not we display the string near by
        String[] parts = original_location.split(LOCATION_SEPARATE);
        if (original_location.contains(LOCATION_SEPARATE)) {
            offset_location = parts[0] + LOCATION_SEPARATE;
            primary_locatin = parts[1];
        } else {
            offset_location = getContext().getString(R.string.near_the);
            primary_locatin = original_location;
        }
        firstLocationTextView.setText(offset_location);
        secondLocationTextView.setText(primary_locatin);


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

        // set the proper color for each magnitude
        switch ((int) magnitudeValue) {
            case 0:
                MmagnitudeColor = R.color.magnitude1;
            case 1:
                MmagnitudeColor = R.color.magnitude1;
                break;
            case 2:
                MmagnitudeColor = R.color.magnitude2;
                break;
            case 3:
                MmagnitudeColor = R.color.magnitude3;
                break;
            case 4:
                MmagnitudeColor = R.color.magnitude4;
                break;
            case 5:
                MmagnitudeColor = R.color.magnitude5;
                break;
            case 6:
                MmagnitudeColor = R.color.magnitude6;
                break;
            case 7:
                MmagnitudeColor = R.color.magnitude7;
                break;
            case 8:
                MmagnitudeColor = R.color.magnitude8;
                break;
            case 9:
                MmagnitudeColor = R.color.magnitude9;
                break;

            case 10:
                MmagnitudeColor = R.color.magnitude10plus;
                break;
            default:
                MmagnitudeColor = R.color.magnitude1;

        }


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnituedTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int color = ContextCompat.getColor(getContext(), MmagnitudeColor);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(color);

// open the url for each earthquake when touch on primary location
        secondLocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getInfo.geturl()));

                // Send the intent to launch a new activity
                getContext().startActivity(websiteIntent);

            }
        });

        return listItemView;
    }
}
