package com.ab.view.pullview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ab.util.AbViewUtil;

public class AbListViewFooter extends LinearLayout
{
  public static final int STATE_EMPTY = 4;
  public static final int STATE_LOADING = 2;
  public static final int STATE_NO = 3;
  public static final int STATE_READY = 1;
  private int footerHeight;
  private ProgressBar footerProgressBar;
  private TextView footerTextView;
  private LinearLayout footerView;
  private Context mContext;

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
    this.footerTextView = new TextView(paramContext);
    this.footerTextView.setGravity(16);
    setTextColor(Color.rgb(107, 107, 107));
    this.footerTextView.setTextSize(15.0F);
    this.footerTextView.setMinimumHeight(50);
    this.footerView.setPadding(0, 10, 0, 10);
    this.footerProgressBar = new ProgressBar(paramContext, null, 16842871);
    this.footerProgressBar.setVisibility(8);
    paramContext = new LinearLayout.LayoutParams(-2, -2);
    paramContext.gravity = 17;
    paramContext.width = 50;
    paramContext.height = 50;
    paramContext.rightMargin = 10;
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
      this.footerTextView.setText("载入更多");
    }
    do
    {
      return;
      if (paramInt == 2)
      {
        this.footerView.setVisibility(0);
        this.footerTextView.setVisibility(0);
        this.footerProgressBar.setVisibility(0);
        this.footerTextView.setText("正在加载...");
        return;
      }
      if (paramInt == 3)
      {
        this.footerView.setVisibility(8);
        this.footerTextView.setVisibility(0);
        this.footerProgressBar.setVisibility(8);
        this.footerTextView.setText("已是全部");
        return;
      }
    }
    while (paramInt != 4);
    this.footerView.setVisibility(8);
    this.footerTextView.setVisibility(8);
    this.footerProgressBar.setVisibility(8);
    this.footerTextView.setText("没有数据");
  }

  public void setTextColor(int paramInt)
  {
    this.footerTextView.setTextColor(paramInt);
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
 * Qualified Name:     com.ab.view.pullview.AbListViewFooter
 * JD-Core Version:    0.6.2
 */