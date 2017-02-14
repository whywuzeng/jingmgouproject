package com.ab.view.carousel;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;
import java.util.Collections;

public abstract class CarouselSpinner extends CarouselAdapter<SpinnerAdapter>
{
  SpinnerAdapter mAdapter;
  boolean mBlockLayoutRequests;
  private DataSetObserver mDataSetObserver;
  int mHeightMeasureSpec;
  final RecycleBin mRecycler = new RecycleBin();
  int mSelectionBottomPadding = 0;
  int mSelectionLeftPadding = 0;
  int mSelectionRightPadding = 0;
  int mSelectionTopPadding = 0;
  final Rect mSpinnerPadding = new Rect();
  int mWidthMeasureSpec;

  public CarouselSpinner(Context paramContext)
  {
    super(paramContext);
    initCarouselSpinner();
  }

  public CarouselSpinner(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public CarouselSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initCarouselSpinner();
  }

  private void initCarouselSpinner()
  {
    setFocusable(true);
    setWillNotDraw(false);
  }

  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new ViewGroup.LayoutParams(-2, -2);
  }

  public SpinnerAdapter getAdapter()
  {
    return this.mAdapter;
  }

  int getChildHeight(View paramView)
  {
    return paramView.getMeasuredHeight();
  }

  int getChildWidth(View paramView)
  {
    return paramView.getMeasuredWidth();
  }

  public int getCount()
  {
    return this.mItemCount;
  }

  public View getSelectedView()
  {
    if ((this.mItemCount > 0) && (this.mSelectedPosition >= 0))
      return getChildAt(this.mSelectedPosition - this.mFirstPosition);
    return null;
  }

  abstract void layout(int paramInt, boolean paramBoolean);

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i2 = View.MeasureSpec.getMode(paramInt1);
    Object localObject = this.mSpinnerPadding;
    if (getPaddingLeft() > this.mSelectionLeftPadding)
    {
      i = getPaddingLeft();
      ((Rect)localObject).left = i;
      localObject = this.mSpinnerPadding;
      if (getPaddingTop() <= this.mSelectionTopPadding)
        break label474;
      i = getPaddingTop();
      label56: ((Rect)localObject).top = i;
      localObject = this.mSpinnerPadding;
      if (getPaddingRight() <= this.mSelectionRightPadding)
        break label482;
      i = getPaddingRight();
      label84: ((Rect)localObject).right = i;
      localObject = this.mSpinnerPadding;
      if (getPaddingBottom() <= this.mSelectionBottomPadding)
        break label490;
    }
    label474: label482: label490: for (int i = getPaddingBottom(); ; i = this.mSelectionBottomPadding)
    {
      ((Rect)localObject).bottom = i;
      if (this.mDataChanged)
        handleDataChanged();
      int m = 0;
      int n = 0;
      int i1 = 1;
      int i3 = getSelectedItemPosition();
      int k = i1;
      int j = m;
      i = n;
      if (i3 >= 0)
      {
        k = i1;
        j = m;
        i = n;
        if (this.mAdapter != null)
        {
          k = i1;
          j = m;
          i = n;
          if (i3 < this.mAdapter.getCount())
          {
            View localView = this.mRecycler.get(i3);
            localObject = localView;
            if (localView == null)
              localObject = this.mAdapter.getView(i3, null, this);
            if (localObject != null)
              this.mRecycler.put(i3, (View)localObject);
            k = i1;
            j = m;
            i = n;
            if (localObject != null)
            {
              if (((View)localObject).getLayoutParams() == null)
              {
                this.mBlockLayoutRequests = true;
                ((View)localObject).setLayoutParams(generateDefaultLayoutParams());
                this.mBlockLayoutRequests = false;
              }
              measureChild((View)localObject, paramInt1, paramInt2);
              j = getChildHeight((View)localObject) + this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
              i = getChildWidth((View)localObject) + this.mSpinnerPadding.left + this.mSpinnerPadding.right;
              k = 0;
            }
          }
        }
      }
      m = j;
      j = i;
      if (k != 0)
      {
        k = this.mSpinnerPadding.top + this.mSpinnerPadding.bottom;
        m = k;
        j = i;
        if (i2 == 0)
        {
          j = this.mSpinnerPadding.left + this.mSpinnerPadding.right;
          m = k;
        }
      }
      i = Math.max(m, getSuggestedMinimumHeight());
      j = Math.max(j, getSuggestedMinimumWidth());
      i = resolveSize(i, paramInt2);
      setMeasuredDimension(resolveSize(j, paramInt1), i);
      this.mHeightMeasureSpec = paramInt2;
      this.mWidthMeasureSpec = paramInt1;
      return;
      i = this.mSelectionLeftPadding;
      break;
      i = this.mSelectionTopPadding;
      break label56;
      i = this.mSelectionRightPadding;
      break label84;
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    if (paramParcelable.selectedId >= 0L)
    {
      this.mDataChanged = true;
      this.mNeedSync = true;
      this.mSyncRowId = paramParcelable.selectedId;
      this.mSyncPosition = paramParcelable.position;
      this.mSyncMode = 0;
      requestLayout();
    }
  }

  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.selectedId = getSelectedItemId();
    if (localSavedState.selectedId >= 0L)
    {
      localSavedState.position = getSelectedItemPosition();
      return localSavedState;
    }
    localSavedState.position = -1;
    return localSavedState;
  }

  public int pointToPositionImage(int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i >= this.mAdapter.getCount())
    {
      Collections.sort(localArrayList);
      if (localArrayList.size() != 0)
        return ((CarouselItemImage)localArrayList.get(0)).getIndex();
    }
    else
    {
      CarouselItemImage localCarouselItemImage = (CarouselItemImage)getChildAt(i);
      Matrix localMatrix = localCarouselItemImage.getCIMatrix();
      float[] arrayOfFloat = new float[3];
      arrayOfFloat[0] = localCarouselItemImage.getLeft();
      arrayOfFloat[1] = localCarouselItemImage.getTop();
      arrayOfFloat[2] = 0.0F;
      localMatrix.mapPoints(arrayOfFloat);
      int j = (int)arrayOfFloat[0];
      int k = (int)arrayOfFloat[1];
      arrayOfFloat[0] = localCarouselItemImage.getRight();
      arrayOfFloat[1] = localCarouselItemImage.getBottom();
      arrayOfFloat[2] = 0.0F;
      localMatrix.mapPoints(arrayOfFloat);
      int n = (int)arrayOfFloat[0];
      int m = (int)arrayOfFloat[1];
      if (j < paramInt1)
      {
        if (n <= paramInt1)
          break label217;
        j = 1;
        label179: if (k >= paramInt2)
          break label223;
      }
      label217: label223: for (k = 1; ; k = 0)
      {
        if (((k & j) != 0) && (m > paramInt2))
          localArrayList.add(localCarouselItemImage);
        i += 1;
        break;
        j = 0;
        break label179;
      }
    }
    return this.mSelectedPosition;
  }

  public int pointToPositionView(int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i >= this.mAdapter.getCount())
    {
      Collections.sort(localArrayList);
      if (localArrayList.size() != 0)
        return ((CarouselItemView)localArrayList.get(0)).getIndex();
    }
    else
    {
      CarouselItemView localCarouselItemView = (CarouselItemView)getChildAt(i);
      Matrix localMatrix = localCarouselItemView.getCIMatrix();
      float[] arrayOfFloat = new float[3];
      arrayOfFloat[0] = localCarouselItemView.getLeft();
      arrayOfFloat[1] = localCarouselItemView.getTop();
      arrayOfFloat[2] = 0.0F;
      localMatrix.mapPoints(arrayOfFloat);
      int j = (int)arrayOfFloat[0];
      int k = (int)arrayOfFloat[1];
      arrayOfFloat[0] = localCarouselItemView.getRight();
      arrayOfFloat[1] = localCarouselItemView.getBottom();
      arrayOfFloat[2] = 0.0F;
      localMatrix.mapPoints(arrayOfFloat);
      int n = (int)arrayOfFloat[0];
      int m = (int)arrayOfFloat[1];
      if (j < paramInt1)
      {
        if (n <= paramInt1)
          break label217;
        j = 1;
        label179: if (k >= paramInt2)
          break label223;
      }
      label217: label223: for (k = 1; ; k = 0)
      {
        if (((k & j) != 0) && (m > paramInt2))
          localArrayList.add(localCarouselItemView);
        i += 1;
        break;
        j = 0;
        break label179;
      }
    }
    return this.mSelectedPosition;
  }

  void recycleAllViews()
  {
    int j = getChildCount();
    RecycleBin localRecycleBin = this.mRecycler;
    int k = this.mFirstPosition;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      localRecycleBin.put(k + i, getChildAt(i));
      i += 1;
    }
  }

  public void requestLayout()
  {
    if (!this.mBlockLayoutRequests)
      super.requestLayout();
  }

  void resetList()
  {
    this.mDataChanged = false;
    this.mNeedSync = false;
    removeAllViewsInLayout();
    this.mOldSelectedPosition = -1;
    this.mOldSelectedRowId = -9223372036854775808L;
    setSelectedPositionInt(-1);
    setNextSelectedPositionInt(-1);
    invalidate();
  }

  public void setAdapter(SpinnerAdapter paramSpinnerAdapter)
  {
    int i = -1;
    if (this.mAdapter != null)
    {
      this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
      resetList();
    }
    this.mAdapter = paramSpinnerAdapter;
    this.mOldSelectedPosition = -1;
    this.mOldSelectedRowId = -9223372036854775808L;
    if (this.mAdapter != null)
    {
      this.mOldItemCount = this.mItemCount;
      this.mItemCount = this.mAdapter.getCount();
      checkFocus();
      this.mDataSetObserver = new CarouselAdapter.AdapterDataSetObserver(this);
      this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
      if (this.mItemCount > 0)
        i = 0;
      setSelectedPositionInt(i);
      setNextSelectedPositionInt(i);
      if (this.mItemCount == 0)
        checkSelectionChanged();
    }
    while (true)
    {
      requestLayout();
      return;
      checkFocus();
      resetList();
      checkSelectionChanged();
    }
  }

  public void setSelection(int paramInt)
  {
    setSelectionInt(paramInt, false);
  }

  public void setSelection(int paramInt, boolean paramBoolean)
  {
    if ((paramBoolean) && (this.mFirstPosition <= paramInt) && (paramInt <= this.mFirstPosition + getChildCount() - 1));
    for (paramBoolean = true; ; paramBoolean = false)
    {
      setSelectionInt(paramInt, paramBoolean);
      return;
    }
  }

  void setSelectionInt(int paramInt, boolean paramBoolean)
  {
    if (paramInt != this.mOldSelectedPosition)
    {
      this.mBlockLayoutRequests = true;
      int i = this.mSelectedPosition;
      setNextSelectedPositionInt(paramInt);
      layout(paramInt - i, paramBoolean);
      this.mBlockLayoutRequests = false;
    }
  }

  class RecycleBin
  {
    private final SparseArray<View> mScrapHeap = new SparseArray();

    RecycleBin()
    {
    }

    void clear()
    {
      SparseArray localSparseArray = this.mScrapHeap;
      int j = localSparseArray.size();
      int i = 0;
      while (true)
      {
        if (i >= j)
        {
          localSparseArray.clear();
          return;
        }
        View localView = (View)localSparseArray.valueAt(i);
        if (localView != null)
          CarouselSpinner.this.removeDetachedView(localView, true);
        i += 1;
      }
    }

    View get(int paramInt)
    {
      View localView = (View)this.mScrapHeap.get(paramInt);
      if (localView != null)
        this.mScrapHeap.delete(paramInt);
      return localView;
    }

    public void put(int paramInt, View paramView)
    {
      this.mScrapHeap.put(paramInt, paramView);
    }
  }

  static class SavedState extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator()
    {
      public CarouselSpinner.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new CarouselSpinner.SavedState(paramAnonymousParcel, null);
      }

      public CarouselSpinner.SavedState[] newArray(int paramAnonymousInt)
      {
        return new CarouselSpinner.SavedState[paramAnonymousInt];
      }
    };
    int position;
    long selectedId;

    private SavedState(Parcel paramParcel)
    {
      super();
      this.selectedId = paramParcel.readLong();
      this.position = paramParcel.readInt();
    }

    SavedState(Parcelable paramParcelable)
    {
      super();
    }

    public String toString()
    {
      return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " selectedId=" + this.selectedId + " position=" + this.position + "}";
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeLong(this.selectedId);
      paramParcel.writeInt(this.position);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.carousel.CarouselSpinner
 * JD-Core Version:    0.6.2
 */