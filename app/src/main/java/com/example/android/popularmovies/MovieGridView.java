package com.example.android.popularmovies;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Custom grid view for movie posters
 */
public class MovieGridView extends GridView {

    private int rowHeight;

    public MovieGridView(Context context) {
        super(context);
    }

    public MovieGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        rowHeight = heightMeasureSpec / 2;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setAdapter(final MovieGridAdapter adapter) {
        adapter.setRowHeight(rowHeight);
        super.setAdapter(adapter);
    }

}
