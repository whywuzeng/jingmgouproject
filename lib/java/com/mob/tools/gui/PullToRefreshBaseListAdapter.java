package com.mob.tools.gui;

import android.view.View;
import android.view.ViewGroup;

public abstract class PullToRefreshBaseListAdapter extends PullToRefreshAdatper
{
  public PullToRefreshBaseListAdapter(PullToRefreshView paramPullToRefreshView)
  {
    super(paramPullToRefreshView);
  }

  public abstract int getCount();

  public abstract Object getItem(int paramInt);

  public abstract long getItemId(int paramInt);

  public int getItemViewType(int paramInt)
  {
    return 0;
  }

  public abstract View getView(int paramInt, View paramView, ViewGroup paramViewGroup);

  public int getViewTypeCount()
  {
    return 1;
  }

  public abstract boolean isFling();

  public abstract void onScroll(Scrollable paramScrollable, int paramInt1, int paramInt2, int paramInt3);
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.PullToRefreshBaseListAdapter
 * JD-Core Version:    0.6.2
 */