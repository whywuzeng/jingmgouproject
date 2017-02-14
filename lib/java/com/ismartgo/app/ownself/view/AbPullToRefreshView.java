package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.ScrollView;

public class AbPullToRefreshView extends LinearLayout
{
  private static final int PULL_DOWN_STATE = 1;
  private static final int PULL_UP_STATE = 0;
  private AdapterView<?> mAdapterView;
  private Context mContext = null;
  private int mCount = 0;
  private boolean mEnableLoadMore = true;
  private boolean mEnablePullRefresh = true;
  private AbListViewFooter mFooterView;
  private int mFooterViewHeight;
  private AbListViewHeader mHeaderView;
  private int mHeaderViewHeight;
  private int mLastMotionX;
  private int mLastMotionY;
  private OnFooterLoadListener mOnFooterLoadListener;
  private OnHeaderRefreshListener mOnHeaderRefreshListener;
  private boolean mPullLoading = false;
  private boolean mPullRefreshing = false;
  private int mPullState;
  private ScrollView mScrollView;

  public AbPullToRefreshView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public AbPullToRefreshView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  private void addFooterView()
  {
    this.mFooterView = new AbListViewFooter(this.mContext);
    this.mFooterViewHeight = this.mFooterView.getFooterHeight();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, this.mFooterViewHeight);
    addView(this.mFooterView, localLayoutParams);
  }

  private void addHeaderView()
  {
    this.mHeaderView = new AbListViewHeader(this.mContext);
    this.mHeaderViewHeight = this.mHeaderView.getHeaderHeight();
    this.mHeaderView.setGravity(80);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, this.mHeaderViewHeight);
    localLayoutParams.topMargin = (-this.mHeaderViewHeight);
    addView(this.mHeaderView, localLayoutParams);
  }

  private void footerLoading()
  {
    this.mPullLoading = true;
    setHeaderTopMargin(-(this.mHeaderViewHeight + this.mFooterViewHeight));
    if (this.mOnFooterLoadListener != null)
      this.mOnFooterLoadListener.onFooterLoad(this);
  }

  private void footerPrepareToRefresh(int paramInt)
  {
    if ((this.mPullRefreshing) || (this.mPullLoading));
    do
    {
      return;
      paramInt = updateHeaderViewTopMargin(paramInt);
      if ((Math.abs(paramInt) >= this.mHeaderViewHeight + this.mFooterViewHeight) && (this.mFooterView.getState() != 2))
      {
        this.mFooterView.setState(1);
        return;
      }
    }
    while (Math.abs(paramInt) >= this.mHeaderViewHeight + this.mFooterViewHeight);
    this.mFooterView.setState(2);
  }

  private int getHeaderTopMargin()
  {
    return ((LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams()).topMargin;
  }

  private void headerPrepareToRefresh(int paramInt)
  {
    if ((this.mPullRefreshing) || (this.mPullLoading));
    do
    {
      return;
      paramInt = updateHeaderViewTopMargin(paramInt);
      if ((paramInt >= 0) && (this.mHeaderView.getState() != 2))
      {
        this.mHeaderView.setState(1);
        return;
      }
    }
    while ((paramInt >= 0) || (paramInt <= -this.mHeaderViewHeight));
    this.mHeaderView.setState(0);
  }

  private void init(Context paramContext)
  {
    this.mContext = paramContext;
    setOrientation(1);
    addHeaderView();
  }

  private void initContentAdapterView()
  {
    int j = getChildCount();
    if (j < 3)
      throw new IllegalArgumentException("this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
    int i = 0;
    while (true)
    {
      if (i >= j - 1)
      {
        if ((this.mAdapterView != null) || (this.mScrollView != null))
          break;
        throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
      }
      View localView = getChildAt(i);
      if ((localView instanceof AdapterView))
        this.mAdapterView = ((AdapterView)localView);
      if ((localView instanceof ScrollView))
        this.mScrollView = ((ScrollView)localView);
      i += 1;
    }
  }

  private boolean isRefreshViewScroll(int paramInt)
  {
    if ((this.mPullRefreshing) || (this.mPullLoading));
    View localView;
    label188: 
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
                return false;
                if (this.mAdapterView == null)
                  break label188;
                if (paramInt <= 0)
                  break;
              }
              while (!this.mEnablePullRefresh);
              localView = this.mAdapterView.getChildAt(0);
            }
            while (localView == null);
            if ((this.mAdapterView.getFirstVisiblePosition() == 0) && (localView.getTop() == 0))
            {
              this.mPullState = 1;
              return true;
            }
            int i = localView.getTop();
            int j = this.mAdapterView.getPaddingTop();
            if ((this.mAdapterView.getFirstVisiblePosition() != 0) || (Math.abs(i - j) > 11))
              break;
            this.mPullState = 1;
            return true;
            if (paramInt >= 0)
              break;
          }
          while (!this.mEnableLoadMore);
          localView = this.mAdapterView.getChildAt(this.mAdapterView.getChildCount() - 1);
        }
        while (localView == null);
        if ((localView.getBottom() <= getHeight()) && (this.mAdapterView.getLastVisiblePosition() == this.mAdapterView.getCount() - 1))
        {
          this.mPullState = 0;
          return true;
        }
      }
      while (this.mScrollView == null);
      localView = this.mScrollView.getChildAt(0);
      if ((paramInt > 0) && (this.mScrollView.getScrollY() == 0))
      {
        this.mPullState = 1;
        return true;
      }
    }
    while ((paramInt >= 0) || (localView.getMeasuredHeight() > getHeight() + this.mScrollView.getScrollY()));
    this.mPullState = 0;
    return true;
  }

  private void setHeaderTopMargin(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams();
    localLayoutParams.topMargin = paramInt;
    this.mHeaderView.setLayoutParams(localLayoutParams);
    invalidate();
  }

  private int updateHeaderViewTopMargin(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams();
    float f1 = localLayoutParams.topMargin;
    float f2 = paramInt;
    if ((paramInt > 0) && (this.mPullState == 0) && (Math.abs(localLayoutParams.topMargin) <= this.mHeaderViewHeight))
      return localLayoutParams.topMargin;
    if ((paramInt < 0) && (this.mPullState == 1) && (Math.abs(localLayoutParams.topMargin) >= this.mHeaderViewHeight))
      return localLayoutParams.topMargin;
    localLayoutParams.topMargin = ((int)(f1 + f2 * 0.3F));
    this.mHeaderView.setLayoutParams(localLayoutParams);
    invalidate();
    return localLayoutParams.topMargin;
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

  public void headerRefreshing()
  {
    this.mPullRefreshing = true;
    this.mHeaderView.setState(2);
    setHeaderTopMargin(0);
    if (this.mOnHeaderRefreshListener != null)
      this.mOnHeaderRefreshListener.onHeaderRefresh(this);
  }

  public boolean isEnableLoadMore()
  {
    return this.mEnableLoadMore;
  }

  public boolean isEnablePullRefresh()
  {
    return this.mEnablePullRefresh;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    addFooterView();
    initContentAdapterView();
  }

  public void onFooterLoadFinish()
  {
    setHeaderTopMargin(-this.mHeaderViewHeight);
    this.mHeaderView.setState(0);
    if (this.mAdapterView != null)
      if (this.mAdapterView.getCount() > this.mCount)
        this.mFooterView.setState(1);
    while (true)
    {
      this.mPullLoading = false;
      return;
      this.mFooterView.setState(3);
      continue;
      this.mFooterView.setState(1);
    }
  }

  public void onHeaderRefreshFinish()
  {
    setHeaderTopMargin(-this.mHeaderViewHeight);
    this.mHeaderView.setState(0);
    if (this.mAdapterView != null)
    {
      this.mCount = this.mAdapterView.getCount();
      if (this.mCount > 0)
        this.mFooterView.setState(1);
    }
    while (true)
    {
      this.mPullRefreshing = false;
      return;
      this.mFooterView.setState(4);
      continue;
      this.mFooterView.setState(1);
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int k = (int)paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    int j;
    do
    {
      while (true)
      {
        return false;
        this.mLastMotionX = i;
        this.mLastMotionY = k;
      }
      j = this.mLastMotionX;
      k -= this.mLastMotionY;
    }
    while ((Math.abs(i - j) >= Math.abs(k)) || (Math.abs(k) <= 10) || (!isRefreshViewScroll(k)));
    return true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    case 0:
    default:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      int j = i - this.mLastMotionY;
      if (this.mPullState == 1)
        headerPrepareToRefresh(j);
      while (true)
      {
        this.mLastMotionY = i;
        break;
        if (this.mPullState == 0)
          footerPrepareToRefresh(j);
      }
      i = getHeaderTopMargin();
      if (this.mPullState == 1)
      {
        if (i >= 0)
          headerRefreshing();
        else
          setHeaderTopMargin(-this.mHeaderViewHeight);
      }
      else if (this.mPullState == 0)
        if (Math.abs(i) >= this.mHeaderViewHeight + this.mFooterViewHeight)
          footerLoading();
        else
          setHeaderTopMargin(-this.mHeaderViewHeight);
    }
  }

  public void setLoadMoreEnable(boolean paramBoolean)
  {
    this.mEnableLoadMore = paramBoolean;
  }

  public void setOnFooterLoadListener(OnFooterLoadListener paramOnFooterLoadListener)
  {
    this.mOnFooterLoadListener = paramOnFooterLoadListener;
  }

  public void setOnHeaderRefreshListener(OnHeaderRefreshListener paramOnHeaderRefreshListener)
  {
    this.mOnHeaderRefreshListener = paramOnHeaderRefreshListener;
  }

  public void setPullRefreshEnable(boolean paramBoolean)
  {
    this.mEnablePullRefresh = paramBoolean;
  }

  public static abstract interface OnFooterLoadListener
  {
    public abstract void onFooterLoad(AbPullToRefreshView paramAbPullToRefreshView);
  }

  public static abstract interface OnHeaderRefreshListener
  {
    public abstract void onHeaderRefresh(AbPullToRefreshView paramAbPullToRefreshView);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.AbPullToRefreshView
 * JD-Core Version:    0.6.2
 */