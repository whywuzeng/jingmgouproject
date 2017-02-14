package com.mob.tools.gui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class PullToRefreshView extends RelativeLayout
{
  private static final long MIN_REF_TIME = 1000L;
  private PullToRefreshAdatper adapter;
  private View bodyView;
  private float downY;
  private int headerHeight;
  private View headerView;
  private boolean pullingLock;
  private long refreshTime;
  private boolean requesting;
  private Runnable stopAct;
  private int top;

  public PullToRefreshView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public PullToRefreshView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public PullToRefreshView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private boolean canPull()
  {
    return (!this.pullingLock) && (this.adapter.isPullReady());
  }

  private MotionEvent getCancelEvent(MotionEvent paramMotionEvent)
  {
    return MotionEvent.obtain(paramMotionEvent.getDownTime(), paramMotionEvent.getEventTime(), 3, paramMotionEvent.getX(), paramMotionEvent.getY(), paramMotionEvent.getMetaState());
  }

  private void init()
  {
    this.stopAct = new Runnable()
    {
      public void run()
      {
        PullToRefreshView.this.reversePulling();
        PullToRefreshView.this.stopRequest();
      }
    };
  }

  private void performRequest()
  {
    this.refreshTime = System.currentTimeMillis();
    this.requesting = true;
    if (this.adapter != null)
      this.adapter.onRequest();
  }

  private void reversePulling()
  {
    this.top = 0;
    scrollTo(0, 0);
    if (this.adapter != null)
      this.adapter.onReversed();
  }

  private void stopRequest()
  {
    this.requesting = false;
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    MotionEvent localMotionEvent;
    switch (paramMotionEvent.getAction())
    {
    default:
      localMotionEvent = paramMotionEvent;
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return super.dispatchTouchEvent(localMotionEvent);
      this.downY = paramMotionEvent.getY();
      localMotionEvent = paramMotionEvent;
      continue;
      float f = paramMotionEvent.getY();
      if (!this.requesting)
      {
        localMotionEvent = paramMotionEvent;
        if (!canPull());
      }
      else
      {
        this.top = ((int)(this.top + (f - this.downY) / 2.0F));
        if (this.top <= 0)
          break label161;
        scrollTo(0, -this.top);
        if ((!this.requesting) && (this.adapter != null))
          this.adapter.onPullDown(this.top * 100 / this.headerHeight);
      }
      for (localMotionEvent = getCancelEvent(paramMotionEvent); ; localMotionEvent = paramMotionEvent)
      {
        this.downY = f;
        break;
        label161: this.top = 0;
        scrollTo(0, 0);
      }
      if (!this.requesting)
      {
        if (this.top > this.headerHeight)
        {
          this.top = this.headerHeight;
          scrollTo(0, -this.top);
          if (this.adapter != null)
            this.adapter.onPullDown(100);
          performRequest();
          localMotionEvent = getCancelEvent(paramMotionEvent);
        }
        else
        {
          localMotionEvent = paramMotionEvent;
          if (this.top != 0)
          {
            reversePulling();
            localMotionEvent = paramMotionEvent;
            if (this.adapter != null)
            {
              this.adapter.onPullDown(0);
              localMotionEvent = paramMotionEvent;
            }
          }
        }
      }
      else
      {
        this.top = this.headerHeight;
        scrollTo(0, -this.top);
        localMotionEvent = paramMotionEvent;
      }
    }
  }

  public void lockPulling()
  {
    this.pullingLock = true;
  }

  public void performPulling(boolean paramBoolean)
  {
    this.top = this.headerHeight;
    scrollTo(0, -this.top);
    if (paramBoolean)
      performRequest();
  }

  public void releaseLock()
  {
    this.pullingLock = false;
  }

  public void setAdapter(PullToRefreshAdatper paramPullToRefreshAdatper)
  {
    this.adapter = paramPullToRefreshAdatper;
    removeAllViews();
    this.bodyView = ((View)paramPullToRefreshAdatper.getBodyView());
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.addRule(9, -1);
    localLayoutParams.addRule(11, -1);
    localLayoutParams.addRule(10, -1);
    addView(this.bodyView, localLayoutParams);
    this.headerView = paramPullToRefreshAdatper.getHeaderView();
    this.headerView.measure(0, 0);
    this.headerHeight = this.headerView.getMeasuredHeight();
    paramPullToRefreshAdatper = new RelativeLayout.LayoutParams(-2, this.headerHeight);
    paramPullToRefreshAdatper.addRule(9, -1);
    paramPullToRefreshAdatper.addRule(11, -1);
    paramPullToRefreshAdatper.addRule(10, -1);
    paramPullToRefreshAdatper.topMargin = (-this.headerHeight);
    addView(this.headerView, paramPullToRefreshAdatper);
  }

  public void stopPulling()
  {
    long l = System.currentTimeMillis() - this.refreshTime;
    if (l < 1000L)
    {
      postDelayed(this.stopAct, 1000L - l);
      return;
    }
    post(this.stopAct);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.PullToRefreshView
 * JD-Core Version:    0.6.2
 */