package com.example.android.quakereport;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

/**
 * Created by MatthewG on 2017/07/08.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String url;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        List<Earthquake> earthquakeList = null;
        String jsonResponse = null;

        if (url == null) {
            return null;
        }

        try {
            jsonResponse = QueryUtils.makeHttpRequest(url);
        } catch (IOException exception) {
            Log.e(LOG_TAG, "Error making HTTP request", exception);
        }

        earthquakeList = QueryUtils.extractEarthquakes(jsonResponse);

        return earthquakeList;
    }

}