package com.example.android.popularmovies.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
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

    private int rowHeight;

    public MovieGridAdapter(final Context context, final List<MovieDetails> resources) {
        super(context, 0, resources);
        this.resources = resources;
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        final ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, rowHeight));
        final String posterUrl = TmdbUtils.buildPosterUrl(resources.get(position).getPosterPath());
        Picasso.with(getContext()).load(posterUrl).into(imageView);
        return imageView;
    }

    public void setRowHeight(final int rowHeight) {
        this.rowHeight = rowHeight;
    }
}
