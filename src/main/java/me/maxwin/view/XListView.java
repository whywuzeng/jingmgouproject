package me.maxwin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListAdapter;
import android.widget.Scroller;
import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.PLA_AbsListView;
import com.huewu.pla.lib.internal.PLA_AbsListView.OnScrollListener;
import java.io.PrintStream;

public class XListView extends MultiColumnListView
  implements PLA_AbsListView.OnScrollListener
{
  private static final float OFFSET_RADIO = 1.8F;
  private static final int PULL_LOAD_MORE_DELTA = 50;
  private static final int SCROLLBACK_FOOTER = 1;
  private static final int SCROLLBACK_HEADER = 0;
  private static final int SCROLL_DURATION = 400;
  int columns = 2;
  private Context context;
  int count = 0;
  private View headerView;
  private boolean mEnablePullLoad;
  private XListViewFooter mFooterView;
  private GestureDetector mGestureDetector;
  private boolean mIsFooterReady = false;
  private float mLastY = -1.0F;
  private IXListViewListener mListViewListener;
  private int mMaxChildHeight = 0;
  private int mMaxChildWidth = 0;
  private boolean mPullLoading;
  private int mScrollBack;
  private PLA_AbsListView.OnScrollListener mScrollListener;
  private Scroller mScroller;
  private int mTotalItemCount;
  int margin = 10;
  int rows = 0;

  public XListView(Context paramContext)
  {
    super(paramContext);
    initWithContext(paramContext);
  }

  public XListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initWithContext(paramContext);
  }

  public XListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initWithContext(paramContext);
  }

  private void initWithContext(Context paramContext)
  {
    this.mScroller = new Scroller(paramContext, new DecelerateInterpolator());
    super.setOnScrollListener(this);
    this.context = paramContext;
    this.mFooterView = new XListViewFooter(paramContext);
    this.mGestureDetector = new GestureDetector(new YScrollDetector());
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

  private void startLoadMore()
  {
    this.mPullLoading = true;
    this.mFooterView.setState(2);
    if (this.mListViewListener != null)
      this.mListViewListener.onLoadMore1();
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

  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      if (this.mScrollBack != 0)
        this.mFooterView.setBottomMargin(this.mScroller.getCurrY());
      postInvalidate();
      invokeOnScrolling();
    }
    super.computeScroll();
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public View getHeaderView()
  {
    return this.headerView;
  }

  public int getTheBottom()
  {
    return getScrollChildBottom();
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    System.out.println("--->XListView onInterceptTouchEvent");
    boolean bool = super.onInterceptTouchEvent(paramMotionEvent);
    this.mGestureDetector.onTouchEvent(paramMotionEvent);
    return bool;
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
      if (getLastVisiblePosition() == this.mTotalItemCount - 1)
      {
        if ((this.mEnablePullLoad) && (this.mFooterView.getBottomMargin() > 50))
          startLoadMore();
        resetFooterHeight();
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
      if ((getLastVisiblePosition() == this.mTotalItemCount - 1) && ((this.mFooterView.getBottomMargin() > 0) || (f < 0.0F)))
        updateFooterHeight(-f / 1.8F);
    }
  }

  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (!this.mIsFooterReady)
    {
      this.mIsFooterReady = true;
      addFooterView(this.mFooterView);
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

  public void setHeadView(int paramInt)
  {
    this.headerView = inflate(this.context, paramInt, null);
    addHeaderView(this.headerView, null, false);
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
    this.mFooterView.setHintGone();
    this.mFooterView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        XListView.this.startLoadMore();
      }
    });
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

  public static abstract interface IXListViewListener
  {
    public abstract void onLoadMore1();

    public abstract void onRefresh1();
  }

  public static abstract interface OnXScrollListener extends PLA_AbsListView.OnScrollListener
  {
    public abstract void onXScrolling(View paramView);
  }

  class YScrollDetector extends GestureDetector.SimpleOnGestureListener
  {
    YScrollDetector()
    {
    }

    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      return Math.abs(paramFloat2) >= Math.abs(paramFloat1);
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     me.maxwin.view.XListView
 * JD-Core Version:    0.6.2
 */