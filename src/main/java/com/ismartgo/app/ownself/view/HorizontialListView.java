package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.Scroller;
import java.util.LinkedList;
import java.util.Queue;

public class HorizontialListView extends AdapterView<ListAdapter>
{
  protected ListAdapter mAdapter;
  public boolean mAlwaysOverrideTouch = true;
  protected int mCurrentX;
  private boolean mDataChanged = false;
  private DataSetObserver mDataObserver = new DataSetObserver()
  {
    public void onChanged()
    {
      synchronized (HorizontialListView.this)
      {
        HorizontialListView.this.mDataChanged = true;
        HorizontialListView.this.invalidate();
        HorizontialListView.this.requestLayout();
        return;
      }
    }

    public void onInvalidated()
    {
      HorizontialListView.this.reset();
      HorizontialListView.this.invalidate();
      HorizontialListView.this.requestLayout();
    }
  };
  private int mDisplayOffset = 0;
  private GestureDetector mGesture;
  private int mLeftViewIndex = -1;
  private int mMaxX = 2147483647;
  protected int mNextX;
  private GestureDetector.OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener()
  {
    private boolean isEventWithinView(MotionEvent paramAnonymousMotionEvent, View paramAnonymousView)
    {
      Rect localRect = new Rect();
      int[] arrayOfInt = new int[2];
      paramAnonymousView.getLocationOnScreen(arrayOfInt);
      int i = arrayOfInt[0];
      int j = paramAnonymousView.getWidth();
      int k = arrayOfInt[1];
      localRect.set(i, k, i + j, k + paramAnonymousView.getHeight());
      return localRect.contains((int)paramAnonymousMotionEvent.getRawX(), (int)paramAnonymousMotionEvent.getRawY());
    }

    public boolean onDown(MotionEvent paramAnonymousMotionEvent)
    {
      return HorizontialListView.this.onDown(paramAnonymousMotionEvent);
    }

    public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      return HorizontialListView.this.onFling(paramAnonymousMotionEvent1, paramAnonymousMotionEvent2, paramAnonymousFloat1, paramAnonymousFloat2);
    }

    public void onLongPress(MotionEvent paramAnonymousMotionEvent)
    {
      int j = HorizontialListView.this.getChildCount();
      int i = 0;
      while (true)
      {
        if (i >= j);
        View localView;
        do
        {
          return;
          localView = HorizontialListView.this.getChildAt(i);
          if (!isEventWithinView(paramAnonymousMotionEvent, localView))
            break;
        }
        while (HorizontialListView.this.mOnItemLongClicked == null);
        HorizontialListView.this.mOnItemLongClicked.onItemLongClick(HorizontialListView.this, localView, HorizontialListView.this.mLeftViewIndex + 1 + i, HorizontialListView.this.mAdapter.getItemId(HorizontialListView.this.mLeftViewIndex + 1 + i));
        return;
        i += 1;
      }
    }

    public boolean onScroll(MotionEvent arg1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      synchronized (HorizontialListView.this)
      {
        paramAnonymousMotionEvent2 = HorizontialListView.this;
        paramAnonymousMotionEvent2.mNextX += (int)paramAnonymousFloat1;
        HorizontialListView.this.requestLayout();
        return true;
      }
    }

