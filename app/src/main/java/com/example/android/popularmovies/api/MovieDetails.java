package com.example.android.popularmovies.api;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Simple object containing movie data loaded from json.
 */
public class MovieDetails {

    private static final String LOG_TAG = MovieDetails.class.getSimpleName();
    private static final String BELONGS_TO_COLLECTION = "belongs_to_collection";
    private static final String POSTER_PATH = "poster_path";

    private String posterPath;

    /**
     * Create a new movie data object from json.
     * @param jsonString Json string from TMDB api request
     */
    public MovieDetails(final String jsonString) {
        loadDetailsFromJson(jsonString);
    }

    private void loadDetailsFromJson(final String jsonString) {
        Log.i(LOG_TAG, "Parsing json string: " + jsonString);
        try {
            final JSONObject root = new JSONObject(jsonString);
            final JSONObject collectionObject = root.getJSONObject(BELONGS_TO_COLLECTION);
            posterPath = collectionObject.getString(POSTER_PATH);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPosterPath() {
        return posterPath;
    }

}
