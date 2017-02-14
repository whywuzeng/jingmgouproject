package com.ab.view.carousel;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.Transformation;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CarouselView extends CarouselSpinner
  implements GestureDetector.OnGestureListener
{
  private static final int MAX_QUANTITY = 12;
  private static final float MAX_THETA = 15.0F;
  private static final int MIN_QUANTITY = 3;
  private static final int SCROLL_TO_FLING_UNCERTAINTY_TIMEOUT = 250;
  private static final String TAG = CarouselView.class.getSimpleName();
  private static final boolean localLOGV = false;
  private int mAnimationDuration = 900;
  private Camera mCamera = new Camera();
  private CarouselAdapter.AdapterContextMenuInfo mContextMenuInfo;
  private Runnable mDisableSuppressSelectionChangedRunnable = new Runnable()
  {
    public void run()
    {
      CarouselView.this.mSuppressSelectionChanged = false;
      CarouselView.this.selectionChanged();
    }
  };
  private int mDownTouchPosition;
  private View mDownTouchView;
  private FlingRotateRunnable mFlingRunnable = new FlingRotateRunnable();
  private GestureDetector mGestureDetector;
  private int mGravity;
  private boolean mIsFirstScroll;
  private int mMaxQuantity = 12;
  private int mMinQuantity = 3;
  private boolean mReceivedInvokeKeyDown;
  private View mSelectedChild;
  private boolean mShouldCallbackDuringFling = true;
  private boolean mShouldCallbackOnUnselectedItemClick = true;
  private boolean mShouldStopFling;
  private boolean mSuppressSelectionChanged;
  private float mTheta = 0.261799F;
  private boolean mUseReflection;

  public CarouselView(Context paramContext)
  {
    this(paramContext, null);
  }

  public CarouselView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public CarouselView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setChildrenDrawingOrderEnabled(true);
    this.mGestureDetector = new GestureDetector(getContext(), this);
    this.mGestureDetector.setIsLongpressEnabled(true);
    setStaticTransformationsEnabled(true);
    this.mAnimationDuration = 400;
    this.mUseReflection = false;
    setNextSelectedPositionInt(0);
  }

  private void Calculate3DPosition(CarouselItemView paramCarouselItemView, int paramInt, float paramFloat)
  {
    float f3 = paramFloat * 0.01745329F;
    paramFloat = -(paramInt / 2 * FloatMath.sin(f3));
    float f1 = paramInt / 2;
    float f2 = paramCarouselItemView.getWidth() / 2;
    f3 = paramInt / 2 * (1.0F - FloatMath.cos(f3));
    float f4 = -getHeight() / 2;
    float f5 = FloatMath.sin(this.mTheta);
    paramCarouselItemView.setItemX(paramFloat + f1 - f2);
    paramCarouselItemView.setItemZ(f3);
    paramCarouselItemView.setItemY(f4 + f5 * f3);
  }

  private int calculateTop(View paramView, boolean paramBoolean)
  {
    int i;
    if (paramBoolean)
    {
      i = getMeasuredHeight();
      label9: if (!paramBoolean)
        break label66;
    }
    label66: for (int j = paramView.getMeasuredHeight(); ; j = paramView.getHeight())
      switch (this.mGravity)
      {
      default:
        return 0;
        i = getHeight();
        break label9;
      case 48:
      case 16:
      case 80:
      }
    return this.mSpinnerPadding.top;
    int k = this.mSpinnerPadding.bottom;
    int m = this.mSpinnerPadding.top;
    return this.mSpinnerPadding.top + (i - k - m - j) / 2;
    return i - this.mSpinnerPadding.bottom - j;
  }

  private boolean dispatchLongPress(View paramView, int paramInt, long paramLong)
  {
    boolean bool1 = false;
    if (this.mOnItemLongClickListener != null)
      bool1 = this.mOnItemLongClickListener.onItemLongClick(this, this.mDownTouchView, this.mDownTouchPosition, paramLong);
    boolean bool2 = bool1;
    if (!bool1)
    {
      this.mContextMenuInfo = new CarouselAdapter.AdapterContextMenuInfo(paramView, paramInt, paramLong);
      bool2 = super.showContextMenuForChild(this);
    }
    if (bool2)
      performHapticFeedback(0);
    return bool2;
  }

  private void dispatchPress(View paramView)
  {
    if (paramView != null)
      paramView.setPressed(true);
    setPressed(true);
  }

  private void dispatchUnpress()
  {
    int i = getChildCount() - 1;
    while (true)
    {
      if (i < 0)
      {
        setPressed(false);
        return;
      }
      getChildAt(i).setPressed(false);
      i -= 1;
    }
  }

  private int getCenterOfGallery()
  {
    return (getWidth() - getPaddingLeft() - getPaddingRight()) / 2 + getPaddingLeft();
  }

  private static int getCenterOfView(View paramView)
  {
    return paramView.getLeft() + paramView.getWidth() / 2;
  }

  private void makeAndAddView(int paramInt, float paramFloat)
  {
    if (!this.mDataChanged)
    {
      localCarouselItemView = (CarouselItemView)this.mRecycler.get(paramInt);
      if (localCarouselItemView != null)
      {
        setUpChild(localCarouselItemView, localCarouselItemView.getIndex(), paramFloat);
        return;
      }
      localCarouselItemView = (CarouselItemView)this.mAdapter.getView(paramInt, null, this);
      setUpChild(localCarouselItemView, localCarouselItemView.getIndex(), paramFloat);
      return;
    }
    CarouselItemView localCarouselItemView = (CarouselItemView)this.mAdapter.getView(paramInt, null, this);
    setUpChild(localCarouselItemView, localCarouselItemView.getIndex(), paramFloat);
  }

  private void onFinishedMovement()
  {
    if (this.mSuppressSelectionChanged)
    {
      this.mSuppressSelectionChanged = false;
      super.selectionChanged();
    }
    checkSelectionChanged();
    invalidate();
  }

  private void scrollIntoSlots()
  {
    if ((getChildCount() == 0) || (this.mSelectedChild == null))
      return;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (true)
    {
      if (i >= getAdapter().getCount())
      {
        Collections.sort(localArrayList, new Comparator()
        {
          public int compare(CarouselItemView paramAnonymousCarouselItemView1, CarouselItemView paramAnonymousCarouselItemView2)
          {
            int j = (int)paramAnonymousCarouselItemView1.getCurrentAngle();
            int i = j;
            if (j > 180)
              i = 360 - j;
            int k = (int)paramAnonymousCarouselItemView2.getCurrentAngle();
            j = k;
            if (k > 180)
              j = 360 - k;
            return i - j;
          }
        });
        float f2 = ((CarouselItemView)localArrayList.get(0)).getCurrentAngle();
        float f1 = f2;
        if (f2 > 180.0F)
          f1 = -(360.0F - f2);
        if (f1 == 0.0F)
          break;
        this.mFlingRunnable.startUsingDistance(-f1);
        return;
      }
      localArrayList.add((CarouselItemView)getAdapter().getView(i, null, null));
      i += 1;
    }
    setSelectedPositionInt(((CarouselItemView)localArrayList.get(0)).getIndex());
    onFinishedMovement();
  }

  private void setUpChild(CarouselItemView paramCarouselItemView, int paramInt, float paramFloat)
  {
    addViewInLayout(paramCarouselItemView, -1, generateDefaultLayoutParams());
    boolean bool;
    int j;
    int i;
    if (paramInt == this.mSelectedPosition)
    {
      bool = true;
      paramCarouselItemView.setSelected(bool);
      if (!this.mInLayout)
        break label94;
      j = paramCarouselItemView.getMeasuredWidth();
      i = paramCarouselItemView.getMeasuredHeight();
    }
    for (paramInt = getMeasuredWidth(); ; paramInt = getWidth())
    {
      paramCarouselItemView.setCurrentAngle(paramFloat);
      paramCarouselItemView.measure(j, i);
      paramCarouselItemView.layout(0, calculateTop(paramCarouselItemView, true), j, i);
      Calculate3DPosition(paramCarouselItemView, paramInt, paramFloat);
      return;
      bool = false;
      break;
      label94: j = paramCarouselItemView.getMeasuredWidth();
      i = paramCarouselItemView.getMeasuredHeight();
    }
  }

  private void updateSelectedItemMetadata()
  {
    View localView1 = this.mSelectedChild;
    View localView2 = getChildAt(this.mSelectedPosition - this.mFirstPosition);
    this.mSelectedChild = localView2;
    if (localView2 == null);
    do
    {
      return;
      localView2.setSelected(true);
      localView2.setFocusable(true);
      if (hasFocus())
        localView2.requestFocus();
    }
    while (localView1 == null);
    localView1.setSelected(false);
    localView1.setFocusable(false);
  }

  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof ViewGroup.LayoutParams;
  }

  protected int computeHorizontalScrollExtent()
  {
    return 1;
  }

  protected int computeHorizontalScrollOffset()
  {
    return this.mSelectedPosition;
  }

  protected int computeHorizontalScrollRange()
  {
    return this.mItemCount;
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return paramKeyEvent.dispatch(this, null, null);
  }

  protected void dispatchSetPressed(boolean paramBoolean)
  {
    if (this.mSelectedChild != null)
      this.mSelectedChild.setPressed(paramBoolean);
  }

  public void dispatchSetSelected(boolean paramBoolean)
  {
  }

  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new ViewGroup.LayoutParams(getContext(), paramAttributeSet);
  }

  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new ViewGroup.LayoutParams(paramLayoutParams);
  }

  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    Object localObject = new ArrayList();
    int i = 0;
    if (i >= paramInt1)
    {
      Collections.sort((List)localObject);
      localObject = ((ArrayList)localObject).iterator();
    }
    CarouselItemView localCarouselItemView;
    do
    {
      if (!((Iterator)localObject).hasNext())
      {
        return 0;
        localCarouselItemView = (CarouselItemView)getAdapter().getView(i, null, null);
        if (paramInt2 == 0)
          localCarouselItemView.setDrawn(false);
        ((ArrayList)localObject).add((CarouselItemView)getAdapter().getView(i, null, null));
        i += 1;
        break;
      }
      localCarouselItemView = (CarouselItemView)((Iterator)localObject).next();
    }
    while (localCarouselItemView.isDrawn());
    localCarouselItemView.setDrawn(true);
    return localCarouselItemView.getIndex();
  }

  protected boolean getChildStaticTransformation(View paramView, Transformation paramTransformation)
  {
    paramTransformation.clear();
    paramTransformation.setTransformationType(Transformation.TYPE_MATRIX);
    float f1 = getWidth() / 2.0F;
    float f2 = getHeight() / 2.0F;
    this.mCamera.save();
    Matrix localMatrix = paramTransformation.getMatrix();
    this.mCamera.translate(((CarouselItemView)paramView).getItemX(), ((CarouselItemView)paramView).getItemY(), ((CarouselItemView)paramView).getItemZ());
    this.mCamera.getMatrix(localMatrix);
    localMatrix.preTranslate(-f1, -f2);
    localMatrix.postTranslate(f1, f2);
    paramTransformation = new float[9];
    localMatrix.getValues(paramTransformation);
    this.mCamera.restore();
    localMatrix = new Matrix();
    localMatrix.setValues(paramTransformation);
    ((CarouselItemView)paramView).setCIMatrix(localMatrix);
    paramView.invalidate();
    return true;
  }

  protected ContextMenu.ContextMenuInfo getContextMenuInfo()
  {
    return this.mContextMenuInfo;
  }

  float getLimitedMotionScrollAmount(boolean paramBoolean, float paramFloat)
  {
    if (paramBoolean);
    View localView;
    for (int i = getCount() - 1; ; i = 0)
    {
      localView = getChildAt(i - getFirstVisiblePosition());
      if (localView != null)
        break;
      return paramFloat;
    }
    i = getCenterOfView(localView);
    int j = getCenterOfGallery();
    if (paramBoolean)
    {
      if (i <= j)
        return 0.0F;
    }
    else if (i >= j)
      return 0.0F;
    i = j - i;
    if (paramBoolean);
    for (paramFloat = Math.max(i, paramFloat); ; paramFloat = Math.min(i, paramFloat))
      return paramFloat;
  }

  int getLimitedMotionScrollAmount(boolean paramBoolean, int paramInt)
  {
    int j = 0;
    int i;
    View localView;
    if (paramBoolean)
    {
      i = this.mItemCount - 1;
      localView = getChildAt(i - this.mFirstPosition);
      if (localView != null)
        break label40;
      i = paramInt;
    }
    label40: int k;
    int m;
    do
    {
      return i;
      i = 0;
      break;
      k = getCenterOfView(localView);
      m = getCenterOfGallery();
      if (!paramBoolean)
        break label83;
      i = j;
    }
    while (k <= m);
    label83: 
    while (k < m)
    {
      i = m - k;
      if (!paramBoolean)
        break;
      return Math.max(i, paramInt);
    }
    return 0;
    return Math.min(i, paramInt);
  }

  void layout(int paramInt, boolean paramBoolean)
  {
    if (this.mDataChanged)
      handleDataChanged();
    if (getCount() == 0)
    {
      resetList();
      return;
    }
    if (this.mNextSelectedPosition >= 0)
      setSelectedPositionInt(this.mNextSelectedPosition);
    recycleAllViews();
    detachAllViewsFromParent();
    float f3 = 360.0F / getAdapter().getCount();
    float f4 = this.mSelectedPosition;
    paramInt = 0;
    while (true)
    {
      if (paramInt >= getAdapter().getCount())
      {
        this.mRecycler.clear();
        invalidate();
        setNextSelectedPositionInt(this.mSelectedPosition);
        checkSelectionChanged();
        this.mNeedSync = false;
        updateSelectedItemMetadata();
        return;
      }
      float f2 = paramInt * f3 - f4 * f3;
      float f1 = f2;
      if (f2 < 0.0F)
        f1 = f2 + 360.0F;
      makeAndAddView(paramInt, f1);
      paramInt += 1;
    }
  }

  void onCancel()
  {
    onUp();
  }

  public boolean onDown(MotionEvent paramMotionEvent)
  {
    this.mFlingRunnable.stop(false);
    this.mDownTouchPosition = pointToPositionView((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    if (this.mDownTouchPosition >= 0)
    {
      this.mDownTouchView = getChildAt(this.mDownTouchPosition - this.mFirstPosition);
      this.mDownTouchView.setPressed(true);
    }
    this.mIsFirstScroll = true;
    return true;
  }

  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    if (!this.mShouldCallbackDuringFling)
    {
      removeCallbacks(this.mDisableSuppressSelectionChangedRunnable);
      if (!this.mSuppressSelectionChanged)
        this.mSuppressSelectionChanged = true;
    }
    this.mFlingRunnable.startUsingVelocity((int)paramFloat1);
    return true;
  }

  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    super.onFocusChanged(paramBoolean, paramInt, paramRect);
    if ((paramBoolean) && (this.mSelectedChild != null))
      this.mSelectedChild.requestFocus(paramInt);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default:
    case 21:
    case 22:
    case 23:
    case 66:
    }
    while (true)
    {
      return super.onKeyDown(paramInt, paramKeyEvent);
      playSoundEffect(1);
      return true;
      playSoundEffect(3);
      return true;
      this.mReceivedInvokeKeyDown = true;
    }
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    default:
      return super.onKeyUp(paramInt, paramKeyEvent);
    case 23:
    case 66:
    }
    if ((this.mReceivedInvokeKeyDown) && (this.mItemCount > 0))
    {
      dispatchPress(this.mSelectedChild);
      postDelayed(new Runnable()
      {
        public void run()
        {
          CarouselView.this.dispatchUnpress();
        }
      }
      , ViewConfiguration.getPressedStateDuration());
      performItemClick(getChildAt(this.mSelectedPosition - this.mFirstPosition), this.mSelectedPosition, this.mAdapter.getItemId(this.mSelectedPosition));
    }
    this.mReceivedInvokeKeyDown = false;
    return true;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mInLayout = true;
    layout(0, false);
    this.mInLayout = false;
  }

  public void onLongPress(MotionEvent paramMotionEvent)
  {
    if (this.mDownTouchPosition < 0)
      return;
    performHapticFeedback(0);
    long l = getItemIdAtPosition(this.mDownTouchPosition);
    dispatchLongPress(this.mDownTouchView, this.mDownTouchPosition, l);
  }

  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    getParent().requestDisallowInterceptTouchEvent(true);
    if (!this.mShouldCallbackDuringFling)
      if (this.mIsFirstScroll)
      {
        if (!this.mSuppressSelectionChanged)
          this.mSuppressSelectionChanged = true;
        postDelayed(this.mDisableSuppressSelectionChangedRunnable, 250L);
      }
    while (true)
    {
      trackMotionScroll((int)paramFloat1);
      this.mIsFirstScroll = false;
      return true;
      if (this.mSuppressSelectionChanged)
        this.mSuppressSelectionChanged = false;
    }
  }

  public void onShowPress(MotionEvent paramMotionEvent)
  {
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    if (this.mDownTouchPosition >= 0)
    {
      if ((this.mShouldCallbackOnUnselectedItemClick) || (this.mDownTouchPosition == this.mSelectedPosition))
        performItemClick(this.mDownTouchView, this.mDownTouchPosition, this.mAdapter.getItemId(this.mDownTouchPosition));
      return true;
    }
    return false;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = this.mGestureDetector.onTouchEvent(paramMotionEvent);
    int i = paramMotionEvent.getAction();
    if (i == 1)
      onUp();
    while (i != 3)
      return bool;
    onCancel();
    return bool;
  }

  void onUp()
  {
    if (this.mFlingRunnable.mRotator.isFinished())
      scrollIntoSlots();
    dispatchUnpress();
  }

  void scrollToChild(int paramInt)
  {
    float f = ((CarouselItemView)getAdapter().getView(paramInt, null, null)).getCurrentAngle();
    if (f == 0.0F)
      return;
    if (f > 180.0F);
    for (f = 360.0F - f; ; f = -f)
    {
      this.mFlingRunnable.startUsingDistance(f);
      return;
    }
  }

  void selectionChanged()
  {
    if (!this.mSuppressSelectionChanged)
      super.selectionChanged();
  }

  public void setAnimationDuration(int paramInt)
  {
    this.mAnimationDuration = paramInt;
  }

  public void setCallbackDuringFling(boolean paramBoolean)
  {
    this.mShouldCallbackDuringFling = paramBoolean;
  }

  public void setCallbackOnUnselectedItemClick(boolean paramBoolean)
  {
    this.mShouldCallbackOnUnselectedItemClick = paramBoolean;
  }

  public void setGravity(int paramInt)
  {
    if (this.mGravity != paramInt)
    {
      this.mGravity = paramInt;
      requestLayout();
    }
  }

  void setSelectedPositionInt(int paramInt)
  {
    super.setSelectedPositionInt(paramInt);
    super.setNextSelectedPositionInt(paramInt);
    updateSelectedItemMetadata();
  }

  public boolean showContextMenu()
  {
    if ((isPressed()) && (this.mSelectedPosition >= 0))
      return dispatchLongPress(getChildAt(this.mSelectedPosition - this.mFirstPosition), this.mSelectedPosition, this.mSelectedRowId);
    return false;
  }

  public boolean showContextMenuForChild(View paramView)
  {
    int i = getPositionForView(paramView);
    if (i < 0)
      return false;
    return dispatchLongPress(paramView, i, this.mAdapter.getItemId(i));
  }

  void trackMotionScroll(float paramFloat)
  {
    if (getChildCount() == 0)
      return;
    int i = 0;
    if (i >= getAdapter().getCount())
    {
      this.mRecycler.clear();
      invalidate();
      return;
    }
    CarouselItemView localCarouselItemView = (CarouselItemView)getAdapter().getView(i, null, null);
    float f = localCarouselItemView.getCurrentAngle() + paramFloat;
    label60: if (f <= 360.0F);
    while (true)
    {
      if (f >= 0.0F)
      {
        localCarouselItemView.setCurrentAngle(f);
        Calculate3DPosition(localCarouselItemView, getWidth(), f);
        i += 1;
        break;
        f -= 360.0F;
        break label60;
      }
      f += 360.0F;
    }
  }

  private class FlingRotateRunnable
    implements Runnable
  {
    private float mLastFlingAngle;
    private Rotator mRotator = new Rotator(CarouselView.this.getContext());

    public FlingRotateRunnable()
    {
    }

    private void endFling(boolean paramBoolean)
    {
      try
      {
        this.mRotator.forceFinished(true);
        if (paramBoolean)
          CarouselView.this.scrollIntoSlots();
        return;
      }
      finally
      {
      }
    }

    private void startCommon()
    {
      CarouselView.this.removeCallbacks(this);
    }

    public void run()
    {
      if (CarouselView.this.getChildCount() == 0)
      {
        endFling(true);
        return;
      }
      CarouselView.this.mShouldStopFling = false;
      try
      {
        Rotator localRotator = this.mRotator;
        boolean bool = localRotator.computeAngleOffset();
        float f1 = localRotator.getCurrAngle();
        float f2 = this.mLastFlingAngle;
        CarouselView.this.trackMotionScroll(f2 - f1);
        if ((bool) && (!CarouselView.this.mShouldStopFling))
        {
          this.mLastFlingAngle = f1;
          CarouselView.this.post(this);
          return;
        }
      }
      finally
      {
      }
      this.mLastFlingAngle = 0.0F;
      endFling(true);
    }

    public void startUsingDistance(float paramFloat)
    {
      if (paramFloat == 0.0F)
        return;
      startCommon();
      this.mLastFlingAngle = 0.0F;
      try
      {
        this.mRotator.startRotate(0.0F, -paramFloat, CarouselView.this.mAnimationDuration);
        CarouselView.this.post(this);
        return;
      }
      finally
      {
      }
    }

    public void startUsingVelocity(float paramFloat)
    {
      if (paramFloat == 0.0F)
        return;
      startCommon();
      this.mLastFlingAngle = 0.0F;
      this.mRotator.fling(paramFloat);
      CarouselView.this.post(this);
    }

    public void stop(boolean paramBoolean)
    {
      CarouselView.this.removeCallbacks(this);
      endFling(paramBoolean);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.carousel.CarouselView
 * JD-Core Version:    0.6.2
 */