package com.huewu.pla.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ListAdapter;
import com.huewu.pla.lib.internal.PLA_ListView;
import com.ismartgo.apppub.R.styleable;

public class MultiColumnListView extends PLA_ListView
{
  private static final int DEFAULT_COLUMN_NUMBER = 2;
  private static final String TAG = "MultiColumnListView";
  private int columnWidth;
  private int mColumnNumber = 2;
  private int mColumnPaddingLeft = 0;
  private int mColumnPaddingRight = 0;
  private Column[] mColumns = null;
  private Column mFixedColumn = null;
  private Rect mFrameRect = new Rect();
  private SparseIntArray mItems = new SparseIntArray();

  public MultiColumnListView(Context paramContext)
  {
    super(paramContext);
    init(null);
  }

  public MultiColumnListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramAttributeSet);
  }

  public MultiColumnListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramAttributeSet);
  }

  private int getColumnLeft(int paramInt)
  {
    paramInt = this.mItems.get(paramInt, -1);
    if (paramInt == -1)
      return 0;
    return this.mColumns[paramInt].getColumnLeft();
  }

  private int getColumnWidth(int paramInt)
  {
    paramInt = this.mItems.get(paramInt, -1);
    if (paramInt == -1)
      return 0;
    return this.mColumns[paramInt].getColumnWidth();
  }

  private Column getNextColumn(boolean paramBoolean, int paramInt)
  {
    int i = this.mItems.get(paramInt, -1);
    if (i != -1)
      return this.mColumns[i];
    paramInt = Math.max(0, Math.max(0, paramInt - getHeaderViewsCount()));
    if (paramInt < getColumnNumber())
      return this.mColumns[paramInt];
    if (paramBoolean)
      return gettBottomColumn();
    return getTopColumn();
  }

  private Column getTopColumn()
  {
    int i = 0;
    Object localObject1 = this.mColumns[0];
    Column[] arrayOfColumn = this.mColumns;
    int j = arrayOfColumn.length;
    while (true)
    {
      if (i >= j)
        return localObject1;
      Column localColumn = arrayOfColumn[i];
      Object localObject2 = localObject1;
      if (((Column)localObject1).getTop() > localColumn.getTop())
        localObject2 = localColumn;
      i += 1;
      localObject1 = localObject2;
    }
  }

  private Column gettBottomColumn()
  {
    int i = 0;
    Object localObject1 = this.mColumns[0];
    Column[] arrayOfColumn = this.mColumns;
    int j = arrayOfColumn.length;
    while (true)
    {
      if (i >= j)
        return localObject1;
      Column localColumn = arrayOfColumn[i];
      Object localObject2 = localObject1;
      if (((Column)localObject1).getBottom() > localColumn.getBottom())
        localObject2 = localColumn;
      i += 1;
      localObject1 = localObject2;
    }
  }

  private void init(AttributeSet paramAttributeSet)
  {
    getWindowVisibleDisplayFrame(this.mFrameRect);
    int i;
    if (paramAttributeSet == null)
    {
      this.mColumnNumber = 2;
      this.mColumns = new Column[getColumnNumber()];
      i = 0;
    }
    while (true)
    {
      if (i >= getColumnNumber())
      {
        this.mFixedColumn = new FixedColumn();
        return;
        paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.PinterestLikeAdapterView);
        i = paramAttributeSet.getInteger(1, 3);
        int j = paramAttributeSet.getInteger(0, 2);
        if ((this.mFrameRect.width() > this.mFrameRect.height()) && (i != -1))
          this.mColumnNumber = i;
        while (true)
        {
          this.mColumnPaddingLeft = paramAttributeSet.getDimensionPixelSize(2, 0);
          this.mColumnPaddingRight = paramAttributeSet.getDimensionPixelSize(3, 0);
          paramAttributeSet.recycle();
          break;
          if (j != -1)
            this.mColumnNumber = j;
          else
            this.mColumnNumber = 2;
        }
      }
      this.mColumns[i] = new Column(i);
      i += 1;
    }
  }

  private boolean isHeaderOrFooterPosition(int paramInt)
  {
    return this.mAdapter.getItemViewType(paramInt) == -2;
  }

  public int getColumnNumber()
  {
    return this.mColumnNumber;
  }

  public int getColumnWidth()
  {
    return this.columnWidth;
  }

  protected int getFillChildBottom()
  {
    int j = 2147483647;
    Column[] arrayOfColumn = this.mColumns;
    int n = arrayOfColumn.length;
    int i = 0;
    while (true)
    {
      if (i >= n)
        return j;
      int m = arrayOfColumn[i].getBottom();
      int k = j;
      if (j > m)
        k = m;
      i += 1;
      j = k;
    }
  }

  protected int getFillChildTop()
  {
    int j = -2147483648;
    Column[] arrayOfColumn = this.mColumns;
    int n = arrayOfColumn.length;
    int i = 0;
    while (true)
    {
      if (i >= n)
        return j;
      int m = arrayOfColumn[i].getTop();
      int k = j;
      if (j < m)
        k = m;
      i += 1;
      j = k;
    }
  }

  protected int getItemBottom(int paramInt)
  {
    if (isHeaderOrFooterPosition(paramInt))
      return this.mFixedColumn.getTop();
    paramInt = this.mItems.get(paramInt, -1);
    if (paramInt == -1)
      return getFillChildTop();
    return this.mColumns[paramInt].getTop();
  }

  public int getItemColumnWidth()
  {
    return this.columnWidth;
  }

  protected int getItemLeft(int paramInt)
  {
    if (isHeaderOrFooterPosition(paramInt))
      return this.mFixedColumn.getColumnLeft();
    return getColumnLeft(paramInt);
  }

  protected int getItemTop(int paramInt)
  {
    if (isHeaderOrFooterPosition(paramInt))
      return this.mFixedColumn.getBottom();
    paramInt = this.mItems.get(paramInt, -1);
    if (paramInt == -1)
      return getFillChildBottom();
    return this.mColumns[paramInt].getBottom();
  }

  protected int getScrollChildBottom()
  {
    int j = -2147483648;
    Column[] arrayOfColumn = this.mColumns;
    int n = arrayOfColumn.length;
    int i = 0;
    while (true)
    {
      if (i >= n)
        return j;
      int m = arrayOfColumn[i].getBottom();
      int k = j;
      if (j < m)
        k = m;
      i += 1;
      j = k;
    }
  }

  protected int getScrollChildTop()
  {
    int j = 2147483647;
    Column[] arrayOfColumn = this.mColumns;
    int n = arrayOfColumn.length;
    int i = 0;
    while (true)
    {
      if (i >= n)
        return j;
      int m = arrayOfColumn[i].getTop();
      int k = j;
      if (j > m)
        k = m;
      i += 1;
      j = k;
    }
  }

  protected int modifyFlingInitialVelocity(int paramInt)
  {
    return paramInt / getColumnNumber();
  }

  protected void onAdjustChildViews(boolean paramBoolean)
  {
    int i = 0;
    int j = getFirstVisiblePosition();
    Column[] arrayOfColumn;
    int k;
    if ((!paramBoolean) && (j == 0))
    {
      j = this.mColumns[0].getTop();
      arrayOfColumn = this.mColumns;
      k = arrayOfColumn.length;
    }
    while (true)
    {
      if (i >= k)
      {
        super.onAdjustChildViews(paramBoolean);
        return;
      }
      Column localColumn = arrayOfColumn[i];
      localColumn.offsetTopAndBottom(j - localColumn.getTop());
      i += 1;
    }
  }

  protected void onItemAddedToList(int paramInt, boolean paramBoolean)
  {
    super.onItemAddedToList(paramInt, paramBoolean);
    if (!isHeaderOrFooterPosition(paramInt))
    {
      Column localColumn = getNextColumn(paramBoolean, paramInt);
      this.mItems.append(paramInt, localColumn.getIndex());
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onLayoutSync(int paramInt)
  {
    Column[] arrayOfColumn = this.mColumns;
    int i = arrayOfColumn.length;
    paramInt = 0;
    while (true)
    {
      if (paramInt >= i)
        return;
      arrayOfColumn[paramInt].save();
      paramInt += 1;
    }
  }

  protected void onLayoutSyncFinished(int paramInt)
  {
    Column[] arrayOfColumn = this.mColumns;
    int i = arrayOfColumn.length;
    paramInt = 0;
    while (true)
    {
      if (paramInt >= i)
        return;
      arrayOfColumn[paramInt].clear();
      paramInt += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    this.columnWidth = ((getMeasuredWidth() - this.mListPadding.left - this.mListPadding.right - this.mColumnPaddingLeft * 3) / this.mColumnNumber);
    paramInt1 = 0;
    if (paramInt1 >= this.mColumnNumber)
    {
      this.mFixedColumn.mColumnLeft = this.mListPadding.left;
      this.mFixedColumn.mColumnWidth = getMeasuredWidth();
      return;
    }
    this.mColumns[paramInt1].mColumnWidth = this.columnWidth;
    if (paramInt1 == 0);
    for (this.mColumns[paramInt1].mColumnLeft = (this.mListPadding.left + this.mColumnPaddingLeft + this.columnWidth * paramInt1); ; this.mColumns[paramInt1].mColumnLeft = (this.mColumnPaddingLeft + this.mListPadding.left + this.mColumnPaddingLeft + this.columnWidth * paramInt1))
    {
      paramInt1 += 1;
      break;
    }
  }

  protected void onMeasureChild(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    if (isFixedView(paramView))
    {
      paramView.measure(paramInt2, paramInt3);
      return;
    }
    paramView.measure(0x40000000 | getColumnWidth(paramInt1), paramInt3);
  }

  private class Column
  {
    private int mColumnLeft;
    private int mColumnWidth;
    private int mIndex;
    private int mSynchedBottom = 0;
    private int mSynchedTop = 0;

    public Column(int arg2)
    {
      int i;
      this.mIndex = i;
    }

    public void clear()
    {
      this.mSynchedTop = 0;
      this.mSynchedBottom = 0;
    }

    public int getBottom()
    {
      int i = -2147483648;
      int m = MultiColumnListView.this.getChildCount();
      int k = 0;
      int j;
      if (k >= m)
      {
        j = i;
        if (i == -2147483648)
          j = this.mSynchedBottom;
        return j;
      }
      View localView = MultiColumnListView.this.getChildAt(k);
      if ((localView.getLeft() != this.mColumnLeft) && (!MultiColumnListView.this.isFixedView(localView)));
      while (true)
      {
        k += 1;
        break;
        j = i;
        if (i < localView.getBottom())
          j = localView.getBottom();
        i = j;
      }
    }

    public int getColumnLeft()
    {
      return this.mColumnLeft;
    }

    public int getColumnWidth()
    {
      return this.mColumnWidth;
    }

    public int getIndex()
    {
      return this.mIndex;
    }

    public int getTop()
    {
      int i = 2147483647;
      int m = MultiColumnListView.this.getChildCount();
      int k = 0;
      int j;
      if (k >= m)
      {
        j = i;
        if (i == 2147483647)
          j = this.mSynchedTop;
        return j;
      }
      View localView = MultiColumnListView.this.getChildAt(k);
      if ((localView.getLeft() != this.mColumnLeft) && (!MultiColumnListView.this.isFixedView(localView)));
      while (true)
      {
        k += 1;
        break;
        j = i;
        if (i > localView.getTop())
          j = localView.getTop();
        i = j;
      }
    }

    public void offsetTopAndBottom(int paramInt)
    {
      if (paramInt == 0)
        return;
      int j = MultiColumnListView.this.getChildCount();
      int i = 0;
      label15: View localView;
      if (i < j)
      {
        localView = MultiColumnListView.this.getChildAt(i);
        if ((localView.getLeft() == this.mColumnLeft) || (MultiColumnListView.this.isFixedView(localView)))
          break label61;
      }
      while (true)
      {
        i += 1;
        break label15;
        break;
        label61: localView.offsetTopAndBottom(paramInt);
      }
    }

    public void save()
    {
      this.mSynchedTop = 0;
      this.mSynchedBottom = getTop();
    }
  }

  private class FixedColumn extends MultiColumnListView.Column
  {
    public FixedColumn()
    {
      super(2147483647);
    }

    public int getBottom()
    {
      return MultiColumnListView.this.getScrollChildBottom();
    }

    public int getTop()
    {
      return MultiColumnListView.this.getScrollChildTop();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.huewu.pla.lib.MultiColumnListView
 * JD-Core Version:    0.6.2
 */