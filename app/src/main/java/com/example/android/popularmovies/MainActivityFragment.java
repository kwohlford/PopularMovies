package com.example.android.popularmovies;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.popularmovies.api.MovieDetails;
import com.example.android.popularmovies.api.TmdbUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivityFragment extends Fragment {

    private static final String[] PLACEHOLDER_MOVIE_IDS =  {"283995", "271110", "246655"};

    private GridView movieGridView;
    private MovieGridAdapter movieGridAdapter;

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
        movieGridView = (GridView) rootView.findViewById(R.id.gridview_movies);

        loadMovieDetails(PLACEHOLDER_MOVIE_IDS);

        return rootView;
    }

    private void loadMovieDetails(final String[] movieIds) {
        final Uri[] movieUris = new Uri[movieIds.length];
        for (int i = 0; i < movieUris.length; i++) {
            movieUris[i] = TmdbUtils.buildMovieDetailsRequestUri(movieIds[i]);
        }
        final TmdbLoadMoviesTask tmdbLoadMoviesTask = new TmdbLoadMoviesTask();
        tmdbLoadMoviesTask.execute(movieUris);
    }

    public class TmdbLoadMoviesTask extends AsyncTask<Uri, Void, List<MovieDetails>> {

        private final String LOG_TAG = TmdbLoadMoviesTask.class.getSimpleName();
        private final ProgressDialog progressDialog;

        TmdbLoadMoviesTask() {
            this.progressDialog = new ProgressDialog(getActivity());
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setTitle("Loading movie data");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }

        @Override
        protected List<MovieDetails> doInBackground(final Uri... params) {
            final List<MovieDetails> loadedMovies = new ArrayList<>();

            for (final Uri param : params) {
                final String jsonResult = executeRequest(param);
                loadedMovies.add(new MovieDetails(jsonResult));
            }

            return loadedMovies;
        }

        @Override
        protected void onPostExecute(final List<MovieDetails> result) {
            progressDialog.dismiss();
            movieGridAdapter = new MovieGridAdapter(getActivity(), result);
            movieGridView.setAdapter(movieGridAdapter);
        }

        private String executeRequest(final Uri uri) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String responseString = null;

            try {
                // Start connection
                final URL url = buildUrlFromUri(uri);
                if (url == null) {
                    return null;
                }
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                Log.i(LOG_TAG, "Opening url connection: " + url.toString());

                // Read the input stream into a string
                final InputStream inputStream = urlConnection.getInputStream();
                final StringBuilder builder = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }

                responseString = builder.toString();
            } catch (final IOException e) {
                Log.e(LOG_TAG, "Error parsing URL request", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream reader", e);
                    }
                }
            }
            return responseString;
        }

        private URL buildUrlFromUri(final Uri uri) {
            URL url;
            try {
                url = new URL(uri.toString());
            } catch (final MalformedURLException e) {
                Log.e(LOG_TAG, "Could not build request URL for uri: " + uri.toString(), e);
                return null;
            }
            return url;
        }

    }
}
