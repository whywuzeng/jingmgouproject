package com.ismartgo.app.andbase.util;

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
import android.widget.TextView;

public class AbLoadDialogFragment extends AbDialogFragment
{
  private int mBackgroundColor = Color.parseColor("#88838B8B");
  private View mContentView;
  private ImageView mImageView = null;
  private int mIndeterminateDrawable;
  private int mStyle;
  private int mTextColor = -1;
  private int mTextSize = 15;
  private TextView mTextView = null;
  private int mTheme;

  public static AbLoadDialogFragment newInstance(int paramInt1, int paramInt2)
  {
    AbLoadDialogFragment localAbLoadDialogFragment = new AbLoadDialogFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("style", paramInt1);
    localBundle.putInt("theme", paramInt2);
    localAbLoadDialogFragment.setArguments(localBundle);
    return localAbLoadDialogFragment;
  }

  public int getBackgroundColor()
  {
    return this.mBackgroundColor;
  }

  public View getContentView()
  {
    return this.mContentView;
  }

  public int getIndeterminateDrawable()
  {
    return this.mIndeterminateDrawable;
  }

  public int getTextColor()
  {
    return this.mTextColor;
  }

  public int getTextSize()
  {
    return this.mTextSize;
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mStyle = getArguments().getInt("style");
    this.mTheme = getArguments().getInt("theme");
    setStyle(this.mStyle, this.mTheme);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramLayoutInflater = new LinearLayout(getActivity());
    paramLayoutInflater.setBackgroundColor(this.mBackgroundColor);
    paramLayoutInflater.setGravity(17);
    paramLayoutInflater.setOrientation(1);
    paramLayoutInflater.setPadding(20, 20, 20, 20);
    paramLayoutInflater.setMinimumWidth(AbViewUtil.scale(getActivity(), 400.0F));
    this.mImageView = new ImageView(getActivity());
    this.mImageView.setImageResource(this.mIndeterminateDrawable);
    this.mImageView.setScaleType(ImageView.ScaleType.MATRIX);
    this.mTextView = new TextView(getActivity());
    this.mTextView.setText(this.mMessage);
    this.mTextView.setTextColor(this.mTextColor);
    this.mTextView.setTextSize(this.mTextSize);
    this.mTextView.setPadding(5, 5, 5, 5);
    paramLayoutInflater.addView(this.mImageView, new LinearLayout.LayoutParams(-2, -2));
    paramLayoutInflater.addView(this.mTextView, new LinearLayout.LayoutParams(-2, -2));
    this.mImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbLoadDialogFragment.this.load(paramAnonymousView);
      }
    });
    load(this.mImageView);
    this.mContentView = paramLayoutInflater;
    return this.mContentView;
  }

  public void setBackgroundColor(int paramInt)
  {
    this.mBackgroundColor = paramInt;
  }

  public void setIndeterminateDrawable(int paramInt)
  {
    this.mIndeterminateDrawable = paramInt;
  }

  public void setMessage(String paramString)
  {
    this.mMessage = paramString;
    if (this.mTextView != null)
      this.mTextView.setText(this.mMessage);
  }

  public void setTextColor(int paramInt)
  {
    this.mTextColor = paramInt;
  }

  public void setTextSize(int paramInt)
  {
    this.mTextSize = paramInt;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbLoadDialogFragment
 * JD-Core Version:    0.6.2
 */