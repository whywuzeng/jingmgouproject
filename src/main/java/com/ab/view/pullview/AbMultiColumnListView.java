package com.ab.view.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.Scroller;
import com.ab.view.listener.AbOnListViewListener;

public class AbMultiColumnListView extends AbMultiColumnBaseListView
  implements AbMultiColumnBaseAbsListView.OnScrollListener
{
  private static final float OFFSET_RADIO = 1.8F;
  private static final int SCROLLBACK_FOOTER = 1;
  private static final int SCROLLBACK_HEADER = 0;
  private static final int SCROLL_DURATION = 200;
  private int count = 0;
  private boolean mEnablePullLoad;
  private boolean mEnablePullRefresh = true;
  private AbListViewFooter mFooterView;
  private int mFooterViewHeight;
  private AbListViewHeader mHeaderView;
  private int mHeaderViewHeight;
  private boolean mIsFooterReady = false;
  private float mLastY = -1.0F;
  private AbOnListViewListener mListViewListener;
  private boolean mPullLoading;
  private boolean mPullRefreshing = false;
  private int mScrollBack;
  private AbMultiColumnBaseAbsListView.OnScrollListener mScrollListener;
  private Scroller mScroller;
  private int mTotalItemCount;

  public AbMultiColumnListView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public AbMultiColumnListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext, new DecelerateInterpolator());
    super.setOnScrollListener(this);
    this.mHeaderView = new AbListViewHeader(paramContext);
    this.mHeaderViewHeight = this.mHeaderView.getHeaderHeight();
    this.mHeaderView.setGravity(80);
    addHeaderView(this.mHeaderView);
    this.mFooterView = new AbListViewFooter(paramContext);
    this.mFooterViewHeight = this.mFooterView.getFooterHeight();
    this.mFooterView.hide();
  }

  private void resetHeaderHeight()
  {
    int i = this.mHeaderView.getVisiableHeight();
    if ((i < this.mHeaderViewHeight) || (!this.mPullRefreshing))
    {
      this.mScrollBack = 0;
      this.mScroller.startScroll(0, i, 0, i * -1, 200);
    }
    while (true)
    {
      invalidate();
      return;
      if ((i > this.mHeaderViewHeight) || (!this.mPullRefreshing))
      {
        this.mScrollBack = 0;
        this.mScroller.startScroll(0, i, 0, -(i - this.mHeaderViewHeight), 200);
      }
    }
  }

  private void startLoadMore()
  {
    this.mFooterView.show();
    this.mPullLoading = true;
    this.mFooterView.setState(2);
    if (this.mListViewListener != null)
      this.mListViewListener.onLoadMore();
  }

  private void updateHeaderHeight(float paramFloat)
  {
    int i = (int)paramFloat;
    int j = this.mHeaderView.getVisiableHeight();
    this.mHeaderView.setVisiableHeight(i + j);
    if ((this.mEnablePullRefresh) && (!this.mPullRefreshing))
    {
      if (this.mHeaderView.getVisiableHeight() < this.mHeaderViewHeight)
        break label63;
      this.mHeaderView.setState(1);
    }
    while (true)
    {
      setSelection(0);
      return;
      label63: this.mHeaderView.setState(0);
    }
  }

  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      if (this.mScrollBack == 0)
        this.mHeaderView.setVisiableHeight(this.mScroller.getCurrY());
      postInvalidate();
    }
    super.computeScroll();
  }

  public ProgressBar getFooterProgressBar()
  {
    return this.mFooterView.getFooterProgressBar();
  }

  public AbListViewFooter getFooterView()
  {
    return this.mFooterView;
  }

  public ProgressBar getHeaderProgressBar()
  {
    return this.mHeaderView.getHeaderProgressBar();
  }

  public AbListViewHeader getHeaderView()
  {
    return this.mHeaderView;
  }

  public void onScroll(AbMultiColumnBaseAbsListView paramAbMultiColumnBaseAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mTotalItemCount = paramInt3;
    if (this.mScrollListener != null)
      this.mScrollListener.onScroll(paramAbMultiColumnBaseAbsListView, paramInt1, paramInt2, paramInt3);
  }

  public void onScrollStateChanged(AbMultiColumnBaseAbsListView paramAbMultiColumnBaseAbsListView, int paramInt)
  {
    if (this.mScrollListener != null)
      this.mScrollListener.onScrollStateChanged(paramAbMultiColumnBaseAbsListView, paramInt);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mLastY == -1.0F)
      this.mLastY = paramMotionEvent.getRawY();
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      this.mLastY = paramMotionEvent.getRawY();
      continue;
      float f = paramMotionEvent.getRawY() - this.mLastY;
      this.mLastY = paramMotionEvent.getRawY();
      if ((getFirstVisiblePosition() == 0) && ((this.mHeaderView.getVisiableHeight() > 0) || (f > 0.0F)))
      {
        updateHeaderHeight(f / 1.8F);
      }
      else if ((this.mEnablePullLoad) && (!this.mPullLoading) && (getLastVisiblePosition() == this.mTotalItemCount - 1) && (f < 0.0F))
      {
        startLoadMore();
        continue;
        this.mLastY = -1.0F;
        if (getFirstVisiblePosition() == 0)
        {
          if ((this.mEnablePullRefresh) && (this.mHeaderView.getVisiableHeight() >= this.mHeaderViewHeight))
          {
            this.mPullRefreshing = true;
            this.mHeaderView.setState(2);
            if (this.mListViewListener != null)
              this.mListViewListener.onRefresh();
          }
          resetHeaderHeight();
        }
      }
    }
  }

  public void setAbOnListViewListener(AbOnListViewListener paramAbOnListViewListener)
  {
    this.mListViewListener = paramAbOnListViewListener;
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (!this.mIsFooterReady)
    {
      this.mIsFooterReady = true;
      this.mFooterView.setGravity(48);
      addFooterView(this.mFooterView);
    }
    super.setAdapter(paramListAdapter);
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
    this.mPullLoading = false;
    this.mFooterView.show();
    this.mFooterView.setState(1);
    this.mFooterView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbMultiColumnListView.this.startLoadMore();
      }
    });
  }

  public void setPullRefreshEnable(boolean paramBoolean)
  {
    this.mEnablePullRefresh = paramBoolean;
    if (!this.mEnablePullRefresh)
    {
      this.mHeaderView.setVisibility(4);
      return;
    }
    this.mHeaderView.setVisibility(0);
  }

  public void stopLoadMore()
  {
    this.mFooterView.hide();
    this.mPullLoading = false;
    if (this.mAdapter.getCount() > this.count)
    {
      this.mFooterView.setState(1);
      return;
    }
    this.mFooterView.setState(3);
  }

  public void stopRefresh()
  {
    if (this.mPullRefreshing)
    {
      this.mPullRefreshing = false;
      resetHeaderHeight();
    }
    if (this.mAdapter.getCount() > 0)
    {
      this.mFooterView.setState(1);
      return;
    }
    this.mFooterView.setState(4);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.pullview.AbMultiColumnListView
 * JD-Core Version:    0.6.2
 */