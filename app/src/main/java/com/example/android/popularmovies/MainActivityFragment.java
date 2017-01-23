package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Arrays;

public class MainActivityFragment extends Fragment {

    final Integer[] placeholderArray = {
            1, 1, 1, 1
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final GridView listView = (GridView) rootView.findViewById(R.id.gridview_movies);
        final MovieGridAdapter movieGridAdapter = new MovieGridAdapter(getActivity(), Arrays.asList(placeholderArray));
        listView.setAdapter(movieGridAdapter);

        return rootView;
    }
}
