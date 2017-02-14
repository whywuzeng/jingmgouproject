package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class InnerScrollView extends ScrollView
{
  int currentY;
  private int lastScrollDelta = 0;
  int mTop = 10;
  public OnScrollView parentScrollView;
  private ScrollViewListener scrollViewListener = null;

  public InnerScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private int getScrollRange()
  {
    int i = 0;
    if (getChildCount() > 0)
      i = Math.max(0, getChildAt(0).getHeight() - getHeight());
    return i;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.parentScrollView == null)
      return super.onInterceptTouchEvent(paramMotionEvent);
    if (paramMotionEvent.getAction() == 0)
    {
      this.currentY = ((int)paramMotionEvent.getY());
      return super.onInterceptTouchEvent(paramMotionEvent);
    }
    if (paramMotionEvent.getAction() != 1)
      paramMotionEvent.getAction();
    return super.onInterceptTouchEvent(paramMotionEvent);
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void resume()
  {
    overScrollBy(0, -this.lastScrollDelta, 0, getScrollY(), 0, getScrollRange(), 0, 0, true);
    this.lastScrollDelta = 0;
  }

  public void scrollTo(View paramView)
  {
    int i = getScrollY();
    i = paramView.getTop() - this.mTop - i;
    this.lastScrollDelta = i;
    overScrollBy(0, i, 0, getScrollY(), 0, getScrollRange(), 0, 0, true);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.InnerScrollView
 * JD-Core Version:    0.6.2
 */