package com.appdesigndm.viewpagerexample;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

public class ShadowTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.1f;
    private ViewPager mViewPager;

    public ShadowTransformer(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public void transformPage(View view, float position) {
        // Fix card position with padding
        float absPosition = Math.abs(position - getStartOffset());

        if (absPosition < -1) { // [-Infinity,-1)
            view.setScaleX(1 - MIN_SCALE);
            view.setScaleY(1 - MIN_SCALE);
        } else if (absPosition <= 1) { // [-1,1]
            view.setScaleX(1 - MIN_SCALE * absPosition);
            view.setScaleY(1 - MIN_SCALE * absPosition);
        } else if (absPosition > 1) { // (1,+Infinity]
            view.setScaleX(1 - MIN_SCALE);
            view.setScaleY(1 - MIN_SCALE);
        }
    }

    /**
     * @return the offset when there is padding in order to center the card
     */
    private float getStartOffset() {
        Point screen = new Point();
        WindowManager window = (WindowManager) mViewPager.getContext().getSystemService(Context.WINDOW_SERVICE);
        assert window != null;
        window.getDefaultDisplay().getSize(screen);


        float padding = mViewPager.getPaddingStart();
        return (padding) / (screen.x - 2 * padding);
    }
}
