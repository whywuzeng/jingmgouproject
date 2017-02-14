package com.ismartgo.app.grid.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class SlidingUpPanelLayout extends ViewGroup
{
  private static final int DEFAULT_FADE_COLOR = -1728053248;
  private static final int DEFAULT_PANEL_HEIGHT = 68;
  private static final int DEFAULT_SHADOW_HEIGHT = 4;
  private static final int MIN_FLING_VELOCITY = 400;
  private static final String TAG = "SlidingPaneLayout";
  private boolean mCanSlide;
  private int mCoveredFadeColor = -1728053248;
  private final Paint mCoveredFadePaint = new Paint();
  private final ViewDragHelper mDragHelper;
  private View mDragView;
  private boolean mDragViewHit;
  private boolean mFirstLayout = true;
  private float mInitialMotionX;
  private float mInitialMotionY;
  private boolean mIsUnableToDrag;
  private int mPanelHeight;
  private PanelSlideListener mPanelSlideListener;
  private boolean mPreservedExpandedState;
  private Drawable mShadowDrawable;
  private final int mShadowHeight;
  private float mSlideOffset;
  private int mSlideRange;
  private View mSlideableView;
  private final Rect mTmpRect = new Rect();

  public SlidingUpPanelLayout(Context paramContext)
  {
    this(paramContext, null);
  }

  public SlidingUpPanelLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public SlidingUpPanelLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    float f = paramContext.getResources().getDisplayMetrics().density;
    this.mPanelHeight = ((int)(68.0F * f + 0.5F));
    this.mShadowHeight = ((int)(4.0F * f + 0.5F));
    setWillNotDraw(false);
    this.mDragHelper = ViewDragHelper.create(this, 0.5F, new DragHelperCallback(null));
    this.mDragHelper.setMinVelocity(400.0F * f);
    this.mCanSlide = true;
    setCoveredFadeColor(-1728053248);
  }

  private boolean collapsePane(View paramView, int paramInt)
  {
    boolean bool = false;
    if ((this.mFirstLayout) || (smoothSlideTo(1.0F, paramInt)))
    {
      this.mPreservedExpandedState = false;
      bool = true;
    }
    return bool;
  }

  private boolean expandPane(View paramView, int paramInt)
  {
    if ((this.mFirstLayout) || (smoothSlideTo(0.0F, paramInt)))
    {
      this.mPreservedExpandedState = true;
      return true;
    }
    return false;
  }

  private static boolean hasOpaqueBackground(View paramView)
  {
    boolean bool2 = false;
    paramView = paramView.getBackground();
    boolean bool1 = bool2;
    if (paramView != null)
    {
      bool1 = bool2;
      if (paramView.getOpacity() == -1)
        bool1 = true;
    }
    return bool1;
  }

  private boolean isDragViewHit(int paramInt1, int paramInt2)
  {
    View localView;
    if (this.mDragView != null)
    {
      localView = this.mDragView;
      if (localView != null)
        break label26;
    }
    label26: int[] arrayOfInt1;
    do
    {
      return false;
      localView = this.mSlideableView;
      break;
      arrayOfInt1 = new int[2];
      localView.getLocationOnScreen(arrayOfInt1);
      int[] arrayOfInt2 = new int[2];
      getLocationOnScreen(arrayOfInt2);
      paramInt1 = arrayOfInt2[0] + paramInt1;
      paramInt2 = arrayOfInt2[1] + paramInt2;
    }
    while ((paramInt1 < arrayOfInt1[0]) || (paramInt1 >= arrayOfInt1[0] + localView.getWidth()) || (paramInt2 < arrayOfInt1[1]) || (paramInt2 >= arrayOfInt1[1] + localView.getHeight()));
    return true;
  }

  private void onPanelDragged(int paramInt)
  {
    this.mSlideOffset = ((paramInt - getPaddingTop()) / this.mSlideRange);
    dispatchOnPanelSlide(this.mSlideableView);
  }

  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    ViewGroup localViewGroup;
    int j;
    int k;
    int i;
    if ((paramView instanceof ViewGroup))
    {
      localViewGroup = (ViewGroup)paramView;
      j = paramView.getScrollX();
      k = paramView.getScrollY();
      i = localViewGroup.getChildCount() - 1;
    }
    while (true)
    {
      if (i < 0)
      {
        if ((!paramBoolean) || (!ViewCompat.canScrollHorizontally(paramView, -paramInt1)))
          break;
        return true;
      }
      View localView = localViewGroup.getChildAt(i);
      if ((paramInt2 + j >= localView.getLeft()) && (paramInt2 + j < localView.getRight()) && (paramInt3 + k >= localView.getTop()) && (paramInt3 + k < localView.getBottom()) && (canScroll(localView, true, paramInt1, paramInt2 + j - localView.getLeft(), paramInt3 + k - localView.getTop())))
        return true;
      i -= 1;
    }
    return false;
  }

  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams));
  }

  public boolean collapsePane()
  {
    return collapsePane(this.mSlideableView, 0);
  }

  public void computeScroll()
  {
    if (this.mDragHelper.continueSettling(true))
    {
      if (!this.mCanSlide)
        this.mDragHelper.abort();
    }
    else
      return;
    ViewCompat.postInvalidateOnAnimation(this);
  }

  void dispatchOnPanelCollapsed(View paramView)
  {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelCollapsed(paramView);
    sendAccessibilityEvent(32);
  }

  void dispatchOnPanelExpanded(View paramView)
  {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelExpanded(paramView);
    sendAccessibilityEvent(32);
  }

  void dispatchOnPanelSlide(View paramView)
  {
    if (this.mPanelSlideListener != null)
      this.mPanelSlideListener.onPanelSlide(paramView, this.mSlideOffset);
  }

  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (this.mSlideableView == null);
    int i;
    int j;
    int k;
    int m;
    int n;
    do
    {
      return;
      i = this.mSlideableView.getRight();
      j = this.mSlideableView.getTop();
      k = this.mShadowHeight;
      m = this.mSlideableView.getTop();
      n = this.mSlideableView.getLeft();
    }
    while (this.mShadowDrawable == null);
    this.mShadowDrawable.setBounds(n, j - k, i, m);
    this.mShadowDrawable.draw(paramCanvas);
  }

  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    int k = paramCanvas.save(2);
    int j = 0;
    int i = j;
    if (this.mCanSlide)
    {
      i = j;
      if (!localLayoutParams.slideable)
      {
        i = j;
        if (this.mSlideableView != null)
        {
          paramCanvas.getClipBounds(this.mTmpRect);
          this.mTmpRect.bottom = Math.min(this.mTmpRect.bottom, this.mSlideableView.getTop());
          paramCanvas.clipRect(this.mTmpRect);
          i = j;
          if (this.mSlideOffset < 1.0F)
            i = 1;
        }
      }
    }
    boolean bool = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(k);
    if (i != 0)
    {
      i = (int)(((this.mCoveredFadeColor & 0xFF000000) >>> 24) * (1.0F - this.mSlideOffset));
      j = this.mCoveredFadeColor;
      this.mCoveredFadePaint.setColor(i << 24 | j & 0xFFFFFF);
      paramCanvas.drawRect(this.mTmpRect, this.mCoveredFadePaint);
    }
    return bool;
  }

  public boolean expandPane()
  {
    if (!isPaneVisible())
      showPane();
    return expandPane(this.mSlideableView, 0);
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams();
  }

  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }

  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams))
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    return new LayoutParams(paramLayoutParams);
  }

  public int getCoveredFadeColor()
  {
    return this.mCoveredFadeColor;
  }

  public int getPanelHeight()
  {
    return this.mPanelHeight;
  }

  public void hidePane()
  {
    if (this.mSlideableView == null)
      return;
    this.mSlideableView.setVisibility(8);
    requestLayout();
  }

  public boolean isExpanded()
  {
    return (this.mCanSlide) && (this.mSlideOffset == 0.0F);
  }

  public boolean isPaneVisible()
  {
    if (getChildCount() < 2);
    while (getChildAt(1).getVisibility() != 0)
      return false;
    return true;
  }

  public boolean isSlideable()
  {
    return this.mCanSlide;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.mFirstLayout = true;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    int k = MotionEventCompat.getActionMasked(paramMotionEvent);
    if ((!this.mCanSlide) || ((this.mIsUnableToDrag) && (k != 0)))
    {
      this.mDragHelper.cancel();
      bool = super.onInterceptTouchEvent(paramMotionEvent);
      return bool;
    }
    if ((k == 3) || (k == 1))
    {
      this.mDragHelper.cancel();
      return false;
    }
    float f2 = paramMotionEvent.getX();
    float f1 = paramMotionEvent.getY();
    int j = 0;
    int i = j;
    switch (k)
    {
    default:
      i = j;
    case 1:
      label116: if ((!this.mDragViewHit) || (!this.mDragHelper.shouldInterceptTouchEvent(paramMotionEvent)))
        break;
    case 0:
    case 2:
    }
    for (j = 1; (j != 0) || (i != 0); j = 0)
    {
      return true;
      this.mIsUnableToDrag = false;
      this.mInitialMotionX = f2;
      this.mInitialMotionY = f1;
      this.mDragViewHit = isDragViewHit((int)f2, (int)f1);
      i = j;
      if (!this.mDragViewHit)
        break label116;
      i = 1;
      break label116;
      f2 = Math.abs(f2 - this.mInitialMotionX);
      f1 = Math.abs(f1 - this.mInitialMotionY);
      i = j;
      if (f1 <= this.mDragHelper.getTouchSlop())
        break label116;
      i = j;
      if (f2 <= f1)
        break label116;
      this.mDragHelper.cancel();
      this.mIsUnableToDrag = true;
      return false;
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getPaddingLeft();
    paramInt1 = getPaddingTop();
    int j = getChildCount();
    paramInt3 = paramInt1;
    float f;
    if (this.mFirstLayout)
    {
      if ((this.mCanSlide) && (this.mPreservedExpandedState))
      {
        f = 0.0F;
        this.mSlideOffset = f;
      }
    }
    else
    {
      paramInt4 = 0;
      paramInt2 = paramInt1;
      paramInt1 = paramInt3;
      paramInt3 = paramInt4;
    }
    View localView;
    while (true)
    {
      if (paramInt3 >= j)
      {
        if (this.mFirstLayout)
          updateObscuredViewVisibility();
        this.mFirstLayout = false;
        return;
        f = 1.0F;
        break;
      }
      localView = getChildAt(paramInt3);
      if (localView.getVisibility() != 8)
        break label119;
      paramInt3 += 1;
    }
    label119: LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
    paramInt4 = localView.getMeasuredHeight();
    if (localLayoutParams.slideable)
    {
      this.mSlideRange = (paramInt4 - this.mPanelHeight);
      paramInt2 += (int)(this.mSlideRange * this.mSlideOffset);
    }
    while (true)
    {
      localView.layout(i, paramInt2, i + localView.getMeasuredWidth(), paramInt2 + paramInt4);
      paramInt1 += localView.getHeight();
      break;
      paramInt2 = paramInt1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int k = View.MeasureSpec.getSize(paramInt1);
    paramInt1 = View.MeasureSpec.getMode(paramInt2);
    int m = View.MeasureSpec.getSize(paramInt2);
    if (i != 1073741824)
      throw new IllegalStateException("Width must have an exact value or MATCH_PARENT");
    if (paramInt1 != 1073741824)
      throw new IllegalStateException("Height must have an exact value or MATCH_PARENT");
    int n = getPaddingTop();
    int i1 = getPaddingBottom();
    i = this.mPanelHeight;
    int i2 = getChildCount();
    int j;
    if (i2 > 2)
    {
      Log.e("SlidingPaneLayout", "onMeasure: More than two child views are not supported.");
      this.mSlideableView = null;
      this.mCanSlide = false;
      j = 0;
    }
    View localView;
    LayoutParams localLayoutParams;
    while (true)
    {
      if (j >= i2)
      {
        setMeasuredDimension(k, m);
        return;
        if (getChildAt(1).getVisibility() != 8)
          break;
        i = 0;
        break;
      }
      localView = getChildAt(j);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      paramInt2 = m - n - i1;
      if (localView.getVisibility() != 8)
        break label195;
      localLayoutParams.dimWhenOffset = false;
      j += 1;
    }
    label195: if (j == 1)
    {
      localLayoutParams.slideable = true;
      localLayoutParams.dimWhenOffset = true;
      this.mSlideableView = localView;
      this.mCanSlide = true;
      label224: if (localLayoutParams.width != -2)
        break label278;
      paramInt1 = View.MeasureSpec.makeMeasureSpec(k, -2147483648);
      label243: if (localLayoutParams.height != -2)
        break label314;
      paramInt2 = View.MeasureSpec.makeMeasureSpec(paramInt2, -2147483648);
    }
    while (true)
    {
      localView.measure(paramInt1, paramInt2);
      break;
      paramInt2 -= i;
      break label224;
      label278: if (localLayoutParams.width == -1)
      {
        paramInt1 = View.MeasureSpec.makeMeasureSpec(k, 1073741824);
        break label243;
      }
      paramInt1 = View.MeasureSpec.makeMeasureSpec(localLayoutParams.width, 1073741824);
      break label243;
      label314: if (localLayoutParams.height == -1)
        paramInt2 = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
      else
        paramInt2 = View.MeasureSpec.makeMeasureSpec(localLayoutParams.height, 1073741824);
    }
  }

  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (paramParcelable.isExpanded)
      expandPane();
    while (true)
    {
      this.mPreservedExpandedState = paramParcelable.isExpanded;
      return;
      collapsePane();
    }
  }

  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if (isSlideable());
    for (boolean bool = isExpanded(); ; bool = this.mPreservedExpandedState)
    {
      localSavedState.isExpanded = bool;
      return localSavedState;
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt2 != paramInt4)
      this.mFirstLayout = true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool1;
    if (!this.mCanSlide)
      bool1 = super.onTouchEvent(paramMotionEvent);
    float f1;
    float f2;
    do
    {
      int i;
      boolean bool2;
      float f3;
      float f4;
      do
      {
        return bool1;
        this.mDragHelper.processTouchEvent(paramMotionEvent);
        i = paramMotionEvent.getAction();
        bool2 = true;
        switch (i & 0xFF)
        {
        default:
          return true;
        case 0:
          f1 = paramMotionEvent.getX();
          f2 = paramMotionEvent.getY();
          this.mInitialMotionX = f1;
          this.mInitialMotionY = f2;
          return true;
        case 1:
        }
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        f3 = f1 - this.mInitialMotionX;
        f4 = f2 - this.mInitialMotionY;
        i = this.mDragHelper.getTouchSlop();
        bool1 = bool2;
      }
      while (f3 * f3 + f4 * f4 >= i * i);
      bool1 = bool2;
    }
    while (!isDragViewHit((int)f1, (int)f2));
    if (this.mDragView != null);
    for (paramMotionEvent = this.mDragView; ; paramMotionEvent = this.mSlideableView)
    {
      paramMotionEvent.playSoundEffect(0);
      if (isExpanded())
        break;
      expandPane(this.mSlideableView, 0);
      return true;
    }
    collapsePane();
    return true;
  }

  public void requestChildFocus(View paramView1, View paramView2)
  {
    super.requestChildFocus(paramView1, paramView2);
    if ((!isInTouchMode()) && (!this.mCanSlide))
      if (paramView1 != this.mSlideableView)
        break label36;
    label36: for (boolean bool = true; ; bool = false)
    {
      this.mPreservedExpandedState = bool;
      return;
    }
  }

  void setAllChildrenVisible()
  {
    int i = 0;
    int j = getChildCount();
    while (true)
    {
      if (i >= j)
        return;
      View localView = getChildAt(i);
      if (localView.getVisibility() == 4)
        localView.setVisibility(0);
      i += 1;
    }
  }

  public void setCoveredFadeColor(int paramInt)
  {
    this.mCoveredFadeColor = paramInt;
    invalidate();
  }

  public void setDragView(View paramView)
  {
    this.mDragView = paramView;
  }

  public void setPanelHeight(int paramInt)
  {
    this.mPanelHeight = paramInt;
  }

  public void setPanelSlideListener(PanelSlideListener paramPanelSlideListener)
  {
    this.mPanelSlideListener = paramPanelSlideListener;
  }

  public void setShadowDrawable(Drawable paramDrawable)
  {
    this.mShadowDrawable = paramDrawable;
  }

  public void showPane()
  {
    if (getChildCount() < 2)
      return;
    getChildAt(1).setVisibility(0);
    requestLayout();
  }

  boolean smoothSlideTo(float paramFloat, int paramInt)
  {
    if (!this.mCanSlide);
    do
    {
      return false;
      paramInt = (int)(getPaddingTop() + this.mSlideRange * paramFloat);
    }
    while (!this.mDragHelper.smoothSlideViewTo(this.mSlideableView, this.mSlideableView.getLeft(), paramInt));
    setAllChildrenVisible();
    ViewCompat.postInvalidateOnAnimation(this);
    return true;
  }

  void updateObscuredViewVisibility()
  {
    if (getChildCount() == 0)
      return;
    int i5 = getPaddingLeft();
    int i2 = getWidth();
    int i3 = getPaddingRight();
    int i4 = getPaddingTop();
    int n = getHeight();
    int i1 = getPaddingBottom();
    int j;
    int k;
    int m;
    View localView;
    if ((this.mSlideableView != null) && (hasOpaqueBackground(this.mSlideableView)))
    {
      j = this.mSlideableView.getLeft();
      k = this.mSlideableView.getRight();
      m = this.mSlideableView.getTop();
      i = this.mSlideableView.getBottom();
      localView = getChildAt(0);
      i5 = Math.max(i5, localView.getLeft());
      i4 = Math.max(i4, localView.getTop());
      i2 = Math.min(i2 - i3, localView.getRight());
      n = Math.min(n - i1, localView.getBottom());
      if ((i5 < j) || (i4 < m) || (i2 > k) || (n > i))
        break label201;
    }
    label201: for (int i = 4; ; i = 0)
    {
      localView.setVisibility(i);
      return;
      i = 0;
      m = 0;
      k = 0;
      j = 0;
      break;
    }
  }

  private class DragHelperCallback extends ViewDragHelper.Callback
  {
    private DragHelperCallback()
    {
    }

    public int clampViewPositionVertical(View paramView, int paramInt1, int paramInt2)
    {
      paramInt2 = SlidingUpPanelLayout.this.getPaddingTop();
      int i = SlidingUpPanelLayout.this.mSlideRange;
      return Math.min(Math.max(paramInt1, paramInt2), paramInt2 + i);
    }

    public int getViewVerticalDragRange(View paramView)
    {
      return SlidingUpPanelLayout.this.mSlideRange;
    }

    public void onViewCaptured(View paramView, int paramInt)
    {
      SlidingUpPanelLayout.this.setAllChildrenVisible();
    }

    public void onViewDragStateChanged(int paramInt)
    {
      if (SlidingUpPanelLayout.this.mDragHelper.getViewDragState() == 0)
      {
        if (SlidingUpPanelLayout.this.mSlideOffset == 0.0F)
        {
          SlidingUpPanelLayout.this.updateObscuredViewVisibility();
          SlidingUpPanelLayout.this.dispatchOnPanelExpanded(SlidingUpPanelLayout.this.mSlideableView);
          SlidingUpPanelLayout.this.mPreservedExpandedState = true;
        }
      }
      else
        return;
      SlidingUpPanelLayout.this.dispatchOnPanelCollapsed(SlidingUpPanelLayout.this.mSlideableView);
      SlidingUpPanelLayout.this.mPreservedExpandedState = false;
    }

    public void onViewPositionChanged(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      SlidingUpPanelLayout.this.onPanelDragged(paramInt2);
      SlidingUpPanelLayout.this.invalidate();
    }

    public void onViewReleased(View paramView, float paramFloat1, float paramFloat2)
    {
      int j = SlidingUpPanelLayout.this.getPaddingTop();
      int i;
      if (paramFloat2 <= 0.0F)
      {
        i = j;
        if (paramFloat2 == 0.0F)
        {
          i = j;
          if (SlidingUpPanelLayout.this.mSlideOffset <= 0.5F);
        }
      }
      else
      {
        i = j + SlidingUpPanelLayout.this.mSlideRange;
      }
      SlidingUpPanelLayout.this.mDragHelper.settleCapturedViewAt(paramView.getLeft(), i);
      SlidingUpPanelLayout.this.invalidate();
    }

    public boolean tryCaptureView(View paramView, int paramInt)
    {
      if (SlidingUpPanelLayout.this.mIsUnableToDrag)
        return false;
      return ((SlidingUpPanelLayout.LayoutParams)paramView.getLayoutParams()).slideable;
    }
  }

  public static class LayoutParams extends ViewGroup.MarginLayoutParams
  {
    private static final int[] ATTRS = { 16843137 };
    Paint dimPaint;
    boolean dimWhenOffset;
    boolean slideable;

    public LayoutParams()
    {
      super(-1);
    }

    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS).recycle();
    }

    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }

    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }

    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
    }
  }

  public static abstract interface PanelSlideListener
  {
    public abstract void onPanelCollapsed(View paramView);

    public abstract void onPanelExpanded(View paramView);

    public abstract void onPanelSlide(View paramView, float paramFloat);
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public SlidingUpPanelLayout.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new SlidingUpPanelLayout.SavedState(paramAnonymousParcel, null);
      }

      public SlidingUpPanelLayout.SavedState[] newArray(int paramAnonymousInt)
      {
        return new SlidingUpPanelLayout.SavedState[paramAnonymousInt];
      }
    };
    boolean isExpanded;

    private SavedState(Parcel paramParcel)
    {
      super();
      if (paramParcel.readInt() != 0);
      for (boolean bool = true; ; bool = false)
      {
        this.isExpanded = bool;
        return;
      }
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      if (this.isExpanded);
      for (paramInt = 1; ; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
  }

  public static class SimplePanelSlideListener
    implements SlidingUpPanelLayout.PanelSlideListener
  {
    public void onPanelCollapsed(View paramView)
    {
    }

    public void onPanelExpanded(View paramView)
    {
    }

    public void onPanelSlide(View paramView, float paramFloat)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.view.SlidingUpPanelLayout
 * JD-Core Version:    0.6.2
 */