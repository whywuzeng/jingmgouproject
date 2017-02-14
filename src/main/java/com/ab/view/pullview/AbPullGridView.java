package com.ab.view.pullview;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Scroller;
import com.ab.view.listener.AbOnListViewListener;

public class AbPullGridView extends AbBaseGridView
  implements AbsListView.OnScrollListener, View.OnTouchListener
{
  private static final float OFFSET_RADIO = 1.8F;
  private static final int SCROLLBACK_HEADER = 0;
  private static final int SCROLL_DURATION = 200;
  private boolean childScrollState = false;
  private int count = 0;
  private BaseAdapter mAdapter = null;
  private boolean mEnablePullLoad = true;
  private boolean mEnablePullRefresh = true;
  private AbListViewFooter mFooterView;
  private GridView mGridView = null;
  private AbListViewHeader mHeaderView;
  private int mHeaderViewHeight;
  private float mLastY = -1.0F;
  private AbOnListViewListener mListViewListener = null;
  private boolean mPullLoading;
  private boolean mPullRefreshing = false;
  private int mScrollBack;
  private Scroller mScroller;
  private int mTotalItemCount;

  public AbPullGridView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public AbPullGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext, new DecelerateInterpolator());
    this.mHeaderView = new AbListViewHeader(paramContext);
    this.mHeaderViewHeight = this.mHeaderView.getHeaderHeight();
    this.mHeaderView.setGravity(80);
    addHeaderView(this.mHeaderView);
    this.mGridView = getGridView();
    this.mGridView.setCacheColorHint(paramContext.getResources().getColor(17170445));
    this.mGridView.setColumnWidth(150);
    this.mGridView.setGravity(17);
    this.mGridView.setHorizontalSpacing(5);
    this.mGridView.setNumColumns(-1);
    this.mGridView.setPadding(5, 5, 5, 5);
    this.mGridView.setStretchMode(2);
    this.mGridView.setVerticalSpacing(5);
    this.mGridView.setOnScrollListener(this);
    this.mGridView.setOnTouchListener(this);
    this.mFooterView = new AbListViewFooter(paramContext);
    addFooterView(this.mFooterView);
    setPullRefreshEnable(true);
    setPullLoadEnable(true);
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
      this.childScrollState = true;
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
      if (this.mHeaderView.getVisiableHeight() >= this.mHeaderViewHeight)
        this.mHeaderView.setState(1);
    }
    else
      return;
    this.mHeaderView.setState(0);
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

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mTotalItemCount = paramInt3;
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    switch (paramInt)
    {
    case 1:
    case 2:
    default:
    case 0:
    }
    do
    {
      return;
      if (paramAbsListView.getFirstVisiblePosition() == 0)
      {
        this.childScrollState = false;
        return;
      }
      if (paramAbsListView.getLastVisiblePosition() == paramAbsListView.getCount() - 1)
      {
        this.childScrollState = false;
        return;
      }
    }
    while (this.mPullRefreshing);
    this.childScrollState = true;
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    return onTouchEvent(paramMotionEvent);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.childScrollState)
      return false;
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
      if ((this.mEnablePullRefresh) && ((this.mHeaderView.getVisiableHeight() > 0) || (f > 0.0F)))
      {
        updateHeaderHeight(f / 1.8F);
      }
      else if ((this.mEnablePullLoad) && (!this.mPullLoading) && (this.mGridView.getLastVisiblePosition() == this.mTotalItemCount - 1) && (f < 0.0F))
      {
        startLoadMore();
        continue;
        this.mLastY = -1.0F;
        if ((this.mEnablePullRefresh) && (this.mHeaderView.getVisiableHeight() >= this.mHeaderViewHeight))
        {
          this.mPullRefreshing = true;
          this.mHeaderView.setState(2);
          if (this.mListViewListener != null)
            this.mListViewListener.onRefresh();
        }
        if (this.mEnablePullRefresh)
          resetHeaderHeight();
      }
    }
  }

  public void setAbOnListViewListener(AbOnListViewListener paramAbOnListViewListener)
  {
    this.mListViewListener = paramAbOnListViewListener;
  }

  public void setAdapter(BaseAdapter paramBaseAdapter)
  {
    this.mAdapter = paramBaseAdapter;
    this.mGridView.setAdapter(this.mAdapter);
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
    this.mFooterView.setState(1);
    this.mFooterView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbPullGridView.this.startLoadMore();
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
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.pullview.AbPullGridView
 * JD-Core Version:    0.6.2
 */