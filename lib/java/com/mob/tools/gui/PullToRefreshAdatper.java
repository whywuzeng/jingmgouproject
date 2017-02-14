package com.mob.tools.gui;

import android.content.Context;
import android.view.View;

public abstract class PullToRefreshAdatper
{
  private Context context;
  private PullToRefreshView parent;

  public PullToRefreshAdatper(PullToRefreshView paramPullToRefreshView)
  {
    this.context = paramPullToRefreshView.getContext();
    this.parent = paramPullToRefreshView;
  }

  public abstract Scrollable getBodyView();

  public Context getContext()
  {
    return this.context;
  }

  public abstract View getHeaderView();

  protected PullToRefreshView getParent()
  {
    return this.parent;
  }

  public abstract boolean isPullReady();

  public void notifyDataSetChanged()
  {
    this.parent.stopPulling();
  }

  public abstract void onPullDown(int paramInt);

  public abstract void onRequest();

  public void onReversed()
  {
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.PullToRefreshAdatper
 * JD-Core Version:    0.6.2
 */