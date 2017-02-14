package com.ismartgo.pulltorefreshlistview.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class RefreshListView extends ListView
  implements AbsListView.OnScrollListener
{
  private static final String TAG = "RefreshListView";
  private final int DOWN_PULL_REFRESH = 0;
  private final int REFRESHING = 2;
  private final int REFRESHING_END = 3;
  private final int RELEASE_REFRESH = 1;
  private int currentState = 0;
  private Animation downAnimation;
  private int downY;
  private int firstVisibleItemPosition;
  private View footerView;
  private int footerViewHeight;
  private Handler handler = new Handler();
  private View headerView;
  private int headerViewHeight;
  private boolean isLoadingMore = false;
  private boolean isScrollToBottom;
  private ImageView ivArrow;
  private OnRefreshListener mOnRefershListener;
  private ProgressBar mProgressBar;
  private TextView tvLastUpdateTime;
  private TextView tvState;
  private Animation upAnimation;

  public RefreshListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initHeaderView();
    initFooterView();
    setOnScrollListener(this);
  }

  private String getLastUpdateTime()
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis()));
  }

  private void initAnimation()
  {
    this.upAnimation = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.upAnimation.setDuration(500L);
    this.upAnimation.setFillAfter(true);
    this.downAnimation = new RotateAnimation(-180.0F, -360.0F, 1, 0.5F, 1, 0.5F);
    this.downAnimation.setDuration(500L);
    this.downAnimation.setFillAfter(true);
  }

  private void initFooterView()
  {
    this.footerView = View.inflate(getContext(), 2130903197, null);
    this.footerViewHeight = this.footerView.getMeasuredHeight();
    this.footerView.setVisibility(8);
    addFooterView(this.footerView);
  }

  private void initHeaderView()
  {
    this.headerView = View.inflate(getContext(), 2130903198, null);
    this.ivArrow = ((ImageView)this.headerView.findViewById(2131231342));
    this.mProgressBar = ((ProgressBar)this.headerView.findViewById(2131231343));
    this.tvState = ((TextView)this.headerView.findViewById(2131231344));
    this.tvLastUpdateTime = ((TextView)this.headerView.findViewById(2131231345));
    this.tvLastUpdateTime.setText("最近更新：" + getLastUpdateTime());
    this.headerView.measure(0, 0);
    this.headerViewHeight = this.headerView.getMeasuredHeight();
    this.headerView.setPadding(0, -this.headerViewHeight, 0, 0);
    addHeaderView(this.headerView);
    initAnimation();
  }

  private void refreshHeaderView()
  {
    switch (this.currentState)
    {
    default:
      return;
    case 0:
      this.tvState.setText("下拉刷新");
      this.ivArrow.startAnimation(this.downAnimation);
      return;
    case 1:
      this.tvState.setText("松开刷新");
      this.ivArrow.startAnimation(this.upAnimation);
      return;
    case 2:
    }
    this.ivArrow.clearAnimation();
    this.ivArrow.setVisibility(8);
    this.mProgressBar.setVisibility(0);
    this.tvState.setText("正在刷新中...");
  }

  public void hideFooterView()
  {
    Log.i("RefreshListView", "隐藏footer view");
    this.footerView.setVisibility(8);
    this.isLoadingMore = false;
  }

  public void hideHeaderView()
  {
    this.ivArrow.setVisibility(8);
    this.mProgressBar.setVisibility(4);
    this.tvState.setText("刷新完成");
    new Timer().schedule(new TimerTask()
    {
      public void run()
      {
        RefreshListView.this.handler.post(new Runnable()
        {
          public void run()
          {
            RefreshListView.this.headerView.setPadding(0, -RefreshListView.this.headerViewHeight, 0, 0);
            RefreshListView.this.ivArrow.setVisibility(0);
            RefreshListView.this.mProgressBar.setVisibility(8);
            RefreshListView.this.tvState.setText("下拉刷新");
            RefreshListView.this.currentState = 0;
          }
        });
      }
    }
    , 1000L);
    this.tvLastUpdateTime.setText("最近更新：" + getLastUpdateTime());
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.firstVisibleItemPosition = paramInt1;
    if (getLastVisiblePosition() == paramInt3 - 1)
    {
      this.isScrollToBottom = true;
      return;
    }
    this.isScrollToBottom = false;
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    if (((paramInt == 0) || (paramInt == 2)) && (this.isScrollToBottom) && (!this.isLoadingMore) && (this.currentState != 2))
    {
      this.isLoadingMore = true;
      Log.i("RefreshListView", "加载更多数据");
      this.footerView.setVisibility(0);
      setSelection(getCount());
      if (this.mOnRefershListener != null)
        this.mOnRefershListener.onLoadingMore();
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
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
      this.downY = ((int)paramMotionEvent.getY());
      continue;
      int i = ((int)paramMotionEvent.getY() - this.downY) / 2;
      i = -this.headerViewHeight + i;
      if ((this.firstVisibleItemPosition == 0) && (-this.headerViewHeight < i))
      {
        if ((i > 0) && (this.currentState == 0))
        {
          Log.i("RefreshListView", "松开刷新");
          this.currentState = 1;
          refreshHeaderView();
        }
        while (true)
        {
          this.headerView.setPadding(0, i, 0, 0);
          return true;
          if ((i < 0) && (this.currentState == 1))
          {
            Log.i("RefreshListView", "下拉刷新");
            this.currentState = 0;
            refreshHeaderView();
          }
        }
        if (this.currentState == 1)
        {
          Log.i("RefreshListView", "刷新数据.");
          this.headerView.setPadding(0, 0, 0, 0);
          this.currentState = 2;
          refreshHeaderView();
          if (this.mOnRefershListener != null)
            this.mOnRefershListener.onDownPullRefresh();
        }
        else if (this.currentState == 0)
        {
          this.headerView.setPadding(0, -this.headerViewHeight, 0, 0);
        }
      }
    }
  }

  public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
  {
    this.mOnRefershListener = paramOnRefreshListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.pulltorefreshlistview.view.RefreshListView
 * JD-Core Version:    0.6.2
 */