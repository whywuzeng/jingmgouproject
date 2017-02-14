package com.mob.tools.gui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;

public abstract class PullToRefreshListAdapter extends PullToRefreshBaseListAdapter
{
  private ListInnerAdapter adapter;
  private boolean fling;
  private ScrollableListView listView = onNewListView(getContext());
  private OnListStopScrollListener osListener;

  public PullToRefreshListAdapter(PullToRefreshView paramPullToRefreshView)
  {
    super(paramPullToRefreshView);
    this.listView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      private int firstVisibleItem;
      private int visibleItemCount;

      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        this.firstVisibleItem = paramAnonymousInt1;
        this.visibleItemCount = paramAnonymousInt2;
        PullToRefreshListAdapter.this.onScroll(PullToRefreshListAdapter.this.listView, paramAnonymousInt1, paramAnonymousInt2, paramAnonymousInt3);
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        paramAnonymousAbsListView = PullToRefreshListAdapter.this;
        boolean bool;
        if (paramAnonymousInt == 2)
        {
          bool = true;
          PullToRefreshListAdapter.access$002(paramAnonymousAbsListView, bool);
          if (paramAnonymousInt == 0)
          {
            if (PullToRefreshListAdapter.this.osListener == null)
              break label58;
            PullToRefreshListAdapter.this.osListener.onListStopScrolling(this.firstVisibleItem, this.visibleItemCount);
          }
        }
        label58: 
        while (PullToRefreshListAdapter.this.adapter == null)
        {
          return;
          bool = false;
          break;
        }
        PullToRefreshListAdapter.this.adapter.notifyDataSetChanged();
      }
    });
    this.adapter = new ListInnerAdapter(this);
    this.listView.setAdapter(this.adapter);
  }

  public Scrollable getBodyView()
  {
    return this.listView;
  }

  public ListView getListView()
  {
    return this.listView;
  }

  public boolean isFling()
  {
    return this.fling;
  }

  public boolean isPullReady()
  {
    return this.listView.isReadyToPull();
  }

  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    this.adapter.notifyDataSetChanged();
  }

  protected ScrollableListView onNewListView(Context paramContext)
  {
    return new ScrollableListView(paramContext);
  }

  public void onScroll(Scrollable paramScrollable, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void setDivider(Drawable paramDrawable)
  {
    this.listView.setDivider(paramDrawable);
  }

  public void setDividerHeight(int paramInt)
  {
    this.listView.setDividerHeight(paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.gui.PullToRefreshListAdapter
 * JD-Core Version:    0.6.2
 */