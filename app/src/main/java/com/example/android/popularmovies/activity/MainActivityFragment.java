package com.example.android.popularmovies.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.api.TmdbLoadMoviesTask;
import com.example.android.popularmovies.api.TmdbUtils;
import com.example.android.popularmovies.ui.MovieGridView;

public class MainActivityFragment extends Fragment {

    private MovieGridView movieGridView;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        movieGridView = (MovieGridView) rootView.findViewById(R.id.gridview_movies);
        loadMovieDetails();
        return rootView;
    }

    private void loadMovieDetails() {
        final Uri requestUri = TmdbUtils.buildPopularMovieListRequestUri(1);
        final TmdbLoadMoviesTask tmdbLoadMoviesTask = new TmdbLoadMoviesTask(movieGridView, getActivity());
        tmdbLoadMoviesTask.execute(requestUri);
    }

}
