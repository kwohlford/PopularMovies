package com.example.android.popularmovies.api;

import android.net.Uri;
import android.util.Log;

import com.example.android.popularmovies.BuildConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for working with The Movie Database API.
 */
public class TmdbUtils {

    private static final String LOG_TAG = TmdbUtils.class.getSimpleName();

    // Movie requests
    private static final String MOVIE_DETAILS_BASE_URL = "https://api.themoviedb.org/3/movie";
    private static final String LIST_POPULAR = "popular";
    private static final String LIST_TOP = "top_rated";
    private static final String API_PARAM = "api_key";
    private static final String PAGE_PARAM = "page";

    // Image requests
    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p";
    private static final String IMAGE_SIZE = "/w185";

    // Json parsing
    private static final String RESULTS_ARRAY = "results";
    private static final String POSTER_PATH = "poster_path";

    public static String buildPosterUrl(final String posterPath) {
        return IMAGE_BASE_URL + IMAGE_SIZE + posterPath;
    }

    public static Uri buildMovieDetailsRequestUri(final String movieId) {
        return Uri.parse(MOVIE_DETAILS_BASE_URL).buildUpon()
                .appendPath(movieId)
                .appendQueryParameter(API_PARAM, BuildConfig.TMDB_API_KEY)
                .build();
    }

    public static Uri buildTopRatedMovieListRequestUri(final int page) {
        return buildMovieListRequestUri(LIST_TOP, page);
    }

    public static Uri buildPopularMovieListRequestUri(final int page) {
        return buildMovieListRequestUri(LIST_POPULAR, page);
    }

    private static Uri buildMovieListRequestUri(final String listType, final int page) {
        return Uri.parse(MOVIE_DETAILS_BASE_URL).buildUpon()
                .appendPath(listType)
                .appendQueryParameter(API_PARAM, BuildConfig.TMDB_API_KEY)
                .appendQueryParameter(PAGE_PARAM, Integer.toString(page))
                .build();
    }

    public static List<MovieDetails> parseMovieDetailsListFromJson(final String jsonString) {
        final List<MovieDetails> moviesList = new ArrayList<>();
        try {
            final JSONObject root = new JSONObject(jsonString);
            final JSONArray moviesArr = root.getJSONArray(RESULTS_ARRAY);
            for (int i = 0; i < moviesArr.length(); i ++) {
                moviesList.add(parseMovieDetailsFromJson(moviesArr.getJSONObject(i)));
            }
        } catch (final JSONException e) {
            Log.e(LOG_TAG, "Error parsing json", e);
        }
        return moviesList;
    }

    private static MovieDetails parseMovieDetailsFromJson(final JSONObject movieJson) throws JSONException {
        Log.i(LOG_TAG, "Parsing json string: " + movieJson.toString());
        final MovieDetails movieDetails = new MovieDetails();
        movieDetails.setPosterPath(movieJson.getString(POSTER_PATH));
        return movieDetails;
    }

}
