package com.example.android.popularmovies.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSortPopular) {
            loadMovieDetails(TmdbUtils.buildPopularMovieListRequestUri(1));
            return true;
        }
        if (id == R.id.menuSortTopRated) {
            loadMovieDetails(TmdbUtils.buildTopRatedMovieListRequestUri(1));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        movieGridView = (MovieGridView) rootView.findViewById(R.id.gridview_movies);
        loadMovieDetails(TmdbUtils.buildPopularMovieListRequestUri(1));
        return rootView;
    }

    private void loadMovieDetails(final Uri requestUri) {
        final TmdbLoadMoviesTask tmdbLoadMoviesTask = new TmdbLoadMoviesTask(movieGridView, getActivity());
        tmdbLoadMoviesTask.execute(requestUri);
    }

}
