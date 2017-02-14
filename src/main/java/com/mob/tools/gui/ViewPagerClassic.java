package com.mob.tools.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.mob.tools.utils.R;

public class ViewPagerClassic extends ViewGroup
{
  private static final int SNAP_VELOCITY = 500;
  private static final int TOUCH_STATE_REST = 0;
  private static final int TOUCH_STATE_SCROLLING = 1;
  private ViewPagerAdapter adapter;
  private int currentScreen;
  private float lastMotionX;
  private float lastMotionY;
  private int mMaximumVelocity;
  private VelocityTracker mVelocityTracker;
  private Scroller scroller;
  private int touchSlop;
  private int touchState = 0;

  public ViewPagerClassic(Context paramContext)
  {
    this(paramContext, null);
  }

  public ViewPagerClassic(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public ViewPagerClassic(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void handleInterceptMove(MotionEvent paramMotionEvent)
  {
    int j = 0;
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    int i = (int)Math.abs(f1 - this.lastMotionX);
    int k = (int)Math.abs(f2 - this.lastMotionY);
    if (i > this.touchSlop);
    for (i = 1; ; i = 0)
    {
      if (k > this.touchSlop)
        j = 1;
      if (((i != 0) || (j != 0)) && (i != 0))
      {
        this.touchState = 1;
        this.lastMotionX = f1;
      }
      return;
    }
  }

  private void handleScrollMove(MotionEvent paramMotionEvent)
  {
    if (this.adapter == null);
    int i;
    int j;
    do
    {
      do
      {
        do
        {
          return;
          float f = paramMotionEvent.getX();
          i = (int)(this.lastMotionX - f);
          this.lastMotionX = f;
          if (i >= 0)
            break;
        }
        while (getScrollX() <= 0);
        scrollBy(Math.max(-getScrollX(), i), 0);
        return;
      }
      while ((i <= 0) || (getChildCount() == 0));
      j = getChildAt(getChildCount() - 1).getRight() - getScrollX() - getWidth();
    }
    while (j <= 0);
    scrollBy(Math.min(j, i), 0);
  }

  private void init(Context paramContext)
  {
    this.scroller = new Scroller(getContext(), new Interpolator()
    {
      float[] values = { 0.0F, 0.0157073F, 0.0314108F, 0.0471065F, 0.0627905F, 0.0784591F, 0.0941083F, 0.109734F, 0.125333F, 0.140901F, 0.156434F, 0.171929F, 0.187381F, 0.202787F, 0.218143F, 0.233445F, 0.24869F, 0.263873F, 0.278991F, 0.29404F, 0.309017F, 0.323917F, 0.338738F, 0.353475F, 0.368125F, 0.382683F, 0.397148F, 0.411514F, 0.425779F, 0.439939F, 0.45399F, 0.46793F, 0.481754F, 0.495459F, 0.509041F, 0.522499F, 0.535827F, 0.549023F, 0.562083F, 0.575005F, 0.587785F, 0.60042F, 0.612907F, 0.625243F, 0.637424F, 0.649448F, 0.661312F, 0.673013F, 0.684547F, 0.695913F, 0.707107F, 0.718126F, 0.728969F, 0.739631F, 0.750111F, 0.760406F, 0.770513F, 0.78043F, 0.790155F, 0.799685F, 0.809017F, 0.81815F, 0.827081F, 0.835807F, 0.844328F, 0.85264F, 0.860742F, 0.868632F, 0.876307F, 0.883766F, 0.891007F, 0.898028F, 0.904827F, 0.911403F, 0.917755F, 0.92388F, 0.929776F, 0.935444F, 0.940881F, 0.946085F, 0.951057F, 0.955793F, 0.960294F, 0.964557F, 0.968583F, 0.97237F, 0.975917F, 0.979223F, 0.982287F, 0.985109F, 0.987688F, 0.990024F, 0.992115F, 0.993961F, 0.995562F, 0.996917F, 0.998027F, 0.99889F, 0.999507F, 0.999877F, 1.0F };

      public float getInterpolation(float paramAnonymousFloat)
      {
        int i = (int)(100.0F * paramAnonymousFloat);
        return this.values[i];
      }
    });
    paramContext = ViewConfiguration.get(paramContext);
    this.touchSlop = paramContext.getScaledTouchSlop();
    this.mMaximumVelocity = paramContext.getScaledMaximumFlingVelocity();
  }

  private void scrollToScreen(int paramInt, boolean paramBoolean)
  {
    int i;
    Object localObject;
    int j;
    if (paramInt != this.currentScreen)
    {
      i = 1;
      localObject = getFocusedChild();
      if ((localObject != null) && (i != 0) && (localObject == getChildAt(this.currentScreen)))
        ((View)localObject).clearFocus();
      i = getWidth() * paramInt - getScrollX();
      localObject = this.scroller;
      j = getScrollX();
      if (!paramBoolean)
        break label94;
    }
    label94: for (paramInt = 0; ; paramInt = Math.abs(i) / 2)
    {
      ((Scroller)localObject).startScroll(j, 0, i, 0, paramInt);
      invalidate();
      return;
      i = 0;
      break;
    }
  }

  public void computeScroll()
  {
    if (this.adapter == null);
    int k;
    do
    {
      return;
      if (this.scroller.computeScrollOffset())
      {
        scrollTo(this.scroller.getCurrX(), this.scroller.getCurrY());
        postInvalidate();
        return;
      }
      k = this.currentScreen;
      int m = this.scroller.getCurrX();
      int n = getWidth();
      int j = m / n;
      int i = j;
      if (m % n > n / 2)
        i = j + 1;
      this.currentScreen = Math.max(0, Math.min(i, getChildCount() - 1));
    }
    while ((k == this.currentScreen) || (this.adapter == null));
    this.adapter.onScreenChange(this.currentScreen, k);
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    if ((this.adapter == null) || (getChildCount() <= 0));
    long l;
    do
    {
      return;
      l = getDrawingTime();
      if (this.currentScreen > 0)
        drawChild(paramCanvas, getChildAt(this.currentScreen - 1), l);
      drawChild(paramCanvas, getChildAt(this.currentScreen), l);
    }
    while (this.currentScreen >= getChildCount() - 1);
    drawChild(paramCanvas, getChildAt(this.currentScreen + 1), l);
  }

  public boolean dispatchUnhandledMove(View paramView, int paramInt)
  {
    if (this.adapter == null)
      return super.dispatchUnhandledMove(paramView, paramInt);
    if (paramInt == 17)
    {
      if (this.currentScreen > 0)
      {
        scrollToScreen(this.currentScreen - 1);
        return true;
      }
    }
    else if ((paramInt == 66) && (this.currentScreen < getChildCount() - 1))
    {
      scrollToScreen(this.currentScreen + 1);
      return true;
    }
    return super.dispatchUnhandledMove(paramView, paramInt);
  }

  public int getCurrentScreen()
  {
    return this.currentScreen;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i == 2) && (this.touchState != 0));
    while (true)
    {
      return true;
      if (this.mVelocityTracker == null)
        this.mVelocityTracker = VelocityTracker.obtain();
      this.mVelocityTracker.addMovement(paramMotionEvent);
      switch (i)
      {
      default:
      case 2:
      case 0:
      case 1:
      case 3:
      }
      while (this.touchState == 0)
      {
        return false;
        handleInterceptMove(paramMotionEvent);
        continue;
        float f1 = paramMotionEvent.getX();
        float f2 = paramMotionEvent.getY();
        this.lastMotionX = f1;
        this.lastMotionY = f2;
        if (this.scroller.isFinished());
        for (i = 0; ; i = 1)
        {
          this.touchState = i;
          break;
        }
        if (this.mVelocityTracker != null)
        {
          this.mVelocityTracker.recycle();
          this.mVelocityTracker = null;
        }
        this.touchState = 0;
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.adapter == null);
    while (true)
    {
      return;
      int j = paramInt3 - paramInt1;
      int k = getChildCount();
      paramInt1 = 0;
      int i;
      for (paramInt3 = 0; paramInt1 < k; paramInt3 = i)
      {
        View localView = getChildAt(paramInt1);
        i = paramInt3;
        if (localView.getVisibility() != 8)
        {
          localView.layout(paramInt3, 0, paramInt3 + j, paramInt4 - paramInt2);
          i = paramInt3 + j;
        }
        paramInt1 += 1;
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.adapter == null)
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    int j = getChildCount();
    int k = View.MeasureSpec.makeMeasureSpec(R.getScreenWidth(getContext()), 1073741824);
    paramInt2 = 0;
    paramInt1 = 0;
    label38: if (paramInt2 < j)
    {
      View localView = getChildAt(paramInt2);
      localView.measure(k, 0);
      int i = localView.getMeasuredHeight();
      if (i <= paramInt1)
        break label119;
      paramInt1 = i;
    }
    label119: 
    while (true)
    {
      paramInt2 += 1;
      break label38;
      paramInt2 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
      super.onMeasure(k, paramInt2);
      paramInt1 = 0;
      while (paramInt1 < j)
      {
        getChildAt(paramInt1).measure(k, paramInt2);
        paramInt1 += 1;
      }
      break;
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.adapter == null)
      return false;
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int i = paramMotionEvent.getAction();
    float f = paramMotionEvent.getX();
    switch (i)
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return true;
      if (this.touchState != 0)
      {
        if (!this.scroller.isFinished())
          this.scroller.abortAnimation();
        this.lastMotionX = f;
        continue;
        if (this.touchState == 1)
        {
          handleScrollMove(paramMotionEvent);
        }
        else if ((onInterceptTouchEvent(paramMotionEvent)) && (this.touchState == 1))
        {
          handleScrollMove(paramMotionEvent);
          continue;
          if (this.touchState == 1)
          {
            paramMotionEvent = this.mVelocityTracker;
            paramMotionEvent.computeCurrentVelocity(1000, this.mMaximumVelocity);
            i = (int)paramMotionEvent.getXVelocity();
            if ((i <= 500) || (this.currentScreen <= 0))
              break label228;
            scrollToScreen(this.currentScreen - 1);
          }
          while (true)
          {
            if (this.mVelocityTracker != null)
            {
              this.mVelocityTracker.recycle();
              this.mVelocityTracker = null;
            }
            this.touchState = 0;
            break;
            label228: if ((i < -500) && (this.currentScreen < getChildCount() - 1))
            {
              scrollToScreen(this.currentScreen + 1);
            }
            else
            {
              i = getWidth();
              scrollToScreen((getScrollX() + i / 2) / i);
            }
          }
          this.touchState = 0;
        }
      }
    }
  }

  public void scrollLeft()
  {
    if (this.adapter == null);
    while ((this.currentScreen <= 0) || (!this.scroller.isFinished()))
      return;
    scrollToScreen(this.currentScreen - 1);
  }

  public void scrollRight()
  {
    if (this.adapter == null);
    while ((this.currentScreen >= getChildCount() - 1) || (!this.scroller.isFinished()))
      return;
    scrollToScreen(this.currentScreen + 1);
  }

  public void scrollToScreen(int paramInt)
  {
    scrollToScreen(paramInt, false);
  }

  public void setAdapter(ViewPagerAdapter paramViewPagerAdapter)
  {
    int i = 0;
    this.adapter = paramViewPagerAdapter;
    removeAllViews();
    this.currentScreen = 0;
    if (this.adapter == null);
    while (true)
    {
      return;
      int j = paramViewPagerAdapter.getCount();
      while (i < j)
      {
        addView(paramViewPagerAdapter.getView(i, this));
        i += 1;
      }
    }
  }

  public void setCurrentScreen(int paramInt)
  {
    if (this.adapter == null)
      return;
    if (!this.scroller.isFinished())
      this.scroller.abortAnimation();
    int i = this.currentScreen;
    this.currentScreen = Math.max(0, Math.min(paramInt, getChildCount()));
    this.adapter.onScreenChange(this.currentScreen, i);
    paramInt = R.getScreenWidth(getContext()) * this.currentScreen;
    this.scroller.startScroll(0, 0, paramInt, 0);
    scrollTo(paramInt, 0);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.ViewPagerClassic
 * JD-Core Version:    0.6.2
 */