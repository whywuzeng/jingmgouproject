package com.ismartgo.app.grid;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.Scroller;

public class SmartStraggeredGridView extends StaggeredGridView
  implements AbsListView.OnScrollListener
{
  private static final int OFFSET = 2;
  private static final float OFFSET_RADIO = 1.8F;
  private static final int PULL_LOAD_MORE_DELTA = 50;
  private static final int SCROLLBACK_FOOTER = 1;
  private static final int SCROLL_DURATION = 400;
  private int firstVisibleItem;
  private boolean isBusy;
  private boolean mEnablePullLoad;
  private XListViewFooter mFooterView;
  private boolean mIsFooterReady = false;
  private int mLastMotionX;
  private int mLastMotionY;
  private float mLastY = -1.0F;
  private IXListViewListener mListViewListener;
  private boolean mPullLoading;
  private int mScrollBack;
  private AbsListView.OnScrollListener mScrollListener;
  private Scroller mScroller;
  private int mTotalItemCount;
  private int mTouchSlop;
  private OnScrollState onScrollState;
  private int totalItemCount;
  private int visibleItemCount;
  private int visibleLastIndex = 0;

  public SmartStraggeredGridView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public SmartStraggeredGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public SmartStraggeredGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext, new DecelerateInterpolator());
    super.setOnScrollListener(this);
    this.mFooterView = new XListViewFooter(paramContext);
  }

  private void invokeOnScrolling()
  {
    if ((this.mScrollListener instanceof OnXScrollListener))
      ((OnXScrollListener)this.mScrollListener).onXScrolling(this);
  }

  private void resetFooterHeight()
  {
    int i = this.mFooterView.getBottomMargin();
    if (i > 0)
    {
      this.mScrollBack = 1;
      this.mScroller.startScroll(0, i, 0, -i, 400);
      invalidate();
    }
  }

  private void startLoadMore()
  {
    this.mPullLoading = true;
    this.mFooterView.setState(2);
    if (this.mListViewListener != null)
      this.mListViewListener.onLoadMore();
  }

  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      if (this.mScrollBack == 1)
        this.mFooterView.setBottomMargin(this.mScroller.getCurrY());
      postInvalidate();
      invokeOnScrolling();
    }
    super.computeScroll();
  }

  public boolean isBusy()
  {
    return this.isBusy;
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.visibleLastIndex = (paramInt1 + paramInt2 - 2);
    this.firstVisibleItem = paramInt1;
    this.visibleItemCount = paramInt2;
    this.totalItemCount = paramInt3;
    if (this.onScrollState != null)
      this.onScrollState.onScrollChange(paramAbsListView, paramInt1, paramInt2, paramInt3);
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    int i = getAdapter().getCount();
    if ((paramInt == 0) && (this.visibleLastIndex == i - 2))
    {
      this.isBusy = true;
      startLoadMore();
      resetFooterHeight();
    }
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (!this.mIsFooterReady)
    {
      this.mIsFooterReady = true;
      addFooterView(this.mFooterView);
    }
    super.setAdapter(paramListAdapter);
  }

  public void setBusy(boolean paramBoolean)
  {
    this.isBusy = paramBoolean;
  }

  public void setOnScrollState(OnScrollState paramOnScrollState)
  {
    this.onScrollState = paramOnScrollState;
  }

  public void setPullLoadEnable(boolean paramBoolean)
  {
    this.mEnablePullLoad = paramBoolean;
    if (!this.mEnablePullLoad)
    {
      this.mFooterView.hide();
      this.mFooterView.setOnClickListener(null);
      return;
    }
    this.mPullLoading = true;
    this.mFooterView.show();
    this.mFooterView.setState(1);
    this.mFooterView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SmartStraggeredGridView.this.startLoadMore();
      }
    });
  }

  public void setStateReady()
  {
    this.mFooterView.setState(1);
  }

  public void setStateRefresh()
  {
    if (this.mFooterView != null)
      this.mFooterView.setState(3);
  }

  public void setXListViewListener(IXListViewListener paramIXListViewListener)
  {
    this.mListViewListener = paramIXListViewListener;
  }

  public void stopLoadMore()
  {
    if (this.mPullLoading)
      this.mFooterView.setState(0);
    this.isBusy = false;
  }

  public static abstract interface IXListViewListener
  {
    public abstract void onLoadMore();

    public abstract void onRefresh();
  }

  public static abstract interface OnScrollState
  {
    public abstract void onScrollChange(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3);
  }

  public static abstract interface OnXScrollListener extends AbsListView.OnScrollListener
  {
    public abstract void onXScrolling(View paramView);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.SmartStraggeredGridView
 * JD-Core Version:    0.6.2
 */