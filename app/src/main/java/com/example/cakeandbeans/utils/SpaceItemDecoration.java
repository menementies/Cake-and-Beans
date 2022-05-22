package com.example.cakeandbeans.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildLayoutPosition(view) %2 !=0)
        {
            outRect.top= 50;
            outRect.bottom = -50;
        }
    }
}