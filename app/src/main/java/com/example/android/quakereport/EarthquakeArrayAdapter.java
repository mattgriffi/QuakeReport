package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        TextView magnitudeView = (TextView) convertView.findViewById(R.id.magnitude);
        TextView locationView = (TextView) convertView.findViewById(R.id.location);
        TextView dateView = (TextView) convertView.findViewById(R.id.date);

        magnitudeView.setText(Double.toString(earthquake.getMagnitude()));
        locationView.setText(earthquake.getLocation());
        dateView.setText(earthquake.getDate());

        return convertView;
    }
}
