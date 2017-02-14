package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ismartgo.app.andbase.util.AbViewUtil;

public class AbListViewFooter extends LinearLayout
{
  public static final int STATE_EMPTY = 4;
  public static final int STATE_LOADING = 2;
  public static final int STATE_NO = 3;
  public static final int STATE_READY = 1;
  private int footerHeight;
  public ProgressBar footerProgressBar;
  private TextView footerTextView;
  public LinearLayout footerView;
  private Context mContext;
  private int mState = -1;

  public AbListViewFooter(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public AbListViewFooter(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
    setState(1);
  }

  private void initView(Context paramContext)
  {
    this.mContext = paramContext;
    this.footerView = new LinearLayout(paramContext);
    this.footerView.setOrientation(0);
    this.footerView.setGravity(17);
    this.footerView.setMinimumHeight(AbViewUtil.scale(this.mContext, 100.0F));
    this.footerTextView = new TextView(paramContext);
    this.footerTextView.setGravity(16);
    setTextColor(Color.rgb(107, 107, 107));
    AbViewUtil.setTextSize(this.footerTextView, 30.0F);
    AbViewUtil.setPadding(this.footerView, 0, 10, 0, 10);
    this.footerProgressBar = new ProgressBar(paramContext, null, 16842871);
    this.footerProgressBar.setVisibility(8);
    paramContext = new LinearLayout.LayoutParams(-2, -2);
    paramContext.gravity = 17;
    paramContext.width = AbViewUtil.scale(this.mContext, 70.0F);
    paramContext.height = AbViewUtil.scale(this.mContext, 70.0F);
    paramContext.rightMargin = AbViewUtil.scale(this.mContext, 10.0F);
    this.footerView.addView(this.footerProgressBar, paramContext);
    paramContext = new LinearLayout.LayoutParams(-2, -2);
    this.footerView.addView(this.footerTextView, paramContext);
    paramContext = new LinearLayout.LayoutParams(-1, -2);
    addView(this.footerView, paramContext);
    AbViewUtil.measureView(this);
    this.footerHeight = getMeasuredHeight();
  }

  public int getFooterHeight()
  {
    return this.footerHeight;
  }

  public ProgressBar getFooterProgressBar()
  {
    return this.footerProgressBar;
  }

  public int getState()
  {
    return this.mState;
  }

  public int getVisiableHeight()
  {
    return ((LinearLayout.LayoutParams)this.footerView.getLayoutParams()).height;
  }

  public void hide()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.footerView.getLayoutParams();
    localLayoutParams.height = 0;
    this.footerView.setLayoutParams(localLayoutParams);
    this.footerView.setVisibility(8);
  }

  public void setBackgroundColor(int paramInt)
  {
    this.footerView.setBackgroundColor(paramInt);
  }

  public void setFooterProgressBarDrawable(Drawable paramDrawable)
  {
    this.footerProgressBar.setIndeterminateDrawable(paramDrawable);
  }

  public void setState(int paramInt)
  {
    if (paramInt == 1)
    {
      this.footerView.setVisibility(0);
      this.footerTextView.setVisibility(0);
      this.footerProgressBar.setVisibility(8);
      this.footerTextView.setText("");
    }
    while (true)
    {
      this.mState = paramInt;
      return;
      if (paramInt == 2)
      {
        this.footerView.setVisibility(0);
        this.footerTextView.setVisibility(0);
        this.footerProgressBar.setVisibility(0);
        this.footerTextView.setText("正在加载...");
      }
      else if (paramInt == 3)
      {
        this.footerView.setVisibility(8);
        this.footerTextView.setVisibility(0);
        this.footerProgressBar.setVisibility(8);
        this.footerTextView.setText("没有了！");
      }
      else if (paramInt == 4)
      {
        this.footerView.setVisibility(8);
        this.footerTextView.setVisibility(8);
        this.footerProgressBar.setVisibility(8);
        this.footerTextView.setText("没有数据");
      }
    }
  }

  public void setTextColor(int paramInt)
  {
    this.footerTextView.setTextColor(paramInt);
  }

  public void setTextSize(int paramInt)
  {
    this.footerTextView.setTextSize(paramInt);
  }

  public void setVisiableHeight(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0)
      i = 0;
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.footerView.getLayoutParams();
    localLayoutParams.height = i;
    this.footerView.setLayoutParams(localLayoutParams);
  }

  public void show()
  {
    this.footerView.setVisibility(0);
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.footerView.getLayoutParams();
    localLayoutParams.height = -2;
    this.footerView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.AbListViewFooter
 * JD-Core Version:    0.6.2
 */