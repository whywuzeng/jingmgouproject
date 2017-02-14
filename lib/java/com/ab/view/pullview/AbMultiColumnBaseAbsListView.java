package com.ab.view.pullview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnTouchModeChangeListener;
import android.widget.ListAdapter;
import android.widget.Scroller;
import java.util.ArrayList;
import java.util.List;

public abstract class AbMultiColumnBaseAbsListView extends AbMultiColumnAdapterView<ListAdapter>
  implements ViewTreeObserver.OnGlobalLayoutListener, ViewTreeObserver.OnTouchModeChangeListener
{
  protected static final boolean DEBUG = false;
  private static final int INVALID_POINTER = -1;
  static final int LAYOUT_FORCE_BOTTOM = 3;
  static final int LAYOUT_FORCE_TOP = 1;
  static final int LAYOUT_MOVE_SELECTION = 6;
  static final int LAYOUT_NORMAL = 0;
  static final int LAYOUT_SET_SELECTION = 2;
  static final int LAYOUT_SPECIFIC = 4;
  static final int LAYOUT_SYNC = 5;
  private static final boolean PROFILE_FLINGING = false;
  private static final boolean PROFILE_SCROLLING = false;
  private static final String TAG = "PLA_AbsListView";
  protected static final int TOUCH_MODE_DONE_WAITING = 2;
  protected static final int TOUCH_MODE_DOWN = 0;
  protected static final int TOUCH_MODE_FLING = 4;
  private static final int TOUCH_MODE_OFF = 1;
  private static final int TOUCH_MODE_ON = 0;
  static final int TOUCH_MODE_REST = -1;
  protected static final int TOUCH_MODE_SCROLL = 3;
  protected static final int TOUCH_MODE_TAP = 1;
  private static final int TOUCH_MODE_UNKNOWN = -1;
  public static final int TRANSCRIPT_MODE_ALWAYS_SCROLL = 2;
  public static final int TRANSCRIPT_MODE_DISABLED = 0;
  public static final int TRANSCRIPT_MODE_NORMAL = 1;
  private int mActivePointerId = -1;
  protected ListAdapter mAdapter;
  private int mCacheColorHint;
  protected boolean mCachingStarted;
  private Runnable mClearScrollingCache;
  private ContextMenu.ContextMenuInfo mContextMenuInfo = null;
  AbMultiColumnAdapterView<ListAdapter>.AdapterDataSetObserver mDataSetObserver;
  boolean mDrawSelectorOnTop = false;
  private boolean mFlingProfilingStarted = false;
  private FlingRunnable mFlingRunnable;
  private boolean mIsChildViewEnabled;
  final boolean[] mIsScrap = new boolean[1];
  private int mLastScrollState = 0;
  private int mLastTouchMode = -1;
  int mLastY;
  int mLayoutMode = 0;
  protected Rect mListPadding = new Rect();
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  int mMotionCorrection;
  protected int mMotionPosition;
  int mMotionViewNewTop;
  int mMotionViewOriginalTop;
  int mMotionX;
  int mMotionY;
  private OnScrollListener mOnScrollListener;
  private Runnable mPendingCheckForTap;
  private PerformClick mPerformClick;
  private PositionScroller mPositionScroller;
  final RecycleBin mRecycler = new RecycleBin();
  int mResurrectToPosition = -1;
  private boolean mScrollProfilingStarted = false;
  boolean mScrollingCacheEnabled;
  int mSelectedTop = 0;
  int mSelectionBottomPadding = 0;
  int mSelectionLeftPadding = 0;
  int mSelectionRightPadding = 0;
  int mSelectionTopPadding = 0;
  Drawable mSelector;
  Rect mSelectorRect = new Rect();
  private boolean mSmoothScrollbarEnabled = true;
  boolean mStackFromBottom;
  private Rect mTouchFrame;
  protected int mTouchMode = -1;
  private int mTouchSlop;
  private int mTranscriptMode;
  private VelocityTracker mVelocityTracker;
  protected int mWidthMeasureSpec = 0;

  public AbMultiColumnBaseAbsListView(Context paramContext)
  {
    super(paramContext);
    initAbsListView();
    setVerticalScrollBarEnabled(true);
  }

  public AbMultiColumnBaseAbsListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initAbsListView();
    if (0 != 0)
      setSelector(null);
    this.mDrawSelectorOnTop = false;
    setStackFromBottom(false);
    setScrollingCacheEnabled(true);
    setTranscriptMode(0);
    setCacheColorHint(0);
    setSmoothScrollbarEnabled(true);
  }

  private void clearScrollingCache()
  {
    if (this.mClearScrollingCache == null)
      this.mClearScrollingCache = new Runnable()
      {
        public void run()
        {
          if (AbMultiColumnBaseAbsListView.this.mCachingStarted)
          {
            AbMultiColumnBaseAbsListView.this.mCachingStarted = false;
            AbMultiColumnBaseAbsListView.this.setChildrenDrawnWithCacheEnabled(false);
            if ((AbMultiColumnBaseAbsListView.this.getPersistentDrawingCache() & 0x2) == 0)
              AbMultiColumnBaseAbsListView.this.setChildrenDrawingCacheEnabled(false);
            if (!AbMultiColumnBaseAbsListView.this.isAlwaysDrawnWithCacheEnabled())
              AbMultiColumnBaseAbsListView.this.invalidate();
          }
        }
      };
    post(this.mClearScrollingCache);
  }

  private void createScrollingCache()
  {
    if ((this.mScrollingCacheEnabled) && (!this.mCachingStarted))
    {
      setChildrenDrawnWithCacheEnabled(true);
      setChildrenDrawingCacheEnabled(true);
      this.mCachingStarted = true;
    }
  }

  private void dispatchFinishTemporaryDetach(View paramView)
  {
    if (paramView == null);
    while (true)
    {
      return;
      paramView.onFinishTemporaryDetach();
      if ((paramView instanceof ViewGroup))
      {
        paramView = (ViewGroup)paramView;
        int j = paramView.getChildCount();
        int i = 0;
        while (i < j)
        {
          dispatchFinishTemporaryDetach(paramView.getChildAt(i));
          i += 1;
        }
      }
    }
  }

  private void drawSelector(Canvas paramCanvas)
  {
    if ((shouldShowSelector()) && (this.mSelectorRect != null) && (!this.mSelectorRect.isEmpty()))
    {
      Drawable localDrawable = this.mSelector;
      localDrawable.setBounds(this.mSelectorRect);
      localDrawable.draw(paramCanvas);
    }
  }

  static int getDistance(Rect paramRect1, Rect paramRect2, int paramInt)
  {
    int j;
    int k;
    int i;
    switch (paramInt)
    {
    default:
      throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
    case 66:
      j = paramRect1.right;
      k = paramRect1.top + paramRect1.height() / 2;
      paramInt = paramRect2.left;
      i = paramRect2.top + paramRect2.height() / 2;
    case 130:
    case 17:
    case 33:
    }
    while (true)
    {
      paramInt -= j;
      i -= k;
      return i * i + paramInt * paramInt;
      j = paramRect1.left + paramRect1.width() / 2;
      k = paramRect1.bottom;
      paramInt = paramRect2.left + paramRect2.width() / 2;
      i = paramRect2.top;
      continue;
      j = paramRect1.left;
      k = paramRect1.top + paramRect1.height() / 2;
      paramInt = paramRect2.right;
      i = paramRect2.top + paramRect2.height() / 2;
      continue;
      j = paramRect1.left + paramRect1.width() / 2;
      k = paramRect1.top;
      paramInt = paramRect2.left + paramRect2.width() / 2;
      i = paramRect2.bottom;
    }
  }

  private void initAbsListView()
  {
    setClickable(true);
    setFocusableInTouchMode(true);
    setWillNotDraw(false);
    setAlwaysDrawnWithCacheEnabled(false);
    setScrollingCacheEnabled(true);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    this.mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    this.mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
  }

  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = (paramMotionEvent.getAction() & 0xFF00) >> 8;
    if (paramMotionEvent.getPointerId(i) == this.mActivePointerId)
      if (i != 0)
        break label74;
    label74: for (i = 1; ; i = 0)
    {
      this.mMotionX = ((int)paramMotionEvent.getX(i));
      this.mMotionY = ((int)paramMotionEvent.getY(i));
      this.mActivePointerId = paramMotionEvent.getPointerId(i);
      if (this.mVelocityTracker != null)
        this.mVelocityTracker.clear();
      return;
    }
  }

  private void positionSelector(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mSelectorRect.set(paramInt1 - this.mSelectionLeftPadding, paramInt2 - this.mSelectionTopPadding, this.mSelectionRightPadding + paramInt3, this.mSelectionBottomPadding + paramInt4);
  }

  private boolean startScrollIfNeeded(int paramInt)
  {
    if (Math.abs(paramInt) > this.mTouchSlop)
    {
      createScrollingCache();
      this.mTouchMode = 3;
      this.mMotionCorrection = paramInt;
      setPressed(false);
      View localView = getChildAt(this.mMotionPosition - this.mFirstPosition);
      if (localView != null)
        localView.setPressed(false);
      reportScrollStateChange(1);
      requestDisallowInterceptTouchEvent(true);
      return true;
    }
    return false;
  }

  private void useDefaultSelector()
  {
    setSelector(getResources().getDrawable(17301602));
  }

  public void addTouchables(ArrayList<View> paramArrayList)
  {
    int j = getChildCount();
    int k = this.mFirstPosition;
    ListAdapter localListAdapter = this.mAdapter;
    if (localListAdapter == null);
    while (true)
    {
      return;
      int i = 0;
      while (i < j)
      {
        View localView = getChildAt(i);
        if (localListAdapter.isEnabled(k + i))
          paramArrayList.add(localView);
        localView.addTouchables(paramArrayList);
        i += 1;
      }
    }
  }

  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }

  protected int computeVerticalScrollExtent()
  {
    int k = getChildCount();
    if (k > 0)
    {
      if (this.mSmoothScrollbarEnabled)
      {
        int j = k * 100;
        View localView = getChildAt(0);
        int m = getFillChildTop();
        int n = localView.getHeight();
        int i = j;
        if (n > 0)
          i = j + m * 100 / n;
        localView = getChildAt(k - 1);
        k = getScrollChildBottom();
        m = localView.getHeight();
        j = i;
        if (m > 0)
          j = i - (k - getHeight()) * 100 / m;
        return j;
      }
      return 1;
    }
    return 0;
  }

  protected int computeVerticalScrollOffset()
  {
    int j = 0;
    int k = this.mFirstPosition;
    int m = getChildCount();
    int i = j;
    if (k >= 0)
    {
      i = j;
      if (m > 0)
      {
        if (!this.mSmoothScrollbarEnabled)
          break label103;
        View localView = getChildAt(0);
        m = getFillChildTop();
        int n = localView.getHeight();
        i = j;
        if (n > 0)
          i = Math.max(k * 100 - m * 100 / n + (int)(getScrollY() / getHeight() * this.mItemCount * 100.0F), 0);
      }
    }
    return i;
    label103: j = this.mItemCount;
    if (k == 0)
      i = 0;
    while (true)
    {
      return (int)(k + m * (i / j));
      if (k + m == j)
        i = j;
      else
        i = k + m / 2;
    }
  }

  protected int computeVerticalScrollRange()
  {
    if (this.mSmoothScrollbarEnabled)
      return Math.max(this.mItemCount * 100, 0);
    return this.mItemCount;
  }

  ContextMenu.ContextMenuInfo createContextMenuInfo(View paramView, int paramInt, long paramLong)
  {
    return new AbMultiColumnAdapterView.AdapterContextMenuInfo(paramView, paramInt, paramLong);
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    boolean bool = this.mDrawSelectorOnTop;
    if (!bool)
      drawSelector(paramCanvas);
    super.dispatchDraw(paramCanvas);
    if (bool)
      drawSelector(paramCanvas);
  }

  protected void dispatchSetPressed(boolean paramBoolean)
  {
  }

  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    if (this.mSelector != null)
      this.mSelector.setState(getDrawableState());
  }

  abstract void fillGap(boolean paramBoolean);

  int findClosestMotionRow(int paramInt)
  {
    int j = getChildCount();
    if (j == 0)
      paramInt = -1;
    int i;
    do
    {
      return paramInt;
      i = findMotionRow(paramInt);
      paramInt = i;
    }
    while (i != -1);
    return this.mFirstPosition + j - 1;
  }

  abstract int findMotionRow(int paramInt);

  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return new LayoutParams(paramLayoutParams);
  }

  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }

  protected float getBottomFadingEdgeStrength()
  {
    int i = getChildCount();
    float f1 = super.getBottomFadingEdgeStrength();
    if (i == 0);
    int j;
    float f2;
    do
    {
      return f1;
      if (this.mFirstPosition + i - 1 < this.mItemCount - 1)
        return 1.0F;
      i = getChildAt(i - 1).getBottom();
      j = getHeight();
      f2 = getVerticalFadingEdgeLength();
    }
    while (i <= j - getPaddingBottom());
    return (i - j + getPaddingBottom()) / f2;
  }

  public int getCacheColorHint()
  {
    return this.mCacheColorHint;
  }

  protected ContextMenu.ContextMenuInfo getContextMenuInfo()
  {
    return this.mContextMenuInfo;
  }

  protected int getFillChildBottom()
  {
    int i = getChildCount();
    if (i == 0)
      return 0;
    return getChildAt(i - 1).getBottom();
  }

  protected int getFillChildTop()
  {
    if (getChildCount() == 0)
      return 0;
    return getChildAt(0).getTop();
  }

  protected int getFirstChildTop()
  {
    if (getChildCount() == 0)
      return 0;
    return getChildAt(0).getTop();
  }

  public void getFocusedRect(Rect paramRect)
  {
    View localView = getSelectedView();
    if ((localView != null) && (localView.getParent() == this))
    {
      localView.getFocusedRect(paramRect);
      offsetDescendantRectToMyCoords(localView, paramRect);
      return;
    }
    super.getFocusedRect(paramRect);
  }

  int getFooterViewsCount()
  {
    return 0;
  }

  int getHeaderViewsCount()
  {
    return 0;
  }

  public int getListPaddingBottom()
  {
    return this.mListPadding.bottom;
  }

  public int getListPaddingLeft()
  {
    return this.mListPadding.left;
  }

  public int getListPaddingRight()
  {
    return this.mListPadding.right;
  }

  public int getListPaddingTop()
  {
    return this.mListPadding.top;
  }

  protected int getScrollChildBottom()
  {
    int i = getChildCount();
    if (i == 0)
      return 0;
    return getChildAt(i - 1).getBottom();
  }

  protected int getScrollChildTop()
  {
    if (getChildCount() == 0)
      return 0;
    return getChildAt(0).getTop();
  }

  @ViewDebug.ExportedProperty
  public View getSelectedView()
  {
    return null;
  }

  public Drawable getSelector()
  {
    return this.mSelector;
  }

  public int getSolidColor()
  {
    return this.mCacheColorHint;
  }

  protected float getTopFadingEdgeStrength()
  {
    int i = getChildCount();
    float f1 = super.getTopFadingEdgeStrength();
    if (i == 0);
    float f2;
    do
    {
      return f1;
      if (this.mFirstPosition > 0)
        return 1.0F;
      i = getChildAt(0).getTop();
      f2 = getVerticalFadingEdgeLength();
    }
    while (i >= getPaddingTop());
    return -(i - getPaddingTop()) / f2;
  }

  public int getTranscriptMode()
  {
    return this.mTranscriptMode;
  }

  protected void handleDataChanged()
  {
    int k = 3;
    int m = this.mItemCount;
    if (m > 0)
    {
      if (this.mNeedSync)
      {
        this.mNeedSync = false;
        if ((this.mTranscriptMode == 2) || ((this.mTranscriptMode == 1) && (this.mFirstPosition + getChildCount() >= this.mOldItemCount)))
          this.mLayoutMode = 3;
      }
      int j;
      do
      {
        return;
        switch (this.mSyncMode)
        {
        default:
          if (isInTouchMode())
            break label178;
          j = getSelectedItemPosition();
          i = j;
          if (j >= m)
            i = m - 1;
          j = i;
          if (i < 0)
            j = 0;
          lookForSelectablePosition(j, true);
        case 1:
        }
      }
      while (lookForSelectablePosition(j, false) >= 0);
    }
    if (this.mStackFromBottom);
    for (int i = k; ; i = 1)
    {
      this.mLayoutMode = i;
      this.mNeedSync = false;
      return;
      this.mLayoutMode = 5;
      this.mSyncPosition = Math.min(Math.max(0, this.mSyncPosition), m - 1);
      return;
      label178: if (this.mResurrectToPosition < 0)
        break;
      return;
    }
  }

  public void invalidateViews()
  {
    this.mDataChanged = true;
    rememberSyncState();
    requestLayout();
    invalidate();
  }

  void invokeOnItemScrollListener()
  {
    if (this.mOnScrollListener != null)
      this.mOnScrollListener.onScroll(this, this.mFirstPosition, getChildCount(), this.mItemCount);
  }

  @ViewDebug.ExportedProperty
  public boolean isScrollingCacheEnabled()
  {
    return this.mScrollingCacheEnabled;
  }

  @ViewDebug.ExportedProperty
  public boolean isSmoothScrollbarEnabled()
  {
    return this.mSmoothScrollbarEnabled;
  }

  @ViewDebug.ExportedProperty
  public boolean isStackFromBottom()
  {
    return this.mStackFromBottom;
  }

  protected void layoutChildren()
  {
  }

  protected int modifyFlingInitialVelocity(int paramInt)
  {
    return paramInt;
  }

  View obtainView(int paramInt, boolean[] paramArrayOfBoolean)
  {
    paramArrayOfBoolean[0] = false;
    View localView2 = this.mRecycler.getScrapView(paramInt);
    View localView1;
    if (localView2 != null)
    {
      localView1 = this.mAdapter.getView(paramInt, localView2, this);
      if (localView1 != localView2)
      {
        this.mRecycler.addScrapView(localView2);
        paramArrayOfBoolean = localView1;
        if (this.mCacheColorHint != 0)
        {
          localView1.setDrawingCacheBackgroundColor(this.mCacheColorHint);
          paramArrayOfBoolean = localView1;
        }
      }
    }
    do
    {
      return paramArrayOfBoolean;
      paramArrayOfBoolean[0] = true;
      dispatchFinishTemporaryDetach(localView1);
      return localView1;
      localView1 = this.mAdapter.getView(paramInt, null, this);
      paramArrayOfBoolean = localView1;
    }
    while (this.mCacheColorHint == 0);
    localView1.setDrawingCacheBackgroundColor(this.mCacheColorHint);
    return localView1;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
    if (localViewTreeObserver != null)
      localViewTreeObserver.addOnTouchModeChangeListener(this);
  }

  protected int[] onCreateDrawableState(int paramInt)
  {
    Object localObject;
    if (this.mIsChildViewEnabled)
    {
      localObject = super.onCreateDrawableState(paramInt);
      return localObject;
    }
    int j = ENABLED_STATE_SET[0];
    int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
    int i = -1;
    paramInt = arrayOfInt.length - 1;
    while (true)
    {
      if (paramInt < 0)
        paramInt = i;
      while (arrayOfInt[paramInt] == j)
      {
        localObject = arrayOfInt;
        if (paramInt < 0)
          break;
        System.arraycopy(arrayOfInt, paramInt + 1, arrayOfInt, paramInt, arrayOfInt.length - paramInt - 1);
        return arrayOfInt;
      }
      paramInt -= 1;
    }
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.mRecycler.clear();
    ViewTreeObserver localViewTreeObserver = getViewTreeObserver();
    if (localViewTreeObserver != null)
      localViewTreeObserver.removeOnTouchModeChangeListener(this);
  }

  public void onGlobalLayout()
  {
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    case 3:
    case 4:
    case 5:
    default:
    case 0:
    case 2:
    case 1:
    case 6:
    }
    while (true)
    {
      return false;
      int i = this.mTouchMode;
      int j = (int)paramMotionEvent.getX();
      int k = (int)paramMotionEvent.getY();
      this.mActivePointerId = paramMotionEvent.getPointerId(0);
      int m = findMotionRow(k);
      if ((i != 4) && (m >= 0))
      {
        this.mMotionViewOriginalTop = getChildAt(m - this.mFirstPosition).getTop();
        this.mMotionX = j;
        this.mMotionY = k;
        this.mMotionPosition = m;
        this.mTouchMode = 0;
        clearScrollingCache();
      }
      this.mLastY = -2147483648;
      if (i == 4)
      {
        return true;
        switch (this.mTouchMode)
        {
        default:
          break;
        case 0:
          if (startScrollIfNeeded((int)paramMotionEvent.getY(paramMotionEvent.findPointerIndex(this.mActivePointerId)) - this.mMotionY))
          {
            return true;
            this.mTouchMode = -1;
            this.mActivePointerId = -1;
            reportScrollStateChange(0);
            continue;
            onSecondaryPointerUp(paramMotionEvent);
          }
          break;
        }
      }
    }
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mInLayout = true;
    if (paramBoolean)
    {
      paramInt2 = getChildCount();
      paramInt1 = 0;
    }
    while (true)
    {
      if (paramInt1 >= paramInt2)
      {
        this.mRecycler.markChildrenDirty();
        layoutChildren();
        this.mInLayout = false;
        return;
      }
      getChildAt(paramInt1).forceLayout();
      paramInt1 += 1;
    }
  }

  protected void onLayoutSync(int paramInt)
  {
  }

  protected void onLayoutSyncFinished(int paramInt)
  {
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.mSelector == null)
      useDefaultSelector();
    Rect localRect = this.mListPadding;
    localRect.left = (this.mSelectionLeftPadding + getPaddingLeft());
    localRect.top = (this.mSelectionTopPadding + getPaddingTop());
    localRect.right = (this.mSelectionRightPadding + getPaddingRight());
    localRect.bottom = (this.mSelectionBottomPadding + getPaddingBottom());
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    super.onRestoreInstanceState(paramParcelable);
    this.mDataChanged = true;
    requestLayout();
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (getChildCount() > 0)
    {
      this.mDataChanged = true;
      rememberSyncState();
    }
  }

  public boolean onTouchEvent(final MotionEvent paramMotionEvent)
  {
    if (!isEnabled())
      return (isClickable()) || (isLongClickable());
    int i = paramMotionEvent.getAction();
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    switch (i & 0xFF)
    {
    case 4:
    case 5:
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    case 6:
    }
    while (true)
    {
      return true;
      this.mActivePointerId = paramMotionEvent.getPointerId(0);
      int k = (int)paramMotionEvent.getX();
      int m = (int)paramMotionEvent.getY();
      int j = pointToPosition(k, m);
      i = j;
      if (!this.mDataChanged)
      {
        if ((this.mTouchMode == 4) || (j < 0) || (!((ListAdapter)getAdapter()).isEnabled(j)))
          break label258;
        this.mTouchMode = 0;
        if (this.mPendingCheckForTap == null)
          this.mPendingCheckForTap = new CheckForTap();
        postDelayed(this.mPendingCheckForTap, ViewConfiguration.getTapTimeout());
        i = j;
      }
      while (true)
      {
        if (i >= 0)
          this.mMotionViewOriginalTop = getChildAt(i - this.mFirstPosition).getTop();
        this.mMotionX = k;
        this.mMotionY = m;
        this.mMotionPosition = i;
        this.mLastY = -2147483648;
        break;
        label258: if ((paramMotionEvent.getEdgeFlags() != 0) && (j < 0))
          return false;
        i = j;
        if (this.mTouchMode == 4)
        {
          createScrollingCache();
          this.mTouchMode = 3;
          this.mMotionCorrection = 0;
          i = findMotionRow(m);
          reportScrollStateChange(1);
        }
      }
      k = (int)paramMotionEvent.getY(paramMotionEvent.findPointerIndex(this.mActivePointerId));
      i = k - this.mMotionY;
      switch (this.mTouchMode)
      {
      default:
        break;
      case 0:
      case 1:
      case 2:
        startScrollIfNeeded(i);
        break;
      case 3:
        if (k != this.mLastY)
        {
          j = i - this.mMotionCorrection;
          if (this.mLastY != -2147483648);
          for (i = k - this.mLastY; ; i = j)
          {
            boolean bool = false;
            if (i != 0)
              bool = trackMotionScroll(j, i);
            if ((bool) && (getChildCount() > 0))
            {
              i = findMotionRow(k);
              if (i >= 0)
                this.mMotionViewOriginalTop = getChildAt(i - this.mFirstPosition).getTop();
              this.mMotionY = k;
              this.mMotionPosition = i;
              invalidate();
            }
            this.mLastY = k;
            break;
          }
          switch (this.mTouchMode)
          {
          default:
          case 0:
          case 1:
          case 2:
          case 3:
          }
          while (true)
          {
            setPressed(false);
            invalidate();
            if (this.mVelocityTracker != null)
            {
              this.mVelocityTracker.recycle();
              this.mVelocityTracker = null;
            }
            this.mActivePointerId = -1;
            break;
            i = this.mMotionPosition;
            paramMotionEvent = getChildAt(i - this.mFirstPosition);
            if ((paramMotionEvent != null) && (!paramMotionEvent.hasFocusable()))
            {
              if (this.mTouchMode != 0)
                paramMotionEvent.setPressed(false);
              if (this.mPerformClick == null)
                this.mPerformClick = new PerformClick(null);
              final PerformClick localPerformClick = this.mPerformClick;
              localPerformClick.mChild = paramMotionEvent;
              localPerformClick.mClickMotionPosition = i;
              localPerformClick.rememberWindowAttachCount();
              this.mResurrectToPosition = i;
              if ((this.mTouchMode == 0) || (this.mTouchMode == 1))
              {
                this.mLayoutMode = 0;
                if ((!this.mDataChanged) && (this.mAdapter.isEnabled(i)))
                {
                  this.mTouchMode = 1;
                  layoutChildren();
                  paramMotionEvent.setPressed(true);
                  positionSelector(paramMotionEvent);
                  setPressed(true);
                  if (this.mSelector != null)
                  {
                    Drawable localDrawable = this.mSelector.getCurrent();
                    if ((localDrawable != null) && ((localDrawable instanceof TransitionDrawable)))
                      ((TransitionDrawable)localDrawable).resetTransition();
                  }
                  postDelayed(new Runnable()
                  {
                    public void run()
                    {
                      paramMotionEvent.setPressed(false);
                      AbMultiColumnBaseAbsListView.this.setPressed(false);
                      if (!AbMultiColumnBaseAbsListView.this.mDataChanged)
                        AbMultiColumnBaseAbsListView.this.post(localPerformClick);
                      AbMultiColumnBaseAbsListView.this.mTouchMode = -1;
                    }
                  }
                  , ViewConfiguration.getPressedStateDuration());
                }
                while (true)
                {
                  return true;
                  this.mTouchMode = -1;
                }
              }
              if ((!this.mDataChanged) && (this.mAdapter.isEnabled(i)))
                post(localPerformClick);
            }
            this.mTouchMode = -1;
            continue;
            i = getChildCount();
            if (i > 0)
            {
              j = getFillChildTop();
              k = getFillChildBottom();
              if ((this.mFirstPosition == 0) && (j >= this.mListPadding.top) && (this.mFirstPosition + i < this.mItemCount) && (k <= getHeight() - this.mListPadding.bottom))
              {
                this.mTouchMode = -1;
                reportScrollStateChange(0);
              }
              else
              {
                paramMotionEvent = this.mVelocityTracker;
                paramMotionEvent.computeCurrentVelocity(1000, this.mMaximumVelocity);
                i = (int)paramMotionEvent.getYVelocity(this.mActivePointerId);
                if (Math.abs(i) > this.mMinimumVelocity)
                {
                  if (this.mFlingRunnable == null)
                    this.mFlingRunnable = new FlingRunnable();
                  reportScrollStateChange(2);
                  this.mFlingRunnable.start(-i);
                }
                else
                {
                  this.mTouchMode = -1;
                  reportScrollStateChange(0);
                }
              }
            }
            else
            {
              this.mTouchMode = -1;
              reportScrollStateChange(0);
            }
          }
          this.mTouchMode = -1;
          setPressed(false);
          paramMotionEvent = getChildAt(this.mMotionPosition - this.mFirstPosition);
          if (paramMotionEvent != null)
            paramMotionEvent.setPressed(false);
          clearScrollingCache();
          if (this.mVelocityTracker != null)
          {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
          }
          this.mActivePointerId = -1;
          continue;
          onSecondaryPointerUp(paramMotionEvent);
          j = this.mMotionX;
          i = this.mMotionY;
          j = pointToPosition(j, i);
          if (j >= 0)
          {
            this.mMotionViewOriginalTop = getChildAt(j - this.mFirstPosition).getTop();
            this.mMotionPosition = j;
          }
          this.mLastY = i;
        }
        break;
      }
    }
  }

  public void onTouchModeChanged(boolean paramBoolean)
  {
    if ((paramBoolean) && (getHeight() > 0) && (getChildCount() > 0))
      layoutChildren();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    int i;
    if (isInTouchMode())
    {
      i = 0;
      if (paramBoolean)
        break label77;
      setChildrenDrawingCacheEnabled(false);
      if (this.mFlingRunnable != null)
      {
        removeCallbacks(this.mFlingRunnable);
        this.mFlingRunnable.endFling();
        if (getScrollY() != 0)
        {
          scrollTo(getScrollX(), 0);
          invalidate();
        }
      }
    }
    while (true)
    {
      this.mLastTouchMode = i;
      return;
      i = 1;
      break;
      label77: if ((i != this.mLastTouchMode) && (this.mLastTouchMode != -1))
      {
        this.mLayoutMode = 0;
        layoutChildren();
      }
    }
  }

  public int pointToPosition(int paramInt1, int paramInt2)
  {
    Object localObject2 = this.mTouchFrame;
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      this.mTouchFrame = new Rect();
      localObject1 = this.mTouchFrame;
    }
    int i = getChildCount() - 1;
    while (true)
    {
      if (i < 0)
        return -1;
      localObject2 = getChildAt(i);
      if (((View)localObject2).getVisibility() == 0)
      {
        ((View)localObject2).getHitRect((Rect)localObject1);
        if (((Rect)localObject1).contains(paramInt1, paramInt2))
          return this.mFirstPosition + i;
      }
      i -= 1;
    }
  }

  public long pointToRowId(int paramInt1, int paramInt2)
  {
    paramInt1 = pointToPosition(paramInt1, paramInt2);
    if (paramInt1 >= 0)
      return this.mAdapter.getItemId(paramInt1);
    return -9223372036854775808L;
  }

  void positionSelector(View paramView)
  {
    Rect localRect = this.mSelectorRect;
    localRect.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
    positionSelector(localRect.left, localRect.top, localRect.right, localRect.bottom);
    boolean bool = this.mIsChildViewEnabled;
    if (paramView.isEnabled() != bool)
      if (!bool)
        break label74;
    label74: for (bool = false; ; bool = true)
    {
      this.mIsChildViewEnabled = bool;
      refreshDrawableState();
      return;
    }
  }

  public void reclaimViews(List<View> paramList)
  {
    int j = getChildCount();
    RecyclerListener localRecyclerListener = this.mRecycler.mRecyclerListener;
    int i = 0;
    while (true)
    {
      if (i >= j)
      {
        this.mRecycler.reclaimScrapViews(paramList);
        removeAllViewsInLayout();
        return;
      }
      View localView = getChildAt(i);
      LayoutParams localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if ((localLayoutParams != null) && (this.mRecycler.shouldRecycleViewType(localLayoutParams.viewType)))
      {
        paramList.add(localView);
        if (localRecyclerListener != null)
          localRecyclerListener.onMovedToScrapHeap(localView);
      }
      i += 1;
    }
  }

  void reportScrollStateChange(int paramInt)
  {
    if ((paramInt != this.mLastScrollState) && (this.mOnScrollListener != null))
    {
      this.mOnScrollListener.onScrollStateChanged(this, paramInt);
      this.mLastScrollState = paramInt;
    }
  }

  public void requestLayout()
  {
    if ((!this.mBlockLayoutRequests) && (!this.mInLayout))
      super.requestLayout();
  }

  void requestLayoutIfNecessary()
  {
    if (getChildCount() > 0)
    {
      resetList();
      requestLayout();
      invalidate();
    }
  }

  void resetList()
  {
    removeAllViewsInLayout();
    this.mFirstPosition = 0;
    this.mDataChanged = false;
    this.mNeedSync = false;
    this.mOldSelectedPosition = -1;
    this.mOldSelectedRowId = -9223372036854775808L;
    this.mSelectedTop = 0;
    this.mSelectorRect.setEmpty();
    invalidate();
  }

  public void setCacheColorHint(int paramInt)
  {
    int j;
    int i;
    if (paramInt != this.mCacheColorHint)
    {
      this.mCacheColorHint = paramInt;
      j = getChildCount();
      i = 0;
    }
    while (true)
    {
      if (i >= j)
      {
        this.mRecycler.setCacheColorHint(paramInt);
        return;
      }
      getChildAt(i).setDrawingCacheBackgroundColor(paramInt);
      i += 1;
    }
  }

  public void setDrawSelectorOnTop(boolean paramBoolean)
  {
    this.mDrawSelectorOnTop = paramBoolean;
  }

  public void setOnScrollListener(OnScrollListener paramOnScrollListener)
  {
    this.mOnScrollListener = paramOnScrollListener;
    invokeOnItemScrollListener();
  }

  public void setRecyclerListener(RecyclerListener paramRecyclerListener)
  {
    this.mRecycler.mRecyclerListener = paramRecyclerListener;
  }

  public void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if ((this.mScrollingCacheEnabled) && (!paramBoolean))
      clearScrollingCache();
    this.mScrollingCacheEnabled = paramBoolean;
  }

  public void setSelector(int paramInt)
  {
    setSelector(getResources().getDrawable(paramInt));
  }

  public void setSelector(Drawable paramDrawable)
  {
    if (this.mSelector != null)
    {
      this.mSelector.setCallback(null);
      unscheduleDrawable(this.mSelector);
    }
    this.mSelector = paramDrawable;
    Rect localRect = new Rect();
    paramDrawable.getPadding(localRect);
    this.mSelectionLeftPadding = localRect.left;
    this.mSelectionTopPadding = localRect.top;
    this.mSelectionRightPadding = localRect.right;
    this.mSelectionBottomPadding = localRect.bottom;
    paramDrawable.setCallback(this);
    paramDrawable.setState(getDrawableState());
  }

  public void setSmoothScrollbarEnabled(boolean paramBoolean)
  {
    this.mSmoothScrollbarEnabled = paramBoolean;
  }

  public void setStackFromBottom(boolean paramBoolean)
  {
    if (this.mStackFromBottom != paramBoolean)
    {
      this.mStackFromBottom = paramBoolean;
      requestLayoutIfNecessary();
    }
  }

  public void setTranscriptMode(int paramInt)
  {
    this.mTranscriptMode = paramInt;
  }

  protected boolean shouldShowSelector()
  {
    return ((hasFocus()) && (!isInTouchMode())) || (touchModeDrawsInPressedState());
  }

  public boolean showContextMenuForChild(View paramView)
  {
    int i = getPositionForView(paramView);
    if (i >= 0)
    {
      long l = this.mAdapter.getItemId(i);
      boolean bool1 = false;
      if (this.mOnItemLongClickListener != null)
        bool1 = this.mOnItemLongClickListener.onItemLongClick(this, paramView, i, l);
      boolean bool2 = bool1;
      if (!bool1)
      {
        this.mContextMenuInfo = createContextMenuInfo(getChildAt(i - this.mFirstPosition), i, l);
        bool2 = super.showContextMenuForChild(paramView);
      }
      return bool2;
    }
    return false;
  }

  public void smoothScrollBy(int paramInt1, int paramInt2)
  {
    if (this.mFlingRunnable == null)
      this.mFlingRunnable = new FlingRunnable();
    while (true)
    {
      this.mFlingRunnable.startScroll(paramInt1, paramInt2);
      return;
      this.mFlingRunnable.endFling();
    }
  }

  public void smoothScrollToPosition(int paramInt)
  {
    if (this.mPositionScroller == null)
      this.mPositionScroller = new PositionScroller();
    this.mPositionScroller.start(paramInt);
  }

  public void smoothScrollToPosition(int paramInt1, int paramInt2)
  {
    if (this.mPositionScroller == null)
      this.mPositionScroller = new PositionScroller();
    this.mPositionScroller.start(paramInt1, paramInt2);
  }

  boolean touchModeDrawsInPressedState()
  {
    switch (this.mTouchMode)
    {
    default:
      return false;
    case 1:
    case 2:
    }
    return true;
  }

  boolean trackMotionScroll(int paramInt1, int paramInt2)
  {
    int i6 = getChildCount();
    if (i6 == 0)
      return true;
    int i = getScrollChildTop();
    int m = getScrollChildBottom();
    Object localObject = this.mListPadding;
    int i1 = getHeight() - ((Rect)localObject).bottom;
    int i2 = ((Rect)localObject).top;
    int i3 = getFillChildTop();
    int i4 = getFillChildBottom();
    int k = getHeight() - getPaddingBottom() - getPaddingTop();
    int j;
    if (paramInt1 < 0)
    {
      j = Math.max(-(k - 1), paramInt1);
      if (paramInt2 >= 0)
        break label147;
    }
    int i5;
    label147: for (k = Math.max(-(k - 1), paramInt2); ; k = Math.min(k - 1, paramInt2))
    {
      i5 = this.mFirstPosition;
      if ((i5 != 0) || (i < ((Rect)localObject).top) || (j < 0))
        break label160;
      return true;
      j = Math.min(k - 1, paramInt1);
      break;
    }
    label160: if ((i5 + i6 == this.mItemCount) && (m <= i1) && (j <= 0))
      return true;
    boolean bool;
    int i7;
    int i8;
    int n;
    if (k < 0)
    {
      bool = true;
      i7 = getHeaderViewsCount();
      i8 = this.mItemCount - getFooterViewsCount();
      m = 0;
      i = 0;
      paramInt2 = 0;
      paramInt1 = 0;
      if (bool)
      {
        i9 = ((Rect)localObject).top;
        paramInt2 = 0;
      }
    }
    else
    {
      while (true)
      {
        if (paramInt2 >= i6)
        {
          n = i;
          m = paramInt1;
        }
        do
        {
          this.mMotionViewNewTop = (this.mMotionViewOriginalTop + j);
          this.mBlockLayoutRequests = true;
          if (m > 0)
            detachViewsFromParent(n, m);
          tryOffsetChildrenTopAndBottom(k);
          if (bool)
            this.mFirstPosition += m;
          invalidate();
          paramInt1 = Math.abs(k);
          if ((i2 - i3 < paramInt1) || (i4 - i1 < paramInt1))
            fillGap(bool);
          this.mBlockLayoutRequests = false;
          invokeOnItemScrollListener();
          awakenScrollBars();
          return false;
          bool = false;
          break;
          localObject = getChildAt(paramInt2);
          m = paramInt1;
          n = i;
        }
        while (((View)localObject).getBottom() >= i9 - k);
        paramInt1 += 1;
        m = i5 + paramInt2;
        if ((m >= i7) && (m < i8))
          this.mRecycler.addScrapView((View)localObject);
        paramInt2 += 1;
      }
    }
    int i9 = getHeight();
    int i10 = ((Rect)localObject).bottom;
    paramInt1 = i6 - 1;
    i = m;
    while (true)
    {
      m = paramInt2;
      n = i;
      if (paramInt1 < 0)
        break;
      localObject = getChildAt(paramInt1);
      m = paramInt2;
      n = i;
      if (((View)localObject).getTop() <= i9 - i10 - k)
        break;
      i = paramInt1;
      paramInt2 += 1;
      m = i5 + paramInt1;
      if ((m >= i7) && (m < i8))
        this.mRecycler.addScrapView((View)localObject);
      paramInt1 -= 1;
    }
  }

  protected void tryOffsetChildrenTopAndBottom(int paramInt)
  {
    int j = getChildCount();
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      getChildAt(i).offsetTopAndBottom(paramInt);
      i += 1;
    }
  }

  public boolean verifyDrawable(Drawable paramDrawable)
  {
    return (this.mSelector == paramDrawable) || (super.verifyDrawable(paramDrawable));
  }

  final class CheckForTap
    implements Runnable
  {
    CheckForTap()
    {
    }

    public void run()
    {
      Object localObject;
      boolean bool;
      if (AbMultiColumnBaseAbsListView.this.mTouchMode == 0)
      {
        AbMultiColumnBaseAbsListView.this.mTouchMode = 1;
        localObject = AbMultiColumnBaseAbsListView.this.getChildAt(AbMultiColumnBaseAbsListView.this.mMotionPosition - AbMultiColumnBaseAbsListView.this.mFirstPosition);
        if ((localObject != null) && (!((View)localObject).hasFocusable()))
        {
          AbMultiColumnBaseAbsListView.this.mLayoutMode = 0;
          if (AbMultiColumnBaseAbsListView.this.mDataChanged)
            break label177;
          AbMultiColumnBaseAbsListView.this.layoutChildren();
          ((View)localObject).setPressed(true);
          AbMultiColumnBaseAbsListView.this.positionSelector((View)localObject);
          AbMultiColumnBaseAbsListView.this.setPressed(true);
          int i = ViewConfiguration.getLongPressTimeout();
          bool = AbMultiColumnBaseAbsListView.this.isLongClickable();
          if (AbMultiColumnBaseAbsListView.this.mSelector != null)
          {
            localObject = AbMultiColumnBaseAbsListView.this.mSelector.getCurrent();
            if ((localObject != null) && ((localObject instanceof TransitionDrawable)))
            {
              if (!bool)
                break label167;
              ((TransitionDrawable)localObject).startTransition(i);
            }
          }
        }
      }
      while (true)
      {
        if (!bool)
          AbMultiColumnBaseAbsListView.this.mTouchMode = 2;
        return;
        label167: ((TransitionDrawable)localObject).resetTransition();
      }
      label177: AbMultiColumnBaseAbsListView.this.mTouchMode = 2;
    }
  }

  private class FlingRunnable
    implements Runnable
  {
    private int mLastFlingY;
    private final Scroller mScroller = new Scroller(AbMultiColumnBaseAbsListView.this.getContext());

    FlingRunnable()
    {
    }

    private void endFling()
    {
      this.mLastFlingY = 0;
      AbMultiColumnBaseAbsListView.this.mTouchMode = -1;
      AbMultiColumnBaseAbsListView.this.reportScrollStateChange(0);
      AbMultiColumnBaseAbsListView.this.clearScrollingCache();
      AbMultiColumnBaseAbsListView.this.removeCallbacks(this);
      if (AbMultiColumnBaseAbsListView.this.mPositionScroller != null)
        AbMultiColumnBaseAbsListView.this.removeCallbacks(AbMultiColumnBaseAbsListView.this.mPositionScroller);
      this.mScroller.forceFinished(true);
    }

    public void run()
    {
      switch (AbMultiColumnBaseAbsListView.this.mTouchMode)
      {
      default:
        return;
      case 4:
      }
      if ((AbMultiColumnBaseAbsListView.this.mItemCount == 0) || (AbMultiColumnBaseAbsListView.this.getChildCount() == 0))
      {
        endFling();
        return;
      }
      Scroller localScroller = this.mScroller;
      boolean bool1 = localScroller.computeScrollOffset();
      int j = localScroller.getCurrY();
      int i = this.mLastFlingY - j;
      if (i > 0)
      {
        AbMultiColumnBaseAbsListView.this.mMotionPosition = AbMultiColumnBaseAbsListView.this.mFirstPosition;
        AbMultiColumnBaseAbsListView.this.mMotionViewOriginalTop = AbMultiColumnBaseAbsListView.this.getScrollChildTop();
      }
      for (i = Math.min(AbMultiColumnBaseAbsListView.this.getHeight() - AbMultiColumnBaseAbsListView.this.getPaddingBottom() - AbMultiColumnBaseAbsListView.this.getPaddingTop() - 1, i); ; i = Math.max(-(AbMultiColumnBaseAbsListView.this.getHeight() - AbMultiColumnBaseAbsListView.this.getPaddingBottom() - AbMultiColumnBaseAbsListView.this.getPaddingTop() - 1), i))
      {
        boolean bool2 = AbMultiColumnBaseAbsListView.this.trackMotionScroll(i, i);
        if ((!bool1) || (bool2))
          break;
        AbMultiColumnBaseAbsListView.this.invalidate();
        this.mLastFlingY = j;
        AbMultiColumnBaseAbsListView.this.post(this);
        return;
        int k = AbMultiColumnBaseAbsListView.this.getChildCount();
        AbMultiColumnBaseAbsListView.this.mMotionPosition = (AbMultiColumnBaseAbsListView.this.mFirstPosition + (k - 1));
        AbMultiColumnBaseAbsListView.this.mMotionViewOriginalTop = AbMultiColumnBaseAbsListView.this.getScrollChildBottom();
      }
      endFling();
    }

    void start(int paramInt)
    {
      int i = AbMultiColumnBaseAbsListView.this.modifyFlingInitialVelocity(paramInt);
      if (i < 0);
      for (paramInt = 2147483647; ; paramInt = 0)
      {
        this.mLastFlingY = paramInt;
        this.mScroller.fling(0, paramInt, 0, i, 0, 2147483647, 0, 2147483647);
        AbMultiColumnBaseAbsListView.this.mTouchMode = 4;
        AbMultiColumnBaseAbsListView.this.post(this);
        return;
      }
    }

    void startScroll(int paramInt1, int paramInt2)
    {
      if (paramInt1 < 0);
      for (int i = 2147483647; ; i = 0)
      {
        this.mLastFlingY = i;
        this.mScroller.startScroll(0, i, 0, paramInt1, paramInt2);
        AbMultiColumnBaseAbsListView.this.mTouchMode = 4;
        AbMultiColumnBaseAbsListView.this.post(this);
        return;
      }
    }
  }

  public static class LayoutParams extends ViewGroup.LayoutParams
  {

    @ViewDebug.ExportedProperty
    public boolean forceAdd;

    @ViewDebug.ExportedProperty
    public boolean recycledHeaderFooter;

    @ViewDebug.ExportedProperty(mapping={@android.view.ViewDebug.IntToString(from=-1, to="ITEM_VIEW_TYPE_IGNORE"), @android.view.ViewDebug.IntToString(from=-2, to="ITEM_VIEW_TYPE_HEADER_OR_FOOTER")})
    public int viewType;

    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }

    public LayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2);
      this.viewType = paramInt3;
    }

    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }

    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
  }

  public static abstract interface OnScrollListener
  {
    public static final int SCROLL_STATE_FLING = 2;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_TOUCH_SCROLL = 1;

    public abstract void onScroll(AbMultiColumnBaseAbsListView paramAbMultiColumnBaseAbsListView, int paramInt1, int paramInt2, int paramInt3);

    public abstract void onScrollStateChanged(AbMultiColumnBaseAbsListView paramAbMultiColumnBaseAbsListView, int paramInt);
  }

  private class PerformClick extends AbMultiColumnBaseAbsListView.WindowRunnnable
    implements Runnable
  {
    View mChild;
    int mClickMotionPosition;

    private PerformClick()
    {
      super(null);
    }

    public void run()
    {
      if (AbMultiColumnBaseAbsListView.this.mDataChanged);
      ListAdapter localListAdapter;
      int i;
      do
      {
        return;
        localListAdapter = AbMultiColumnBaseAbsListView.this.mAdapter;
        i = this.mClickMotionPosition;
      }
      while ((localListAdapter == null) || (AbMultiColumnBaseAbsListView.this.mItemCount <= 0) || (i == -1) || (i >= localListAdapter.getCount()) || (!sameWindow()));
      AbMultiColumnBaseAbsListView.this.performItemClick(this.mChild, i, localListAdapter.getItemId(i));
    }
  }

  class PositionScroller
    implements Runnable
  {
    private static final int MOVE_DOWN_BOUND = 3;
    private static final int MOVE_DOWN_POS = 1;
    private static final int MOVE_UP_BOUND = 4;
    private static final int MOVE_UP_POS = 2;
    private static final int SCROLL_DURATION = 400;
    private int mBoundPos;
    private final int mExtraScroll = ViewConfiguration.get(AbMultiColumnBaseAbsListView.this.getContext()).getScaledFadingEdgeLength();
    private int mLastSeenPos;
    private int mMode;
    private int mScrollDuration;
    private int mTargetPos;

    PositionScroller()
    {
    }

    public void run()
    {
      int j = AbMultiColumnBaseAbsListView.this.getHeight();
      int k = AbMultiColumnBaseAbsListView.this.mFirstPosition;
      switch (this.mMode)
      {
      default:
      case 1:
      case 3:
      case 2:
      case 4:
      }
      int i;
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return;
                  i = AbMultiColumnBaseAbsListView.this.getChildCount() - 1;
                  k += i;
                }
                while (i < 0);
                if (k == this.mLastSeenPos)
                {
                  AbMultiColumnBaseAbsListView.this.post(this);
                  return;
                }
                localView = AbMultiColumnBaseAbsListView.this.getChildAt(i);
                m = localView.getHeight();
                int n = localView.getTop();
                if (k < AbMultiColumnBaseAbsListView.this.mItemCount - 1);
                for (i = this.mExtraScroll; ; i = AbMultiColumnBaseAbsListView.this.mListPadding.bottom)
                {
                  AbMultiColumnBaseAbsListView.this.smoothScrollBy(m - (j - n) + i, this.mScrollDuration);
                  this.mLastSeenPos = k;
                  if (k >= this.mTargetPos)
                    break;
                  AbMultiColumnBaseAbsListView.this.post(this);
                  return;
                }
                i = AbMultiColumnBaseAbsListView.this.getChildCount();
              }
              while ((k == this.mBoundPos) || (i <= 1) || (k + i >= AbMultiColumnBaseAbsListView.this.mItemCount));
              i = k + 1;
              if (i == this.mLastSeenPos)
              {
                AbMultiColumnBaseAbsListView.this.post(this);
                return;
              }
              localView = AbMultiColumnBaseAbsListView.this.getChildAt(1);
              j = localView.getHeight();
              k = localView.getTop();
              m = this.mExtraScroll;
              if (i < this.mBoundPos)
              {
                AbMultiColumnBaseAbsListView.this.smoothScrollBy(Math.max(0, j + k - m), this.mScrollDuration);
                this.mLastSeenPos = i;
                AbMultiColumnBaseAbsListView.this.post(this);
                return;
              }
            }
            while (k <= m);
            AbMultiColumnBaseAbsListView.this.smoothScrollBy(k - m, this.mScrollDuration);
            return;
            if (k == this.mLastSeenPos)
            {
              AbMultiColumnBaseAbsListView.this.post(this);
              return;
            }
            localView = AbMultiColumnBaseAbsListView.this.getChildAt(0);
          }
          while (localView == null);
          j = localView.getTop();
          if (k > 0);
          for (i = this.mExtraScroll; ; i = AbMultiColumnBaseAbsListView.this.mListPadding.top)
          {
            AbMultiColumnBaseAbsListView.this.smoothScrollBy(j - i, this.mScrollDuration);
            this.mLastSeenPos = k;
            if (k <= this.mTargetPos)
              break;
            AbMultiColumnBaseAbsListView.this.post(this);
            return;
          }
          m = AbMultiColumnBaseAbsListView.this.getChildCount() - 2;
        }
        while (m < 0);
        i = k + m;
        if (i == this.mLastSeenPos)
        {
          AbMultiColumnBaseAbsListView.this.post(this);
          return;
        }
        View localView = AbMultiColumnBaseAbsListView.this.getChildAt(m);
        k = localView.getHeight();
        int m = localView.getTop();
        this.mLastSeenPos = i;
        if (i > this.mBoundPos)
        {
          AbMultiColumnBaseAbsListView.this.smoothScrollBy(-(j - m - this.mExtraScroll), this.mScrollDuration);
          AbMultiColumnBaseAbsListView.this.post(this);
          return;
        }
        i = j - this.mExtraScroll;
        j = m + k;
      }
      while (i <= j);
      AbMultiColumnBaseAbsListView.this.smoothScrollBy(-(i - j), this.mScrollDuration);
    }

    void start(int paramInt)
    {
      int i = AbMultiColumnBaseAbsListView.this.mFirstPosition;
      int j = AbMultiColumnBaseAbsListView.this.getChildCount() + i - 1;
      if (paramInt <= i)
      {
        i = i - paramInt + 1;
        this.mMode = 2;
        if (i <= 0)
          break label93;
      }
      label93: for (this.mScrollDuration = (400 / i); ; this.mScrollDuration = 400)
      {
        this.mTargetPos = paramInt;
        this.mBoundPos = -1;
        this.mLastSeenPos = -1;
        AbMultiColumnBaseAbsListView.this.post(this);
        do
          return;
        while (paramInt < j);
        i = paramInt - j + 1;
        this.mMode = 1;
        break;
      }
    }

    void start(int paramInt1, int paramInt2)
    {
      if (paramInt2 == -1)
        start(paramInt1);
      int j;
      do
      {
        return;
        i = AbMultiColumnBaseAbsListView.this.mFirstPosition;
        j = AbMultiColumnBaseAbsListView.this.getChildCount() + i - 1;
        if (paramInt1 > i)
          break;
        j -= paramInt2;
      }
      while (j < 1);
      int i = i - paramInt1 + 1;
      j -= 1;
      if (j < i)
      {
        i = j;
        this.mMode = 4;
        label75: if (i <= 0)
          break label176;
      }
      label176: for (this.mScrollDuration = (400 / i); ; this.mScrollDuration = 400)
      {
        this.mTargetPos = paramInt1;
        this.mBoundPos = paramInt2;
        this.mLastSeenPos = -1;
        AbMultiColumnBaseAbsListView.this.post(this);
        return;
        this.mMode = 2;
        break label75;
        if (paramInt1 < j)
          break;
        int k = paramInt2 - i;
        if (k < 1)
          break;
        i = paramInt1 - j + 1;
        j = k - 1;
        if (j < i)
        {
          i = j;
          this.mMode = 3;
          break label75;
        }
        this.mMode = 1;
        break label75;
      }
    }

    void stop()
    {
      AbMultiColumnBaseAbsListView.this.removeCallbacks(this);
    }
  }

  class RecycleBin
  {
    private View[] mActiveViews = new View[0];
    private ArrayList<View> mCurrentScrap;
    private int mFirstActivePosition;
    private AbMultiColumnBaseAbsListView.RecyclerListener mRecyclerListener;
    private ArrayList<View>[] mScrapViews;
    private int mViewTypeCount;

    RecycleBin()
    {
    }

    private void pruneScrapViews()
    {
      int m = this.mActiveViews.length;
      int n = this.mViewTypeCount;
      ArrayList[] arrayOfArrayList = this.mScrapViews;
      int i = 0;
      if (i >= n)
        return;
      ArrayList localArrayList = arrayOfArrayList[i];
      int i1 = localArrayList.size();
      int k = 0;
      int j = i1 - 1;
      while (true)
      {
        if (k >= i1 - m)
        {
          i += 1;
          break;
        }
        AbMultiColumnBaseAbsListView.this.removeDetachedView((View)localArrayList.remove(j), false);
        k += 1;
        j -= 1;
      }
    }

    void addScrapView(View paramView)
    {
      AbMultiColumnBaseAbsListView.LayoutParams localLayoutParams = (AbMultiColumnBaseAbsListView.LayoutParams)paramView.getLayoutParams();
      if (localLayoutParams == null);
      while (true)
      {
        return;
        int i = localLayoutParams.viewType;
        if (!shouldRecycleViewType(i))
        {
          if (i != -2)
            AbMultiColumnBaseAbsListView.this.removeDetachedView(paramView, false);
        }
        else
        {
          if (this.mViewTypeCount == 1)
          {
            AbMultiColumnBaseAbsListView.this.dispatchFinishTemporaryDetach(paramView);
            this.mCurrentScrap.add(paramView);
          }
          while (this.mRecyclerListener != null)
          {
            this.mRecyclerListener.onMovedToScrapHeap(paramView);
            return;
            AbMultiColumnBaseAbsListView.this.dispatchFinishTemporaryDetach(paramView);
            this.mScrapViews[i].add(paramView);
          }
        }
      }
    }

    void clear()
    {
      ArrayList localArrayList;
      int j;
      if (this.mViewTypeCount == 1)
      {
        localArrayList = this.mCurrentScrap;
        j = localArrayList.size();
        i = 0;
        while (true)
        {
          if (i >= j)
            return;
          AbMultiColumnBaseAbsListView.this.removeDetachedView((View)localArrayList.remove(j - 1 - i), false);
          i += 1;
        }
      }
      int k = this.mViewTypeCount;
      int i = 0;
      label63: int m;
      if (i < k)
      {
        localArrayList = this.mScrapViews[i];
        m = localArrayList.size();
        j = 0;
      }
      while (true)
      {
        if (j >= m)
        {
          i += 1;
          break label63;
          break;
        }
        AbMultiColumnBaseAbsListView.this.removeDetachedView((View)localArrayList.remove(m - 1 - j), false);
        j += 1;
      }
    }

    void fillActiveViews(int paramInt1, int paramInt2)
    {
      if (this.mActiveViews.length < paramInt1)
        this.mActiveViews = new View[paramInt1];
      this.mFirstActivePosition = paramInt2;
      View[] arrayOfView = this.mActiveViews;
      paramInt2 = 0;
      while (true)
      {
        if (paramInt2 >= paramInt1)
          return;
        View localView = AbMultiColumnBaseAbsListView.this.getChildAt(paramInt2);
        AbMultiColumnBaseAbsListView.LayoutParams localLayoutParams = (AbMultiColumnBaseAbsListView.LayoutParams)localView.getLayoutParams();
        if ((localLayoutParams != null) && (localLayoutParams.viewType != -2))
          arrayOfView[paramInt2] = localView;
        paramInt2 += 1;
      }
    }

    View getActiveView(int paramInt)
    {
      paramInt -= this.mFirstActivePosition;
      View[] arrayOfView = this.mActiveViews;
      if ((paramInt >= 0) && (paramInt < arrayOfView.length))
      {
        View localView = arrayOfView[paramInt];
        arrayOfView[paramInt] = null;
        return localView;
      }
      return null;
    }

    View getScrapView(int paramInt)
    {
      Object localObject2 = null;
      ArrayList localArrayList;
      Object localObject1;
      if (this.mViewTypeCount == 1)
      {
        localArrayList = this.mCurrentScrap;
        paramInt = localArrayList.size();
        localObject1 = localObject2;
        if (paramInt > 0)
          localObject1 = (View)localArrayList.remove(paramInt - 1);
      }
      do
      {
        do
        {
          do
          {
            return localObject1;
            paramInt = AbMultiColumnBaseAbsListView.this.mAdapter.getItemViewType(paramInt);
            localObject1 = localObject2;
          }
          while (paramInt < 0);
          localObject1 = localObject2;
        }
        while (paramInt >= this.mScrapViews.length);
        localArrayList = this.mScrapViews[paramInt];
        paramInt = localArrayList.size();
        localObject1 = localObject2;
      }
      while (paramInt <= 0);
      return (View)localArrayList.remove(paramInt - 1);
    }

    public void markChildrenDirty()
    {
      ArrayList localArrayList;
      int j;
      if (this.mViewTypeCount == 1)
      {
        localArrayList = this.mCurrentScrap;
        j = localArrayList.size();
        i = 0;
        while (true)
        {
          if (i >= j)
            return;
          ((View)localArrayList.get(i)).forceLayout();
          i += 1;
        }
      }
      int k = this.mViewTypeCount;
      int i = 0;
      label54: int m;
      if (i < k)
      {
        localArrayList = this.mScrapViews[i];
        m = localArrayList.size();
        j = 0;
      }
      while (true)
      {
        if (j >= m)
        {
          i += 1;
          break label54;
          break;
        }
        ((View)localArrayList.get(j)).forceLayout();
        j += 1;
      }
    }

    void reclaimScrapViews(List<View> paramList)
    {
      if (this.mViewTypeCount == 1)
        paramList.addAll(this.mCurrentScrap);
      while (true)
      {
        return;
        int j = this.mViewTypeCount;
        ArrayList[] arrayOfArrayList = this.mScrapViews;
        int i = 0;
        while (i < j)
        {
          paramList.addAll(arrayOfArrayList[i]);
          i += 1;
        }
      }
    }

    void scrapActiveViews()
    {
      View[] arrayOfView = this.mActiveViews;
      int i;
      if (this.mRecyclerListener != null)
      {
        i = 1;
        if (this.mViewTypeCount <= 1)
          break label51;
      }
      Object localObject1;
      int k;
      label51: for (int j = 1; ; j = 0)
      {
        localObject1 = this.mCurrentScrap;
        k = arrayOfView.length - 1;
        if (k >= 0)
          break label56;
        pruneScrapViews();
        return;
        i = 0;
        break;
      }
      label56: View localView = arrayOfView[k];
      Object localObject2 = localObject1;
      int m;
      if (localView != null)
      {
        m = ((AbMultiColumnBaseAbsListView.LayoutParams)localView.getLayoutParams()).viewType;
        arrayOfView[k] = null;
        if (shouldRecycleViewType(m))
          break label134;
        localObject2 = localObject1;
        if (m != -2)
        {
          AbMultiColumnBaseAbsListView.this.removeDetachedView(localView, false);
          localObject2 = localObject1;
        }
      }
      while (true)
      {
        k -= 1;
        localObject1 = localObject2;
        break;
        label134: if (j != 0)
          localObject1 = this.mScrapViews[m];
        AbMultiColumnBaseAbsListView.this.dispatchFinishTemporaryDetach(localView);
        ((ArrayList)localObject1).add(localView);
        localObject2 = localObject1;
        if (i != 0)
        {
          this.mRecyclerListener.onMovedToScrapHeap(localView);
          localObject2 = localObject1;
        }
      }
    }

    void setCacheColorHint(int paramInt)
    {
      Object localObject1;
      int j;
      int i;
      if (this.mViewTypeCount == 1)
      {
        localObject1 = this.mCurrentScrap;
        j = ((ArrayList)localObject1).size();
        i = 0;
        if (i >= j)
        {
          localObject1 = this.mActiveViews;
          j = localObject1.length;
          i = 0;
        }
      }
      while (true)
      {
        if (i >= j)
        {
          return;
          ((View)((ArrayList)localObject1).get(i)).setDrawingCacheBackgroundColor(paramInt);
          i += 1;
          break;
          int k = this.mViewTypeCount;
          i = 0;
          label73: int m;
          if (i < k)
          {
            localObject1 = this.mScrapViews[i];
            m = ((ArrayList)localObject1).size();
            j = 0;
          }
          while (true)
          {
            if (j >= m)
            {
              i += 1;
              break label73;
              break;
            }
            ((View)((ArrayList)localObject1).get(i)).setDrawingCacheBackgroundColor(paramInt);
            j += 1;
          }
        }
        Object localObject2 = localObject1[i];
        if (localObject2 != null)
          localObject2.setDrawingCacheBackgroundColor(paramInt);
        i += 1;
      }
    }

    public void setViewTypeCount(int paramInt)
    {
      if (paramInt < 1)
        throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
      ArrayList[] arrayOfArrayList = new ArrayList[paramInt];
      int i = 0;
      while (true)
      {
        if (i >= paramInt)
        {
          this.mViewTypeCount = paramInt;
          this.mCurrentScrap = arrayOfArrayList[0];
          this.mScrapViews = arrayOfArrayList;
          return;
        }
        arrayOfArrayList[i] = new ArrayList();
        i += 1;
      }
    }

    public boolean shouldRecycleViewType(int paramInt)
    {
      return paramInt >= 0;
    }
  }

  public static abstract interface RecyclerListener
  {
    public abstract void onMovedToScrapHeap(View paramView);
  }

  private class WindowRunnnable
  {
    private int mOriginalAttachCount;

    private WindowRunnnable()
    {
    }

    public void rememberWindowAttachCount()
    {
      this.mOriginalAttachCount = AbMultiColumnBaseAbsListView.this.getWindowAttachCount();
    }

    public boolean sameWindow()
    {
      return (AbMultiColumnBaseAbsListView.this.hasWindowFocus()) && (AbMultiColumnBaseAbsListView.this.getWindowAttachCount() == this.mOriginalAttachCount);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.pullview.AbMultiColumnBaseAbsListView
 * JD-Core Version:    0.6.2
 */