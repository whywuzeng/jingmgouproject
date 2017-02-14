package com.ab.view.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ListView;

public class AbOuterListView extends ListView
{
  private GestureDetector mGestureDetector;

  public AbOuterListView(Context paramContext)
  {
    super(paramContext);
    this.mGestureDetector = new GestureDetector(new YScrollDetector());
    setFadingEdgeLength(0);
  }

  public AbOuterListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mGestureDetector = new GestureDetector(new YScrollDetector());
    setFadingEdgeLength(0);
  }

  public AbOuterListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return (super.onInterceptTouchEvent(paramMotionEvent)) && (this.mGestureDetector.onTouchEvent(paramMotionEvent));
  }

  class YScrollDetector extends GestureDetector.SimpleOnGestureListener
  {
    YScrollDetector()
    {
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      return Math.abs(paramFloat2) >= Math.abs(paramFloat1);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbOuterListView
 * JD-Core Version:    0.6.2
 */