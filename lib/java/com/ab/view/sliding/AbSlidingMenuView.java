package com.ab.view.sliding;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;
import com.ab.global.AbAppData;

public class AbSlidingMenuView extends ViewGroup
{
  public static final int SCREEN_STATE_CLOSE = 0;
  public static final int SCREEN_STATE_OPEN = 1;
  public static final int SCROLL_STATE_ALLOW = 1;
  public static final int SCROLL_STATE_NO_ALLOW = 0;
  public static final int TOUCH_STATE_RESTART = 0;
  public static final int TOUCH_STATE_SCROLLING = 1;
  private boolean D = AbAppData.DEBUG;
  private String TAG = AbSlidingMenuView.class.getSimpleName();
  private boolean mOnClick = false;
  private int mScreenState = 0;
  private int mScrollState = 0;
  private Scroller mScroller;
  private int mTouchState = 0;
  private VelocityTracker mVelocityTracker;
  private int mVelocityValue = 0;
  private int mWidth;

  public AbSlidingMenuView(Context paramContext)
  {
    super(paramContext);
    this.mScroller = new Scroller(paramContext);
    this.mWidth = ((int)TypedValue.applyDimension(1, 60.0F, getResources().getDisplayMetrics()));
  }

  public AbSlidingMenuView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void obtainVelocityTracker(MotionEvent paramMotionEvent)
  {
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
  }

  private void releaseVelocityTracker()
  {
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }

  public void close()
  {
    this.mScreenState = 0;
    this.mScroller.startScroll(getChildAt(1).getScrollX(), 0, -getChildAt(1).getScrollX(), 0, 800);
    invalidate();
  }

  public void computeScroll()
  {
    super.computeScroll();
    if (this.mScroller.computeScrollOffset())
    {
      getChildAt(1).scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
      postInvalidate();
    }
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    obtainVelocityTracker(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      bool = super.dispatchTouchEvent(paramMotionEvent);
      label175: label188: 
      do
      {
        return bool;
        if (this.D)
          Log.d(this.TAG, "--dispatchTouchEvent ACTION_DOWN--");
        if (this.mScroller.isFinished());
        for (int i = 0; ; i = 1)
        {
          this.mTouchState = i;
          if (this.mTouchState != 0)
            break label188;
          i = (int)paramMotionEvent.getX();
          int j = getWidth();
          if (((i > this.mWidth) || (this.mScreenState != 0) || (this.mTouchState != 0)) && ((i < j - this.mWidth) || (this.mScreenState != 1) || (this.mTouchState != 0)))
            break label175;
          if (this.mScreenState == 1)
            this.mOnClick = true;
          this.mScrollState = 1;
          break;
        }
        this.mOnClick = false;
        this.mScrollState = 0;
        break;
      }
      while (!this.D);
      Log.d(this.TAG, "--dispatchTouchEvent ACTION_DOWN return false--");
      return false;
      if (this.D)
        Log.d(this.TAG, "--dispatchTouchEvent ACTION_MOVE--");
      this.mVelocityTracker.computeCurrentVelocity(1000, ViewConfiguration.getMaximumFlingVelocity());
      if ((this.mScrollState == 1) && (getWidth() - (int)paramMotionEvent.getX() < this.mWidth))
      {
        if (this.D)
          Log.d(this.TAG, "--dispatchTouchEvent ACTION_MOVE return true--");
        return true;
        releaseVelocityTracker();
        if (this.mOnClick)
        {
          this.mOnClick = false;
          this.mScreenState = 0;
          this.mScroller.startScroll(getChildAt(1).getScrollX(), 0, -getChildAt(1).getScrollX(), 0, 800);
          invalidate();
        }
      }
    }
  }

