package com.miloisbadboy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.Date;

public class PullToRefreshView extends LinearLayout
{
  private static final int PULL_DOWN_STATE = 1;
  private static final int PULL_TO_REFRESH = 2;
  private static final int PULL_UP_STATE = 0;
  private static final int REFRESHING = 4;
  private static final int RELEASE_TO_REFRESH = 3;
  private static final String TAG = "PullToRefreshView";
  private boolean enablePullLoadMoreDataStatus = true;
  private boolean enablePullTorefresh = true;
  private AdapterView<?> mAdapterView;
  private RotateAnimation mFlipAnimation;
  private ImageView mFooterImageView;
  private ProgressBar mFooterProgressBar;
  private int mFooterState;
  private TextView mFooterTextView;
  private View mFooterView;
  private int mFooterViewHeight;
  private ImageView mHeaderImageView;
  private ProgressBar mHeaderProgressBar;
  private int mHeaderState;
  private TextView mHeaderTextView;
  private TextView mHeaderUpdateTextView;
  private View mHeaderView;
  private int mHeaderViewHeight;
  private LayoutInflater mInflater;
  private int mLastMotionY;
  private boolean mLock;
  private OnFooterRefreshListener mOnFooterRefreshListener;
  private OnHeaderRefreshListener mOnHeaderRefreshListener;
  private int mPullState;
  private RotateAnimation mReverseFlipAnimation;
  private ScrollView mScrollView;

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

