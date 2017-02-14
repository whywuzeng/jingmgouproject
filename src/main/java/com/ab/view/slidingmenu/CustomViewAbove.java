package com.ab.view.slidingmenu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.view.KeyEventCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomViewAbove extends ViewGroup
{
  private static final boolean DEBUG = false;
  private static final int INVALID_POINTER = -1;
  private static final int MAX_SETTLE_DURATION = 600;
  private static final int MIN_DISTANCE_FOR_FLING = 25;
  private static final String TAG = "CustomViewAbove";
  private static final boolean USE_CACHE = false;
  private static final Interpolator sInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      paramAnonymousFloat -= 1.0F;
      return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat + 1.0F;
    }
  };
  protected int mActivePointerId = -1;
  private SlidingMenu.OnClosedListener mClosedListener;
  private View mContent;
  private int mCurItem;
  private boolean mEnabled = true;
  private int mFlingDistance;
  private List<View> mIgnoredViews = new ArrayList();
  private float mInitialMotionX;
  private OnPageChangeListener mInternalPageChangeListener;
  private boolean mIsBeingDragged;
  private boolean mIsUnableToDrag;
  private float mLastMotionX;
  private float mLastMotionY;
  protected int mMaximumVelocity;
  private int mMinimumVelocity;
  private OnPageChangeListener mOnPageChangeListener;
  private SlidingMenu.OnOpenedListener mOpenedListener;
  private boolean mQuickReturn = false;
  private float mScrollX = 0.0F;
  private Scroller mScroller;
  private boolean mScrolling;
  private boolean mScrollingCacheEnabled;
  protected int mTouchMode = 0;
  private int mTouchSlop;
  protected VelocityTracker mVelocityTracker;
  private CustomViewBehind mViewBehind;

  public CustomViewAbove(Context paramContext)
  {
    this(paramContext, null);
  }

  public CustomViewAbove(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initCustomViewAbove();
  }

  private void completeScroll()
  {
    if (this.mScrolling)
    {
      setScrollingCacheEnabled(false);
      this.mScroller.abortAnimation();
      int i = getScrollX();
      int j = getScrollY();
      int k = this.mScroller.getCurrX();
      int m = this.mScroller.getCurrY();
      if ((i != k) || (j != m))
        scrollTo(k, m);
      if (!isMenuOpen())
        break label93;
      if (this.mOpenedListener != null)
        this.mOpenedListener.onOpened();
    }
    while (true)
    {
      this.mScrolling = false;
      return;
      label93: if (this.mClosedListener != null)
        this.mClosedListener.onClosed();
    }
  }

  private void determineDrag(MotionEvent paramMotionEvent)
  {
    int i = this.mActivePointerId;
    int j = getPointerIndex(paramMotionEvent, i);
    if (i == -1);
    float f3;
    do
    {
      return;
      float f1 = MotionEventCompat.getX(paramMotionEvent, j);
      float f2 = f1 - this.mLastMotionX;
      f3 = Math.abs(f2);
      float f4 = MotionEventCompat.getY(paramMotionEvent, j);
      float f5 = Math.abs(f4 - this.mLastMotionY);
      if (isMenuOpen());
      for (i = this.mTouchSlop / 2; (f3 > i) && (f3 > f5) && (thisSlideAllowed(f2)); i = this.mTouchSlop)
      {
        startDrag();
        this.mLastMotionX = f1;
        this.mLastMotionY = f4;
        setScrollingCacheEnabled(true);
        return;
      }
    }
    while (f3 <= this.mTouchSlop);
    this.mIsUnableToDrag = true;
  }

  private int determineTargetPage(float paramFloat, int paramInt1, int paramInt2)
  {
    int j = this.mCurItem;
    if ((Math.abs(paramInt2) > this.mFlingDistance) && (Math.abs(paramInt1) > this.mMinimumVelocity))
    {
      int i;
      if ((paramInt1 > 0) && (paramInt2 > 0))
        i = j - 1;
      do
      {
        do
        {
          return i;
          i = j;
        }
        while (paramInt1 >= 0);
        i = j;
      }
      while (paramInt2 >= 0);
      return j + 1;
    }
    return Math.round(this.mCurItem + paramFloat);
  }

  private void endDrag()
  {
    this.mQuickReturn = false;
    this.mIsBeingDragged = false;
    this.mIsUnableToDrag = false;
    this.mActivePointerId = -1;
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }

  private int getLeftBound()
  {
    return this.mViewBehind.getAbsLeftBound(this.mContent);
  }

  private int getPointerIndex(MotionEvent paramMotionEvent, int paramInt)
  {
    paramInt = MotionEventCompat.findPointerIndex(paramMotionEvent, paramInt);
    if (paramInt == -1)
      this.mActivePointerId = -1;
    return paramInt;
  }

  private int getRightBound()
  {
    return this.mViewBehind.getAbsRightBound(this.mContent);
  }

  private boolean isInIgnoredView(MotionEvent paramMotionEvent)
  {
    Rect localRect = new Rect();
    Iterator localIterator = this.mIgnoredViews.iterator();
    do
    {
      if (!localIterator.hasNext())
        return false;
      ((View)localIterator.next()).getHitRect(localRect);
    }
    while (!localRect.contains((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY()));
    return true;
  }

  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == this.mActivePointerId)
      if (i != 0)
        break label56;
    label56: for (i = 1; ; i = 0)
    {
      this.mLastMotionX = MotionEventCompat.getX(paramMotionEvent, i);
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
      if (this.mVelocityTracker != null)
        this.mVelocityTracker.clear();
      return;
    }
  }

  private void pageScrolled(int paramInt)
  {
    int i = getWidth();
    int j = paramInt / i;
    paramInt %= i;
    onPageScrolled(j, paramInt / i, paramInt);
  }

  private void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if (this.mScrollingCacheEnabled != paramBoolean)
      this.mScrollingCacheEnabled = paramBoolean;
  }

  private void startDrag()
  {
    this.mIsBeingDragged = true;
    this.mQuickReturn = false;
  }

  private boolean thisSlideAllowed(float paramFloat)
  {
    if (isMenuOpen())
      return this.mViewBehind.menuOpenSlideAllowed(paramFloat);
    return this.mViewBehind.menuClosedSlideAllowed(paramFloat);
  }

  private boolean thisTouchAllowed(MotionEvent paramMotionEvent)
  {
    boolean bool2 = false;
    int i = (int)(paramMotionEvent.getX() + this.mScrollX);
    boolean bool1;
    if (isMenuOpen())
      bool1 = this.mViewBehind.menuOpenTouchAllowed(this.mContent, this.mCurItem, i);
    do
    {
      return bool1;
      bool1 = bool2;
      switch (this.mTouchMode)
      {
      case 2:
      default:
        return false;
      case 0:
        return this.mViewBehind.marginTouchAllowed(this.mContent, i);
      case 1:
      }
      bool1 = bool2;
    }
    while (isInIgnoredView(paramMotionEvent));
    return true;
  }

  public void addIgnoredView(View paramView)
  {
    if (!this.mIgnoredViews.contains(paramView))
      this.mIgnoredViews.add(paramView);
  }

  public boolean arrowScroll(int paramInt)
  {
    View localView2 = findFocus();
    View localView1 = localView2;
    if (localView2 == this)
      localView1 = null;
    boolean bool = false;
    localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, paramInt);
    if ((localView2 != null) && (localView2 != localView1))
      if (paramInt == 17)
        bool = localView2.requestFocus();
    while (true)
    {
      if (bool)
        playSoundEffect(SoundEffectConstants.getContantForFocusDirection(paramInt));
      return bool;
      if (paramInt == 66)
        if ((localView1 != null) && (localView2.getLeft() <= localView1.getLeft()))
        {
          bool = pageRight();
        }
        else
        {
          bool = localView2.requestFocus();
          continue;
          if ((paramInt == 17) || (paramInt == 1))
            bool = pageLeft();
          else if ((paramInt == 66) || (paramInt == 2))
            bool = pageRight();
        }
    }
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

  public void clearIgnoredViews()
  {
    this.mIgnoredViews.clear();
  }

  public void computeScroll()
  {
    if ((!this.mScroller.isFinished()) && (this.mScroller.computeScrollOffset()))
    {
      int i = getScrollX();
      int j = getScrollY();
      int k = this.mScroller.getCurrX();
      int m = this.mScroller.getCurrY();
      if ((i != k) || (j != m))
      {
        scrollTo(k, m);
        pageScrolled(k);
      }
      invalidate();
      return;
    }
    completeScroll();
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    this.mViewBehind.drawShadow(this.mContent, paramCanvas);
    this.mViewBehind.drawFade(this.mContent, paramCanvas, getPercentOpen());
    this.mViewBehind.drawSelector(this.mContent, paramCanvas, getPercentOpen());
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return (super.dispatchKeyEvent(paramKeyEvent)) || (executeKeyEvent(paramKeyEvent));
  }

  float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return FloatMath.sin((float)((paramFloat - 0.5F) * 0.47123891676382D));
  }

  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0)
      switch (paramKeyEvent.getKeyCode())
      {
      default:
      case 21:
      case 22:
      case 61:
      }
    do
    {
      do
      {
        return false;
        return arrowScroll(17);
        return arrowScroll(66);
      }
      while (Build.VERSION.SDK_INT < 11);
      if (KeyEventCompat.hasNoModifiers(paramKeyEvent))
        return arrowScroll(2);
    }
    while (!KeyEventCompat.hasModifiers(paramKeyEvent, 1));
    return arrowScroll(1);
  }

  public int getBehindWidth()
  {
    if (this.mViewBehind == null)
      return 0;
    return this.mViewBehind.getBehindWidth();
  }

  public int getChildWidth(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return 0;
    case 0:
      return getBehindWidth();
    case 1:
    }
    return this.mContent.getWidth();
  }

  public View getContent()
  {
    return this.mContent;
  }

  public int getContentLeft()
  {
    return this.mContent.getLeft() + this.mContent.getPaddingLeft();
  }

  public int getCurrentItem()
  {
    return this.mCurItem;
  }

  public int getDestScrollX(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return 0;
    case 0:
    case 2:
      return this.mViewBehind.getMenuLeft(this.mContent, paramInt);
    case 1:
    }
    return this.mContent.getLeft();
  }

  protected float getPercentOpen()
  {
    return Math.abs(this.mScrollX - this.mContent.getLeft()) / getBehindWidth();
  }

  public int getTouchMode()
  {
    return this.mTouchMode;
  }

  void initCustomViewAbove()
  {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context localContext = getContext();
    this.mScroller = new Scroller(localContext, sInterpolator);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(localContext);
    this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(localViewConfiguration);
    this.mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    setInternalPageChangeListener(new SimpleOnPageChangeListener()
    {
      public void onPageSelected(int paramAnonymousInt)
      {
        if (CustomViewAbove.this.mViewBehind != null);
        switch (paramAnonymousInt)
        {
        default:
          return;
        case 0:
        case 2:
          CustomViewAbove.this.mViewBehind.setChildrenEnabled(true);
          return;
        case 1:
        }
        CustomViewAbove.this.mViewBehind.setChildrenEnabled(false);
      }
    });
    this.mFlingDistance = ((int)(25.0F * localContext.getResources().getDisplayMetrics().density));
  }

  public boolean isMenuOpen()
  {
    return (this.mCurItem == 0) || (this.mCurItem == 2);
  }

  public boolean isSlidingEnabled()
  {
    return this.mEnabled;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.mEnabled)
      return false;
    int i = paramMotionEvent.getAction() & 0xFF;
    if ((i == 3) || (i == 1) || ((i != 0) && (this.mIsUnableToDrag)))
    {
      endDrag();
      return false;
    }
    switch (i)
    {
    default:
    case 2:
    case 0:
    case 6:
    }
    while (true)
    {
      if (!this.mIsBeingDragged)
      {
        if (this.mVelocityTracker == null)
          this.mVelocityTracker = VelocityTracker.obtain();
        this.mVelocityTracker.addMovement(paramMotionEvent);
      }
      if ((!this.mIsBeingDragged) && (!this.mQuickReturn))
        break;
      return true;
      determineDrag(paramMotionEvent);
      continue;
      i = MotionEventCompat.getActionIndex(paramMotionEvent);
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
      if (this.mActivePointerId != -1)
      {
        float f = MotionEventCompat.getX(paramMotionEvent, i);
        this.mInitialMotionX = f;
        this.mLastMotionX = f;
        this.mLastMotionY = MotionEventCompat.getY(paramMotionEvent, i);
        if (thisTouchAllowed(paramMotionEvent))
        {
          this.mIsBeingDragged = false;
          this.mIsUnableToDrag = false;
          if ((isMenuOpen()) && (this.mViewBehind.menuTouchInQuickReturn(this.mContent, this.mCurItem, paramMotionEvent.getX() + this.mScrollX)))
            this.mQuickReturn = true;
        }
        else
        {
          this.mIsUnableToDrag = true;
          continue;
          onSecondaryPointerUp(paramMotionEvent);
        }
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContent.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int j = getDefaultSize(0, paramInt1);
    int i = getDefaultSize(0, paramInt2);
    setMeasuredDimension(j, i);
    paramInt1 = getChildMeasureSpec(paramInt1, 0, j);
    paramInt2 = getChildMeasureSpec(paramInt2, 0, i);
    this.mContent.measure(paramInt1, paramInt2);
  }

  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (this.mOnPageChangeListener != null)
      this.mOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    if (this.mInternalPageChangeListener != null)
      this.mInternalPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3)
    {
      completeScroll();
      scrollTo(getDestScrollX(this.mCurItem), getScrollY());
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.mEnabled)
      return false;
    if ((!this.mIsBeingDragged) && (!thisTouchAllowed(paramMotionEvent)))
      return false;
    int i = paramMotionEvent.getAction();
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (i & 0xFF)
    {
    case 4:
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    case 5:
    case 6:
    }
    while (true)
    {
      return true;
      completeScroll();
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, MotionEventCompat.getActionIndex(paramMotionEvent));
      float f1 = paramMotionEvent.getX();
      this.mInitialMotionX = f1;
      this.mLastMotionX = f1;
      continue;
      if (!this.mIsBeingDragged)
      {
        determineDrag(paramMotionEvent);
        if (this.mIsUnableToDrag)
          return false;
      }
      if (this.mIsBeingDragged)
      {
        i = getPointerIndex(paramMotionEvent, this.mActivePointerId);
        if (this.mActivePointerId != -1)
        {
          f1 = MotionEventCompat.getX(paramMotionEvent, i);
          float f2 = this.mLastMotionX;
          this.mLastMotionX = f1;
          f2 = getScrollX() + (f2 - f1);
          f1 = getLeftBound();
          float f3 = getRightBound();
          if (f2 < f1);
          while (true)
          {
            this.mLastMotionX += f1 - (int)f1;
            scrollTo((int)f1, getScrollY());
            pageScrolled((int)f1);
            break;
            f1 = f2;
            if (f2 > f3)
              f1 = f3;
          }
          if (this.mIsBeingDragged)
          {
            VelocityTracker localVelocityTracker = this.mVelocityTracker;
            localVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            i = (int)VelocityTrackerCompat.getXVelocity(localVelocityTracker, this.mActivePointerId);
            f1 = (getScrollX() - getDestScrollX(this.mCurItem)) / getBehindWidth();
            int j = getPointerIndex(paramMotionEvent, this.mActivePointerId);
            if (this.mActivePointerId != -1)
              setCurrentItemInternal(determineTargetPage(f1, i, (int)(MotionEventCompat.getX(paramMotionEvent, j) - this.mInitialMotionX)), true, true, i);
            while (true)
            {
              this.mActivePointerId = -1;
              endDrag();
              break;
              setCurrentItemInternal(this.mCurItem, true, true, i);
            }
          }
          if ((this.mQuickReturn) && (this.mViewBehind.menuTouchInQuickReturn(this.mContent, this.mCurItem, paramMotionEvent.getX() + this.mScrollX)))
          {
            setCurrentItem(1);
            endDrag();
            continue;
            if (this.mIsBeingDragged)
            {
              setCurrentItemInternal(this.mCurItem, true, true);
              this.mActivePointerId = -1;
              endDrag();
              continue;
              i = MotionEventCompat.getActionIndex(paramMotionEvent);
              this.mLastMotionX = MotionEventCompat.getX(paramMotionEvent, i);
              this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
              continue;
              onSecondaryPointerUp(paramMotionEvent);
              i = getPointerIndex(paramMotionEvent, this.mActivePointerId);
              if (this.mActivePointerId != -1)
                this.mLastMotionX = MotionEventCompat.getX(paramMotionEvent, i);
            }
          }
        }
      }
    }
  }

  boolean pageLeft()
  {
    if (this.mCurItem > 0)
    {
      setCurrentItem(this.mCurItem - 1, true);
      return true;
    }
    return false;
  }

  boolean pageRight()
  {
    if (this.mCurItem < 1)
    {
      setCurrentItem(this.mCurItem + 1, true);
      return true;
    }
    return false;
  }

  public void removeIgnoredView(View paramView)
  {
    this.mIgnoredViews.remove(paramView);
  }

  public void scrollTo(int paramInt1, int paramInt2)
  {
    super.scrollTo(paramInt1, paramInt2);
    this.mScrollX = paramInt1;
    this.mViewBehind.scrollBehindTo(this.mContent, paramInt1, paramInt2);
  }

  public void setAboveOffset(int paramInt)
  {
    this.mContent.setPadding(paramInt, this.mContent.getPaddingTop(), this.mContent.getPaddingRight(), this.mContent.getPaddingBottom());
  }

  public void setContent(View paramView)
  {
    if (this.mContent != null)
      removeView(this.mContent);
    this.mContent = paramView;
    addView(this.mContent);
  }

  public void setCurrentItem(int paramInt)
  {
    setCurrentItemInternal(paramInt, true, false);
  }

  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }

  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }

  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    if ((!paramBoolean2) && (this.mCurItem == paramInt1))
    {
      setScrollingCacheEnabled(false);
      return;
    }
    int i = this.mViewBehind.getMenuPage(paramInt1);
    if (this.mCurItem != i);
    int j;
    for (paramInt1 = 1; ; paramInt1 = 0)
    {
      this.mCurItem = i;
      j = getDestScrollX(this.mCurItem);
      if ((paramInt1 != 0) && (this.mOnPageChangeListener != null))
        this.mOnPageChangeListener.onPageSelected(i);
      if ((paramInt1 != 0) && (this.mInternalPageChangeListener != null))
        this.mInternalPageChangeListener.onPageSelected(i);
      if (!paramBoolean1)
        break;
      smoothScrollTo(j, 0, paramInt2);
      return;
    }
    completeScroll();
    scrollTo(j, 0);
  }

  public void setCustomViewBehind(CustomViewBehind paramCustomViewBehind)
  {
    this.mViewBehind = paramCustomViewBehind;
  }

  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    OnPageChangeListener localOnPageChangeListener = this.mInternalPageChangeListener;
    this.mInternalPageChangeListener = paramOnPageChangeListener;
    return localOnPageChangeListener;
  }

  public void setOnClosedListener(SlidingMenu.OnClosedListener paramOnClosedListener)
  {
    this.mClosedListener = paramOnClosedListener;
  }

  public void setOnOpenedListener(SlidingMenu.OnOpenedListener paramOnOpenedListener)
  {
    this.mOpenedListener = paramOnOpenedListener;
  }

  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    this.mOnPageChangeListener = paramOnPageChangeListener;
  }

  public void setSlidingEnabled(boolean paramBoolean)
  {
    this.mEnabled = paramBoolean;
  }

  public void setTouchMode(int paramInt)
  {
    this.mTouchMode = paramInt;
  }

  void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollTo(paramInt1, paramInt2, 0);
  }

  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3)
  {
    if (getChildCount() == 0)
      setScrollingCacheEnabled(false);
    int i;
    int j;
    int k;
    do
    {
      do
      {
        return;
        i = getScrollX();
        j = getScrollY();
        k = paramInt1 - i;
        paramInt2 -= j;
        if ((k != 0) || (paramInt2 != 0))
          break label90;
        completeScroll();
        if (!isMenuOpen())
          break;
      }
      while (this.mOpenedListener == null);
      this.mOpenedListener.onOpened();
      return;
    }
    while (this.mClosedListener == null);
    this.mClosedListener.onClosed();
    return;
    label90: setScrollingCacheEnabled(true);
    this.mScrolling = true;
    paramInt1 = getBehindWidth();
    int m = paramInt1 / 2;
    float f3 = Math.min(1.0F, 1.0F * Math.abs(k) / paramInt1);
    float f1 = m;
    float f2 = m;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt3 = Math.abs(paramInt3);
    if (paramInt3 > 0);
    for (paramInt1 = Math.round(1000.0F * Math.abs((f1 + f2 * f3) / paramInt3)) * 4; ; paramInt1 = 600)
    {
      paramInt1 = Math.min(paramInt1, 600);
      this.mScroller.startScroll(i, j, k, paramInt2, paramInt1);
      invalidate();
      return;
      paramInt1 = (int)((1.0F + Math.abs(k) / paramInt1) * 100.0F);
    }
  }

  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageScrolled(int paramInt1, float paramFloat, int paramInt2);

    public abstract void onPageSelected(int paramInt);
  }

  public static class SimpleOnPageChangeListener
    implements CustomViewAbove.OnPageChangeListener
  {
    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.slidingmenu.CustomViewAbove
 * JD-Core Version:    0.6.2
 */