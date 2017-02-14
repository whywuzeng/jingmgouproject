package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ismartgo.app.tools.TimeUtils;

public class AutoListView extends ListView
  implements AbsListView.OnScrollListener
{
  public static final int LOAD = 1;
  private static final int NONE = 0;
  private static final int PULL = 1;
  public static final int REFRESH = 0;
  private static final int REFRESHING = 3;
  private static final int RELEASE = 2;
  private static final int SPACE = 20;
  private RotateAnimation animation;
  private ImageView arrow;
  private int firstVisibleItem;
  private View footer;
  private View header;
  private int headerContentHeight;
  private int headerContentInitialHeight;
  private LayoutInflater inflater;
  private boolean isLoadFull;
  private boolean isLoading;
  private boolean isRecorded;
  private TextView lastUpdate;
  private boolean loadEnable = true;
  private TextView loadFull;
  private ProgressBar loading;
  private TextView more;
  private TextView noData;
  private OnLoadListener onLoadListener;
  private OnRefreshListener onRefreshListener;
  private int pageSize = 10;
  private ProgressBar refreshing;
  private RotateAnimation reverseAnimation;
  private int scrollState;
  private int startY;
  private int state;
  private TextView tip;

  public AutoListView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public AutoListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  public AutoListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initView(paramContext);
  }

  private void ifNeedLoad(AbsListView paramAbsListView, int paramInt)
  {
    if (!this.loadEnable);
    while (true)
    {
      return;
      if (paramInt == 0)
        try
        {
          if ((!this.isLoading) && (paramAbsListView.getLastVisiblePosition() == paramAbsListView.getPositionForView(this.footer)) && (!this.isLoadFull))
          {
            onLoad();
            this.isLoading = true;
            return;
          }
        }
        catch (Exception paramAbsListView)
        {
        }
    }
  }

  private void initView(Context paramContext)
  {
    this.animation = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.animation.setInterpolator(new LinearInterpolator());
    this.animation.setDuration(100L);
    this.animation.setFillAfter(true);
    this.reverseAnimation = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.reverseAnimation.setInterpolator(new LinearInterpolator());
    this.reverseAnimation.setDuration(100L);
    this.reverseAnimation.setFillAfter(true);
    this.inflater = LayoutInflater.from(paramContext);
    this.footer = this.inflater.inflate(2130903177, null);
    this.loadFull = ((TextView)this.footer.findViewById(2131231282));
    this.noData = ((TextView)this.footer.findViewById(2131231283));
    this.more = ((TextView)this.footer.findViewById(2131231284));
    this.loading = ((ProgressBar)this.footer.findViewById(2131231141));
    this.footer.setVisibility(8);
    this.header = this.inflater.inflate(2130903199, null);
    this.arrow = ((ImageView)this.header.findViewById(2131231350));
    this.tip = ((TextView)this.header.findViewById(2131231348));
    this.lastUpdate = ((TextView)this.header.findViewById(2131231349));
    this.refreshing = ((ProgressBar)this.header.findViewById(2131231347));
    this.headerContentInitialHeight = this.header.getPaddingTop();
    measureView(this.header);
    this.headerContentHeight = this.header.getMeasuredHeight();
    topPadding(-this.headerContentHeight);
    addHeaderView(this.header);
    addFooterView(this.footer);
    setOnScrollListener(this);
  }

  private void measureView(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams2 = paramView.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null)
      localLayoutParams1 = new ViewGroup.LayoutParams(-1, -2);
    int j = ViewGroup.getChildMeasureSpec(0, 0, localLayoutParams1.width);
    int i = localLayoutParams1.height;
    if (i > 0);
    for (i = View.MeasureSpec.makeMeasureSpec(i, 1073741824); ; i = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(j, i);
      return;
    }
  }

  private void refreshHeaderViewByState()
  {
    switch (this.state)
    {
    default:
      return;
    case 0:
      topPadding(-this.headerContentHeight);
      this.tip.setText(2131296268);
      this.refreshing.setVisibility(8);
      this.arrow.clearAnimation();
      this.arrow.setImageResource(2130837828);
      return;
    case 1:
      this.arrow.setVisibility(0);
      this.tip.setVisibility(0);
      this.lastUpdate.setVisibility(0);
      this.refreshing.setVisibility(8);
      this.tip.setText(2131296268);
      this.arrow.clearAnimation();
      this.arrow.setAnimation(this.reverseAnimation);
      return;
    case 2:
      this.arrow.setVisibility(0);
      this.tip.setVisibility(0);
      this.lastUpdate.setVisibility(0);
      this.refreshing.setVisibility(8);
      this.tip.setText(2131296268);
      this.tip.setText(2131296269);
      this.arrow.clearAnimation();
      this.arrow.setAnimation(this.animation);
      return;
    case 3:
    }
    topPadding(this.headerContentInitialHeight);
    this.refreshing.setVisibility(0);
    this.arrow.clearAnimation();
    this.arrow.setVisibility(8);
    this.tip.setVisibility(8);
    this.lastUpdate.setVisibility(8);
  }

  private void topPadding(int paramInt)
  {
    this.header.setPadding(this.header.getPaddingLeft(), paramInt, this.header.getPaddingRight(), this.header.getPaddingBottom());
    this.header.invalidate();
  }

  private void whenMove(MotionEvent paramMotionEvent)
  {
    if (!this.isRecorded);
    int i;
    do
    {
      int j;
      do
      {
        do
        {
          return;
          i = (int)paramMotionEvent.getY() - this.startY;
          j = i - this.headerContentHeight;
          switch (this.state)
          {
          default:
            return;
          case 0:
          case 1:
          case 2:
          }
        }
        while (i <= 0);
        this.state = 1;
        refreshHeaderViewByState();
        return;
        topPadding(j);
      }
      while ((this.scrollState != 1) || (i <= this.headerContentHeight + 20));
      this.state = 2;
      refreshHeaderViewByState();
      return;
      topPadding(j);
      if ((i > 0) && (i < this.headerContentHeight + 20))
      {
        this.state = 1;
        refreshHeaderViewByState();
        return;
      }
    }
    while (i > 0);
    this.state = 0;
    refreshHeaderViewByState();
  }

  public int getPageSize()
  {
    return this.pageSize;
  }

  public boolean isLoadEnable()
  {
    return this.loadEnable;
  }

  public void onLoad()
  {
    if (this.onLoadListener != null)
      this.onLoadListener.onLoad();
  }

  public void onLoadComplete()
  {
    this.isLoading = false;
  }

  public void onRefresh()
  {
    if (this.onRefreshListener != null)
      this.onRefreshListener.onRefresh();
  }

  public void onRefreshComplete()
  {
    onRefreshComplete(TimeUtils.getCurrentTime());
  }

  public void onRefreshComplete(String paramString)
  {
    this.lastUpdate.setText(getContext().getString(2131296379, new Object[] { TimeUtils.getCurrentTime() }));
    this.state = 0;
    refreshHeaderViewByState();
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.firstVisibleItem = paramInt1;
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
    this.scrollState = paramInt;
    ifNeedLoad(paramAbsListView, paramInt);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 1:
    case 3:
    case 2:
    }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      if (this.firstVisibleItem == 0)
      {
        this.isRecorded = true;
        this.startY = ((int)paramMotionEvent.getY());
        continue;
        if (this.state == 1)
        {
          this.state = 0;
          refreshHeaderViewByState();
        }
        while (true)
        {
          this.isRecorded = false;
          break;
          if (this.state == 2)
          {
            this.state = 3;
            refreshHeaderViewByState();
            onRefresh();
          }
        }
        whenMove(paramMotionEvent);
      }
    }
  }

  public void setLoadEnable(boolean paramBoolean)
  {
    this.loadEnable = paramBoolean;
    removeFooterView(this.footer);
  }

  public void setOnLoadListener(OnLoadListener paramOnLoadListener)
  {
    this.loadEnable = true;
    this.onLoadListener = paramOnLoadListener;
  }

  public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
  {
    this.onRefreshListener = paramOnRefreshListener;
  }

  public void setPageSize(int paramInt)
  {
    this.pageSize = paramInt;
  }

  public void setResultSize(int paramInt)
  {
    if (paramInt == 0)
    {
      this.isLoadFull = true;
      this.loadFull.setVisibility(8);
      this.loading.setVisibility(8);
      this.more.setVisibility(8);
      this.noData.setVisibility(0);
    }
    do
    {
      return;
      if ((paramInt > 0) && (paramInt < this.pageSize))
      {
        this.isLoadFull = true;
        this.loadFull.setVisibility(0);
        this.loading.setVisibility(8);
        this.more.setVisibility(8);
        this.noData.setVisibility(8);
        return;
      }
    }
    while (paramInt != this.pageSize);
    this.isLoadFull = false;
    this.loadFull.setVisibility(8);
    this.loading.setVisibility(0);
    this.more.setVisibility(0);
    this.noData.setVisibility(8);
  }

  public static abstract interface OnLoadListener
  {
    public abstract void onLoad();
  }

  public static abstract interface OnRefreshListener
  {
    public abstract void onRefresh();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.AutoListView
 * JD-Core Version:    0.6.2
 */