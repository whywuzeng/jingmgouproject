package com.ismartgo.app.grid.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import java.io.PrintStream;

public class ReceiptViewPager extends ViewPager
{
  PointF curP = new PointF();
  PointF downP = new PointF();

  public ReceiptViewPager(Context paramContext)
  {
    super(paramContext);
  }

  public ReceiptViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = true;
    System.out.println("onTouchEvent");
    if (getChildCount() <= 1)
      bool = super.onTouchEvent(paramMotionEvent);
    do
    {
      return bool;
      this.curP.x = paramMotionEvent.getX();
      this.curP.y = paramMotionEvent.getY();
      if (paramMotionEvent.getAction() == 0)
      {
        this.downP.x = paramMotionEvent.getX();
        this.downP.y = paramMotionEvent.getY();
        getParent().requestDisallowInterceptTouchEvent(true);
      }
      if (paramMotionEvent.getAction() == 2)
        getParent().requestDisallowInterceptTouchEvent(true);
      if ((paramMotionEvent.getAction() != 1) && (paramMotionEvent.getAction() != 3))
        break;
      getParent().requestDisallowInterceptTouchEvent(false);
    }
    while ((this.downP.x == this.curP.x) && (this.downP.y == this.curP.y));
    super.onTouchEvent(paramMotionEvent);
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.ReceiptViewPager
 * JD-Core Version:    0.6.2
 */