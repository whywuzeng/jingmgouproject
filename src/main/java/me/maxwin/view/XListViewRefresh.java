package me.maxwin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;
import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.PLA_AbsListView;
import com.huewu.pla.lib.internal.PLA_AbsListView.OnScrollListener;

public class XListViewRefresh extends MultiColumnListView
  implements PLA_AbsListView.OnScrollListener
{
  private static final float OFFSET_RADIO = 1.8F;
  private static final int PULL_LOAD_MORE_DELTA = 50;
  private static final int SCROLLBACK_FOOTER = 1;
  private static final int SCROLLBACK_HEADER = 0;
  private static final int SCROLL_DURATION = 400;
  private boolean mEnablePullLoad;
  private boolean mEnablePullRefresh = true;
  private XListViewFooter mFooterView;
  private TextView mHeaderTimeView;
  private XListViewHeader mHeaderView;
  private RelativeLayout mHeaderViewContent;
  private int mHeaderViewHeight;
  private boolean mIsFooterReady = false;
  private float mLastY = -1.0F;
  private IXListViewListener mListViewListener;
  private boolean mPullLoading;
  private boolean mPullRefreshing = false;
  private int mScrollBack;
  private PLA_AbsListView.OnScrollListener mScrollListener;
  private Scroller mScroller;
  private int mTotalItemCount;

  public XListViewRefresh(Context paramContext)
  {
    super(paramContext);
    initWithContext(paramContext);
  }

  public XListViewRefresh(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initWithContext(paramContext);
  }

  public XListViewRefresh(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initWithContext(paramContext);
  }

  private void initWithContext(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext, new DecelerateInterpolator());
    super.setOnScrollListener(this);
    this.mHeaderView = new XListViewHeader(paramContext);
    this.mHeaderViewContent = ((RelativeLayout)this.mHeaderView.findViewById(2131231499));
    this.mHeaderTimeView = ((TextView)this.mHeaderView.findViewById(2131231502));
    addHeaderView(this.mHeaderView);
    this.mFooterView = new XListViewFooter(paramContext);
    this.mFooterView.setVisibility(0);
    this.mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
    {
      public void onGlobalLayout()
      {
        XListViewRefresh.this.mHeaderViewHeight = XListViewRefresh.this.mHeaderViewContent.getHeight();
        XListViewRefresh.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
      }
    });
  }

  private void invokeOnScrolling()
  {
    if ((this.mScrollListener instanceof OnXScrollListener))
      ((OnXScrollListener)this.mScrollListener).onXScrolling(this);
  }

  private void resetFooterHeight()
  {
    int i = this.mFooterView.getBottomMargin();
    if (i > 0)
    {
      this.mScrollBack = 1;
      this.mScroller.startScroll(0, i, 0, -i, 400);
      invalidate();
    }
  }

  private void resetHeaderHeight()
  {
    int k = this.mHeaderView.getVisiableHeight();
    if (k == 0);
    while ((this.mPullRefreshing) && (k <= this.mHeaderViewHeight))
      return;
    int j = 0;
    int i = j;
    if (this.mPullRefreshing)
    {
      i = j;
      if (k > this.mHeaderViewHeight)
        i = this.mHeaderViewHeight;
    }
    this.mScrollBack = 0;
    this.mScroller.startScroll(0, k, 0, i - k, 400);
    invalidate();
  }

  private void startLoadMore()
  {
    this.mPullLoading = true;
    this.mFooterView.setState(2);
    if (this.mListViewListener != null)
      this.mListViewListener.onLoadMore();
  }

  private void updateFooterHeight(float paramFloat)
  {
    int i = this.mFooterView.getBottomMargin() + (int)paramFloat;
    if ((this.mEnablePullLoad) && (!this.mPullLoading))
    {
      if (i <= 50)
        break label48;
      this.mFooterView.setState(1);
    }
    while (true)
    {
      this.mFooterView.setBottomMargin(i);
      return;
      label48: this.mFooterView.setState(0);
    }
  }

  private void updateHeaderHeight(float paramFloat)
  {
    this.mHeaderView.setVisiableHeight((int)paramFloat + this.mHeaderView.getVisiableHeight());
    if ((this.mEnablePullRefresh) && (!this.mPullRefreshing))
    {
      if (this.mHeaderView.getVisiableHeight() <= this.mHeaderViewHeight)
        break label59;
      this.mHeaderView.setState(1);
    }
    while (true)
    {
      setSelection(0);
      return;
      label59: this.mHeaderView.setState(0);
    }
  }

  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      if (this.mScrollBack != 0)
        break label44;
      this.mHeaderView.setVisiableHeight(this.mScroller.getCurrY());
    }
    while (true)
    {
      postInvalidate();
      invokeOnScrolling();
      super.computeScroll();
      return;
      label44: this.mFooterView.setBottomMargin(this.mScroller.getCurrY());
    }
  }

  public void onScroll(PLA_AbsListView paramPLA_AbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mTotalItemCount = paramInt3;
    if (this.mScrollListener != null)
      this.mScrollListener.onScroll(paramPLA_AbsListView, paramInt1, paramInt2, paramInt3);
  }

  public void onScrollStateChanged(PLA_AbsListView paramPLA_AbsListView, int paramInt)
  {
    if (this.mScrollListener != null)
      this.mScrollListener.onScrollStateChanged(paramPLA_AbsListView, paramInt);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mLastY == -1.0F)
      this.mLastY = paramMotionEvent.getRawY();
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
      this.mLastY = -1.0F;
      if (getFirstVisiblePosition() == 0)
      {
        if ((this.mEnablePullRefresh) && (this.mHeaderView.getVisiableHeight() > this.mHeaderViewHeight))
        {
          this.mPullRefreshing = true;
          this.mHeaderView.setState(2);
          if (this.mListViewListener != null)
            this.mListViewListener.onRefresh();
        }
        resetHeaderHeight();
      }
      break;
    case 0:
    case 2:
    }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      this.mLastY = paramMotionEvent.getRawY();
      continue;
      float f = paramMotionEvent.getRawY() - this.mLastY;
      this.mLastY = paramMotionEvent.getRawY();
      if ((getFirstVisiblePosition() == 0) && ((this.mHeaderView.getVisiableHeight() > 0) || (f > 0.0F)))
      {
        updateHeaderHeight(f / 1.8F);
        invokeOnScrolling();
      }
      else if ((getLastVisiblePosition() == this.mTotalItemCount - 1) && ((this.mFooterView.getBottomMargin() > 0) || (f < 0.0F)))
      {
        updateFooterHeight(-f / 1.8F);
        continue;
        if (getLastVisiblePosition() == this.mTotalItemCount - 1)
        {
          if ((this.mEnablePullLoad) && (this.mFooterView.getBottomMargin() > 50))
            startLoadMore();
          resetFooterHeight();
        }
      }
    }
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (!this.mIsFooterReady)
    {
      this.mIsFooterReady = true;
      addFooterView(this.mFooterView);
      Log.i("Test", "addFooterView");
    }
    super.setAdapter(paramListAdapter);
  }

  public void setFootHide()
  {
    this.mFooterView.setHintGone();
  }

  public void setFootVisible()
  {
    this.mFooterView.setHintVisible();
  }

  public void setPullLoadEnable(boolean paramBoolean)
  {
    this.mEnablePullLoad = paramBoolean;
    if (!this.mEnablePullLoad)
    {
      this.mFooterView.hide();
      this.mFooterView.setOnClickListener(null);
      return;
    }
    this.mPullLoading = false;
    this.mFooterView.show();
    this.mFooterView.setState(0);
    this.mFooterView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        XListViewRefresh.this.startLoadMore();
      }
    });
  }

  public void setPullRefreshEnable(boolean paramBoolean)
  {
    this.mEnablePullRefresh = paramBoolean;
    if (!this.mEnablePullRefresh)
    {
      this.mHeaderViewContent.setVisibility(4);
      return;
    }
    this.mHeaderViewContent.setVisibility(0);
  }

  public void setRefreshTime(String paramString)
  {
    this.mHeaderTimeView.setText(paramString);
  }

  public void setXListViewListener(IXListViewListener paramIXListViewListener)
  {
    this.mListViewListener = paramIXListViewListener;
  }

  public void stopLoadMore()
  {
    if (this.mPullLoading)
    {
      this.mPullLoading = false;
      this.mFooterView.setState(0);
    }
  }

  public void stopRefresh()
  {
    if (this.mPullRefreshing)
    {
      this.mPullRefreshing = false;
      resetHeaderHeight();
    }
  }

  public static abstract interface IXListViewListener
  {
    public abstract void onLoadMore();

    public abstract void onRefresh();
  }

  public static abstract interface OnXScrollListener extends PLA_AbsListView.OnScrollListener
  {
    public abstract void onXScrolling(View paramView);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     me.maxwin.view.XListViewRefresh
 * JD-Core Version:    0.6.2
 */