package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Scroller;
import com.ismartgo.apppub.R.styleable;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class ViewFlow extends AdapterView<Adapter>
{
  private static final int INVALID_SCREEN = -1;
  private static final int SNAP_VELOCITY = 1000;
  private static final int TOUCH_STATE_REST = 0;
  private static final int TOUCH_STATE_SCROLLING = 1;
  private Handler handler;
  private Adapter mAdapter;
  private int mCurrentAdapterIndex;
  private int mCurrentBufferIndex;
  private int mCurrentScreen;
  private AdapterDataSetObserver mDataSetObserver;
  private boolean mFirstLayout = true;
  private FlowIndicator mIndicator;
  private float mLastMotionX;
  private int mLastOrientation = -1;
  private int mLastScrollDirection;
  private LinkedList<View> mLoadedViews;
  private int mMaximumVelocity;
  private int mNextScreen = -1;
  private Scroller mScroller;
  private int mSideBuffer = 1;
  private int mTouchSlop;
  private int mTouchState = 0;
  private VelocityTracker mVelocityTracker;
  private ViewSwitchListener mViewSwitchListener;
  private ViewTreeObserver.OnGlobalLayoutListener orientationChangeListener = new ViewTreeObserver.OnGlobalLayoutListener()
  {
    public void onGlobalLayout()
    {
      ViewFlow.this.getViewTreeObserver().removeGlobalOnLayoutListener(ViewFlow.this.orientationChangeListener);
      ViewFlow.this.setSelection(ViewFlow.this.mCurrentAdapterIndex);
    }
  };
  private long timeSpan = 5000L;

  public ViewFlow(Context paramContext)
  {
    super(paramContext);
    this.mSideBuffer = 5;
    init();
  }

  public ViewFlow(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mSideBuffer = paramInt;
    init();
  }

  public ViewFlow(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mSideBuffer = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ViewFlow).getInt(0, 5);
    init();
  }

  private void init()
  {
    this.mLoadedViews = new LinkedList();
    this.mScroller = new Scroller(getContext());
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    this.mTouchSlop = localViewConfiguration.getScaledTouchSlop();
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
  }

  private View makeAndAddView(int paramInt, boolean paramBoolean, View paramView)
  {
    View localView = this.mAdapter.getView(paramInt, paramView, this);
    if (paramView != null);
    for (boolean bool = true; ; bool = false)
      return setupChild(localView, paramBoolean, bool);
  }

  private void postViewSwitched(int paramInt)
  {
    if (paramInt == 0)
      return;
    View localView;
    if (paramInt > 0)
    {
      this.mCurrentAdapterIndex += 1;
      this.mCurrentBufferIndex += 1;
      localView = null;
      if (this.mCurrentAdapterIndex > this.mSideBuffer)
      {
        localView = (View)this.mLoadedViews.removeFirst();
        detachViewFromParent(localView);
        this.mCurrentBufferIndex -= 1;
      }
      paramInt = this.mCurrentAdapterIndex + this.mSideBuffer;
      if (paramInt < this.mAdapter.getCount())
        this.mLoadedViews.addLast(makeAndAddView(paramInt, true, localView));
    }
    while (true)
    {
      requestLayout();
      setVisibleView(this.mCurrentBufferIndex, true);
      if (this.mIndicator != null)
        this.mIndicator.onSwitched((View)this.mLoadedViews.get(this.mCurrentBufferIndex), this.mCurrentAdapterIndex);
      if (this.mViewSwitchListener == null)
        break;
      this.mViewSwitchListener.onSwitched((View)this.mLoadedViews.get(this.mCurrentBufferIndex), this.mCurrentAdapterIndex);
      return;
      this.mCurrentAdapterIndex -= 1;
      this.mCurrentBufferIndex -= 1;
      localView = null;
      if (this.mAdapter.getCount() - 1 - this.mCurrentAdapterIndex > this.mSideBuffer)
      {
        localView = (View)this.mLoadedViews.removeLast();
        detachViewFromParent(localView);
      }
      paramInt = this.mCurrentAdapterIndex - this.mSideBuffer;
      if (paramInt > -1)
      {
        this.mLoadedViews.addFirst(makeAndAddView(paramInt, false, localView));
        this.mCurrentBufferIndex += 1;
      }
    }
  }

  private void resetFocus()
  {
    this.mLoadedViews.clear();
    removeAllViewsInLayout();
    int i = Math.max(0, this.mCurrentAdapterIndex - this.mSideBuffer);
    while (true)
    {
      if (i >= Math.min(this.mAdapter.getCount(), this.mCurrentAdapterIndex + this.mSideBuffer + 1))
      {
        requestLayout();
        return;
      }
      this.mLoadedViews.addLast(makeAndAddView(i, true, null));
      if (i == this.mCurrentAdapterIndex)
        this.mCurrentBufferIndex = (this.mLoadedViews.size() - 1);
      i += 1;
    }
  }

  private void setVisibleView(int paramInt, boolean paramBoolean)
  {
    this.mCurrentScreen = Math.max(0, Math.min(paramInt, getChildCount() - 1));
    paramInt = this.mCurrentScreen * getWidth() - this.mScroller.getCurrX();
    this.mScroller.startScroll(this.mScroller.getCurrX(), this.mScroller.getCurrY(), paramInt, 0, 0);
    if (paramInt == 0)
      onScrollChanged(this.mScroller.getCurrX() + paramInt, this.mScroller.getCurrY(), this.mScroller.getCurrX() + paramInt, this.mScroller.getCurrY());
    if (paramBoolean)
    {
      invalidate();
      return;
    }
    postInvalidate();
  }

  private View setupChild(View paramView, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i = -1;
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    Object localObject = localLayoutParams;
    if (localLayoutParams == null)
      localObject = new AbsListView.LayoutParams(-1, -2, 0);
    if (paramBoolean2)
    {
      if (paramBoolean1);
      while (true)
      {
        attachViewToParent(paramView, i, (ViewGroup.LayoutParams)localObject);
        return paramView;
        i = 0;
      }
    }
    if (paramBoolean1);
    while (true)
    {
      addViewInLayout(paramView, i, (ViewGroup.LayoutParams)localObject, true);
      return paramView;
      i = 0;
    }
  }

  private void snapToDestination()
  {
    int i = getWidth();
    snapToScreen((getScrollX() + i / 2) / i);
  }

  private void snapToScreen(int paramInt)
  {
    this.mLastScrollDirection = (paramInt - this.mCurrentScreen);
    if (!this.mScroller.isFinished())
      return;
    paramInt = Math.max(0, Math.min(paramInt, getChildCount() - 1));
    this.mNextScreen = paramInt;
    paramInt = paramInt * getWidth() - getScrollX();
    this.mScroller.startScroll(getScrollX(), 0, paramInt, 0, Math.abs(paramInt) * 2);
    invalidate();
  }

  public void computeScroll()
  {
    if (this.mScroller.computeScrollOffset())
    {
      scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
      postInvalidate();
    }
    while (this.mNextScreen == -1)
      return;
    this.mCurrentScreen = Math.max(0, Math.min(this.mNextScreen, getChildCount() - 1));
    this.mNextScreen = -1;
    postViewSwitched(this.mLastScrollDirection);
  }

  public Adapter getAdapter()
  {
    return this.mAdapter;
  }

  public int getSelectedItemPosition()
  {
    return this.mCurrentAdapterIndex;
  }

  public View getSelectedView()
  {
    if (this.mCurrentBufferIndex < this.mLoadedViews.size())
      return (View)this.mLoadedViews.get(this.mCurrentBufferIndex);
    return null;
  }

  public int getViewsCount()
  {
    return this.mSideBuffer;
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    if (paramConfiguration.orientation != this.mLastOrientation)
    {
      this.mLastOrientation = paramConfiguration.orientation;
      getViewTreeObserver().addOnGlobalLayoutListener(this.orientationChangeListener);
    }
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    System.out.println("--->ViewFlow onInterceptTouchEvent");
    if (getChildCount() == 0)
      return false;
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int i = paramMotionEvent.getAction();
    float f = paramMotionEvent.getX();
    switch (i)
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return false;
      if (!this.mScroller.isFinished())
        this.mScroller.abortAnimation();
      this.mLastMotionX = f;
      if (this.mScroller.isFinished());
      for (i = 0; ; i = 1)
      {
        this.mTouchState = i;
        if (this.handler == null)
          break;
        this.handler.removeMessages(0);
        break;
      }
      int j;
      if ((int)Math.abs(f - this.mLastMotionX) > this.mTouchSlop)
      {
        i = 1;
        if (i != 0)
          this.mTouchState = 1;
        if (this.mTouchState == 1)
        {
          i = (int)(this.mLastMotionX - f);
          this.mLastMotionX = f;
          j = getScrollX();
          if (i < 0)
            if (j > 0)
              scrollBy(Math.max(-j, i), 0);
        }
      }
      else
      {
        while (true)
        {
          return true;
          i = 0;
          break;
          if (i > 0)
          {
            j = getChildAt(getChildCount() - 1).getRight() - j - getWidth();
            if (j > 0)
              scrollBy(Math.min(j, i), 0);
          }
        }
        if (this.mTouchState == 1)
        {
          paramMotionEvent = this.mVelocityTracker;
          paramMotionEvent.computeCurrentVelocity(1000, this.mMaximumVelocity);
          i = (int)paramMotionEvent.getXVelocity();
          if ((i <= 1000) || (this.mCurrentScreen <= 0))
            break label384;
          snapToScreen(this.mCurrentScreen - 1);
        }
        while (true)
        {
          if (this.mVelocityTracker != null)
          {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
          }
          this.mTouchState = 0;
          if (this.handler == null)
            break;
          paramMotionEvent = this.handler.obtainMessage(0);
          this.handler.sendMessageDelayed(paramMotionEvent, this.timeSpan);
          break;
          label384: if ((i < -1000) && (this.mCurrentScreen < getChildCount() - 1))
            snapToScreen(this.mCurrentScreen + 1);
          else
            snapToDestination();
        }
        this.mTouchState = 0;
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = 0;
    paramInt4 = getChildCount();
    paramInt1 = 0;
    while (true)
    {
      if (paramInt1 >= paramInt4)
        return;
      View localView = getChildAt(paramInt1);
      paramInt3 = paramInt2;
      if (localView.getVisibility() != 8)
      {
        paramInt3 = localView.getMeasuredWidth();
        localView.layout(paramInt2, 0, paramInt2 + paramInt3, localView.getMeasuredHeight());
        paramInt3 = paramInt2 + paramInt3;
      }
      paramInt1 += 1;
      paramInt2 = paramInt3;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    if ((View.MeasureSpec.getMode(paramInt1) != 1073741824) && (!isInEditMode()))
      throw new IllegalStateException("ViewFlow can only be used in EXACTLY mode.");
    if ((View.MeasureSpec.getMode(paramInt2) != 1073741824) && (!isInEditMode()))
      throw new IllegalStateException("ViewFlow can only be used in EXACTLY mode.");
    int k = getChildCount();
    int i = 0;
    while (true)
    {
      if (i >= k)
      {
        if (this.mFirstLayout)
        {
          this.mScroller.startScroll(0, 0, this.mCurrentScreen * j, 0, 0);
          this.mFirstLayout = false;
        }
        return;
      }
      getChildAt(i).measure(paramInt1, paramInt2);
      i += 1;
    }
  }

  protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mIndicator != null)
    {
      int i = this.mCurrentAdapterIndex;
      int j = this.mCurrentBufferIndex;
      int k = getWidth();
      this.mIndicator.onScrolled(paramInt1 + (i - j) * k, paramInt2, paramInt3, paramInt4);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    System.out.println("--->ViewFlow onTouch");
    if (getChildCount() == 0)
    {
      System.out.println("--->return false");
      return false;
    }
    if (this.mVelocityTracker == null)
      this.mVelocityTracker = VelocityTracker.obtain();
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int i = paramMotionEvent.getAction();
    float f = paramMotionEvent.getX();
    switch (i)
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      System.out.println("--->return true");
      return true;
      if (!this.mScroller.isFinished())
        this.mScroller.abortAnimation();
      this.mLastMotionX = f;
      if (this.mScroller.isFinished());
      for (i = 0; ; i = 1)
      {
        this.mTouchState = i;
        if (this.handler == null)
          break;
        this.handler.removeMessages(0);
        break;
      }
      int j;
      if ((int)Math.abs(f - this.mLastMotionX) > this.mTouchSlop)
      {
        i = 1;
        if (i != 0)
          this.mTouchState = 1;
        if (this.mTouchState == 1)
        {
          i = (int)(this.mLastMotionX - f);
          this.mLastMotionX = f;
          j = getScrollX();
          if (i < 0)
            if (j > 0)
              scrollBy(Math.max(-j, i), 0);
        }
      }
      else
      {
        while (true)
        {
          System.out.println("--->return true");
          return true;
          i = 0;
          break;
          if (i > 0)
          {
            j = getChildAt(getChildCount() - 1).getRight() - j - getWidth();
            if (j > 0)
              scrollBy(Math.min(j, i), 0);
          }
        }
        if (this.mTouchState == 1)
        {
          paramMotionEvent = this.mVelocityTracker;
          paramMotionEvent.computeCurrentVelocity(1000, this.mMaximumVelocity);
          i = (int)paramMotionEvent.getXVelocity();
          if ((i <= 1000) || (this.mCurrentScreen <= 0))
            break label414;
          snapToScreen(this.mCurrentScreen - 1);
        }
        while (true)
        {
          if (this.mVelocityTracker != null)
          {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
          }
          this.mTouchState = 0;
          if (this.handler == null)
            break;
          paramMotionEvent = this.handler.obtainMessage(0);
          this.handler.sendMessageDelayed(paramMotionEvent, this.timeSpan);
          break;
          label414: if ((i < -1000) && (this.mCurrentScreen < getChildCount() - 1))
            snapToScreen(this.mCurrentScreen + 1);
          else
            snapToDestination();
        }
        snapToDestination();
        this.mTouchState = 0;
      }
    }
  }

  public void setAdapter(Adapter paramAdapter)
  {
    setAdapter(paramAdapter, 0);
  }

  public void setAdapter(Adapter paramAdapter, int paramInt)
  {
    if (this.mAdapter != null)
      this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
    this.mAdapter = paramAdapter;
    if (this.mAdapter != null)
    {
      this.mDataSetObserver = new AdapterDataSetObserver();
      this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
    }
    if ((this.mAdapter == null) || (this.mAdapter.getCount() == 0))
      return;
    setSelection(paramInt);
  }

  public void setFlowIndicator(FlowIndicator paramFlowIndicator)
  {
    this.mIndicator = paramFlowIndicator;
    this.mIndicator.setViewFlow(this);
  }

  public void setOnViewSwitchListener(ViewSwitchListener paramViewSwitchListener)
  {
    this.mViewSwitchListener = paramViewSwitchListener;
  }

  public void setSelection(int paramInt)
  {
    this.mNextScreen = -1;
    this.mScroller.forceFinished(true);
    if (this.mAdapter == null)
      return;
    int i = Math.min(Math.max(paramInt, 0), this.mAdapter.getCount() - 1);
    ArrayList localArrayList = new ArrayList();
    label50: Object localObject;
    if (this.mLoadedViews.isEmpty())
    {
      if (!localArrayList.isEmpty())
        break label247;
      localObject = null;
      label71: View localView = makeAndAddView(i, true, (View)localObject);
      this.mLoadedViews.addLast(localView);
      paramInt = 1;
      if (this.mSideBuffer - paramInt >= 0)
        break label261;
      this.mCurrentBufferIndex = this.mLoadedViews.indexOf(localView);
      this.mCurrentAdapterIndex = i;
      localObject = localArrayList.iterator();
    }
    while (true)
    {
      if (!((Iterator)localObject).hasNext())
      {
        requestLayout();
        setVisibleView(this.mCurrentBufferIndex, false);
        if (this.mIndicator != null)
          this.mIndicator.onSwitched((View)this.mLoadedViews.get(this.mCurrentBufferIndex), this.mCurrentAdapterIndex);
        if (this.mViewSwitchListener == null)
          break;
        this.mViewSwitchListener.onSwitched((View)this.mLoadedViews.get(this.mCurrentBufferIndex), this.mCurrentAdapterIndex);
        return;
        localObject = (View)this.mLoadedViews.remove();
        localArrayList.add(localObject);
        detachViewFromParent((View)localObject);
        break label50;
        label247: localObject = (View)localArrayList.remove(0);
        break label71;
        label261: int j = i - paramInt;
        int k = i + paramInt;
        LinkedList localLinkedList;
        if (j >= 0)
        {
          localLinkedList = this.mLoadedViews;
          if (localArrayList.isEmpty())
          {
            localObject = null;
            label291: localLinkedList.addFirst(makeAndAddView(j, false, (View)localObject));
          }
        }
        else if (k < this.mAdapter.getCount())
        {
          localLinkedList = this.mLoadedViews;
          if (!localArrayList.isEmpty())
            break label370;
        }
        label370: for (localObject = null; ; localObject = (View)localArrayList.remove(0))
        {
          localLinkedList.addLast(makeAndAddView(k, true, (View)localObject));
          paramInt += 1;
          break;
          localObject = (View)localArrayList.remove(0);
          break label291;
        }
      }
      removeDetachedView((View)((Iterator)localObject).next(), false);
    }
  }

  public void setTimeSpan(long paramLong)
  {
    this.timeSpan = paramLong;
  }

  public void setmSideBuffer(int paramInt)
  {
    this.mSideBuffer = paramInt;
  }

  public void startAutoFlowTimer()
  {
    this.handler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        ViewFlow.this.snapToScreen((ViewFlow.this.mCurrentScreen + 1) % ViewFlow.this.getChildCount());
        sendMessageDelayed(ViewFlow.this.handler.obtainMessage(0), ViewFlow.this.timeSpan);
      }
    };
    Message localMessage = this.handler.obtainMessage(0);
    this.handler.sendMessageDelayed(localMessage, this.timeSpan);
  }

  public void stopAutoFlowTimer()
  {
    if (this.handler != null)
      this.handler.removeMessages(0);
    this.handler = null;
  }

  class AdapterDataSetObserver extends DataSetObserver
  {
    AdapterDataSetObserver()
    {
    }

    public void onChanged()
    {
      View localView = ViewFlow.this.getChildAt(ViewFlow.this.mCurrentBufferIndex);
      int i;
      if (localView != null)
        i = 0;
      while (true)
      {
        if (i >= ViewFlow.this.mAdapter.getCount());
        while (true)
        {
          ViewFlow.this.resetFocus();
          return;
          if (!localView.equals(ViewFlow.this.mAdapter.getItem(i)))
            break;
          ViewFlow.this.mCurrentAdapterIndex = i;
        }
        i += 1;
      }
    }

    public void onInvalidated()
    {
    }
  }

  public static abstract interface ViewSwitchListener
  {
    public abstract void onSwitched(View paramView, int paramInt);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.ViewFlow
 * JD-Core Version:    0.6.2
 */