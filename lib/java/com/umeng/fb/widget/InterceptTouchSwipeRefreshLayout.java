package com.umeng.fb.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

public class InterceptTouchSwipeRefreshLayout extends SwipeRefreshLayout
{
  private final String a = InterceptTouchSwipeRefreshLayout.class.getName();
  private View.OnTouchListener b;

  public InterceptTouchSwipeRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.b != null)
      this.b.onTouch(this, paramMotionEvent);
    return super.onInterceptTouchEvent(paramMotionEvent);
  }

  public void setInterceptTouch(View.OnTouchListener paramOnTouchListener)
  {
    this.b = paramOnTouchListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.widget.InterceptTouchSwipeRefreshLayout
 * JD-Core Version:    0.6.2
 */