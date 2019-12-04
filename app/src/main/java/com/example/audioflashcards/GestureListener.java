package com.example.audioflashcards;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.GestureDetectorCompat;


public class GestureListener implements View.OnTouchListener {

    private final GestureDetectorCompat gestureDetector;

    public GestureListener(Context context) {
        gestureDetector = new GestureDetectorCompat(context, new MyGestureListener());
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        public boolean onDown(MotionEvent event) {
            return true;
        }

        public boolean onSingleTapUp(MotionEvent event) {
            onSingleClick();
            return true;
        }

        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {
            boolean result = false;
            float diffY = event2.getY() - event1.getY();
            float diffX = event2.getX() - event1.getX();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX < 0){
                        onSwipeLeft();
                    }
                    result = true;
                }
            }

            return result;
        }
    }

    public void onSwipeLeft() {
    }

    public void onSingleClick() {
    }
}
