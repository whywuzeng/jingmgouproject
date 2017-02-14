package com.ab.view.sample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import android.widget.ScrollView;

public class AbInnerListView extends ListView
{
  private int maxHeight;
  private ScrollView parentScrollView;

  public AbInnerListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setParentScrollAble(boolean paramBoolean)
  {
    ScrollView localScrollView = this.parentScrollView;
    if (paramBoolean);
    for (paramBoolean = false; ; paramBoolean = true)
    {
      localScrollView.requestDisallowInterceptTouchEvent(paramBoolean);
      return;
    }
  }

  public int getMaxHeight()
  {
    return this.maxHeight;
  }

  public ScrollView getParentScrollView()
  {
    return this.parentScrollView;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 2:
    default:
    case 0:
    case 1:
    case 3:
    }
    while (true)
    {
      return super.onInterceptTouchEvent(paramMotionEvent);
      setParentScrollAble(false);
      continue;
      setParentScrollAble(true);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.maxHeight > -1)
      paramInt2 = View.MeasureSpec.makeMeasureSpec(this.maxHeight, -2147483648);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void setMaxHeight(int paramInt)
  {
    this.maxHeight = paramInt;
  }

  public void setParentScrollView(ScrollView paramScrollView)
  {
    this.parentScrollView = paramScrollView;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbInnerListView
 * JD-Core Version:    0.6.2
 */