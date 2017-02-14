package com.ab.view.titlebar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import com.ab.util.AbViewUtil;

public class AbBottomBar extends LinearLayout
{
  public int diaplayWidth = 320;
  private Activity mActivity;
  public int mBottomBarID = 2;
  public LayoutInflater mInflater;
  private WindowManager mWindowManager = null;
  private PopupWindow popupWindow;

  public AbBottomBar(Context paramContext)
  {
    super(paramContext);
    ininBottomBar(paramContext);
  }

  public AbBottomBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    ininBottomBar(paramContext);
  }

  private void showWindow(View paramView1, View paramView2, boolean paramBoolean)
  {
    AbViewUtil.measureView(paramView2);
    int i = paramView1.getMeasuredWidth();
    if (paramView2.getMeasuredWidth() > paramView1.getMeasuredWidth())
      i = paramView2.getMeasuredWidth();
    int m = getMeasuredHeight();
    if (paramBoolean);
    for (this.popupWindow = new PopupWindow(paramView2, i, -2, true); ; this.popupWindow = new PopupWindow(paramView2, -1, -2, true))
    {
      paramView2 = new int[2];
      paramView1.getLocationInWindow(paramView2);
      int k = paramView2[0] - paramView1.getLeft();
      int j = k;
      if (k + i >= this.diaplayWidth)
        j = this.diaplayWidth - i - 2;
      this.popupWindow.setFocusable(true);
      this.popupWindow.setOutsideTouchable(true);
      this.popupWindow.setBackgroundDrawable(new ColorDrawable(17170445));
      this.popupWindow.showAtLocation(paramView1, 83, j, m + 2);
      return;
    }
  }

  public void ininBottomBar(Context paramContext)
  {
    this.mActivity = ((Activity)paramContext);
    setOrientation(0);
    setId(this.mBottomBarID);
    setPadding(0, 0, 0, 0);
    this.mInflater = LayoutInflater.from(paramContext);
    this.mWindowManager = this.mActivity.getWindowManager();
    this.diaplayWidth = this.mWindowManager.getDefaultDisplay().getWidth();
  }

  public void setBottomBarBackground(int paramInt)
  {
    setBackgroundResource(paramInt);
  }

  public void setBottomBarBackgroundColor(int paramInt)
  {
    setBackgroundColor(paramInt);
  }

  public void setBottomBarBackgroundDrawable(Drawable paramDrawable)
  {
    setBackgroundDrawable(paramDrawable);
  }

  public void setBottomView(int paramInt)
  {
    setBottomView(this.mInflater.inflate(paramInt, null));
  }

  public void setBottomView(View paramView)
  {
    removeAllViews();
    addView(paramView, new LinearLayout.LayoutParams(-1, -2));
  }

  public void setDropDown(final View paramView1, final View paramView2)
  {
    if ((paramView1 == null) || (paramView2 == null))
      return;
    paramView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbBottomBar.this.showWindow(paramView1, paramView2, true);
      }
    });
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.titlebar.AbBottomBar
 * JD-Core Version:    0.6.2
 */