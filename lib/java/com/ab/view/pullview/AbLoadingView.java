package com.ab.view.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;

public class AbLoadingView extends LinearLayout
{
  private ProgressBar loadingProgressBar;
  private LinearLayout loadingView;
  private Context mContext;

  public AbLoadingView(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public AbLoadingView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    this.mContext = paramContext;
    this.loadingView = new LinearLayout(paramContext);
    this.loadingView.setOrientation(0);
    this.loadingView.setGravity(17);
    this.loadingProgressBar = new ProgressBar(paramContext, null, 16842871);
    paramContext = new LinearLayout.LayoutParams(-2, -2);
    paramContext.gravity = 17;
    this.loadingView.addView(this.loadingProgressBar, paramContext);
    addView(this.loadingView, new LinearLayout.LayoutParams(-1, -1));
  }

  public ProgressBar getFooterProgressBar()
  {
    return this.loadingProgressBar;
  }

  public void setLoadingProgressBar(ProgressBar paramProgressBar)
  {
    this.loadingProgressBar = paramProgressBar;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.pullview.AbLoadingView
 * JD-Core Version:    0.6.2
 */