    public boolean onSingleTapConfirmed(MotionEvent paramAnonymousMotionEvent)
    {
      int i = 0;
      while (true)
      {
        if (i >= HorizontialListView.this.getChildCount());
        while (true)
        {
          return true;
          View localView = HorizontialListView.this.getChildAt(i);
          if (!isEventWithinView(paramAnonymousMotionEvent, localView))
            break;
          if (HorizontialListView.this.mOnItemClicked != null)
            HorizontialListView.this.mOnItemClicked.onItemClick(HorizontialListView.this, localView, HorizontialListView.this.mLeftViewIndex + 1 + i, HorizontialListView.this.mAdapter.getItemId(HorizontialListView.this.mLeftViewIndex + 1 + i));
          if (HorizontialListView.this.mOnItemSelected != null)
            HorizontialListView.this.mOnItemSelected.onItemSelected(HorizontialListView.this, localView, HorizontialListView.this.mLeftViewIndex + 1 + i, HorizontialListView.this.mAdapter.getItemId(HorizontialListView.this.mLeftViewIndex + 1 + i));
        }
        i += 1;
      }
    }
  };
  private AdapterView.OnItemClickListener mOnItemClicked;
  private AdapterView.OnItemLongClickListener mOnItemLongClicked;
  private AdapterView.OnItemSelectedListener mOnItemSelected;
  private Queue<View> mRemovedViewQueue = new LinkedList();
  private int mRightViewIndex = 0;
  protected Scroller mScroller;

  public HorizontialListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView();
  }

  private void addAndMeasureChild(View paramView, int paramInt)
  {
    ViewGroup.LayoutParams localLayoutParams2 = paramView.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null)
      localLayoutParams1 = new ViewGroup.LayoutParams(-1, -1);
    addViewInLayout(paramView, paramInt, localLayoutParams1, true);
    paramView.measure(View.MeasureSpec.makeMeasureSpec(getWidth(), -2147483648), View.MeasureSpec.makeMeasureSpec(getHeight(), -2147483648));
  }

  private void fillList(int paramInt)
  {
    int i = 0;
    View localView = getChildAt(getChildCount() - 1);
    if (localView != null)
      i = localView.getRight();
    fillListRight(i, paramInt);
    i = 0;
    localView = getChildAt(0);
    if (localView != null)
      i = localView.getLeft();
    fillListLeft(i, paramInt);
  }

  private void fillListLeft(int paramInt1, int paramInt2)
  {
    while (true)
    {
      if ((paramInt1 + paramInt2 <= 0) || (this.mLeftViewIndex < 0))
        return;
      View localView = this.mAdapter.getView(this.mLeftViewIndex, (View)this.mRemovedViewQueue.poll(), this);
      addAndMeasureChild(localView, 0);
      paramInt1 -= localView.getMeasuredWidth();
      this.mLeftViewIndex -= 1;
      this.mDisplayOffset -= localView.getMeasuredWidth();
    }
  }

  private void fillListRight(int paramInt1, int paramInt2)
  {
    while (true)
    {
      if ((paramInt1 + paramInt2 >= getWidth()) || (this.mRightViewIndex >= this.mAdapter.getCount()))
        return;
      View localView = this.mAdapter.getView(this.mRightViewIndex, (View)this.mRemovedViewQueue.poll(), this);
      addAndMeasureChild(localView, -1);
      paramInt1 += localView.getMeasuredWidth();
      if (this.mRightViewIndex == this.mAdapter.getCount() - 1)
        this.mMaxX = (this.mCurrentX + paramInt1 - getWidth());
      if (this.mMaxX < 0)
        this.mMaxX = 0;
      this.mRightViewIndex += 1;
    }
  }

  private void initView()
  {
    try
    {
      this.mLeftViewIndex = -1;
      this.mRightViewIndex = 0;
      this.mDisplayOffset = 0;
      this.mCurrentX = 0;
      this.mNextX = 0;
      this.mMaxX = 2147483647;
      this.mScroller = new Scroller(getContext());
      this.mGesture = new GestureDetector(getContext(), this.mOnGesture);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void positionItems(int paramInt)
  {
    int i;
    if (getChildCount() > 0)
    {
      this.mDisplayOffset += paramInt;
      i = this.mDisplayOffset;
      paramInt = 0;
    }
    while (true)
    {
      if (paramInt >= getChildCount())
        return;
      View localView = getChildAt(paramInt);
      int j = localView.getMeasuredWidth();
      localView.layout(i, 0, i + j, localView.getMeasuredHeight());
      i += localView.getPaddingRight() + j;
      paramInt += 1;
    }
  }

  private void removeNonVisibleItems(int paramInt)
  {
    View localView = getChildAt(0);
    if ((localView == null) || (localView.getRight() + paramInt > 0));
    for (localView = getChildAt(getChildCount() - 1); ; localView = getChildAt(getChildCount() - 1))
    {
      if ((localView == null) || (localView.getLeft() + paramInt < getWidth()))
      {
        return;
        this.mDisplayOffset += localView.getMeasuredWidth();
        this.mRemovedViewQueue.offer(localView);
        removeViewInLayout(localView);
        this.mLeftViewIndex += 1;
        localView = getChildAt(0);
        break;
      }
      this.mRemovedViewQueue.offer(localView);
      removeViewInLayout(localView);
      this.mRightViewIndex -= 1;
    }
  }

  private void reset()
  {
    try
    {
      initView();
      removeAllViewsInLayout();
      requestLayout();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.dispatchTouchEvent(paramMotionEvent) | this.mGesture.onTouchEvent(paramMotionEvent);
  }

  public ListAdapter getAdapter()
  {
    return this.mAdapter;
  }

  public View getSelectedView()
  {
    return null;
  }

  protected boolean onDown(MotionEvent paramMotionEvent)
  {
    this.mScroller.forceFinished(true);
    return true;
  }

  protected boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    try
    {
      this.mScroller.fling(this.mNextX, 0, (int)-paramFloat1, 0, 0, this.mMaxX, 0, 0);
      requestLayout();
      return true;
    }
    finally
    {
    }
    throw paramMotionEvent1;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    try
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      ListAdapter localListAdapter = this.mAdapter;
      if (localListAdapter == null);
      while (true)
      {
        return;
        if (this.mDataChanged)
        {
          paramInt1 = this.mCurrentX;
          initView();
          removeAllViewsInLayout();
          this.mNextX = paramInt1;
          this.mDataChanged = false;
        }
        if (this.mScroller.computeScrollOffset())
          this.mNextX = this.mScroller.getCurrX();
        if (this.mNextX <= 0)
        {
          this.mNextX = 0;
          this.mScroller.forceFinished(true);
        }
        if (this.mNextX >= this.mMaxX)
        {
          this.mNextX = this.mMaxX;
          this.mScroller.forceFinished(true);
        }
        paramInt1 = this.mCurrentX - this.mNextX;
        removeNonVisibleItems(paramInt1);
        fillList(paramInt1);
        positionItems(paramInt1);
        this.mCurrentX = this.mNextX;
        if (!this.mScroller.isFinished())
          post(new Runnable()
          {
            public void run()
            {
              HorizontialListView.this.requestLayout();
            }
          });
      }
    }
    finally
    {
    }
  }

  public void scrollTo(int paramInt)
  {
    try
    {
      this.mScroller.startScroll(this.mNextX, 0, paramInt - this.mNextX, 0);
      requestLayout();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (this.mAdapter != null)
      this.mAdapter.unregisterDataSetObserver(this.mDataObserver);
    this.mAdapter = paramListAdapter;
    this.mAdapter.registerDataSetObserver(this.mDataObserver);
    reset();
  }

  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.mOnItemClicked = paramOnItemClickListener;
  }

  public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener paramOnItemLongClickListener)
  {
    this.mOnItemLongClicked = paramOnItemLongClickListener;
  }

  public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener)
  {
    this.mOnItemSelected = paramOnItemSelectedListener;
  }

  public void setSelection(int paramInt)
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.HorizontialListView
 * JD-Core Version:    0.6.2
 */