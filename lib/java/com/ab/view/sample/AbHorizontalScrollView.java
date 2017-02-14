package com.ab.view.sample;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.ab.view.listener.AbOnScrollListener;

public class AbHorizontalScrollView extends HorizontalScrollView
{
  private int childWidth = 0;
  private int intitPosition;
  private AbOnScrollListener onScrollListner;

  public AbHorizontalScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public AbHorizontalScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void checkTotalWidth()
  {
    if (this.childWidth > 0);
    while (true)
    {
      return;
      int i = 0;
      while (i < getChildCount())
      {
        this.childWidth += getChildAt(i).getWidth();
        i += 1;
      }
    }
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getScrollX();
    if (this.intitPosition - i == 0)
    {
      if (this.onScrollListner == null)
        return;
      this.onScrollListner.onScrollStoped();
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          Rect localRect = new Rect();
          AbHorizontalScrollView.this.getDrawingRect(localRect);
          if (AbHorizontalScrollView.this.getScrollX() == 0)
          {
            AbHorizontalScrollView.this.onScrollListner.onScroll(0);
            AbHorizontalScrollView.this.onScrollListner.onScrollToLeft();
            return;
          }
          if (AbHorizontalScrollView.this.childWidth + AbHorizontalScrollView.this.getPaddingLeft() + AbHorizontalScrollView.this.getPaddingRight() == localRect.right)
          {
            AbHorizontalScrollView.this.onScrollListner.onScroll(AbHorizontalScrollView.this.getScrollX());
            AbHorizontalScrollView.this.onScrollListner.onScrollToRight();
            return;
          }
          AbHorizontalScrollView.this.onScrollListner.onScroll(AbHorizontalScrollView.this.getScrollX());
        }
      }
      , 200L);
    }
    while (true)
    {
      super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
      this.intitPosition = getScrollX();
      checkTotalWidth();
    }
  }

  public void setOnScrollListener(AbOnScrollListener paramAbOnScrollListener)
  {
    this.onScrollListner = paramAbOnScrollListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.sample.AbHorizontalScrollView
 * JD-Core Version:    0.6.2
 */