package com.dodowaterfall.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class HeadScrollView extends ScrollView
{
  private boolean mDisableEdgeEffects = true;
  private OnScrollChangedListener mOnScrollChangedListener;

  public HeadScrollView(Context paramContext)
  {
    super(paramContext);
  }

  public HeadScrollView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public HeadScrollView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected float getBottomFadingEdgeStrength()
  {
    if ((this.mDisableEdgeEffects) && (Build.VERSION.SDK_INT < 11))
      return 0.0F;
    return super.getBottomFadingEdgeStrength();
  }

  protected float getTopFadingEdgeStrength()
  {
    if ((this.mDisableEdgeEffects) && (Build.VERSION.SDK_INT < 11))
      return 0.0F;
    return super.getTopFadingEdgeStrength();
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mOnScrollChangedListener != null)
      this.mOnScrollChangedListener.onScrollChanged(this, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setOnScrollChangedListener(OnScrollChangedListener paramOnScrollChangedListener)
  {
    this.mOnScrollChangedListener = paramOnScrollChangedListener;
  }

  public static abstract interface OnScrollChangedListener
  {
    public abstract void onScrollChanged(ScrollView paramScrollView, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.dodowaterfall.widget.HeadScrollView
 * JD-Core Version:    0.6.2
 */