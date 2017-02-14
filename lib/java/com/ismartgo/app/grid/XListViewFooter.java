package com.ismartgo.app.grid;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class XListViewFooter extends LinearLayout
{
  public static final int STATE_LOADING = 2;
  public static final int STATE_NORMAL = 0;
  public static final int STATE_READY = 1;
  public static final int STATE_REFRESH = 3;
  private View mContentView;
  private Context mContext;
  private TextView mHintView;
  private ImageView mImageView;

  public XListViewFooter(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public XListViewFooter(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    this.mContext = paramContext;
    paramContext = (LinearLayout)LayoutInflater.from(this.mContext).inflate(2130903123, null);
    addView(paramContext);
    paramContext.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    this.mContentView = paramContext.findViewById(2131231122);
    this.mImageView = ((ImageView)paramContext.findViewById(2131231123));
    ((AnimationDrawable)this.mImageView.getDrawable()).start();
    this.mHintView = ((TextView)paramContext.findViewById(2131231124));
  }

  public int getBottomMargin()
  {
    return ((LinearLayout.LayoutParams)this.mContentView.getLayoutParams()).bottomMargin;
  }

  public void hide()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.height = 0;
    this.mContentView.setLayoutParams(localLayoutParams);
  }

  public void loading()
  {
    this.mImageView.setVisibility(0);
  }

  public void normal()
  {
    this.mHintView.setVisibility(8);
  }

  public void setBottomMargin(int paramInt)
  {
    if (paramInt < 0)
      return;
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.bottomMargin = paramInt;
    this.mContentView.setLayoutParams(localLayoutParams);
  }

  public void setState(int paramInt)
  {
    if (paramInt == 1)
    {
      this.mImageView.setVisibility(0);
      this.mHintView.setVisibility(8);
    }
    do
    {
      return;
      if (paramInt == 2)
      {
        this.mImageView.setVisibility(0);
        this.mHintView.setVisibility(8);
        return;
      }
      if (paramInt == 0)
      {
        this.mImageView.setVisibility(8);
        this.mHintView.setVisibility(0);
        return;
      }
    }
    while (paramInt != 3);
    this.mImageView.setVisibility(8);
    this.mHintView.setVisibility(8);
  }

  public void show()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.height = -2;
    this.mContentView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.grid.XListViewFooter
 * JD-Core Version:    0.6.2
 */