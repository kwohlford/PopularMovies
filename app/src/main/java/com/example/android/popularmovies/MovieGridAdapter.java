package com.example.android.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.api.MovieDetails;
import com.example.android.popularmovies.api.TmdbUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Custom adapter for displaying movie posters.
 */
public class MovieGridAdapter extends ArrayAdapter<MovieDetails> {

    private final List<MovieDetails> resources;

    public MovieGridAdapter(final Context context, final List<MovieDetails> resources) {
        super(context, 0, resources);
        this.resources = resources;
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        final ImageView imageView = new ImageView(getContext());
        final String posterUrl = TmdbUtils.buildPosterUrl(resources.get(position).getPosterPath());
        Picasso.with(getContext()).load(posterUrl).into(imageView);
        return imageView;
    }
}
