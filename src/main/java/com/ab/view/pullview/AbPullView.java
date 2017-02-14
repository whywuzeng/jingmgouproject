package com.ab.view.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import com.ab.view.listener.AbOnRefreshListener;

public class AbPullView extends ScrollView
{
  private static final float OFFSET_RADIO = 1.8F;
  private static final int SCROLLBACK_HEADER = 0;
  private static final int SCROLL_DURATION = 400;
  private AbOnRefreshListener mAbOnRefreshListener = null;
  private boolean mEnablePullRefresh = true;
  private AbListViewHeader mHeaderView;
  private int mHeaderViewHeight;
  private float mLastY = -1.0F;
  private boolean mPullRefreshing = false;
  private int mScrollBack;
  private LinearLayout mScrollLayout;
  private Scroller mScroller;

  public AbPullView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public AbPullView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext, new DecelerateInterpolator());
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    this.mScrollLayout = new LinearLayout(paramContext);
    this.mScrollLayout.setLayoutParams(localLayoutParams);
    this.mScrollLayout.setOrientation(1);
    this.mHeaderView = new AbListViewHeader(paramContext);
    this.mHeaderViewHeight = this.mHeaderView.getHeaderHeight();
    this.mHeaderView.setGravity(80);
    this.mScrollLayout.addView(this.mHeaderView, localLayoutParams);
    addView(this.mScrollLayout);
  }

  private void resetHeaderHeight()
  {
    int i = this.mHeaderView.getVisiableHeight();
    if ((i < this.mHeaderViewHeight) || (!this.mPullRefreshing))
    {
      this.mScrollBack = 0;
      this.mScroller.startScroll(0, i, 0, i * -1, 400);
    }
    while (true)
    {
      invalidate();
      return;
      if ((i > this.mHeaderViewHeight) || (!this.mPullRefreshing))
      {
        this.mScrollBack = 0;
        this.mScroller.startScroll(0, i, 0, -(i - this.mHeaderViewHeight), 400);
      }
    }
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

  public void addChildView(View paramView)
  {
    this.mScrollLayout.addView(paramView);
  }

  public void addChildView(View paramView, int paramInt)
  {
    this.mScrollLayout.addView(paramView, paramInt);
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

  public ProgressBar getHeaderProgressBar()
  {
    return this.mHeaderView.getHeaderProgressBar();
  }

  public AbListViewHeader getHeaderView()
  {
    return this.mHeaderView;
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
      if ((this.mHeaderView.getVisiableHeight() > 0) || (f > 0.0F))
      {
        updateHeaderHeight(f / 1.8F);
        continue;
        this.mLastY = -1.0F;
        if ((this.mEnablePullRefresh) && (this.mHeaderView.getVisiableHeight() >= this.mHeaderViewHeight))
        {
          this.mPullRefreshing = true;
          this.mHeaderView.setState(2);
          if (this.mAbOnRefreshListener != null)
            this.mAbOnRefreshListener.onRefresh();
        }
        if (this.mEnablePullRefresh)
          resetHeaderHeight();
      }
    }
  }

  public void setAbOnRefreshListener(AbOnRefreshListener paramAbOnRefreshListener)
  {
    this.mAbOnRefreshListener = paramAbOnRefreshListener;
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
 * Qualified Name:     com.ab.view.pullview.AbPullView
 * JD-Core Version:    0.6.2
 */