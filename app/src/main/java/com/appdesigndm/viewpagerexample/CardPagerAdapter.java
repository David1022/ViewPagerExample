package com.appdesigndm.viewpagerexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 2/7/18.
 */

public class CardPagerAdapter extends PagerAdapter implements CardAdapter{

    public static final int MAX_ELEVATION_FACTOR = 8;
    private String[] list = {"texto 1", "texto 2", "texto 3", "texto 4", "texto 5"};
    private List<CardView> mViews;
    private List<String> mData;
    private float mBaseElevation;

    public CardPagerAdapter(Context context) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        mBaseElevation = dpToPixels(2, context);
        for (String t : list) {
            mViews.add(null);
            mData.add(t);
        }
    }

    @Override
    public float getPageWidth(int position) {
        return 0.9f;
    }

    @Override
    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.custom_item, container, false);
        container.addView(view);

        TextView textView= view.findViewById(R.id.texto);
        textView.setText(mData.get(position));

        CardView cardView = view.findViewById(R.id.card_view);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

}
