package com.ab.view.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class AbBaseGridView extends LinearLayout
{
  private Context context;
  private LinearLayout.LayoutParams layoutParamsFW = null;
  private GridView mGridView = null;
  private LinearLayout mLinearLayoutFooter = null;
  private LinearLayout mLinearLayoutHeader = null;

  public AbBaseGridView(Context paramContext)
  {
    super(paramContext);
  }

  public AbBaseGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.layoutParamsFW = new LinearLayout.LayoutParams(-1, -2);
    setOrientation(1);
    this.mLinearLayoutHeader = new LinearLayout(paramContext);
    this.mLinearLayoutFooter = new LinearLayout(paramContext);
    this.mGridView = new GridView(paramContext);
    addView(this.mLinearLayoutHeader, this.layoutParamsFW);
    paramContext = new LinearLayout.LayoutParams(-1, -2);
    paramContext.weight = 1.0F;
    this.mGridView.setLayoutParams(paramContext);
    addView(this.mGridView);
    addView(this.mLinearLayoutFooter, this.layoutParamsFW);
  }

  public void addFooterView(View paramView)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLayoutParams.topMargin = 2;
    this.mLinearLayoutFooter.addView(paramView, localLayoutParams);
  }

  public void addHeaderView(View paramView)
  {
    this.mLinearLayoutHeader.addView(paramView, this.layoutParamsFW);
  }

  public GridView getGridView()
  {
    return this.mGridView;
  }

  public void setGridView(GridView paramGridView)
  {
    this.mGridView = paramGridView;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.pullview.AbBaseGridView
 * JD-Core Version:    0.6.2
 */