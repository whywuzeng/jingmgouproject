package com.ab.view.pullview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ListAdapter;
import java.util.ArrayList;

public class AbMultiColumnAbsListView extends AbMultiColumnBaseAbsListView
{
  private static final float MAX_SCROLL_FACTOR = 0.33F;
  static final int NO_POSITION = -1;
  private boolean mAreAllItemsSelectable = true;
  private boolean mDividerIsOpaque;
  private ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList();
  private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList();
  private boolean mIsCacheColorOpaque;
  private boolean mItemsCanFocus = false;
  Drawable mOverScrollFooter;
  Drawable mOverScrollHeader;
  private final Rect mTempRect = new Rect();

  public AbMultiColumnAbsListView(Context paramContext)
  {
    super(paramContext);
  }

  public AbMultiColumnAbsListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (0 != 0)
      setOverscrollHeader(null);
    if (0 != 0)
      setOverscrollFooter(null);
  }

  private View addViewAbove(View paramView, int paramInt)
  {
    paramInt -= 1;
    View localView = obtainView(paramInt, this.mIsScrap);
    setupChild(localView, paramInt, paramView.getTop(), false, this.mListPadding.left, false, this.mIsScrap[0]);
    return localView;
  }

  private View addViewBelow(View paramView, int paramInt)
  {
    paramInt += 1;
    View localView = obtainView(paramInt, this.mIsScrap);
    setupChild(localView, paramInt, paramView.getBottom(), true, this.mListPadding.left, false, this.mIsScrap[0]);
    return localView;
  }

  private void adjustViewsUpOrDown()
  {
    int j;
    int i;
    if (getChildCount() > 0)
    {
      if (this.mStackFromBottom)
        break label46;
      j = getScrollChildTop() - this.mListPadding.top;
      i = j;
      if (j < 0)
        i = 0;
    }
    while (true)
    {
      if (i != 0)
        tryOffsetChildrenTopAndBottom(-i);
      return;
      label46: j = getScrollChildBottom() - (getHeight() - this.mListPadding.bottom);
      i = j;
      if (j > 0)
        i = 0;
    }
  }

  private void clearRecycledState(ArrayList<FixedViewInfo> paramArrayList)
  {
    int j;
    int i;
    if (paramArrayList != null)
    {
      j = paramArrayList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= j)
        return;
      AbMultiColumnBaseAbsListView.LayoutParams localLayoutParams = (AbMultiColumnBaseAbsListView.LayoutParams)((FixedViewInfo)paramArrayList.get(i)).view.getLayoutParams();
      if (localLayoutParams != null)
        localLayoutParams.recycledHeaderFooter = false;
      i += 1;
    }
  }

  private void correctTooHigh(int paramInt)
  {
    if ((this.mFirstPosition + paramInt - 1 == this.mItemCount - 1) && (paramInt > 0))
    {
      paramInt = getScrollChildBottom();
      int i = getBottom() - getTop() - this.mListPadding.bottom - paramInt;
      int j = getScrollChildTop();
      if ((i > 0) && ((this.mFirstPosition > 0) || (j < this.mListPadding.top)))
      {
        paramInt = i;
        if (this.mFirstPosition == 0)
          paramInt = Math.min(i, this.mListPadding.top - j);
        tryOffsetChildrenTopAndBottom(paramInt);
        if (this.mFirstPosition > 0)
        {
          paramInt = getScrollChildTop();
          fillUp(this.mFirstPosition - 1, paramInt);
          adjustViewsUpOrDown();
        }
      }
    }
  }

  private void correctTooLow(int paramInt)
  {
    int m;
    if ((this.mFirstPosition == 0) && (paramInt > 0))
    {
      int i = getScrollChildTop();
      int k = this.mListPadding.top;
      int j = getBottom() - getTop() - this.mListPadding.bottom;
      i -= k;
      k = getScrollChildBottom();
      m = this.mFirstPosition + paramInt - 1;
      if (i > 0)
      {
        if ((m >= this.mItemCount - 1) && (k <= j))
          break label142;
        paramInt = i;
        if (m == this.mItemCount - 1)
          paramInt = Math.min(i, k - j);
        tryOffsetChildrenTopAndBottom(-paramInt);
        if (m < this.mItemCount - 1)
        {
          fillDown(m + 1, getFillChildTop());
          adjustViewsUpOrDown();
        }
      }
    }
    label142: 
    while (m != this.mItemCount - 1)
      return;
    adjustViewsUpOrDown();
  }

  private View fillDown(int paramInt1, int paramInt2)
  {
    int j = getBottom();
    int k = getTop();
    int m = this.mListPadding.bottom;
    int i = getFillChildBottom();
    paramInt2 = paramInt1;
    for (paramInt1 = i; ; paramInt1 = getFillChildBottom())
    {
      if ((paramInt1 >= j - k - m) || (paramInt2 >= this.mItemCount))
        return null;
      makeAndAddView(paramInt2, getItemTop(paramInt2), true, false);
      paramInt2 += 1;
    }
  }

  private View fillFromTop(int paramInt)
  {
    this.mFirstPosition = Math.min(this.mFirstPosition, -1);
    this.mFirstPosition = Math.min(this.mFirstPosition, this.mItemCount - 1);
    if (this.mFirstPosition < 0)
      this.mFirstPosition = 0;
    return fillDown(this.mFirstPosition, paramInt);
  }

  private View fillSpecific(int paramInt1, int paramInt2)
  {
    View localView = makeAndAddView(paramInt1, paramInt2, true, false);
    this.mFirstPosition = paramInt1;
    if (!this.mStackFromBottom)
    {
      fillUp(paramInt1 - 1, localView.getTop());
      adjustViewsUpOrDown();
      fillDown(paramInt1 + 1, localView.getBottom());
      paramInt1 = getChildCount();
      if (paramInt1 > 0)
        correctTooHigh(paramInt1);
    }
    while (true)
    {
      return null;
      fillDown(paramInt1 + 1, localView.getBottom());
      adjustViewsUpOrDown();
      fillUp(paramInt1 - 1, localView.getTop());
      paramInt1 = getChildCount();
      if (paramInt1 > 0)
        correctTooLow(paramInt1);
    }
  }

  private View fillUp(int paramInt1, int paramInt2)
  {
    int j = this.mListPadding.top;
    int i = getFillChildTop();
    paramInt2 = paramInt1;
    for (paramInt1 = i; ; paramInt1 = getItemBottom(paramInt2))
    {
      if ((paramInt1 <= j) || (paramInt2 < 0))
      {
        this.mFirstPosition = (paramInt2 + 1);
        return null;
      }
      makeAndAddView(paramInt2, getItemBottom(paramInt2), false, false);
      paramInt2 -= 1;
    }
  }

  private boolean isDirectChildHeaderOrFooter(View paramView)
  {
    ArrayList localArrayList = this.mHeaderViewInfos;
    int j = localArrayList.size();
    int i = 0;
    if (i >= j)
    {
      localArrayList = this.mFooterViewInfos;
      j = localArrayList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= j)
      {
        return false;
        if (paramView == ((FixedViewInfo)localArrayList.get(i)).view)
          return true;
        i += 1;
        break;
      }
      if (paramView == ((FixedViewInfo)localArrayList.get(i)).view)
        return true;
      i += 1;
    }
  }

  private View makeAndAddView(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!this.mDataChanged)
    {
      localView = this.mRecycler.getActiveView(paramInt1);
      if (localView != null)
      {
        setupChild(localView, paramInt1, paramInt2, paramBoolean1, getItemLeft(paramInt1), paramBoolean2, true);
        return localView;
      }
    }
    onItemAddedToList(paramInt1, paramBoolean1);
    int i = getItemLeft(paramInt1);
    View localView = obtainView(paramInt1, this.mIsScrap);
    setupChild(localView, paramInt1, paramInt2, paramBoolean1, i, paramBoolean2, this.mIsScrap[0]);
    return localView;
  }

  private void measureScrapChild(View paramView, int paramInt1, int paramInt2)
  {
    AbMultiColumnBaseAbsListView.LayoutParams localLayoutParams2 = (AbMultiColumnBaseAbsListView.LayoutParams)paramView.getLayoutParams();
    AbMultiColumnBaseAbsListView.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null)
    {
      localLayoutParams1 = new AbMultiColumnBaseAbsListView.LayoutParams(-1, -2, 0);
      paramView.setLayoutParams(localLayoutParams1);
    }
    localLayoutParams1.viewType = this.mAdapter.getItemViewType(paramInt1);
    localLayoutParams1.forceAdd = true;
    paramInt2 = ViewGroup.getChildMeasureSpec(paramInt2, this.mListPadding.left + this.mListPadding.right, localLayoutParams1.width);
    paramInt1 = localLayoutParams1.height;
    if (paramInt1 > 0);
    for (paramInt1 = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824); ; paramInt1 = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(paramInt2, paramInt1);
      return;
    }
  }

  private void removeFixedViewInfo(View paramView, ArrayList<FixedViewInfo> paramArrayList)
  {
    int j = paramArrayList.size();
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      if (((FixedViewInfo)paramArrayList.get(i)).view == paramView)
      {
        paramArrayList.remove(i);
        return;
      }
      i += 1;
    }
  }

  private void scrollListItemsBy(int paramInt)
  {
    tryOffsetChildrenTopAndBottom(paramInt);
    int i = getHeight() - this.mListPadding.bottom;
    int j = this.mListPadding.top;
    AbMultiColumnBaseAbsListView.RecycleBin localRecycleBin = this.mRecycler;
    if (paramInt < 0)
    {
      localView = getLastChild();
      paramInt = getChildCount();
      while (true)
      {
        if (localView.getBottom() >= i);
        int k;
        do
        {
          if (localView.getBottom() < i)
            tryOffsetChildrenTopAndBottom(i - localView.getBottom());
          localView = getChildAt(0);
          if (localView.getBottom() < j)
            break;
          return;
          k = this.mFirstPosition + paramInt - 1;
        }
        while (k >= this.mItemCount - 1);
        addViewBelow(localView, k);
        localView = getLastChild();
        paramInt += 1;
      }
      if (localRecycleBin.shouldRecycleViewType(((AbMultiColumnBaseAbsListView.LayoutParams)localView.getLayoutParams()).viewType))
      {
        detachViewFromParent(localView);
        localRecycleBin.addScrapView(localView);
      }
      while (true)
      {
        localView = getChildAt(0);
        this.mFirstPosition += 1;
        break;
        removeViewInLayout(localView);
      }
    }
    View localView = getChildAt(0);
    label204: if ((localView.getTop() <= j) || (this.mFirstPosition <= 0))
    {
      if (localView.getTop() > j)
        tryOffsetChildrenTopAndBottom(j - localView.getTop());
      paramInt = getChildCount() - 1;
      localView = getChildAt(paramInt);
      label254: if (localView.getTop() > i)
      {
        if (!localRecycleBin.shouldRecycleViewType(((AbMultiColumnBaseAbsListView.LayoutParams)localView.getLayoutParams()).viewType))
          break label334;
        detachViewFromParent(localView);
        localRecycleBin.addScrapView(localView);
      }
    }
    while (true)
    {
      paramInt -= 1;
      localView = getChildAt(paramInt);
      break label254;
      break;
      localView = addViewAbove(localView, this.mFirstPosition);
      this.mFirstPosition -= 1;
      break label204;
      label334: removeViewInLayout(localView);
    }
  }

  private void setupChild(View paramView, int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, boolean paramBoolean3)
  {
    int i;
    boolean bool2;
    label52: label78: AbMultiColumnBaseAbsListView.LayoutParams localLayoutParams1;
    int j;
    label163: int k;
    if ((paramBoolean2) && (shouldShowSelector()))
    {
      paramBoolean2 = true;
      boolean bool1 = paramBoolean2 ^ paramView.isSelected();
      i = this.mTouchMode;
      if ((i <= 0) || (i >= 3) || (this.mMotionPosition != paramInt1))
        break label328;
      bool2 = true;
      boolean bool3 = paramView.isPressed();
      if ((!paramBoolean3) || (bool1) || (paramView.isLayoutRequested()))
        break label334;
      i = 0;
      AbMultiColumnBaseAbsListView.LayoutParams localLayoutParams2 = (AbMultiColumnBaseAbsListView.LayoutParams)paramView.getLayoutParams();
      localLayoutParams1 = localLayoutParams2;
      if (localLayoutParams2 == null)
        localLayoutParams1 = new AbMultiColumnBaseAbsListView.LayoutParams(-1, -2, 0);
      localLayoutParams1.viewType = this.mAdapter.getItemViewType(paramInt1);
      if (((!paramBoolean3) || (localLayoutParams1.forceAdd)) && ((!localLayoutParams1.recycledHeaderFooter) || (localLayoutParams1.viewType != -2)))
        break label346;
      if (!paramBoolean1)
        break label340;
      j = -1;
      attachViewToParent(paramView, j, localLayoutParams1);
      if (bool1)
        paramView.setSelected(paramBoolean2);
      if ((bool2 ^ bool3))
        paramView.setPressed(bool2);
      if (i == 0)
        break label406;
      k = ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mListPadding.left + this.mListPadding.right, localLayoutParams1.width);
      j = localLayoutParams1.height;
      if (j <= 0)
        break label396;
      j = View.MeasureSpec.makeMeasureSpec(j, 1073741824);
      label252: onMeasureChild(paramView, paramInt1, k, j);
      label262: j = paramView.getMeasuredWidth();
      k = paramView.getMeasuredHeight();
      if (!paramBoolean1)
        break label414;
      label279: if (i == 0)
        break label422;
      onLayoutChild(paramView, paramInt1, paramInt3, paramInt2, paramInt3 + j, paramInt2 + k);
    }
    while (true)
    {
      if ((this.mCachingStarted) && (!paramView.isDrawingCacheEnabled()))
        paramView.setDrawingCacheEnabled(true);
      return;
      paramBoolean2 = false;
      break;
      label328: bool2 = false;
      break label52;
      label334: i = 1;
      break label78;
      label340: j = 0;
      break label163;
      label346: localLayoutParams1.forceAdd = false;
      if (localLayoutParams1.viewType == -2)
        localLayoutParams1.recycledHeaderFooter = true;
      if (paramBoolean1);
      for (j = -1; ; j = 0)
      {
        addViewInLayout(paramView, j, localLayoutParams1, true);
        break;
      }
      label396: j = View.MeasureSpec.makeMeasureSpec(0, 0);
      break label252;
      label406: cleanupLayoutState(paramView);
      break label262;
      label414: paramInt2 -= k;
      break label279;
      label422: onOffsetChild(paramView, paramInt1, paramInt3 - paramView.getLeft(), paramInt2 - paramView.getTop());
    }
  }

  private boolean showingBottomFadingEdge()
  {
    int i = getChildCount();
    int j = getChildAt(i - 1).getBottom();
    int k = this.mFirstPosition;
    int m = getScrollY();
    int n = getHeight();
    int i1 = this.mListPadding.bottom;
    return (k + i - 1 < this.mItemCount - 1) || (j < m + n - i1);
  }

  private boolean showingTopFadingEdge()
  {
    int i = getScrollY();
    int j = this.mListPadding.top;
    return (this.mFirstPosition > 0) || (getChildAt(0).getTop() > i + j);
  }

  public void addFooterView(View paramView)
  {
    addFooterView(paramView, null, true);
  }

  public void addFooterView(View paramView, Object paramObject, boolean paramBoolean)
  {
    FixedViewInfo localFixedViewInfo = new FixedViewInfo();
    localFixedViewInfo.view = paramView;
    localFixedViewInfo.data = paramObject;
    localFixedViewInfo.isSelectable = paramBoolean;
    this.mFooterViewInfos.add(localFixedViewInfo);
    if (this.mDataSetObserver != null)
      this.mDataSetObserver.onChanged();
  }

  public void addHeaderView(View paramView)
  {
    addHeaderView(paramView, null, true);
  }

  public void addHeaderView(View paramView, Object paramObject, boolean paramBoolean)
  {
    if (this.mAdapter != null)
      throw new IllegalStateException("Cannot add header view to list -- setAdapter has already been called.");
    FixedViewInfo localFixedViewInfo = new FixedViewInfo();
    localFixedViewInfo.view = paramView;
    localFixedViewInfo.data = paramObject;
    localFixedViewInfo.isSelectable = paramBoolean;
    this.mHeaderViewInfos.add(localFixedViewInfo);
  }

  protected boolean canAnimate()
  {
    return (super.canAnimate()) && (this.mItemCount > 0);
  }

  public void clearChoices()
  {
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    Drawable localDrawable1 = this.mOverScrollHeader;
    Drawable localDrawable2 = this.mOverScrollFooter;
    int j;
    label26: Rect localRect;
    int n;
    int i2;
    int i4;
    int i3;
    int i1;
    boolean bool;
    ListAdapter localListAdapter;
    int i5;
    int i6;
    int i7;
    int i8;
    if (localDrawable1 != null)
    {
      i = 1;
      if (localDrawable2 == null)
        break label277;
      j = 1;
      if ((i != 0) || (j != 0))
      {
        localRect = this.mTempRect;
        localRect.left = getPaddingLeft();
        localRect.right = (getRight() - getLeft() - getPaddingRight());
        n = getChildCount();
        i2 = this.mHeaderViewInfos.size();
        i4 = this.mItemCount;
        i3 = i4 - this.mFooterViewInfos.size() - 1;
        i1 = this.mFirstPosition;
        bool = this.mAreAllItemsSelectable;
        localListAdapter = this.mAdapter;
        i5 = getBottom();
        i6 = getTop();
        i7 = this.mListPadding.bottom;
        i8 = getScrollY();
        if (this.mStackFromBottom)
          break label431;
        m = 0;
        k = getScrollY();
        if ((n > 0) && (k < 0) && (i != 0))
        {
          localRect.bottom = 0;
          localRect.top = k;
          drawOverscrollHeader(paramCanvas, localDrawable1, localRect);
        }
        k = 0;
      }
    }
    for (int i = m; ; i = m)
    {
      if (k >= n)
      {
        k = getBottom() + getScrollY();
        if ((j != 0) && (i1 + n == i4) && (k > i))
        {
          localRect.top = i;
          localRect.bottom = k;
          drawOverscrollFooter(paramCanvas, localDrawable2, localRect);
        }
        super.dispatchDraw(paramCanvas);
        return;
        i = 0;
        break;
        label277: j = 0;
        break label26;
      }
      m = i;
      if (i1 + k >= i2)
      {
        m = i;
        if (i1 + k < i3)
        {
          i = getChildAt(k).getBottom();
          m = i;
          if (i < i5 - i6 - i7 + i8)
            if (j != 0)
            {
              m = i;
              if (k == n - 1);
            }
            else
            {
              if (!bool)
              {
                m = i;
                if (!localListAdapter.isEnabled(i1 + k))
                  break label419;
                if (k != n - 1)
                {
                  m = i;
                  if (!localListAdapter.isEnabled(i1 + k + 1))
                    break label419;
                }
              }
              localRect.top = i;
              localRect.bottom = i;
              m = i;
            }
        }
      }
      label419: k += 1;
    }
    label431: int m = this.mListPadding.top;
    int k = getScrollY();
    if ((n > 0) && (i != 0))
    {
      localRect.top = k;
      localRect.bottom = getChildAt(0).getTop();
      drawOverscrollHeader(paramCanvas, localDrawable1, localRect);
    }
    if (i != 0)
      i = 1;
    while (true)
    {
      if (i >= n)
      {
        if ((n <= 0) || (k <= 0) || (j == 0))
          break;
        i = getBottom();
        localRect.top = i;
        localRect.bottom = (i + k);
        drawOverscrollFooter(paramCanvas, localDrawable2, localRect);
        break;
        i = 0;
        continue;
      }
      if ((i1 + i >= i2) && (i1 + i < i3))
      {
        i4 = getChildAt(i).getTop();
        if ((i4 > m) && ((bool) || ((localListAdapter.isEnabled(i1 + i)) && ((i == n - 1) || (localListAdapter.isEnabled(i1 + i + 1))))))
        {
          localRect.top = i4;
          localRect.bottom = i4;
        }
      }
      i += 1;
    }
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    boolean bool = super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
    int j;
    int m;
    int i;
    ListAdapter localListAdapter;
    int i1;
    if (!bool)
    {
      j = 0;
      m = 0;
      i = getSelectedItemPosition();
      localListAdapter = getAdapter();
      k = i;
      if (localListAdapter != null)
      {
        i1 = localListAdapter.getCount();
        if (i1 >= 15)
          break label140;
        k = 0;
        j = m;
        if (k < i1)
          break label82;
      }
    }
    for (int k = i; ; k = i)
    {
      paramAccessibilityEvent.setItemCount(j);
      paramAccessibilityEvent.setCurrentItemIndex(k);
      return bool;
      label82: int n;
      if (localListAdapter.isEnabled(k))
      {
        n = j + 1;
        m = i;
      }
      while (true)
      {
        k += 1;
        i = m;
        j = n;
        break;
        m = i;
        n = j;
        if (k <= i)
        {
          m = i - 1;
          n = j;
        }
      }
      label140: j = i1;
    }
  }

  void drawOverscrollFooter(Canvas paramCanvas, Drawable paramDrawable, Rect paramRect)
  {
    int i = paramDrawable.getMinimumHeight();
    paramCanvas.save();
    paramCanvas.clipRect(paramRect);
    if (paramRect.bottom - paramRect.top < i)
      paramRect.bottom = (paramRect.top + i);
    paramDrawable.setBounds(paramRect);
    paramDrawable.draw(paramCanvas);
    paramCanvas.restore();
  }

  void drawOverscrollHeader(Canvas paramCanvas, Drawable paramDrawable, Rect paramRect)
  {
    int i = paramDrawable.getMinimumHeight();
    paramCanvas.save();
    paramCanvas.clipRect(paramRect);
    if (paramRect.bottom - paramRect.top < i)
      paramRect.top = (paramRect.bottom - i);
    paramDrawable.setBounds(paramRect);
    paramDrawable.draw(paramCanvas);
    paramCanvas.restore();
  }

  protected void fillGap(boolean paramBoolean)
  {
    int i = getChildCount();
    if (paramBoolean)
    {
      fillDown(this.mFirstPosition + i, getItemTop(this.mFirstPosition + i));
      onAdjustChildViews(paramBoolean);
      return;
    }
    fillUp(this.mFirstPosition - 1, getItemBottom(this.mFirstPosition - 1));
    onAdjustChildViews(paramBoolean);
  }

  int findMotionRow(int paramInt)
  {
    int j = getChildCount();
    int i;
    if (j > 0)
    {
      if (this.mStackFromBottom)
        break label51;
      i = 0;
      if (i < j)
        break label25;
    }
    while (true)
    {
      return -1;
      label25: if (paramInt <= getChildAt(i).getBottom())
        return this.mFirstPosition + i;
      i += 1;
      break;
      label51: i = j - 1;
      while (i >= 0)
      {
        if (paramInt >= getChildAt(i).getTop())
          return this.mFirstPosition + i;
        i -= 1;
      }
    }
  }

  public boolean fullScroll(int paramInt)
  {
    boolean bool = false;
    if (paramInt == 33)
      if (lookForSelectablePosition(0, true) >= 0)
      {
        this.mLayoutMode = 1;
        invokeOnItemScrollListener();
        bool = true;
      }
    while (true)
    {
      if ((bool) && (!awakenScrollBars()))
      {
        awakenScrollBars();
        invalidate();
      }
      return bool;
      if (paramInt == 130)
      {
        if (lookForSelectablePosition(this.mItemCount - 1, true) >= 0)
        {
          this.mLayoutMode = 3;
          invokeOnItemScrollListener();
        }
        bool = true;
      }
    }
  }

  public ListAdapter getAdapter()
  {
    return this.mAdapter;
  }

  @Deprecated
  public long[] getCheckItemIds()
  {
    if ((this.mAdapter != null) && (this.mAdapter.hasStableIds()))
      return getCheckedItemIds();
    return new long[0];
  }

  public long[] getCheckedItemIds()
  {
    return new long[0];
  }

  public int getCheckedItemPosition()
  {
    return -1;
  }

  public SparseBooleanArray getCheckedItemPositions()
  {
    return null;
  }

  public int getFirstVisiblePosition()
  {
    return Math.max(0, this.mFirstPosition - getHeaderViewsCount());
  }

  public int getFooterViewsCount()
  {
    return this.mFooterViewInfos.size();
  }

  public int getHeaderViewsCount()
  {
    return this.mHeaderViewInfos.size();
  }

  protected int getItemBottom(int paramInt)
  {
    if (getChildCount() > 0)
      return getChildAt(0).getTop();
    return getHeight() - getListPaddingBottom();
  }

  protected int getItemLeft(int paramInt)
  {
    return this.mListPadding.left;
  }

  protected int getItemTop(int paramInt)
  {
    paramInt = getChildCount();
    if (paramInt > 0)
      return getChildAt(paramInt - 1).getBottom();
    return getListPaddingTop();
  }

  public boolean getItemsCanFocus()
  {
    return this.mItemsCanFocus;
  }

  protected View getLastChild()
  {
    return getChildAt(getChildCount() - 1);
  }

  public int getLastVisiblePosition()
  {
    return Math.min(this.mFirstPosition + getChildCount() - 1, this.mAdapter.getCount() - 1);
  }

  public int getMaxScrollAmount()
  {
    return (int)(0.33F * (getBottom() - getTop()));
  }

  public Drawable getOverscrollFooter()
  {
    return this.mOverScrollFooter;
  }

  public Drawable getOverscrollHeader()
  {
    return this.mOverScrollHeader;
  }

  public boolean isFixedView(View paramView)
  {
    boolean bool2 = true;
    ArrayList localArrayList = this.mHeaderViewInfos;
    int j = localArrayList.size();
    int i = 0;
    if (i >= j)
    {
      localArrayList = this.mFooterViewInfos;
      j = localArrayList.size();
      i = 0;
    }
    while (true)
    {
      boolean bool1;
      if (i >= j)
        bool1 = false;
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        }
        while (((FixedViewInfo)localArrayList.get(i)).view == paramView);
        i += 1;
        break;
        bool1 = bool2;
      }
      while (((FixedViewInfo)localArrayList.get(i)).view == paramView);
      i += 1;
    }
  }

  public boolean isItemChecked(int paramInt)
  {
    return false;
  }

  public boolean isOpaque()
  {
    return ((this.mCachingStarted) && (this.mIsCacheColorOpaque) && (this.mDividerIsOpaque)) || (super.isOpaque());
  }

  protected void layoutChildren()
  {
    boolean bool1 = this.mBlockLayoutRequests;
    if (!bool1)
      this.mBlockLayoutRequests = true;
    int j;
    int k;
    int m;
    View localView1;
    View localView2;
    boolean bool2;
    try
    {
      super.layoutChildren();
      invalidate();
      if (this.mAdapter == null)
      {
        resetList();
        invokeOnItemScrollListener();
        return;
      }
      j = this.mListPadding.top;
      k = getBottom() - getTop() - this.mListPadding.bottom;
      m = getChildCount();
      localView1 = null;
      Object localObject1 = null;
      localView2 = null;
      switch (this.mLayoutMode)
      {
      case 2:
        localView1 = getChildAt(0);
      case 1:
      case 3:
      case 4:
      case 5:
        bool2 = this.mDataChanged;
        if (bool2)
          handleDataChanged();
        if (this.mItemCount == 0)
        {
          resetList();
          invokeOnItemScrollListener();
          return;
        }
        if (this.mItemCount != this.mAdapter.getCount())
          throw new IllegalStateException("The content of the adapter has changed but ListView did not receive a notification. Make sure the content of your adapter is not modified from a background thread, but only from the UI thread. [in ListView(" + getId() + ", " + getClass() + ") with Adapter(" + this.mAdapter.getClass() + ")]");
        break;
      }
    }
    finally
    {
      if (!bool1)
        this.mBlockLayoutRequests = false;
    }
    int i = this.mFirstPosition;
    AbMultiColumnBaseAbsListView.RecycleBin localRecycleBin = this.mRecycler;
    label291: Object localObject3;
    if (bool2)
    {
      i = 0;
      break label750;
      View localView3 = getFocusedChild();
      if (localView3 != null)
      {
        if (bool2)
        {
          localObject3 = localView2;
          if (!isDirectChildHeaderOrFooter(localView3));
        }
        else
        {
          localView2 = findFocus();
          localObject3 = localView2;
          if (localView2 != null)
          {
            localView2.onStartTemporaryDetach();
            localObject3 = localView2;
          }
        }
        requestFocus();
      }
      switch (this.mLayoutMode)
      {
      case 2:
      case 4:
      case 5:
      case 3:
      case 1:
      }
    }
    while (true)
    {
      if (m == 0)
      {
        detachAllViewsFromParent();
        if (!this.mStackFromBottom)
        {
          fillFromTop(j);
          label410: localRecycleBin.scrapActiveViews();
          if ((this.mTouchMode <= 0) || (this.mTouchMode >= 3))
            break label732;
          localView1 = getChildAt(this.mMotionPosition - this.mFirstPosition);
          if (localView1 != null)
            positionSelector(localView1);
          label456: if ((hasFocus()) && (localObject3 != null))
            localObject3.requestFocus();
          if ((localObject3 != null) && (localObject3.getWindowToken() != null))
            localObject3.onFinishTemporaryDetach();
          this.mLayoutMode = 0;
          this.mDataChanged = false;
          this.mNeedSync = false;
          invokeOnItemScrollListener();
          if (bool1)
            break;
          this.mBlockLayoutRequests = false;
          return;
        }
      }
      label732: label750: 
      do
      {
        localRecycleBin.addScrapView(getChildAt(i));
        i += 1;
        continue;
        localRecycleBin.fillActiveViews(m, i);
        break label291;
        onLayoutSync(this.mSyncPosition);
        detachAllViewsFromParent();
        fillSpecific(this.mSyncPosition, this.mSpecificTop);
        onLayoutSyncFinished(this.mSyncPosition);
        break label410;
        detachAllViewsFromParent();
        fillUp(this.mItemCount - 1, k);
        adjustViewsUpOrDown();
        break label410;
        detachAllViewsFromParent();
        this.mFirstPosition = 0;
        fillFromTop(j);
        adjustViewsUpOrDown();
        break label410;
        fillUp(this.mItemCount - 1, k);
        break label410;
        if (this.mFirstPosition < this.mItemCount)
        {
          onLayoutSync(this.mFirstPosition);
          detachAllViewsFromParent();
          k = this.mFirstPosition;
          if (localView1 == null);
          for (i = j; ; i = localView1.getTop())
          {
            fillSpecific(k, i);
            onLayoutSyncFinished(this.mFirstPosition);
            break;
          }
        }
        onLayoutSync(0);
        detachAllViewsFromParent();
        fillSpecific(0, j);
        onLayoutSyncFinished(0);
        break label410;
        this.mSelectedTop = 0;
        this.mSelectorRect.setEmpty();
        break label456;
        break;
      }
      while (i < m);
      break label291;
    }
  }

  int lookForSelectablePosition(int paramInt, boolean paramBoolean)
  {
    ListAdapter localListAdapter = this.mAdapter;
    if ((localListAdapter == null) || (isInTouchMode()))
      break label67;
    int j;
    label67: 
    do
    {
      return -1;
      j = localListAdapter.getCount();
      if (!this.mAreAllItemsSelectable)
      {
        int i;
        if (paramBoolean)
        {
          paramInt = Math.max(0, paramInt);
          while (true)
          {
            i = paramInt;
            if (paramInt < j)
            {
              if (localListAdapter.isEnabled(paramInt))
                i = paramInt;
            }
            else
            {
              if ((i < 0) || (i >= j))
                break;
              return i;
            }
            paramInt += 1;
          }
        }
        paramInt = Math.min(paramInt, j - 1);
        while (true)
        {
          i = paramInt;
          if (paramInt < 0)
            break;
          i = paramInt;
          if (localListAdapter.isEnabled(paramInt))
            break;
          paramInt -= 1;
        }
      }
    }
    while ((paramInt < 0) || (paramInt >= j));
    return paramInt;
  }

  final int measureHeightOfChildren(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    Object localObject = this.mAdapter;
    if (localObject == null)
    {
      paramInt2 = this.mListPadding.top + this.mListPadding.bottom;
      return paramInt2;
    }
    int m = this.mListPadding.top + this.mListPadding.bottom;
    int k = 0;
    int i = paramInt3;
    if (paramInt3 == -1)
      i = ((ListAdapter)localObject).getCount() - 1;
    localObject = this.mRecycler;
    boolean bool = recycleOnMeasure();
    boolean[] arrayOfBoolean = this.mIsScrap;
    int j = paramInt2;
    paramInt3 = m;
    for (paramInt2 = k; ; paramInt2 = k)
    {
      if (j > i)
        return paramInt3;
      View localView = obtainView(j, arrayOfBoolean);
      measureScrapChild(localView, j, paramInt1);
      if ((bool) && (((AbMultiColumnBaseAbsListView.RecycleBin)localObject).shouldRecycleViewType(((AbMultiColumnBaseAbsListView.LayoutParams)localView.getLayoutParams()).viewType)))
        ((AbMultiColumnBaseAbsListView.RecycleBin)localObject).addScrapView(localView);
      paramInt3 += localView.getMeasuredHeight();
      if (paramInt3 >= paramInt4)
      {
        if ((paramInt5 >= 0) && (j > paramInt5) && (paramInt2 > 0) && (paramInt3 != paramInt4))
          break;
        return paramInt4;
      }
      k = paramInt2;
      if (paramInt5 >= 0)
      {
        k = paramInt2;
        if (j >= paramInt5)
          k = paramInt3;
      }
      j += 1;
    }
  }

  protected void onAdjustChildViews(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      correctTooHigh(getChildCount());
      return;
    }
    correctTooLow(getChildCount());
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    int j = getChildCount();
    int i;
    if (j > 0)
      i = 0;
    while (true)
    {
      if (i >= j)
      {
        removeAllViews();
        return;
      }
      addHeaderView(getChildAt(i));
      i += 1;
    }
  }

  protected void onFocusChanged(boolean paramBoolean, int paramInt, Rect paramRect)
  {
    super.onFocusChanged(paramBoolean, paramInt, paramRect);
    int j = -1;
    int i = j;
    ListAdapter localListAdapter;
    Rect localRect;
    int k;
    int i2;
    if (paramBoolean)
    {
      i = j;
      if (paramRect != null)
      {
        paramRect.offset(getScrollX(), getScrollY());
        localListAdapter = this.mAdapter;
        if (localListAdapter.getCount() < getChildCount() + this.mFirstPosition)
        {
          this.mLayoutMode = 0;
          layoutChildren();
        }
        localRect = this.mTempRect;
        k = 2147483647;
        int i1 = getChildCount();
        i2 = this.mFirstPosition;
        i = 0;
        if (i < i1)
          break label126;
        i = j;
      }
    }
    if (i >= 0)
    {
      setSelection(this.mFirstPosition + i);
      return;
      label126: int m;
      if (!localListAdapter.isEnabled(i2 + i))
        m = k;
      while (true)
      {
        i += 1;
        k = m;
        break;
        View localView = getChildAt(i);
        localView.getDrawingRect(localRect);
        offsetDescendantRectToMyCoords(localView, localRect);
        int n = getDistance(paramRect, localRect, paramInt);
        m = k;
        if (n < k)
        {
          m = n;
          j = i;
        }
      }
    }
    requestLayout();
  }

  protected void onItemAddedToList(int paramInt, boolean paramBoolean)
  {
  }

  protected void onLayoutChild(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    paramView.layout(paramInt2, paramInt3, paramInt4, paramInt5);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i2 = View.MeasureSpec.getMode(paramInt1);
    int i1 = View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getSize(paramInt2);
    int m = 0;
    int n = 0;
    if (this.mAdapter == null);
    for (paramInt2 = 0; ; paramInt2 = this.mAdapter.getCount())
    {
      this.mItemCount = paramInt2;
      int i = n;
      paramInt2 = m;
      if (this.mItemCount > 0)
        if (i2 != 0)
        {
          i = n;
          paramInt2 = m;
          if (i1 != 0);
        }
        else
        {
          View localView = obtainView(0, this.mIsScrap);
          measureScrapChild(localView, 0, paramInt1);
          m = localView.getMeasuredWidth();
          n = localView.getMeasuredHeight();
          i = n;
          paramInt2 = m;
          if (recycleOnMeasure())
          {
            i = n;
            paramInt2 = m;
            if (this.mRecycler.shouldRecycleViewType(((AbMultiColumnBaseAbsListView.LayoutParams)localView.getLayoutParams()).viewType))
            {
              this.mRecycler.addScrapView(localView);
              paramInt2 = m;
              i = n;
            }
          }
        }
      if (i2 == 0)
        j = this.mListPadding.left + this.mListPadding.right + paramInt2 + getVerticalScrollbarWidth();
      paramInt2 = k;
      if (i1 == 0)
        paramInt2 = this.mListPadding.top + this.mListPadding.bottom + i + getVerticalFadingEdgeLength() * 2;
      i = paramInt2;
      if (i1 == -2147483648)
        i = measureHeightOfChildren(paramInt1, 0, -1, paramInt2, -1);
      setMeasuredDimension(j, i);
      this.mWidthMeasureSpec = paramInt1;
      return;
    }
  }

  protected void onMeasureChild(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    paramView.measure(paramInt2, paramInt3);
  }

  protected void onOffsetChild(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    paramView.offsetLeftAndRight(paramInt2);
    paramView.offsetTopAndBottom(paramInt3);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((this.mItemsCanFocus) && (paramMotionEvent.getAction() == 0) && (paramMotionEvent.getEdgeFlags() != 0))
      return false;
    return super.onTouchEvent(paramMotionEvent);
  }

  public boolean performItemClick(View paramView, int paramInt, long paramLong)
  {
    return false | super.performItemClick(paramView, paramInt, paramLong);
  }

  protected boolean recycleOnMeasure()
  {
    return true;
  }

  public boolean removeFooterView(View paramView)
  {
    if (this.mFooterViewInfos.size() > 0)
    {
      boolean bool = false;
      if (((AbMultiColumnHeaderViewListAdapter)this.mAdapter).removeFooter(paramView))
      {
        this.mDataSetObserver.onChanged();
        bool = true;
      }
      removeFixedViewInfo(paramView, this.mFooterViewInfos);
      return bool;
    }
    return false;
  }

  public boolean removeHeaderView(View paramView)
  {
    if (this.mHeaderViewInfos.size() > 0)
    {
      boolean bool = false;
      if (((AbMultiColumnHeaderViewListAdapter)this.mAdapter).removeHeader(paramView))
      {
        this.mDataSetObserver.onChanged();
        bool = true;
      }
      removeFixedViewInfo(paramView, this.mHeaderViewInfos);
      return bool;
    }
    return false;
  }

  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    int i1 = paramRect.top;
    paramRect.offset(paramView.getLeft(), paramView.getTop());
    paramRect.offset(-paramView.getScrollX(), -paramView.getScrollY());
    int n = getHeight();
    int k = getScrollY();
    int i = k + n;
    int m = getVerticalFadingEdgeLength();
    int j = k;
    if (showingTopFadingEdge())
    {
      j = k;
      if (i1 > m)
        j = k + m;
    }
    i1 = getChildAt(getChildCount() - 1).getBottom();
    k = i;
    if (showingBottomFadingEdge())
    {
      k = i;
      if (paramRect.bottom < i1 - m)
        k = i - m;
    }
    m = 0;
    if ((paramRect.bottom > k) && (paramRect.top > j))
      if (paramRect.height() > n)
      {
        i = 0 + (paramRect.top - j);
        i = Math.min(i, i1 - k);
        label188: if (i == 0)
          break label320;
      }
    label320: for (paramBoolean = true; ; paramBoolean = false)
    {
      if (paramBoolean)
      {
        scrollListItemsBy(-i);
        positionSelector(paramView);
        this.mSelectedTop = paramView.getTop();
        invalidate();
      }
      return paramBoolean;
      i = 0 + (paramRect.bottom - k);
      break;
      i = m;
      if (paramRect.top >= j)
        break label188;
      i = m;
      if (paramRect.bottom >= k)
        break label188;
      if (paramRect.height() > n);
      for (i = 0 - (k - paramRect.bottom); ; i = 0 - (j - paramRect.top))
      {
        i = Math.max(i, getChildAt(0).getTop() - j);
        break;
      }
    }
  }

  void resetList()
  {
    clearRecycledState(this.mHeaderViewInfos);
    clearRecycledState(this.mFooterViewInfos);
    super.resetList();
    this.mLayoutMode = 0;
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (this.mAdapter != null)
      this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
    resetList();
    this.mRecycler.clear();
    if ((this.mHeaderViewInfos.size() > 0) || (this.mFooterViewInfos.size() > 0))
    {
      this.mAdapter = new AbMultiColumnHeaderViewListAdapter(this.mHeaderViewInfos, this.mFooterViewInfos, paramListAdapter);
      this.mOldSelectedPosition = -1;
      this.mOldSelectedRowId = -9223372036854775808L;
      if (this.mAdapter == null)
        break label182;
      this.mAreAllItemsSelectable = this.mAdapter.areAllItemsEnabled();
      this.mOldItemCount = this.mItemCount;
      this.mItemCount = this.mAdapter.getCount();
      checkFocus();
      this.mDataSetObserver = new AbMultiColumnAdapterView.AdapterDataSetObserver(this);
      this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
      this.mRecycler.setViewTypeCount(this.mAdapter.getViewTypeCount());
    }
    while (true)
    {
      requestLayout();
      return;
      this.mAdapter = paramListAdapter;
      break;
      label182: this.mAreAllItemsSelectable = true;
      checkFocus();
    }
  }

  public void setCacheColorHint(int paramInt)
  {
    if (paramInt >>> 24 == 255);
    for (boolean bool = true; ; bool = false)
    {
      this.mIsCacheColorOpaque = bool;
      super.setCacheColorHint(paramInt);
      return;
    }
  }

  public void setItemChecked(int paramInt, boolean paramBoolean)
  {
  }

  public void setItemsCanFocus(boolean paramBoolean)
  {
    this.mItemsCanFocus = paramBoolean;
    if (!paramBoolean)
      setDescendantFocusability(393216);
  }

  public void setOverscrollFooter(Drawable paramDrawable)
  {
    this.mOverScrollFooter = paramDrawable;
    invalidate();
  }

  public void setOverscrollHeader(Drawable paramDrawable)
  {
    this.mOverScrollHeader = paramDrawable;
    if (getScrollY() < 0)
      invalidate();
  }

  public void setSelection(int paramInt)
  {
  }

  public class FixedViewInfo
  {
    public Object data;
    public boolean isSelectable;
    public View view;

    public FixedViewInfo()
    {
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.pullview.AbMultiColumnAbsListView
 * JD-Core Version:    0.6.2
 */