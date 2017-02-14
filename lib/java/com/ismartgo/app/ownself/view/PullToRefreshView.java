package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.util.AttributeSet;
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

public class PullToRefreshView extends LinearLayout
{
  private static final int PULL_DOWN_STATE = 1;
  private static final int PULL_TO_REFRESH = 1;
  private static final int PULL_UP_STATE = 0;
  private static final int REFRESHING = 3;
  private static final int RELEASE_TO_REFRESH = 2;
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
    this.mFooterView = this.mInflater.inflate(2130903197, this, false);
    this.mFooterTextView = ((TextView)this.mFooterView.findViewById(2131231284));
    this.mFooterProgressBar = ((ProgressBar)this.mFooterView.findViewById(2131231141));
    measureView(this.mFooterView);
    this.mFooterViewHeight = this.mFooterView.getMeasuredHeight();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, this.mFooterViewHeight);
    addView(this.mFooterView, localLayoutParams);
  }

  private void addHeaderView()
  {
    this.mHeaderView = this.mInflater.inflate(2130903198, this, false);
    this.mHeaderImageView = ((ImageView)this.mHeaderView.findViewById(2131231342));
    this.mHeaderTextView = ((TextView)this.mHeaderView.findViewById(2131231344));
    this.mHeaderUpdateTextView = ((TextView)this.mHeaderView.findViewById(2131231345));
    this.mHeaderProgressBar = ((ProgressBar)this.mHeaderView.findViewById(2131231343));
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

  private int getHeaderTopMargin()
  {
    return ((LinearLayout.LayoutParams)this.mHeaderView.getLayoutParams()).topMargin;
  }

  private void headerPrepareToRefresh(int paramInt)
  {
    paramInt = changingHeaderViewTopMargin(paramInt);
    if ((paramInt >= 0) && (this.mHeaderState != 2))
    {
      this.mHeaderTextView.setText(2131296377);
      this.mHeaderUpdateTextView.setVisibility(0);
      this.mHeaderImageView.clearAnimation();
      this.mHeaderImageView.startAnimation(this.mFlipAnimation);
      this.mHeaderState = 2;
    }
    while ((paramInt >= 0) || (paramInt <= -this.mHeaderViewHeight))
      return;
    this.mHeaderImageView.clearAnimation();
    this.mHeaderImageView.startAnimation(this.mFlipAnimation);
    this.mHeaderTextView.setText(2131296377);
    this.mHeaderState = 1;
  }

  private void headerRefreshing()
  {
    this.mHeaderState = 3;
    setHeaderTopMargin(0);
    this.mHeaderImageView.setVisibility(8);
    this.mHeaderImageView.clearAnimation();
    this.mHeaderImageView.setImageDrawable(null);
    this.mHeaderProgressBar.setVisibility(0);
    this.mHeaderTextView.setText("正在刷新...");
    if (this.mOnHeaderRefreshListener != null)
      this.mOnHeaderRefreshListener.onHeaderRefresh(this);
  }

  private void init()
  {
    setOrientation(1);
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
      throw new IllegalArgumentException("This layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
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
    if ((this.mHeaderState == 3) || (this.mFooterState == 3));
    View localView;
    label176: 
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
              break label176;
            if (paramInt <= 0)
              break;
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
          if ((this.mAdapterView.getFirstVisiblePosition() != 0) || (Math.abs(i - j) > 8))
            break;
          this.mPullState = 1;
          return true;
          if (paramInt >= 0)
            break;
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

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    addFooterView();
    initContentAdapterView();
  }

  public void onFooterRefreshComplete()
  {
    setHeaderTopMargin(-this.mHeaderViewHeight);
    this.mFooterImageView.setVisibility(0);
    this.mFooterTextView.setText(2131296378);
    this.mFooterProgressBar.setVisibility(8);
    this.mFooterState = 1;
  }

  public void onHeaderRefreshComplete()
  {
    setHeaderTopMargin(-this.mHeaderViewHeight);
    this.mHeaderImageView.setVisibility(0);
    this.mHeaderImageView.setImageResource(2130837828);
    this.mHeaderTextView.setText("正在刷新...");
    this.mHeaderProgressBar.setVisibility(8);
    this.mHeaderState = 1;
  }

  public void onHeaderRefreshComplete(CharSequence paramCharSequence)
  {
    setLastUpdated(paramCharSequence);
    onHeaderRefreshComplete();
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
    int i = (int)paramMotionEvent.getRawY();
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return super.onTouchEvent(paramMotionEvent);
      this.mLastMotionY = i;
      continue;
      int j = this.mLastMotionY;
      if (this.mPullState == 1)
        headerPrepareToRefresh(i - j);
      this.mLastMotionY = i;
      continue;
      i = getHeaderTopMargin();
      if (this.mPullState == 1)
      {
        if (i >= 0)
          headerRefreshing();
        else
          setHeaderTopMargin(-this.mHeaderViewHeight);
      }
      else if ((this.mPullState == 0) && (Math.abs(i) < this.mHeaderViewHeight + this.mFooterViewHeight))
        setHeaderTopMargin(-this.mHeaderViewHeight);
    }
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
 * Qualified Name:     com.ismartgo.app.ownself.view.PullToRefreshView
 * JD-Core Version:    0.6.2
 */