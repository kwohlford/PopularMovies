package com.example.android.popularmovies.api;


/**
 * POJO containing movie data loaded from json.
 */
public class MovieDetails {

    private String posterPath;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(final String posterPath) {
        this.posterPath = posterPath;
    }
}
