package com.ismartgo.app.andbase.util;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AbFragment extends Fragment
{
  private AbFragmentOnLoadListener mAbFragmentOnLoadListener = null;
  private int mBackgroundColor = Color.parseColor("#88838B8B");
  private View mContentView;
  private View mIndeterminateView = null;
  private int mLoadDrawable;
  private ImageView mLoadImageView = null;
  public String mLoadMessage = "正在查询,请稍候";
  private TextView mLoadTextView = null;
  private LinearLayout mLoadView = null;
  private int mRefreshDrawable;
  private ImageView mRefreshImageView = null;
  public String mRefreshMessage = "请求出错，请重试";
  private TextView mRefreshTextView = null;
  private LinearLayout mRefreshView = null;
  private int mTextColor = -1;
  private int mTextSize = 15;
  private RelativeLayout rootView = null;

  public AbFragmentOnLoadListener getAbFragmentOnLoadListener()
  {
    return this.mAbFragmentOnLoadListener;
  }

  public int getBackgroundColor()
  {
    return this.mBackgroundColor;
  }

  public View getContentView()
  {
    return this.mContentView;
  }

  public int getLoadDrawable()
  {
    return this.mLoadDrawable;
  }

  public int getRefreshDrawable()
  {
    return this.mRefreshDrawable;
  }

  public int getTextColor()
  {
    return this.mTextColor;
  }

  public int getTextSize()
  {
    return this.mTextSize;
  }

  public void initLoadView()
  {
    this.mLoadView = new LinearLayout(getActivity());
    this.mLoadView.setGravity(17);
    this.mLoadView.setOrientation(1);
    this.mLoadView.setPadding(20, 20, 20, 20);
    this.mLoadView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    this.mLoadImageView = new ImageView(getActivity());
    this.mLoadImageView.setImageResource(this.mLoadDrawable);
    this.mLoadImageView.setScaleType(ImageView.ScaleType.MATRIX);
    this.mLoadTextView = new TextView(getActivity());
    this.mLoadTextView.setText(this.mLoadMessage);
    this.mLoadTextView.setTextColor(this.mTextColor);
    this.mLoadTextView.setTextSize(this.mTextSize);
    this.mLoadTextView.setPadding(5, 5, 5, 5);
    this.mLoadView.addView(this.mLoadImageView, new LinearLayout.LayoutParams(-2, -2));
    this.mLoadView.addView(this.mLoadTextView, new LinearLayout.LayoutParams(-2, -2));
    this.mLoadImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbFragment.this.load(paramAnonymousView);
      }
    });
  }

  public void initRefreshView()
  {
    this.mRefreshView = new LinearLayout(getActivity());
    this.mRefreshView.setGravity(17);
    this.mRefreshView.setOrientation(1);
    this.mRefreshView.setPadding(20, 20, 20, 20);
    this.mRefreshView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    this.mRefreshImageView = new ImageView(getActivity());
    this.mRefreshImageView.setImageResource(this.mRefreshDrawable);
    this.mRefreshImageView.setScaleType(ImageView.ScaleType.MATRIX);
    this.mRefreshTextView = new TextView(getActivity());
    this.mRefreshTextView.setText(this.mRefreshMessage);
    this.mRefreshTextView.setTextColor(this.mTextColor);
    this.mRefreshTextView.setTextSize(this.mTextSize);
    this.mRefreshTextView.setPadding(5, 5, 5, 5);
    this.mRefreshView.addView(this.mRefreshImageView, new LinearLayout.LayoutParams(-2, -2));
    this.mRefreshView.addView(this.mRefreshTextView, new LinearLayout.LayoutParams(-2, -2));
    this.mRefreshImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbFragment.this.load(paramAnonymousView);
      }
    });
  }

  public void load(View paramView)
  {
    if (this.mAbFragmentOnLoadListener != null)
      this.mAbFragmentOnLoadListener.onLoad();
    this.mIndeterminateView = paramView;
    AbAnimationUtil.playRotateAnimation(this.mIndeterminateView, 300L, -1, 1);
  }

  public void loadFinish()
  {
    loadStop(this.mIndeterminateView);
  }

  public void loadStop(final View paramView)
  {
    if (paramView == null)
      return;
    paramView.postDelayed(new Runnable()
    {
      public void run()
      {
        paramView.clearAnimation();
      }
    }
    , 200L);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  public View onCreateContentView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return null;
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.rootView = new RelativeLayout(getActivity());
    this.rootView.setBackgroundColor(this.mBackgroundColor);
    this.mContentView = onCreateContentView(paramLayoutInflater, paramViewGroup, paramBundle);
    setResource();
    showLoadView();
    return this.rootView;
  }

  public void setAbFragmentOnLoadListener(AbFragmentOnLoadListener paramAbFragmentOnLoadListener)
  {
    this.mAbFragmentOnLoadListener = paramAbFragmentOnLoadListener;
  }

  public void setBackgroundColor(int paramInt)
  {
    this.mBackgroundColor = paramInt;
  }

  public void setLoadDrawable(int paramInt)
  {
    this.mLoadDrawable = paramInt;
    if (this.mLoadImageView != null)
      this.mLoadImageView.setBackgroundResource(paramInt);
  }

  public void setLoadMessage(String paramString)
  {
    this.mLoadMessage = paramString;
    if (this.mLoadTextView != null)
      this.mLoadTextView.setText(this.mLoadMessage);
  }

  public void setRefreshDrawable(int paramInt)
  {
    this.mRefreshDrawable = paramInt;
    if (this.mRefreshImageView != null)
      this.mRefreshImageView.setBackgroundResource(paramInt);
  }

  public void setRefreshMessage(String paramString)
  {
    this.mRefreshMessage = paramString;
    if (this.mRefreshTextView != null)
      this.mRefreshTextView.setText(this.mRefreshMessage);
  }

  public void setResource()
  {
  }

  public void setTextColor(int paramInt)
  {
    this.mTextColor = paramInt;
  }

  public void setTextSize(int paramInt)
  {
    this.mTextSize = paramInt;
  }

  public void showContentView()
  {
    if ((this.rootView.getChildCount() > 0) && (this.mContentView == this.rootView.getChildAt(0)))
      return;
    this.rootView.removeAllViews();
    AbViewUtil.removeSelfFromParent(this.mContentView);
    this.rootView.addView(this.mContentView, new LinearLayout.LayoutParams(-1, -1));
  }

  public void showLoadView()
  {
    if ((this.rootView.getChildCount() > 0) && (this.mLoadView == this.rootView.getChildAt(0)))
      return;
    this.rootView.removeAllViews();
    if (this.mLoadView == null)
      initLoadView();
    AbViewUtil.removeSelfFromParent(this.mLoadView);
    this.rootView.addView(this.mLoadView);
    load(this.mLoadImageView);
  }

  public void showRefreshView()
  {
    if ((this.rootView.getChildCount() > 0) && (this.mRefreshView == this.rootView.getChildAt(0)))
    {
      loadStop(this.mRefreshImageView);
      return;
    }
    this.rootView.removeAllViews();
    if (this.mRefreshView == null)
      initRefreshView();
    AbViewUtil.removeSelfFromParent(this.mRefreshView);
    this.rootView.addView(this.mRefreshView);
  }

  public static abstract interface AbFragmentOnLoadListener
  {
    public abstract void onLoad();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbFragment
 * JD-Core Version:    0.6.2
 */