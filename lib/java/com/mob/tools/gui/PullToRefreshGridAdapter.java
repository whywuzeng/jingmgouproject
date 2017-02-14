package com.mob.tools.gui;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;

public abstract class PullToRefreshGridAdapter extends PullToRefreshBaseListAdapter
{
  private ListInnerAdapter adapter;
  private boolean fling;
  private ScrollableGridView gridView = onNewGridView(getContext());
  private OnListStopScrollListener osListener;

  public PullToRefreshGridAdapter(PullToRefreshView paramPullToRefreshView)
  {
    super(paramPullToRefreshView);
    this.gridView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      private int firstVisibleItem;
      private int visibleItemCount;

      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        this.firstVisibleItem = paramAnonymousInt1;
        this.visibleItemCount = paramAnonymousInt2;
        PullToRefreshGridAdapter.this.onScroll(PullToRefreshGridAdapter.this.gridView, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        paramAnonymousAbsListView = PullToRefreshGridAdapter.this;
        boolean bool;
        if (paramAnonymousInt == 2)
        {
          bool = true;
          PullToRefreshGridAdapter.access$002(paramAnonymousAbsListView, bool);
          if (paramAnonymousInt == 0)
          {
            if (PullToRefreshGridAdapter.this.osListener == null)
              break label58;
            PullToRefreshGridAdapter.this.osListener.onListStopScrolling(this.firstVisibleItem, this.visibleItemCount);
          }
        }
        label58: 
        while (PullToRefreshGridAdapter.this.adapter == null)
        {
          return;
          bool = false;
          break;
        }
        PullToRefreshGridAdapter.this.adapter.notifyDataSetChanged();
      }
    });
    this.adapter = new ListInnerAdapter(this);
    this.gridView.setAdapter(this.adapter);
  }

  public Scrollable getBodyView()
  {
    return this.gridView;
  }

  public GridView getGridView()
  {
    return this.gridView;
  }

  public boolean isFling()
  {
    return this.fling;
  }

  public boolean isPullReady()
  {
    return this.gridView.isReadyToPull();
  }

  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    this.adapter.notifyDataSetChanged();
  }

  protected ScrollableGridView onNewGridView(Context paramContext)
  {
    return new ScrollableGridView(paramContext);
  }

  public void onScroll(Scrollable paramScrollable, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void setColumnWidth(int paramInt)
  {
    this.gridView.setColumnWidth(paramInt);
  }

  public void setHorizontalSpacing(int paramInt)
  {
    this.gridView.setHorizontalSpacing(paramInt);
  }

  public void setNumColumns(int paramInt)
  {
    this.gridView.setNumColumns(paramInt);
  }

  public void setStretchMode(int paramInt)
  {
    this.gridView.setStretchMode(paramInt);
  }

  public void setVerticalSpacing(int paramInt)
  {
    this.gridView.setVerticalSpacing(paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.PullToRefreshGridAdapter
 * JD-Core Version:    0.6.2
 */