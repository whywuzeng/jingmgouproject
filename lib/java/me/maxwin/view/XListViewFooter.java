package me.maxwin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class XListViewFooter extends LinearLayout
{
  public static final int STATE_LOADING = 2;
  public static final int STATE_NORMAL = 0;
  public static final int STATE_READY = 1;
  private View mContentView;
  private Context mContext;
  private TextView mHintView;
  private View mProgressBar;

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
    paramContext = (LinearLayout)LayoutInflater.from(this.mContext).inflate(2130903253, null);
    addView(paramContext);
    paramContext.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    this.mContentView = paramContext.findViewById(2131231122);
    this.mProgressBar = paramContext.findViewById(2131231498);
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
    this.mHintView.setVisibility(8);
    this.mProgressBar.setVisibility(0);
  }

  public void normal()
  {
    this.mHintView.setVisibility(0);
    this.mProgressBar.setVisibility(8);
  }

  public void setBottomMargin(int paramInt)
  {
    if (paramInt < 0)
      return;
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.bottomMargin = paramInt;
    this.mContentView.setLayoutParams(localLayoutParams);
  }

  public void setHintGone()
  {
    this.mHintView.setVisibility(4);
  }

  public void setHintVisible()
  {
    this.mHintView.setVisibility(0);
  }

  public void setState(int paramInt)
  {
    this.mProgressBar.setVisibility(4);
    this.mHintView.setVisibility(4);
    if (paramInt == 1)
    {
      this.mHintView.setVisibility(0);
      this.mHintView.setText("上拉加载更多");
      return;
    }
    if (paramInt == 2)
    {
      this.mProgressBar.setVisibility(0);
      this.mHintView.setVisibility(0);
      this.mHintView.setText("加载中...");
      return;
    }
    this.mHintView.setVisibility(0);
    this.mHintView.setText("上拉加载更多");
  }

  public void show()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContentView.getLayoutParams();
    localLayoutParams.height = -2;
    this.mContentView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     me.maxwin.view.XListViewFooter
 * JD-Core Version:    0.6.2
 */