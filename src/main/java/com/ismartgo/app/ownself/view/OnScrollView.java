package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;
import java.io.PrintStream;

public class OnScrollView extends ScrollView
{
  private ScrollViewListener scrollViewListener = null;
  private float xDistance;
  private float xLast;
  private float yDistance;
  private float yLast;

  public OnScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public OnScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public OnScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    do
    {
      while (true)
      {
        return super.onInterceptTouchEvent(paramMotionEvent);
        this.yDistance = 0.0F;
        this.xDistance = 0.0F;
        this.xLast = paramMotionEvent.getX();
        this.yLast = paramMotionEvent.getY();
      }
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      this.xDistance += Math.abs(f1 - this.xLast);
      this.yDistance += Math.abs(f2 - this.yLast);
      this.xLast = f1;
      this.yLast = f2;
    }
    while (this.xDistance <= this.yDistance);
    return false;
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.scrollViewListener != null)
      this.scrollViewListener.onScrollChanged(this, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    System.out.println("--->home scrollview onTouchEvent=" + paramMotionEvent.getAction());
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setScrollViewListener(ScrollViewListener paramScrollViewListener)
  {
    this.scrollViewListener = paramScrollViewListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.OnScrollView
 * JD-Core Version:    0.6.2
 */