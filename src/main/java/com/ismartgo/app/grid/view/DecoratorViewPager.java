package com.ismartgo.app.grid.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import java.io.PrintStream;

public class DecoratorViewPager extends ViewPager
{
  boolean intercept;
  private float mDownX;
  private float mDownY;
  private GestureDetector mGestureDetector;
  View.OnTouchListener mGestureListener;

  public DecoratorViewPager(Context paramContext)
  {
    super(paramContext);
  }

  public DecoratorViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    System.out.println(paramMotionEvent.getAction());
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      System.out.println("intercept: " + this.intercept);
      return this.intercept;
      this.mDownX = paramMotionEvent.getX();
      this.mDownY = paramMotionEvent.getY();
      this.intercept = super.onInterceptTouchEvent(paramMotionEvent);
      continue;
      if (Math.abs(paramMotionEvent.getX() - this.mDownX) > Math.abs(paramMotionEvent.getY() - this.mDownY))
      {
        this.intercept = true;
      }
      else
      {
        this.intercept = super.onInterceptTouchEvent(paramMotionEvent);
        continue;
        this.intercept = super.onInterceptTouchEvent(paramMotionEvent);
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.DecoratorViewPager
 * JD-Core Version:    0.6.2
 */