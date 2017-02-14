package com.ab.view.sample;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

public class AbInnerViewPager extends ViewPager
{
  private GestureDetector mGestureDetector = new GestureDetector(new YScrollDetector());
  private ListView parentListView;
  private ScrollView parentScrollView;

  public AbInnerViewPager(Context paramContext)
  {
    super(paramContext);
    setFadingEdgeLength(0);
  }

  public AbInnerViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setFadingEdgeLength(0);
  }

  private void setParentScrollAble(boolean paramBoolean)
  {
    boolean bool2 = false;
    Object localObject;
    boolean bool1;
    if (this.parentScrollView != null)
    {
      localObject = this.parentScrollView;
      if (paramBoolean)
      {
        bool1 = false;
        ((ScrollView)localObject).requestDisallowInterceptTouchEvent(bool1);
      }
    }
    else if (this.parentListView != null)
    {
      localObject = this.parentListView;
      if (!paramBoolean)
        break label58;
    }
    label58: for (paramBoolean = bool2; ; paramBoolean = true)
    {
      ((ListView)localObject).requestDisallowInterceptTouchEvent(paramBoolean);
      return;
      bool1 = true;
      break;
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return (super.onInterceptTouchEvent(paramMotionEvent)) && (this.mGestureDetector.onTouchEvent(paramMotionEvent));
  }

  public void setParentListView(ListView paramListView)
  {
    this.parentListView = paramListView;
  }

  public void setParentScrollView(ScrollView paramScrollView)
  {
    this.parentScrollView = paramScrollView;
  }

  class YScrollDetector extends GestureDetector.SimpleOnGestureListener
  {
    YScrollDetector()
    {
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      if (Math.abs(paramFloat1) >= Math.abs(paramFloat2))
      {
        AbInnerViewPager.this.setParentScrollAble(false);
        return true;
      }
      AbInnerViewPager.this.setParentScrollAble(true);
      return false;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbInnerViewPager
 * JD-Core Version:    0.6.2
 */