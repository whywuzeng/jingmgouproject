package com.ab.view.slidingmenu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class CustomViewBehind extends ViewGroup
{
  private static final int MARGIN_THRESHOLD = 48;
  private static final String TAG = "CustomViewBehind";
  private boolean mChildrenEnabled;
  private View mContent;
  private float mFadeDegree;
  private boolean mFadeEnabled;
  private final Paint mFadePaint = new Paint();
  private int mMarginThreshold = (int)TypedValue.applyDimension(1, 48.0F, getResources().getDisplayMetrics());
  private int mMode;
  private float mScrollScale;
  private View mSecondaryContent;
  private Drawable mSecondaryShadowDrawable;
  private View mSelectedView;
  private Bitmap mSelectorDrawable;
  private boolean mSelectorEnabled = true;
  private Drawable mShadowDrawable;
  private int mShadowWidth;
  private int mTouchMode = 0;
  private SlidingMenu.CanvasTransformer mTransformer;
  private CustomViewAbove mViewAbove;
  private int mWidthOffset;
  private int selectedViewId = 2012;

  public CustomViewBehind(Context paramContext)
  {
    this(paramContext, null);
  }

  public CustomViewBehind(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private int getSelectorTop()
  {
    return this.mSelectedView.getTop() + (this.mSelectedView.getHeight() - this.mSelectorDrawable.getHeight()) / 2;
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    if (this.mTransformer != null)
    {
      paramCanvas.save();
      this.mTransformer.transformCanvas(paramCanvas, this.mViewAbove.getPercentOpen());
      super.dispatchDraw(paramCanvas);
      paramCanvas.restore();
      return;
    }
    super.dispatchDraw(paramCanvas);
  }

  public void drawFade(View paramView, Canvas paramCanvas, float paramFloat)
  {
    if (!this.mFadeEnabled)
      return;
    int i = (int)(this.mFadeDegree * 255.0F * Math.abs(1.0F - paramFloat));
    this.mFadePaint.setColor(Color.argb(i, 0, 0, 0));
    i = 0;
    int j = 0;
    if (this.mMode == 0)
    {
      i = paramView.getLeft() - getBehindWidth();
      j = paramView.getLeft();
    }
    while (true)
    {
      paramCanvas.drawRect(i, 0.0F, j, getHeight(), this.mFadePaint);
      return;
      if (this.mMode == 1)
      {
        i = paramView.getRight();
        j = paramView.getRight() + getBehindWidth();
      }
      else if (this.mMode == 2)
      {
        i = paramView.getLeft();
        j = getBehindWidth();
        int k = paramView.getLeft();
        paramCanvas.drawRect(i - j, 0.0F, k, getHeight(), this.mFadePaint);
        i = paramView.getRight();
        j = paramView.getRight() + getBehindWidth();
      }
    }
  }

  public void drawSelector(View paramView, Canvas paramCanvas, float paramFloat)
  {
    if (!this.mSelectorEnabled);
    while ((this.mSelectorDrawable == null) || (this.mSelectedView == null) || (!((String)this.mSelectedView.getTag(this.selectedViewId)).equals("CustomViewBehindSelectedView")))
      return;
    paramCanvas.save();
    int i = (int)(this.mSelectorDrawable.getWidth() * paramFloat);
    int j;
    if (this.mMode == 0)
    {
      j = paramView.getLeft();
      i = j - i;
      paramCanvas.clipRect(i, 0, j, getHeight());
      paramCanvas.drawBitmap(this.mSelectorDrawable, i, getSelectorTop(), null);
    }
    while (true)
    {
      paramCanvas.restore();
      return;
      if (this.mMode == 1)
      {
        j = paramView.getRight();
        i = j + i;
        paramCanvas.clipRect(j, 0, i, getHeight());
        paramCanvas.drawBitmap(this.mSelectorDrawable, i - this.mSelectorDrawable.getWidth(), getSelectorTop(), null);
      }
    }
  }

  public void drawShadow(View paramView, Canvas paramCanvas)
  {
    if ((this.mShadowDrawable == null) || (this.mShadowWidth <= 0))
      return;
    int i = 0;
    if (this.mMode == 0)
      i = paramView.getLeft() - this.mShadowWidth;
    while (true)
    {
      this.mShadowDrawable.setBounds(i, 0, this.mShadowWidth + i, getHeight());
      this.mShadowDrawable.draw(paramCanvas);
      return;
      if (this.mMode == 1)
      {
        i = paramView.getRight();
      }
      else if (this.mMode == 2)
      {
        if (this.mSecondaryShadowDrawable != null)
        {
          i = paramView.getRight();
          this.mSecondaryShadowDrawable.setBounds(i, 0, this.mShadowWidth + i, getHeight());
          this.mSecondaryShadowDrawable.draw(paramCanvas);
        }
        i = paramView.getLeft() - this.mShadowWidth;
      }
    }
  }

  public int getAbsLeftBound(View paramView)
  {
    if ((this.mMode == 0) || (this.mMode == 2))
      return paramView.getLeft() - getBehindWidth();
    if (this.mMode == 1)
      return paramView.getLeft();
    return 0;
  }

  public int getAbsRightBound(View paramView)
  {
    if (this.mMode == 0)
      return paramView.getLeft();
    if ((this.mMode == 1) || (this.mMode == 2))
      return paramView.getLeft() + getBehindWidth();
    return 0;
  }

  public int getBehindWidth()
  {
    return this.mContent.getWidth();
  }

  public View getContent()
  {
    return this.mContent;
  }

  public int getMarginThreshold()
  {
    return this.mMarginThreshold;
  }

  public int getMenuLeft(View paramView, int paramInt)
  {
    if (this.mMode == 0)
      switch (paramInt)
      {
      case 1:
      default:
      case 0:
      case 2:
      }
    while (true)
    {
      return paramView.getLeft();
      return paramView.getLeft() - getBehindWidth();
      return paramView.getLeft();
      if (this.mMode == 1)
        switch (paramInt)
        {
        case 1:
        default:
          break;
        case 0:
          return paramView.getLeft();
        case 2:
          return paramView.getLeft() + getBehindWidth();
        }
      else if (this.mMode == 2)
        switch (paramInt)
        {
        case 1:
        default:
        case 0:
        case 2:
        }
    }
    return paramView.getLeft() - getBehindWidth();
    return paramView.getLeft() + getBehindWidth();
  }

  public int getMenuPage(int paramInt)
  {
    int i;
    if (paramInt > 1)
      i = 2;
    while ((this.mMode == 0) && (i > 1))
    {
      return 0;
      i = paramInt;
      if (paramInt < 1)
        i = 0;
    }
    if ((this.mMode == 1) && (i < 1))
      return 2;
    return i;
  }

  public int getMode()
  {
    return this.mMode;
  }

  public float getScrollScale()
  {
    return this.mScrollScale;
  }

  public View getSecondaryContent()
  {
    return this.mSecondaryContent;
  }

  public boolean marginTouchAllowed(View paramView, int paramInt)
  {
    int i = paramView.getLeft();
    int j = paramView.getRight();
    if (this.mMode == 0)
      if ((paramInt < i) || (paramInt > this.mMarginThreshold + i));
    do
    {
      do
      {
        return true;
        return false;
        if (this.mMode != 1)
          break;
      }
      while ((paramInt <= j) && (paramInt >= j - this.mMarginThreshold));
      return false;
      if (this.mMode != 2)
        break;
    }
    while (((paramInt >= i) && (paramInt <= this.mMarginThreshold + i)) || ((paramInt <= j) && (paramInt >= j - this.mMarginThreshold)));
    return false;
    return false;
  }

  public boolean menuClosedSlideAllowed(float paramFloat)
  {
    if (this.mMode == 0)
      if (paramFloat <= 0.0F);
    do
    {
      do
      {
        return true;
        return false;
        if (this.mMode != 1)
          break;
      }
      while (paramFloat < 0.0F);
      return false;
    }
    while (this.mMode == 2);
    return false;
  }

  public boolean menuOpenSlideAllowed(float paramFloat)
  {
    if (this.mMode == 0)
      if (paramFloat >= 0.0F);
    do
    {
      do
      {
        return true;
        return false;
        if (this.mMode != 1)
          break;
      }
      while (paramFloat > 0.0F);
      return false;
    }
    while (this.mMode == 2);
    return false;
  }

  public boolean menuOpenTouchAllowed(View paramView, int paramInt, float paramFloat)
  {
    switch (this.mTouchMode)
    {
    default:
      return false;
    case 1:
      return true;
    case 0:
    }
    return menuTouchInQuickReturn(paramView, paramInt, paramFloat);
  }

  public boolean menuTouchInQuickReturn(View paramView, int paramInt, float paramFloat)
  {
    boolean bool2 = false;
    boolean bool1;
    if ((this.mMode == 0) || ((this.mMode == 2) && (paramInt == 0)))
    {
      bool1 = bool2;
      if (paramFloat >= paramView.getLeft())
        bool1 = true;
    }
    do
    {
      do
      {
        do
        {
          return bool1;
          if (this.mMode == 1)
            break;
          bool1 = bool2;
        }
        while (this.mMode != 2);
        bool1 = bool2;
      }
      while (paramInt != 2);
      bool1 = bool2;
    }
    while (paramFloat > paramView.getRight());
    return true;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return !this.mChildrenEnabled;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = paramInt3 - paramInt1;
    paramInt2 = paramInt4 - paramInt2;
    this.mContent.layout(0, 0, paramInt1 - this.mWidthOffset, paramInt2);
    if (this.mSecondaryContent != null)
      this.mSecondaryContent.layout(0, 0, paramInt1 - this.mWidthOffset, paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = getDefaultSize(0, paramInt1);
    int i = getDefaultSize(0, paramInt2);
    setMeasuredDimension(j, i);
    paramInt1 = getChildMeasureSpec(paramInt1, 0, j - this.mWidthOffset);
    paramInt2 = getChildMeasureSpec(paramInt2, 0, i);
    this.mContent.measure(paramInt1, paramInt2);
    if (this.mSecondaryContent != null)
      this.mSecondaryContent.measure(paramInt1, paramInt2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return !this.mChildrenEnabled;
  }

  public void scrollBehindTo(View paramView, int paramInt1, int paramInt2)
  {
    int j = 0;
    int k = 0;
    int i = 0;
    if (this.mMode == 0)
    {
      if (paramInt1 >= paramView.getLeft())
        i = 4;
      scrollTo((int)((getBehindWidth() + paramInt1) * this.mScrollScale), paramInt2);
    }
    while (true)
    {
      if (i == 4)
        Log.v("CustomViewBehind", "behind INVISIBLE");
      setVisibility(i);
      return;
      if (this.mMode == 1)
      {
        i = k;
        if (paramInt1 <= paramView.getLeft())
          i = 4;
        scrollTo((int)(getBehindWidth() - getWidth() + (paramInt1 - getBehindWidth()) * this.mScrollScale), paramInt2);
      }
      else
      {
        i = j;
        if (this.mMode == 2)
        {
          View localView = this.mContent;
          if (paramInt1 >= paramView.getLeft())
          {
            i = 4;
            label151: localView.setVisibility(i);
            localView = this.mSecondaryContent;
            if (paramInt1 > paramView.getLeft())
              break label224;
            i = 4;
            label175: localView.setVisibility(i);
            if (paramInt1 != 0)
              break label230;
          }
          label224: label230: for (i = 4; ; i = 0)
          {
            if (paramInt1 > paramView.getLeft())
              break label236;
            scrollTo((int)((getBehindWidth() + paramInt1) * this.mScrollScale), paramInt2);
            break;
            i = 0;
            break label151;
            i = 0;
            break label175;
          }
          label236: scrollTo((int)(getBehindWidth() - getWidth() + (paramInt1 - getBehindWidth()) * this.mScrollScale), paramInt2);
        }
      }
    }
  }

  public void scrollTo(int paramInt1, int paramInt2)
  {
    super.scrollTo(paramInt1, paramInt2);
    if (this.mTransformer != null)
      invalidate();
  }

  public void setCanvasTransformer(SlidingMenu.CanvasTransformer paramCanvasTransformer)
  {
    this.mTransformer = paramCanvasTransformer;
  }

  public void setChildrenEnabled(boolean paramBoolean)
  {
    this.mChildrenEnabled = paramBoolean;
  }

  public void setContent(View paramView)
  {
    if (this.mContent != null)
      removeView(this.mContent);
    this.mContent = paramView;
    addView(this.mContent);
  }

  public void setCustomViewAbove(CustomViewAbove paramCustomViewAbove)
  {
    this.mViewAbove = paramCustomViewAbove;
  }

  public void setFadeDegree(float paramFloat)
  {
    if ((paramFloat > 1.0F) || (paramFloat < 0.0F))
      throw new IllegalStateException("The BehindFadeDegree must be between 0.0f and 1.0f");
    this.mFadeDegree = paramFloat;
  }

  public void setFadeEnabled(boolean paramBoolean)
  {
    this.mFadeEnabled = paramBoolean;
  }

  public void setMarginThreshold(int paramInt)
  {
    this.mMarginThreshold = paramInt;
  }

  public void setMode(int paramInt)
  {
    if ((paramInt == 0) || (paramInt == 1))
    {
      if (this.mContent != null)
        this.mContent.setVisibility(0);
      if (this.mSecondaryContent != null)
        this.mSecondaryContent.setVisibility(4);
    }
    this.mMode = paramInt;
  }

  public void setScrollScale(float paramFloat)
  {
    this.mScrollScale = paramFloat;
  }

  public void setSecondaryContent(View paramView)
  {
    if (this.mSecondaryContent != null)
      removeView(this.mSecondaryContent);
    this.mSecondaryContent = paramView;
    addView(this.mSecondaryContent);
  }

  public void setSecondaryShadowDrawable(Drawable paramDrawable)
  {
    this.mSecondaryShadowDrawable = paramDrawable;
    invalidate();
  }

  public void setSelectedView(View paramView)
  {
    if (this.mSelectedView != null)
    {
      this.mSelectedView.setTag(this.selectedViewId, null);
      this.mSelectedView = null;
    }
    if ((paramView != null) && (paramView.getParent() != null))
    {
      this.mSelectedView = paramView;
      this.mSelectedView.setTag(this.selectedViewId, "CustomViewBehindSelectedView");
      invalidate();
    }
  }

  public void setSelectorBitmap(Bitmap paramBitmap)
  {
    this.mSelectorDrawable = paramBitmap;
    refreshDrawableState();
  }

  public void setSelectorEnabled(boolean paramBoolean)
  {
    this.mSelectorEnabled = paramBoolean;
  }

  public void setShadowDrawable(Drawable paramDrawable)
  {
    this.mShadowDrawable = paramDrawable;
    invalidate();
  }

  public void setShadowWidth(int paramInt)
  {
    this.mShadowWidth = paramInt;
    invalidate();
  }

  public void setTouchMode(int paramInt)
  {
    this.mTouchMode = paramInt;
  }

  public void setWidthOffset(int paramInt)
  {
    this.mWidthOffset = paramInt;
    requestLayout();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.slidingmenu.CustomViewBehind
 * JD-Core Version:    0.6.2
 */