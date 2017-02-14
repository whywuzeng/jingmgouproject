package com.ab.view.sample;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class AbUnSlideViewPager extends ViewPager
{
  private boolean enabled = false;

  public AbUnSlideViewPager(Context paramContext)
  {
    super(paramContext);
  }

  public AbUnSlideViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.enabled)
      return super.onInterceptTouchEvent(paramMotionEvent);
    return false;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.enabled)
      return super.onTouchEvent(paramMotionEvent);
    return false;
  }

  public void setPagingEnabled(boolean paramBoolean)
  {
    this.enabled = paramBoolean;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbUnSlideViewPager
 * JD-Core Version:    0.6.2
 */