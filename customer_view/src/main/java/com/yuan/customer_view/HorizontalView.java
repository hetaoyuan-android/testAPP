package com.yuan.customer_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalView extends ViewGroup {

    private int lastX;
    private int lastY;
    private int currentIndex = 0;
    private int childWidth = 0;
    private Scroller scroller;
    private VelocityTracker tracker;
    private int lastInterceptX = 0;
    private int lastInterceptY = 0;

    public HorizontalView(Context context) {
        super(context);
        init();
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onLayout(boolean b, int j, int i1, int i2, int i3) {
        int childCount = getChildCount();
        int left = 0;
        View child;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                int width = child.getMeasuredWidth();
                childWidth = width;
                child.layout(left, 0 ,left + width, child.getMeasuredHeight());
                left += width;
            }
        }
    }

    private void init() {
        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastInterceptX;
                int deltaY = y - lastInterceptX;
                if (Math.abs(deltaX) - Math.abs(deltaY) > 0) {
                    intercept = true;
                    Log.i("hetao", "intecept = true");
                } else {
                    intercept = false;
                    Log.i("hetao", "intecept = false");
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
        }
        lastX = x;
        lastY = y;
        lastInterceptX = x;
        lastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - lastX;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int distance = getScrollX() - currentIndex * childWidth;
                if (Math.abs(distance) > childWidth / 2) {
                    if (distance > 0) {
                        currentIndex++;
                    } else {
                        currentIndex--;
                    }
                } else {
                    tracker.computeCurrentVelocity(1000);
                    float xV = tracker.getXVelocity();
                    if (Math.abs(xV) > 50) {
                        if (xV > 0) {
                            currentIndex--;
                        } else {
                            currentIndex++;
                        }
                    }
                }
                currentIndex = currentIndex < 0 ? 0 : currentIndex > getChildCount() - 1 ? getChildCount() - 1 : currentIndex;
                smoothScrollTo(currentIndex * childWidth, 0);
                tracker.clear();
                break;
            default:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int hightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int hightSize = MeasureSpec.getSize(heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else if (widthMode == MeasureSpec.AT_MOST && hightMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth * getChildCount(), childHeight);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            setMeasuredDimension(childWidth * getChildCount(), hightSize);
        } else if (hightMode == MeasureSpec.AT_MOST) {
            int childHeight = getChildAt(0).getMeasuredHeight();
            setMeasuredDimension(widthSize,childHeight);
        }
    }

    private void smoothScrollTo(int destX, int destY) {
        scroller.startScroll(getScrollX(), getScrollY(), destX - getScrollX(), destY - getScrollY(), 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
