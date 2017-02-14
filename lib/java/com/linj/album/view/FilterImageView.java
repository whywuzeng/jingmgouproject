package com.linj.album.view;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

public class FilterImageView extends ImageView
  implements GestureDetector.OnGestureListener
{
  public static final String TAG = "FilterImageView";
  private GestureDetector mGestureDetector = new GestureDetector(paramContext, this);

  public FilterImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void removeFilter()
  {
    Drawable localDrawable2 = getDrawable();
    Drawable localDrawable1 = localDrawable2;
    if (localDrawable2 == null)
      localDrawable1 = getBackground();
    if (localDrawable1 != null)
      localDrawable1.clearColorFilter();
  }

  private void setFilter()
  {
    Drawable localDrawable2 = getDrawable();
    Drawable localDrawable1 = localDrawable2;
    if (localDrawable2 == null)
      localDrawable1 = getBackground();
    if (localDrawable1 != null)
      localDrawable1.setColorFilter(-7829368, PorterDuff.Mode.MULTIPLY);
  }

  public boolean onDown(MotionEvent paramMotionEvent)
  {
    setFilter();
    return true;
  }

  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    return false;
  }

  public void onLongPress(MotionEvent paramMotionEvent)
  {
    performLongClick();
  }

  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    return false;
  }

  public void onShowPress(MotionEvent paramMotionEvent)
  {
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    performClick();
    return false;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getActionMasked() == 3) || (paramMotionEvent.getActionMasked() == 1))
      removeFilter();
    return this.mGestureDetector.onTouchEvent(paramMotionEvent);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.album.view.FilterImageView
 * JD-Core Version:    0.6.2
 */