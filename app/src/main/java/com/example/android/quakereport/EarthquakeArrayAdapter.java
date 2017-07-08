package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by MatthewG on 2017/07/05.
 */

public class EarthquakeArrayAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeArrayAdapter(@NonNull Context context, @NonNull List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Earthquake earthquake = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get all of the list item views
        TextView magnitudeView = (TextView) convertView.findViewById(R.id.magnitude);
        TextView locationOffsetView = (TextView) convertView.findViewById(R.id.location_offset);
        TextView locationPrimaryView = (TextView) convertView.findViewById(R.id.location_primary);
        TextView dateView = (TextView) convertView.findViewById(R.id.date);

        // Format the magnitude
        double magnitudeDouble = earthquake.getMagnitude();
        String magnitude = new DecimalFormat("0.0").format(magnitudeDouble);

        // Set the magnitude circle color
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(magnitudeDouble);
        magnitudeCircle.setColor(magnitudeColor);

        // Format the location
        String location = earthquake.getLocation();
        String locationOffset;
        String locationPrimary;
        if (Character.isDigit(location.charAt(0))) {
            locationOffset = location.substring(0, location.indexOf("of") + 2);
            locationPrimary = location.substring(location.indexOf("of") + 3);
        } else {
            locationOffset = "Near the";
            locationPrimary = location;
        }

        // Format the date
        long dateLong = earthquake.getDate();
        Date dateObject = new Date(dateLong);
        String date = new SimpleDateFormat("LLL dd, yyyy\nh:mm a").format(dateObject);

        // Add the text to the views
        magnitudeView.setText(magnitude);
        locationOffsetView.setText(locationOffset);
        locationPrimaryView.setText(locationPrimary);
        dateView.setText(date);

        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {

        int colorId;

        switch ((int) magnitude) {
            case 0:
            case 1:
                colorId = R.color.magnitude1;
                break;
            case 2:
                colorId = R.color.magnitude2;
                break;
            case 3:
                colorId = R.color.magnitude3;
                break;
            case 4:
                colorId = R.color.magnitude4;
                break;
            case 5:
                colorId = R.color.magnitude5;
                break;
            case 6:
                colorId = R.color.magnitude6;
                break;
            case 7:
                colorId = R.color.magnitude7;
                break;
            case 8:
                colorId = R.color.magnitude8;
                break;
            case 9:
                colorId = R.color.magnitude9;
                break;
            default:
                colorId = R.color.magnitude10plus;
        }

        return ContextCompat.getColor(getContext(), colorId);
    }
}
