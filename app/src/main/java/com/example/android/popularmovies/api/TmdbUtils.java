package com.example.android.popularmovies.api;

import android.net.Uri;

import com.example.android.popularmovies.BuildConfig;

/**
 * Utility methods for working with The Movie Database API.
 */
public class TmdbUtils {

    private static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p";
    private static final String IMAGE_SIZE = "/w185";

    private static final String MOVIE_DETAILS_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_PARAM = "api_key";

    public static String buildPosterUrl(final String posterPath) {
        return IMAGE_BASE_URL + IMAGE_SIZE + posterPath;
    }

    public static Uri buildMovieDetailsRequestUri(final String movieId) {
        return Uri.parse(MOVIE_DETAILS_BASE_URL).buildUpon()
                .appendPath(movieId)
                .appendQueryParameter(API_PARAM, BuildConfig.TMDB_API_KEY)
                .build();
    }

}
