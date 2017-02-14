package com.mob.tools.gui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListInnerAdapter extends BaseAdapter
{
  private PullToRefreshBaseListAdapter adapter;

  public ListInnerAdapter(PullToRefreshBaseListAdapter paramPullToRefreshBaseListAdapter)
  {
    this.adapter = paramPullToRefreshBaseListAdapter;
  }

  public int getCount()
  {
    return this.adapter.getCount();
  }

  public Object getItem(int paramInt)
  {
    return this.adapter.getItem(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return this.adapter.getItemId(paramInt);
  }

  public int getItemViewType(int paramInt)
  {
    return this.adapter.getItemViewType(paramInt);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return this.adapter.getView(paramInt, paramView, paramViewGroup);
  }

  public int getViewTypeCount()
  {
    return this.adapter.getViewTypeCount();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.ListInnerAdapter
 * JD-Core Version:    0.6.2
 */