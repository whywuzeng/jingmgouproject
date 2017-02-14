package com.ab.view.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class AbOuterScrollView extends ScrollView
{
  private GestureDetector mGestureDetector = new GestureDetector(new YScrollDetector());

  public AbOuterScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setFadingEdgeLength(0);
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
      return (paramFloat2 == 0.0F) || (Math.abs(paramFloat2) >= Math.abs(paramFloat1));
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbOuterScrollView
 * JD-Core Version:    0.6.2
 */