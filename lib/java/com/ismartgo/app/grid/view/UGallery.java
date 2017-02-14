package com.ismartgo.app.grid.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Gallery;

public class UGallery extends Gallery
{
  public UGallery(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private boolean isScrollingLeft(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2)
  {
    return paramMotionEvent2.getX() > paramMotionEvent1.getX();
  }

  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if (isScrollingLeft(paramMotionEvent1, paramMotionEvent2));
    for (int i = 21; ; i = 22)
    {
      onKeyDown(i, null);
      return true;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.UGallery
 * JD-Core Version:    0.6.2
 */