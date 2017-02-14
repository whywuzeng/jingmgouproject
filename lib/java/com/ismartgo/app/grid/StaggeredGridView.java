package com.ismartgo.app.grid;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import com.ismartgo.apppub.R.styleable;
import java.util.Arrays;

public class StaggeredGridView extends ExtendableListView
{
  private static final boolean DBG = false;
  private static final int DEFAULT_COLUMNS_LANDSCAPE = 3;
  private static final int DEFAULT_COLUMNS_PORTRAIT = 2;
  private static final String TAG = "StaggeredGridView";
  private int[] mColumnBottoms;
  private int mColumnCount;
  private int mColumnCountLandscape = 3;
  private int mColumnCountPortrait = 2;
  private int[] mColumnLefts;
  private int[] mColumnTops;
  private int mColumnWidth;
  private int mDistanceToTop;
  private int mGridPaddingBottom;
  private int mGridPaddingLeft;
  private int mGridPaddingRight;
  private int mGridPaddingTop;
  private int mItemMargin;
  private boolean mNeedSync;
  private SparseArray<GridItemRecord> mPositionData;

  public StaggeredGridView(Context paramContext)
  {
    this(paramContext, null);
  }

  public StaggeredGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public StaggeredGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (paramAttributeSet != null)
    {
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.StaggeredGridView, paramInt, 0);
      this.mColumnCount = paramContext.getInteger(0, 0);
      if (this.mColumnCount <= 0)
        break label159;
      this.mColumnCountPortrait = this.mColumnCount;
    }
    for (this.mColumnCountLandscape = this.mColumnCount; ; this.mColumnCountLandscape = paramContext.getInteger(2, 3))
    {
      this.mItemMargin = paramContext.getDimensionPixelSize(3, 0);
      this.mGridPaddingLeft = paramContext.getDimensionPixelSize(4, 0);
      this.mGridPaddingRight = paramContext.getDimensionPixelSize(5, 0);
      this.mGridPaddingTop = paramContext.getDimensionPixelSize(6, 0);
      this.mGridPaddingBottom = paramContext.getDimensionPixelSize(7, 0);
      paramContext.recycle();
      this.mColumnCount = 0;
      this.mColumnTops = new int[0];
      this.mColumnBottoms = new int[0];
      this.mColumnLefts = new int[0];
      this.mPositionData = new SparseArray();
      return;
      label159: this.mColumnCountPortrait = paramContext.getInteger(1, 2);
    }
  }

  private void alignTops()
  {
    int[] arrayOfInt;
    int n;
    int k;
    int j;
    if (this.mFirstPosition == getHeaderViewsCount())
    {
      arrayOfInt = getHighestNonHeaderTops();
      n = 1;
      k = -1;
      j = 2147483647;
      i = 0;
    }
    while (true)
    {
      if (i >= arrayOfInt.length)
      {
        if (n == 0)
          break;
        return;
      }
      int m = n;
      if (n != 0)
      {
        m = n;
        if (i > 0)
        {
          m = n;
          if (arrayOfInt[i] != j)
            m = 0;
        }
      }
      n = j;
      if (arrayOfInt[i] < j)
      {
        n = arrayOfInt[i];
        k = i;
      }
      i += 1;
      j = n;
      n = m;
    }
    int i = 0;
    while (true)
    {
      if (i >= arrayOfInt.length)
      {
        invalidate();
        return;
      }
      if (i != k)
        offsetChildrenTopAndBottom(j - arrayOfInt[i], i);
      i += 1;
    }
  }

  private int calculateColumnLeft(int paramInt)
  {
    return getRowPaddingLeft() + this.mItemMargin + (this.mItemMargin + this.mColumnWidth) * paramInt;
  }

  private int calculateColumnWidth(int paramInt)
  {
    return (paramInt - (getRowPaddingLeft() + getRowPaddingRight()) - this.mItemMargin * (this.mColumnCount + 1)) / this.mColumnCount;
  }

  private int getChildBottomMargin()
  {
    return this.mItemMargin;
  }

  private int getChildColumn(int paramInt, boolean paramBoolean)
  {
    int i = getPositionColumn(paramInt);
    int j = this.mColumnCount;
    if (i >= 0)
    {
      paramInt = i;
      if (i < j);
    }
    else
    {
      if (!paramBoolean)
        break label35;
      paramInt = getHighestPositionedBottomColumn();
    }
    return paramInt;
    label35: return getLowestPositionedTopColumn();
  }

  private int getChildHeight(View paramView)
  {
    return paramView.getMeasuredHeight();
  }

  private int getChildTopMargin(int paramInt)
  {
    int i = 0;
    if (paramInt < getHeaderViewsCount() + this.mColumnCount);
    for (paramInt = 1; ; paramInt = 0)
    {
      if (paramInt != 0)
        i = this.mItemMargin;
      return i;
    }
  }

  private int[] getHighestNonHeaderTops()
  {
    int[] arrayOfInt = new int[this.mColumnCount];
    int j = getChildCount();
    int i;
    if (j > 0)
      i = 0;
    while (true)
    {
      if (i >= j)
        return arrayOfInt;
      View localView = getChildAt(i);
      if ((localView != null) && (localView.getLayoutParams() != null) && ((localView.getLayoutParams() instanceof GridLayoutParams)))
      {
        GridLayoutParams localGridLayoutParams = (GridLayoutParams)localView.getLayoutParams();
        if ((localGridLayoutParams.viewType != -2) && (localView.getTop() < arrayOfInt[localGridLayoutParams.column]))
          arrayOfInt[localGridLayoutParams.column] = localView.getTop();
      }
      i += 1;
    }
  }

  private int getHighestPositionedBottom()
  {
    int i = getHighestPositionedBottomColumn();
    return this.mColumnBottoms[i];
  }

  private int getHighestPositionedBottomColumn()
  {
    int m = 0;
    int j = 2147483647;
    int i = 0;
    while (true)
    {
      if (i >= this.mColumnCount)
        return m;
      int n = this.mColumnBottoms[i];
      int k = j;
      if (n < j)
      {
        k = n;
        m = i;
      }
      i += 1;
      j = k;
    }
  }

  private int getHighestPositionedTop()
  {
    int i = getHighestPositionedTopColumn();
    return this.mColumnTops[i];
  }

  private int getHighestPositionedTopColumn()
  {
    int m = 0;
    int j = 2147483647;
    int i = 0;
    while (true)
    {
      if (i >= this.mColumnCount)
        return m;
      int n = this.mColumnTops[i];
      int k = j;
      if (n < j)
      {
        k = n;
        m = i;
      }
      i += 1;
      j = k;
    }
  }

  private int getLowestPositionedBottom()
  {
    int i = getLowestPositionedBottomColumn();
    return this.mColumnBottoms[i];
  }

  private int getLowestPositionedBottomColumn()
  {
    int m = 0;
    int j = -2147483648;
    int i = 0;
    while (true)
    {
      if (i >= this.mColumnCount)
        return m;
      int n = this.mColumnBottoms[i];
      int k = j;
      if (n > j)
      {
        k = n;
        m = i;
      }
      i += 1;
      j = k;
    }
  }

  private int getLowestPositionedTop()
  {
    int i = getLowestPositionedTopColumn();
    return this.mColumnTops[i];
  }

  private int getLowestPositionedTopColumn()
  {
    int m = 0;
    int j = -2147483648;
    int i = 0;
    while (true)
    {
      if (i >= this.mColumnCount)
        return m;
      int n = this.mColumnTops[i];
      int k = j;
      if (n > j)
      {
        k = n;
        m = i;
      }
      i += 1;
      j = k;
    }
  }

  private GridItemRecord getOrCreateRecord(int paramInt)
  {
    GridItemRecord localGridItemRecord2 = (GridItemRecord)this.mPositionData.get(paramInt, null);
    GridItemRecord localGridItemRecord1 = localGridItemRecord2;
    if (localGridItemRecord2 == null)
    {
      localGridItemRecord1 = new GridItemRecord();
      this.mPositionData.append(paramInt, localGridItemRecord1);
    }
    return localGridItemRecord1;
  }

  private int getPositionColumn(int paramInt)
  {
    GridItemRecord localGridItemRecord = (GridItemRecord)this.mPositionData.get(paramInt, null);
    if (localGridItemRecord != null)
      return localGridItemRecord.column;
    return -1;
  }

  private void initColumnBottoms()
  {
    Arrays.fill(this.mColumnBottoms, getPaddingTop() + this.mGridPaddingTop);
  }

  private void initColumnLefts()
  {
    int i = 0;
    while (true)
    {
      if (i >= this.mColumnCount)
        return;
      this.mColumnLefts[i] = calculateColumnLeft(i);
      i += 1;
    }
  }

  private void initColumnTops()
  {
    Arrays.fill(this.mColumnTops, getPaddingTop() + this.mGridPaddingTop);
  }

  private void initColumnTopsAndBottoms()
  {
    initColumnTops();
    initColumnBottoms();
  }

  private boolean isHeaderOrFooter(int paramInt)
  {
    return this.mAdapter.getItemViewType(paramInt) == -2;
  }

  private void layoutGridChild(View paramView, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3)
  {
    int j = getPositionColumn(paramInt1);
    int k = getChildTopMargin(paramInt1);
    int m = getChildBottomMargin();
    int n = k + m;
    int i;
    if (paramBoolean)
    {
      i = this.mColumnBottoms[j];
      paramInt1 = i + (getChildHeight(paramView) + n);
    }
    while (true)
    {
      ((GridLayoutParams)paramView.getLayoutParams()).column = j;
      updateColumnBottomIfNeeded(j, paramInt1);
      updateColumnTopIfNeeded(j, i);
      paramView.layout(paramInt2, i + k, paramInt3, paramInt1 - m);
      return;
      paramInt1 = this.mColumnTops[j];
      i = paramInt1 - (getChildHeight(paramView) + n);
    }
  }

  private void layoutGridHeaderFooter(View paramView, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i;
    if (paramBoolean)
    {
      paramInt3 = getLowestPositionedBottom();
      paramInt5 = paramInt3 + getChildHeight(paramView);
      i = 0;
    }
    while (true)
    {
      if (i >= this.mColumnCount)
      {
        super.onLayoutChild(paramView, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5);
        return;
        paramInt5 = getHighestPositionedTop();
        paramInt3 = paramInt5 - getChildHeight(paramView);
        break;
      }
      updateColumnTopIfNeeded(i, paramInt3);
      updateColumnBottomIfNeeded(i, paramInt5);
      i += 1;
    }
  }

  private void offsetAllColumnsTopAndBottom(int paramInt)
  {
    int i;
    if (paramInt != 0)
      i = 0;
    while (true)
    {
      if (i >= this.mColumnCount)
        return;
      offsetColumnTopAndBottom(paramInt, i);
      i += 1;
    }
  }

  private void offsetColumnTopAndBottom(int paramInt1, int paramInt2)
  {
    if (paramInt1 != 0)
    {
      int[] arrayOfInt = this.mColumnTops;
      arrayOfInt[paramInt2] += paramInt1;
      arrayOfInt = this.mColumnBottoms;
      arrayOfInt[paramInt2] += paramInt1;
    }
  }

  private void offsetDistanceToTop(int paramInt)
  {
    this.mDistanceToTop += paramInt;
  }

  private void offsetGridChild(View paramView, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3)
  {
    int j = getPositionColumn(paramInt1);
    int k = getChildTopMargin(paramInt1);
    int m = k + getChildBottomMargin();
    int i;
    if (paramBoolean)
    {
      i = this.mColumnBottoms[j];
      paramInt3 = i + (getChildHeight(paramView) + m);
    }
    while (true)
    {
      ((GridLayoutParams)paramView.getLayoutParams()).column = j;
      updateColumnBottomIfNeeded(j, paramInt3);
      updateColumnTopIfNeeded(j, i);
      super.onOffsetChild(paramView, paramInt1, paramBoolean, paramInt2, i + k);
      return;
      paramInt3 = this.mColumnTops[j];
      i = paramInt3 - (getChildHeight(paramView) + m);
    }
  }

  private void offsetGridHeaderFooter(View paramView, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3)
  {
    int i;
    int j;
    if (paramBoolean)
    {
      paramInt3 = getLowestPositionedBottom();
      i = paramInt3 + getChildHeight(paramView);
      j = 0;
    }
    while (true)
    {
      if (j >= this.mColumnCount)
      {
        super.onOffsetChild(paramView, paramInt1, paramBoolean, paramInt2, paramInt3);
        return;
        i = getHighestPositionedTop();
        paramInt3 = i - getChildHeight(paramView);
        break;
      }
      updateColumnTopIfNeeded(j, paramInt3);
      updateColumnBottomIfNeeded(j, i);
      j += 1;
    }
  }

  private void onColumnSync()
  {
    int k = Math.min(this.mSyncPosition, getCount() - 1);
    SparseArray localSparseArray = new SparseArray(k);
    int i = 0;
    while (true)
    {
      if (i >= k);
      do
      {
        this.mPositionData.clear();
        i = 0;
        if (i < k)
          break;
        i = getHighestPositionedBottomColumn();
        setPositionColumn(k, i);
        i = this.mColumnBottoms[i];
        offsetAllColumnsTopAndBottom(-i + this.mSpecificTop);
        this.mDistanceToTop = (-i);
        System.arraycopy(this.mColumnBottoms, 0, this.mColumnTops, 0, this.mColumnCount);
        return;
        localGridItemRecord = (GridItemRecord)this.mPositionData.get(i);
      }
      while (localGridItemRecord == null);
      Log.d("StaggeredGridView", "onColumnSync:" + i + " ratio:" + localGridItemRecord.heightRatio);
      localSparseArray.append(i, Double.valueOf(localGridItemRecord.heightRatio));
      i += 1;
    }
    GridItemRecord localGridItemRecord = getOrCreateRecord(i);
    double d = ((Double)localSparseArray.get(i)).doubleValue();
    int m = (int)(this.mColumnWidth * d);
    localGridItemRecord.heightRatio = d;
    int n;
    int j;
    if (isHeaderOrFooter(i))
    {
      n = getLowestPositionedBottom();
      j = 0;
      label232: if (j < this.mColumnCount);
    }
    while (true)
    {
      i += 1;
      break;
      this.mColumnTops[j] = n;
      this.mColumnBottoms[j] = (n + m);
      j += 1;
      break label232;
      j = getHighestPositionedBottomColumn();
      n = this.mColumnBottoms[j];
      int i1 = getChildTopMargin(i);
      int i2 = getChildBottomMargin();
      this.mColumnTops[j] = n;
      this.mColumnBottoms[j] = (n + m + i1 + i2);
      localGridItemRecord.column = j;
    }
  }

  private void preLayoutChildren()
  {
    if (!this.mNeedSync)
      Arrays.fill(this.mColumnBottoms, 0);
    while (true)
    {
      System.arraycopy(this.mColumnTops, 0, this.mColumnBottoms, 0, this.mColumnCount);
      return;
      this.mNeedSync = false;
    }
  }

  private void requestLayoutChildren()
  {
    int j = getChildCount();
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      View localView = getChildAt(i);
      if (localView != null)
        localView.requestLayout();
      i += 1;
    }
  }

  private void setPositionColumn(int paramInt1, int paramInt2)
  {
    getOrCreateRecord(paramInt1).column = paramInt2;
  }

  private void setPositionHeightRatio(int paramInt1, int paramInt2)
  {
    getOrCreateRecord(paramInt1).heightRatio = (paramInt2 / this.mColumnWidth);
  }

  private void setPositionIsHeaderFooter(int paramInt)
  {
    getOrCreateRecord(paramInt).isHeaderFooter = true;
  }

  private void updateColumnBottomIfNeeded(int paramInt1, int paramInt2)
  {
    if (paramInt2 > this.mColumnBottoms[paramInt1])
      this.mColumnBottoms[paramInt1] = paramInt2;
  }

  private void updateColumnTopIfNeeded(int paramInt1, int paramInt2)
  {
    if (paramInt2 < this.mColumnTops[paramInt1])
      this.mColumnTops[paramInt1] = paramInt2;
  }

  protected void adjustViewsAfterFillGap(boolean paramBoolean)
  {
    super.adjustViewsAfterFillGap(paramBoolean);
    if (!paramBoolean)
      alignTops();
  }

  public void clearColumnData()
  {
    if ((this.mPositionData != null) && (this.mPositionData.size() > 0))
      this.mPositionData.clear();
  }

  protected ExtendableListView.LayoutParams generateChildLayoutParams(View paramView)
  {
    Object localObject = null;
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    paramView = (View)localObject;
    if (localLayoutParams != null)
      if (!(localLayoutParams instanceof GridLayoutParams))
        break label47;
    label47: for (paramView = (GridLayoutParams)localLayoutParams; ; paramView = new GridLayoutParams(localLayoutParams))
    {
      localObject = paramView;
      if (paramView == null)
        localObject = new GridLayoutParams(this.mColumnWidth, -2);
      return localObject;
    }
  }

  protected int getChildBottom(int paramInt)
  {
    if (isHeaderOrFooter(paramInt))
      return super.getChildBottom(paramInt);
    paramInt = getPositionColumn(paramInt);
    if (paramInt == -1)
      return getLowestPositionedTop();
    return this.mColumnTops[paramInt];
  }

  protected int getChildLeft(int paramInt)
  {
    if (isHeaderOrFooter(paramInt))
      return super.getChildLeft(paramInt);
    paramInt = getPositionColumn(paramInt);
    return this.mColumnLefts[paramInt];
  }

  protected int getChildTop(int paramInt)
  {
    if (isHeaderOrFooter(paramInt))
      return super.getChildTop(paramInt);
    paramInt = getPositionColumn(paramInt);
    if (paramInt == -1)
      return getHighestPositionedBottom();
    return this.mColumnBottoms[paramInt];
  }

  public int getColumnWidth()
  {
    return this.mColumnWidth;
  }

  public int getDistanceToTop()
  {
    return this.mDistanceToTop;
  }

  protected int getFirstChildTop()
  {
    if (isHeaderOrFooter(this.mFirstPosition))
      return super.getFirstChildTop();
    return getLowestPositionedTop();
  }

  protected int getHighestChildTop()
  {
    if (isHeaderOrFooter(this.mFirstPosition))
      return super.getHighestChildTop();
    return getHighestPositionedTop();
  }

  protected int getLastChildBottom()
  {
    if (isHeaderOrFooter(this.mFirstPosition + (getChildCount() - 1)))
      return super.getLastChildBottom();
    return getHighestPositionedBottom();
  }

  protected int getLowestChildBottom()
  {
    if (isHeaderOrFooter(this.mFirstPosition + (getChildCount() - 1)))
      return super.getLowestChildBottom();
    return getLowestPositionedBottom();
  }

  protected int getNextChildDownsTop(int paramInt)
  {
    if (isHeaderOrFooter(paramInt))
      return super.getNextChildDownsTop(paramInt);
    return getHighestPositionedBottom();
  }

  protected int getNextChildUpsBottom(int paramInt)
  {
    if (isHeaderOrFooter(paramInt))
      return super.getNextChildUpsBottom(paramInt);
    return getLowestPositionedTop();
  }

  public int getRowPaddingBottom()
  {
    return getListPaddingBottom() + this.mGridPaddingBottom;
  }

  public int getRowPaddingLeft()
  {
    return getListPaddingLeft() + this.mGridPaddingLeft;
  }

  public int getRowPaddingRight()
  {
    return getListPaddingRight() + this.mGridPaddingRight;
  }

  public int getRowPaddingTop()
  {
    return getListPaddingTop() + this.mGridPaddingTop;
  }

  protected boolean hasSpaceUp()
  {
    boolean bool = false;
    if (this.mClipToPadding);
    for (int i = getRowPaddingTop(); ; i = 0)
    {
      if (getLowestPositionedTop() > i)
        bool = true;
      return bool;
    }
  }

  protected void layoutChildren()
  {
    preLayoutChildren();
    super.layoutChildren();
  }

  protected void offsetChildrenTopAndBottom(int paramInt)
  {
    super.offsetChildrenTopAndBottom(paramInt);
    offsetAllColumnsTopAndBottom(paramInt);
    offsetDistanceToTop(paramInt);
  }

  protected void offsetChildrenTopAndBottom(int paramInt1, int paramInt2)
  {
    int j = getChildCount();
    int i = 0;
    while (true)
    {
      if (i >= j)
      {
        offsetColumnTopAndBottom(paramInt1, paramInt2);
        return;
      }
      View localView = getChildAt(i);
      if ((localView != null) && (localView.getLayoutParams() != null) && ((localView.getLayoutParams() instanceof GridLayoutParams)) && (((GridLayoutParams)localView.getLayoutParams()).column == paramInt2))
        localView.offsetTopAndBottom(paramInt1);
      i += 1;
    }
  }

  protected void onChildCreated(int paramInt, boolean paramBoolean)
  {
    super.onChildCreated(paramInt, paramBoolean);
    if (!isHeaderOrFooter(paramInt))
    {
      setPositionColumn(paramInt, getChildColumn(paramInt, paramBoolean));
      return;
    }
    setPositionIsHeaderFooter(paramInt);
  }

  protected void onChildrenDetached(int paramInt1, int paramInt2)
  {
    super.onChildrenDetached(paramInt1, paramInt2);
    Arrays.fill(this.mColumnTops, 2147483647);
    Arrays.fill(this.mColumnBottoms, 0);
    paramInt1 = 0;
    if (paramInt1 >= getChildCount())
      return;
    View localView = getChildAt(paramInt1);
    int i;
    int j;
    if (localView != null)
    {
      Object localObject = (ExtendableListView.LayoutParams)localView.getLayoutParams();
      if ((((ExtendableListView.LayoutParams)localObject).viewType == -2) || (!(localObject instanceof GridLayoutParams)))
        break label160;
      localObject = (GridLayoutParams)localObject;
      paramInt2 = ((GridLayoutParams)localObject).column;
      i = ((GridLayoutParams)localObject).position;
      j = localView.getTop();
      if (j < this.mColumnTops[paramInt2])
        this.mColumnTops[paramInt2] = (j - getChildTopMargin(i));
      i = localView.getBottom();
      if (i > this.mColumnBottoms[paramInt2])
        this.mColumnBottoms[paramInt2] = (getChildBottomMargin() + i);
    }
    while (true)
    {
      paramInt1 += 1;
      break;
      label160: i = localView.getTop();
      j = localView.getBottom();
      paramInt2 = 0;
      while (paramInt2 < this.mColumnCount)
      {
        if (i < this.mColumnTops[paramInt2])
          this.mColumnTops[paramInt2] = i;
        if (j > this.mColumnBottoms[paramInt2])
          this.mColumnBottoms[paramInt2] = j;
        paramInt2 += 1;
      }
    }
  }

  protected void onLayoutChild(View paramView, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (isHeaderOrFooter(paramInt1))
    {
      layoutGridHeaderFooter(paramView, paramInt1, paramBoolean, paramInt2, paramInt3, paramInt4, paramInt5);
      return;
    }
    layoutGridChild(paramView, paramInt1, paramBoolean, paramInt2, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mColumnCount <= 0)
    {
      if (getMeasuredWidth() <= getMeasuredHeight())
        break label152;
      paramInt1 = 1;
      if (paramInt1 == 0)
        break label157;
    }
    label152: label157: for (paramInt1 = this.mColumnCountLandscape; ; paramInt1 = this.mColumnCountPortrait)
    {
      this.mColumnCount = paramInt1;
      this.mColumnWidth = calculateColumnWidth(getMeasuredWidth());
      if ((this.mColumnTops == null) || (this.mColumnTops.length != this.mColumnCount))
      {
        this.mColumnTops = new int[this.mColumnCount];
        initColumnTops();
      }
      if ((this.mColumnBottoms == null) || (this.mColumnBottoms.length != this.mColumnCount))
      {
        this.mColumnBottoms = new int[this.mColumnCount];
        initColumnBottoms();
      }
      if ((this.mColumnLefts == null) || (this.mColumnLefts.length != this.mColumnCount))
      {
        this.mColumnLefts = new int[this.mColumnCount];
        initColumnLefts();
      }
      return;
      paramInt1 = 0;
      break;
    }
  }

  protected void onMeasureChild(View paramView, ExtendableListView.LayoutParams paramLayoutParams)
  {
    int i = paramLayoutParams.viewType;
    int j = paramLayoutParams.position;
    if ((i == -2) || (i == -1))
    {
      super.onMeasureChild(paramView, paramLayoutParams);
      setPositionHeightRatio(j, getChildHeight(paramView));
      return;
    }
    int k = View.MeasureSpec.makeMeasureSpec(this.mColumnWidth, 1073741824);
    if (paramLayoutParams.height > 0);
    for (i = View.MeasureSpec.makeMeasureSpec(paramLayoutParams.height, 1073741824); ; i = View.MeasureSpec.makeMeasureSpec(-2, 0))
    {
      paramView.measure(k, i);
      break;
    }
  }

  protected void onOffsetChild(View paramView, int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3)
  {
    if (isHeaderOrFooter(paramInt1))
    {
      offsetGridHeaderFooter(paramView, paramInt1, paramBoolean, paramInt2, paramInt3);
      return;
    }
    offsetGridChild(paramView, paramInt1, paramBoolean, paramInt2, paramInt3);
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (GridListSavedState)paramParcelable;
    this.mColumnCount = paramParcelable.columnCount;
    this.mColumnTops = paramParcelable.columnTops;
    this.mColumnBottoms = new int[this.mColumnCount];
    this.mPositionData = paramParcelable.positionData;
    this.mNeedSync = true;
    super.onRestoreInstanceState(paramParcelable);
  }

  public Parcelable onSaveInstanceState()
  {
    int j = 0;
    ExtendableListView.ListSavedState localListSavedState = (ExtendableListView.ListSavedState)super.onSaveInstanceState();
    GridListSavedState localGridListSavedState = new GridListSavedState(localListSavedState.getSuperState());
    localGridListSavedState.selectedId = localListSavedState.selectedId;
    localGridListSavedState.firstId = localListSavedState.firstId;
    localGridListSavedState.viewTop = localListSavedState.viewTop;
    localGridListSavedState.position = localListSavedState.position;
    localGridListSavedState.height = localListSavedState.height;
    if ((getChildCount() > 0) && (getCount() > 0));
    for (int i = 1; (i != 0) && (this.mFirstPosition > 0); i = 0)
    {
      localGridListSavedState.columnCount = this.mColumnCount;
      localGridListSavedState.columnTops = this.mColumnTops;
      localGridListSavedState.positionData = this.mPositionData;
      return localGridListSavedState;
    }
    i = j;
    if (this.mColumnCount >= 0)
      i = this.mColumnCount;
    localGridListSavedState.columnCount = i;
    localGridListSavedState.columnTops = new int[localGridListSavedState.columnCount];
    localGridListSavedState.positionData = new SparseArray();
    return localGridListSavedState;
  }

  protected void onSizeChanged(int paramInt1, int paramInt2)
  {
    super.onSizeChanged(paramInt1, paramInt2);
    if (paramInt1 > paramInt2)
    {
      paramInt2 = 1;
      if (paramInt2 == 0)
        break label118;
    }
    label118: for (paramInt2 = this.mColumnCountLandscape; ; paramInt2 = this.mColumnCountPortrait)
    {
      if (this.mColumnCount != paramInt2)
      {
        this.mColumnCount = paramInt2;
        this.mColumnWidth = calculateColumnWidth(paramInt1);
        this.mColumnTops = new int[this.mColumnCount];
        this.mColumnBottoms = new int[this.mColumnCount];
        this.mColumnLefts = new int[this.mColumnCount];
        this.mDistanceToTop = 0;
        initColumnTopsAndBottoms();
        initColumnLefts();
        if ((getCount() > 0) && (this.mPositionData.size() > 0))
          onColumnSync();
        requestLayout();
      }
      return;
      paramInt2 = 0;
      break;
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    onSizeChanged(paramInt1, paramInt2);
  }

  public void resetToTop()
  {
    if (this.mColumnCount > 0)
    {
      if (this.mColumnTops == null)
        this.mColumnTops = new int[this.mColumnCount];
      if (this.mColumnBottoms == null)
        this.mColumnBottoms = new int[this.mColumnCount];
      initColumnTopsAndBottoms();
      this.mPositionData.clear();
      this.mNeedSync = false;
      this.mDistanceToTop = 0;
      setSelection(0);
    }
  }

  public void setColumnCount(int paramInt)
  {
    this.mColumnCountPortrait = paramInt;
    this.mColumnCountLandscape = paramInt;
    onSizeChanged(getWidth(), getHeight());
    requestLayoutChildren();
  }

  public void setColumnCountLandscape(int paramInt)
  {
    this.mColumnCountLandscape = paramInt;
    onSizeChanged(getWidth(), getHeight());
    requestLayoutChildren();
  }

  public void setColumnCountPortrait(int paramInt)
  {
    this.mColumnCountPortrait = paramInt;
    onSizeChanged(getWidth(), getHeight());
    requestLayoutChildren();
  }

  public void setGridPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mGridPaddingLeft = paramInt1;
    this.mGridPaddingTop = paramInt2;
    this.mGridPaddingRight = paramInt3;
    this.mGridPaddingBottom = paramInt4;
  }

  static class GridItemRecord
    implements Parcelable
  {
    public static final Parcelable.Creator<GridItemRecord> CREATOR = new Parcelable.Creator()
    {
      public StaggeredGridView.GridItemRecord createFromParcel(Parcel paramAnonymousParcel)
      {
        return new StaggeredGridView.GridItemRecord(paramAnonymousParcel, null);
      }

      public StaggeredGridView.GridItemRecord[] newArray(int paramAnonymousInt)
      {
        return new StaggeredGridView.GridItemRecord[paramAnonymousInt];
      }
    };
    int column;
    double heightRatio;
    boolean isHeaderFooter;

    GridItemRecord()
    {
    }

    private GridItemRecord(Parcel paramParcel)
    {
      this.column = paramParcel.readInt();
      this.heightRatio = paramParcel.readDouble();
      if (paramParcel.readByte() == 1);
      while (true)
      {
        this.isHeaderFooter = bool;
        return;
        bool = false;
      }
    }

    public int describeContents()
    {
      return 0;
    }

    public String toString()
    {
      return "GridItemRecord.ListSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " column:" + this.column + " heightRatio:" + this.heightRatio + " isHeaderFooter:" + this.isHeaderFooter + "}";
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(this.column);
      paramParcel.writeDouble(this.heightRatio);
      if (this.isHeaderFooter);
      for (paramInt = 1; ; paramInt = 0)
      {
        paramParcel.writeByte((byte)paramInt);
        return;
      }
    }
  }

  public static class GridLayoutParams extends ExtendableListView.LayoutParams
  {
    int column;

    public GridLayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
      enforceStaggeredLayout();
    }

    public GridLayoutParams(int paramInt1, int paramInt2, int paramInt3)
    {
      super(paramInt2);
      enforceStaggeredLayout();
    }

    public GridLayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      enforceStaggeredLayout();
    }

    public GridLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
      enforceStaggeredLayout();
    }

    private void enforceStaggeredLayout()
    {
      if (this.width != -1)
        this.width = -1;
      if (this.height == -1)
        this.height = -2;
    }
  }

  public static class GridListSavedState extends ExtendableListView.ListSavedState
  {
    public static final Parcelable.Creator<GridListSavedState> CREATOR = new Parcelable.Creator()
    {
      public StaggeredGridView.GridListSavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new StaggeredGridView.GridListSavedState(paramAnonymousParcel);
      }

      public StaggeredGridView.GridListSavedState[] newArray(int paramAnonymousInt)
      {
        return new StaggeredGridView.GridListSavedState[paramAnonymousInt];
      }
    };
    int columnCount;
    int[] columnTops;
    SparseArray positionData;

    public GridListSavedState(Parcel paramParcel)
    {
      super();
      this.columnCount = paramParcel.readInt();
      if (this.columnCount >= 0);
      for (int i = this.columnCount; ; i = 0)
      {
        this.columnTops = new int[i];
        paramParcel.readIntArray(this.columnTops);
        this.positionData = paramParcel.readSparseArray(StaggeredGridView.GridItemRecord.class.getClassLoader());
        return;
      }
    }

    public GridListSavedState(Parcelable paramParcelable)
    {
      super();
    }

    public String toString()
    {
      return "StaggeredGridView.GridListSavedState{" + Integer.toHexString(System.identityHashCode(this)) + "}";
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.columnCount);
      paramParcel.writeIntArray(this.columnTops);
      paramParcel.writeSparseArray(this.positionData);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.StaggeredGridView
 * JD-Core Version:    0.6.2
 */