  public int getScreenState()
  {
    return this.mScreenState;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = true;
    obtainVelocityTracker(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    do
    {
      bool = super.onInterceptTouchEvent(paramMotionEvent);
      do
      {
        return bool;
        if (this.D)
          Log.d(this.TAG, "--onInterceptTouchEvent ACTION_DOWN--");
        if (this.mScroller.isFinished());
        for (int i = 0; ; i = 1)
        {
          this.mTouchState = i;
          if (this.mTouchState != 1)
            break;
          if (this.D)
            Log.d(this.TAG, "--onInterceptTouchEvent ACTION_DOWN return false--");
          return false;
        }
        if (this.D)
          Log.d(this.TAG, "--onInterceptTouchEvent ACTION_MOVE--");
        this.mOnClick = false;
        this.mVelocityTracker.computeCurrentVelocity(1000, ViewConfiguration.getMaximumFlingVelocity());
        if ((this.mScrollState != 1) || (Math.abs(this.mVelocityTracker.getXVelocity()) <= 200.0F))
          break;
      }
      while (!this.D);
      Log.d(this.TAG, "--onInterceptTouchEvent ACTION_MOVE return true--");
      return true;
      releaseVelocityTracker();
    }
    while ((this.mScrollState != 1) || (this.mScreenState != 1));
    return true;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = 0;
    while (true)
    {
      if (paramInt1 >= getChildCount())
        return;
      View localView = getChildAt(paramInt1);
      paramInt2 = localView.getMeasuredHeight();
      localView.layout(0, 0, localView.getMeasuredWidth(), paramInt2);
      paramInt1 += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    int i = 0;
    while (true)
    {
      if (i >= getChildCount())
        return;
      getChildAt(i).measure(paramInt1, paramInt2);
      i += 1;
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    obtainVelocityTracker(paramMotionEvent);
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      bool = super.onTouchEvent(paramMotionEvent);
      label42: return bool;
      if (this.D)
        Log.d(this.TAG, "--onTouchEvent ACTION_DOWN--");
      if (this.mScroller.isFinished());
      for (int i = 0; ; i = 1)
      {
        this.mTouchState = i;
        if (this.mTouchState != 1)
          break;
        if (!this.D)
          break label42;
        Log.d(this.TAG, "--onTouchEvent ACTION_DOWN return false--");
        return false;
      }
      if (this.D)
        Log.d(this.TAG, "--onTouchEvent ACTION_MOVE--");
      this.mVelocityTracker.computeCurrentVelocity(1000, ViewConfiguration.getMaximumFlingVelocity());
      this.mVelocityValue = ((int)this.mVelocityTracker.getXVelocity());
      getChildAt(1).scrollTo(-(int)paramMotionEvent.getX(), 0);
      continue;
      if (this.mScrollState == 1)
        if (this.mVelocityValue > 2000)
        {
          this.mScreenState = 1;
          this.mScroller.startScroll(getChildAt(1).getScrollX(), 0, -(getWidth() - Math.abs(getChildAt(1).getScrollX()) - this.mWidth), 0, 250);
          invalidate();
        }
        else if (this.mVelocityValue < -2000)
        {
          this.mScreenState = 0;
          this.mScroller.startScroll(getChildAt(1).getScrollX(), 0, -getChildAt(1).getScrollX(), 0, 250);
          invalidate();
        }
        else if (paramMotionEvent.getX() < getWidth() / 2)
        {
          this.mScreenState = 0;
          this.mScroller.startScroll(getChildAt(1).getScrollX(), 0, -getChildAt(1).getScrollX(), 0, 800);
          invalidate();
        }
        else
        {
          this.mScreenState = 1;
          this.mScroller.startScroll(getChildAt(1).getScrollX(), 0, -(getWidth() - Math.abs(getChildAt(1).getScrollX()) - this.mWidth), 0, 800);
          invalidate();
        }
    }
  }

  public void open()
  {
    if (this.mScroller.isFinished());
    for (int i = 0; ; i = 1)
    {
      this.mTouchState = i;
      if (this.mTouchState == 0)
      {
        this.mScreenState = 1;
        this.mScroller.startScroll(getChildAt(1).getScrollX(), 0, -(getWidth() - Math.abs(getChildAt(1).getScrollX()) - this.mWidth), 0, 800);
        invalidate();
      }
      return;
    }
  }

  public void setContentView(View paramView)
  {
    removeViewAt(1);
    addView(paramView, 1, getLayoutParams());
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sliding.AbSlidingMenuView
 * JD-Core Version:    0.6.2
 */