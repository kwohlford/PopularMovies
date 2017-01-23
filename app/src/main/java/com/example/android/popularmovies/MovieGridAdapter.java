package com.example.android.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

public class MovieGridAdapter extends ArrayAdapter<Integer> {

    public MovieGridAdapter(final Context context, final List<Integer> resources) {
        super(context, 0, resources);
    }

    @NonNull
    @Override
    public View getView(final int position, final View convertView, @NonNull final ViewGroup parent) {
        final ImageView placeholderImage = new ImageView(getContext());
        placeholderImage.setImageResource(R.drawable.ic_launcher);
        return placeholderImage;
    }
}