  private void addFooterView()
  {
    this.mFooterView = this.mInflater.inflate(2130903200, this, false);
    this.mFooterImageView = ((ImageView)this.mFooterView.findViewById(2131231353));
    this.mFooterTextView = ((TextView)this.mFooterView.findViewById(2131231354));
    this.mFooterProgressBar = ((ProgressBar)this.mFooterView.findViewById(2131231352));
    measureView(this.mFooterView);
    this.mFooterViewHeight = this.mFooterView.getMeasuredHeight();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, this.mFooterViewHeight);
    addView(this.mFooterView, localLayoutParams);
    this.mFooterView.setVisibility(0);
  }

  private void addHeaderView()
  {
    this.mHeaderView = this.mInflater.inflate(2130903201, this, false);
    this.mHeaderImageView = ((ImageView)this.mHeaderView.findViewById(2131231356));
    this.mHeaderTextView = ((TextView)this.mHeaderView.findViewById(2131231357));
    this.mHeaderUpdateTextView = ((TextView)this.mHeaderView.findViewById(2131231358));
    this.mHeaderProgressBar = ((ProgressBar)this.mHeaderView.findViewById(2131231355));
    measureView(this.mHeaderView);
    this.mHeaderViewHeight = this.mHeaderView.getMeasuredHeight();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, this.mHeaderViewHeight);
    localLayoutParams.topMargin = (-this.mHeaderViewHeight);
    addView(this.mHeaderView, localLayoutParams);
  }

  private int changingHeaderViewTopMargin(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams();
    float f1 = localLayoutParams.topMargin;
    float f2 = paramInt;
    if ((paramInt > 0) && (this.mPullState == 0) && (Math.abs(localLayoutParams.topMargin) <= this.mHeaderViewHeight))
      return localLayoutParams.topMargin;
    if ((paramInt < 0) && (this.mPullState == 1) && (Math.abs(localLayoutParams.topMargin) >= this.mHeaderViewHeight))
      return localLayoutParams.topMargin;
    localLayoutParams.topMargin = ((int)(f1 + f2 * 0.3F));
    this.mHeaderView.setLayoutParams(localLayoutParams);
    invalidate();
    return localLayoutParams.topMargin;
  }

  private void footerPrepareToRefresh(int paramInt)
  {
    paramInt = changingHeaderViewTopMargin(paramInt);
    if ((Math.abs(paramInt) >= this.mHeaderViewHeight + this.mFooterViewHeight) && (this.mFooterState != 3))
    {
      this.mFooterTextView.setText(2131296387);
      this.mFooterImageView.clearAnimation();
      this.mFooterImageView.startAnimation(this.mFlipAnimation);
      this.mFooterState = 3;
    }
    while (Math.abs(paramInt) >= this.mHeaderViewHeight + this.mFooterViewHeight)
      return;
    this.mFooterImageView.clearAnimation();
    this.mFooterImageView.startAnimation(this.mFlipAnimation);
    this.mFooterTextView.setText(2131296388);
    this.mFooterState = 2;
  }

  private void footerRefreshing()
  {
    this.mFooterState = 4;
    setHeaderTopMargin(-(this.mHeaderViewHeight + this.mFooterViewHeight));
    this.mFooterImageView.setVisibility(8);
    this.mFooterImageView.clearAnimation();
    this.mFooterImageView.setImageDrawable(null);
    this.mFooterProgressBar.setVisibility(0);
    this.mFooterTextView.setText(2131296389);
    if (this.mOnFooterRefreshListener != null)
      this.mOnFooterRefreshListener.onFooterRefresh(this);
  }

  private int getHeaderTopMargin()
  {
    return ((LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams()).topMargin;
  }

  private void headerPrepareToRefresh(int paramInt)
  {
    paramInt = changingHeaderViewTopMargin(paramInt);
    if ((paramInt >= 0) && (this.mHeaderState != 3))
    {
      this.mHeaderTextView.setText(2131296385);
      this.mHeaderUpdateTextView.setVisibility(0);
      this.mHeaderImageView.clearAnimation();
      this.mHeaderImageView.startAnimation(this.mFlipAnimation);
      this.mHeaderState = 3;
    }
    while ((paramInt >= 0) || (paramInt <= -this.mHeaderViewHeight))
      return;
    this.mHeaderImageView.clearAnimation();
    this.mHeaderImageView.startAnimation(this.mFlipAnimation);
    this.mHeaderTextView.setText(2131296384);
    this.mHeaderState = 2;
  }

  private void init()
  {
    this.mFlipAnimation = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.mFlipAnimation.setInterpolator(new LinearInterpolator());
    this.mFlipAnimation.setDuration(250L);
    this.mFlipAnimation.setFillAfter(true);
    this.mReverseFlipAnimation = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
    this.mReverseFlipAnimation.setDuration(250L);
    this.mReverseFlipAnimation.setFillAfter(true);
    this.mInflater = LayoutInflater.from(getContext());
    addHeaderView();
  }

  private void initContentAdapterView()
  {
    int j = getChildCount();
    if (j < 3)
      throw new IllegalArgumentException("this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
    int i = 0;
    while (true)
    {
      if (i >= j - 1)
      {
        if ((this.mAdapterView != null) || (this.mScrollView != null))
          break;
        throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
      }
      View localView = getChildAt(i);
      if ((localView instanceof AdapterView))
        this.mAdapterView = ((AdapterView)localView);
      if ((localView instanceof ScrollView))
        this.mScrollView = ((ScrollView)localView);
      i += 1;
    }
  }

  private boolean isRefreshViewScroll(int paramInt)
  {
    if ((this.mHeaderState == 4) || (this.mFooterState == 4));
    View localView;
    label190: 
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                return false;
                if (this.mAdapterView == null)
                  break label190;
                if (paramInt <= 0)
                  break;
              }
              while (!this.enablePullTorefresh);
              localView = this.mAdapterView.getChildAt(0);
            }
            while (localView == null);
            if ((this.mAdapterView.getFirstVisiblePosition() == 0) && (localView.getTop() == 0))
            {
              this.mPullState = 1;
              return true;
            }
            int i = localView.getTop();
            int j = this.mAdapterView.getPaddingTop();
            if ((this.mAdapterView.getFirstVisiblePosition() != 0) || (Math.abs(i - j) > 11))
              break;
            this.mPullState = 1;
            return true;
            if (paramInt >= 0)
              break;
          }
          while (!this.enablePullLoadMoreDataStatus);
          localView = this.mAdapterView.getChildAt(this.mAdapterView.getChildCount() - 1);
        }
        while (localView == null);
        if ((localView.getBottom() <= getHeight()) && (this.mAdapterView.getLastVisiblePosition() == this.mAdapterView.getCount() - 1))
        {
          this.mPullState = 0;
          return true;
        }
      }
      while (this.mScrollView == null);
      localView = this.mScrollView.getChildAt(0);
      if ((paramInt > 0) && (this.mScrollView.getScrollY() == 0))
      {
        this.mPullState = 1;
        return true;
      }
    }
    while ((paramInt >= 0) || (localView.getMeasuredHeight() > getHeight() + this.mScrollView.getScrollY()));
    this.mPullState = 0;
    return true;
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

  private void setHeaderTopMargin(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams();
    localLayoutParams.topMargin = paramInt;
    this.mHeaderView.setLayoutParams(localLayoutParams);
    invalidate();
  }

  public void headerRefreshing()
  {
    this.mHeaderState = 4;
    setHeaderTopMargin(0);
    this.mHeaderImageView.setVisibility(8);
    this.mHeaderImageView.clearAnimation();
    this.mHeaderImageView.setImageDrawable(null);
    this.mHeaderProgressBar.setVisibility(0);
    this.mHeaderTextView.setText(2131296386);
    if (this.mOnHeaderRefreshListener != null)
      this.mOnHeaderRefreshListener.onHeaderRefresh(this);
  }

  public boolean isEnablePullLoadMoreDataStatus()
  {
    return this.enablePullLoadMoreDataStatus;
  }

  public boolean isEnablePullTorefresh()
  {
    return this.enablePullTorefresh;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    addFooterView();
    initContentAdapterView();
  }

  public void onFooterRefreshComplete()
  {
    setHeaderTopMargin(-this.mHeaderViewHeight);
    this.mFooterImageView.setImageResource(2130837708);
    this.mFooterTextView.setText(2131296388);
    this.mFooterProgressBar.setVisibility(8);
    this.mFooterState = 2;
  }

  public void onFooterRefreshComplete(int paramInt)
  {
    if (paramInt > 0)
      this.mFooterView.setVisibility(0);
    while (true)
    {
      setHeaderTopMargin(-this.mHeaderViewHeight);
      this.mFooterImageView.setImageResource(2130837708);
      this.mFooterTextView.setText(2131296388);
      this.mFooterProgressBar.setVisibility(8);
      this.mFooterState = 2;
      return;
      this.mFooterView.setVisibility(8);
    }
  }

  public void onHeaderRefreshComplete()
  {
    setHeaderTopMargin(-this.mHeaderViewHeight);
    this.mHeaderImageView.setVisibility(0);
    this.mHeaderImageView.setImageResource(2130837828);
    this.mHeaderTextView.setText(2131296384);
    this.mHeaderProgressBar.setVisibility(8);
    this.mHeaderState = 2;
    setLastUpdated("最近更新:" + new Date().toLocaleString());
  }

  public void onHeaderRefreshComplete(CharSequence paramCharSequence)
  {
    setLastUpdated(paramCharSequence);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getRawY();
    switch (paramMotionEvent.getAction())
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    do
      while (true)
      {
        return false;
        this.mLastMotionY = i;
      }
    while (!isRefreshViewScroll(i - this.mLastMotionY));
    return true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mLock)
      return true;
    int i = (int)paramMotionEvent.getRawY();
    switch (paramMotionEvent.getAction())
    {
    case 0:
    default:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      int j = i - this.mLastMotionY;
      if (this.mPullState == 1)
      {
        Log.i("PullToRefreshView", " pull down!parent view move!");
        headerPrepareToRefresh(j);
      }
      while (true)
      {
        this.mLastMotionY = i;
        break;
        if (this.mPullState == 0)
        {
          Log.i("PullToRefreshView", "pull up!parent view move!");
          footerPrepareToRefresh(j);
        }
      }
      i = getHeaderTopMargin();
      if (this.mPullState == 1)
      {
        if (i >= 0)
          headerRefreshing();
        else
          setHeaderTopMargin(-this.mHeaderViewHeight);
      }
      else if (this.mPullState == 0)
        if (Math.abs(i) >= this.mHeaderViewHeight + this.mFooterViewHeight)
          footerRefreshing();
        else
          setHeaderTopMargin(-this.mHeaderViewHeight);
    }
  }

  public void setEnablePullLoadMoreDataStatus(boolean paramBoolean)
  {
    this.enablePullLoadMoreDataStatus = paramBoolean;
  }

  public void setEnablePullTorefresh(boolean paramBoolean)
  {
    this.enablePullTorefresh = paramBoolean;
  }

  public void setLastUpdated(CharSequence paramCharSequence)
  {
    if (paramCharSequence != null)
    {
      this.mHeaderUpdateTextView.setVisibility(0);
      this.mHeaderUpdateTextView.setText(paramCharSequence);
      return;
    }
    this.mHeaderUpdateTextView.setVisibility(8);
  }

  public void setOnFooterRefreshListener(OnFooterRefreshListener paramOnFooterRefreshListener)
  {
    this.mOnFooterRefreshListener = paramOnFooterRefreshListener;
  }

  public void setOnHeaderRefreshListener(OnHeaderRefreshListener paramOnHeaderRefreshListener)
  {
    this.mOnHeaderRefreshListener = paramOnHeaderRefreshListener;
  }

  public static abstract interface OnFooterRefreshListener
  {
    public abstract void onFooterRefresh(PullToRefreshView paramPullToRefreshView);
  }

  public static abstract interface OnHeaderRefreshListener
  {
    public abstract void onHeaderRefresh(PullToRefreshView paramPullToRefreshView);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.miloisbadboy.view.PullToRefreshView
 * JD-Core Version:    0.6.2
 */