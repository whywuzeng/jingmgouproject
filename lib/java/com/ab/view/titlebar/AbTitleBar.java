package com.ab.view.titlebar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import com.ab.util.AbStrUtil;
import com.ab.util.AbViewUtil;

public class AbTitleBar extends LinearLayout
{
  public LinearLayout.LayoutParams layoutParamsFF = null;
  public LinearLayout.LayoutParams layoutParamsFW = null;
  public LinearLayout.LayoutParams layoutParamsWF = null;
  public LinearLayout.LayoutParams layoutParamsWW = null;
  protected ImageView logoLineView = null;
  protected ImageView logoView = null;
  protected ImageView logoView2 = null;
  public int mAbTitleBarID = 1;
  private Activity mActivity;
  public LayoutInflater mInflater;
  private PopupWindow popupWindow;
  protected LinearLayout rightLayout = null;
  private LinearLayout.LayoutParams rightViewLayoutParams = null;
  protected Button titleSmallTextBtn = null;
  protected Button titleTextBtn = null;
  protected LinearLayout titleTextLayout = null;
  private LinearLayout.LayoutParams titleTextLayoutParams = null;

  public AbTitleBar(Context paramContext)
  {
    super(paramContext);
    ininTitleBar(paramContext);
  }

  public AbTitleBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    ininTitleBar(paramContext);
  }

  public void addRightView(int paramInt)
  {
    this.rightLayout.setVisibility(0);
    this.rightLayout.addView(this.mInflater.inflate(paramInt, null), this.layoutParamsFF);
  }

  public void addRightView(View paramView)
  {
    this.rightLayout.setVisibility(0);
    this.rightLayout.addView(paramView, this.layoutParamsFF);
  }

  public void clearRightView()
  {
    this.rightLayout.removeAllViews();
  }

  public ImageView getLogoView()
  {
    return this.logoView;
  }

  public ImageView getLogoView2()
  {
    return this.logoView2;
  }

  public LinearLayout getRightLayout()
  {
    return this.rightLayout;
  }

  public Button getTitleSmallTextButton()
  {
    return this.titleSmallTextBtn;
  }

  public Button getTitleTextButton()
  {
    return this.titleTextBtn;
  }

  public LinearLayout getTitleTextLayout()
  {
    return this.titleTextLayout;
  }

  public void hideWindow()
  {
    if (this.popupWindow != null)
      this.popupWindow.dismiss();
  }

  public void ininTitleBar(Context paramContext)
  {
    this.mActivity = ((Activity)paramContext);
    setOrientation(0);
    setId(this.mAbTitleBarID);
    this.mInflater = LayoutInflater.from(paramContext);
    this.layoutParamsFF = new LinearLayout.LayoutParams(-1, -1);
    this.layoutParamsFW = new LinearLayout.LayoutParams(-1, -2);
    this.layoutParamsWF = new LinearLayout.LayoutParams(-2, -1);
    this.layoutParamsWW = new LinearLayout.LayoutParams(-2, -2);
    this.layoutParamsWW.gravity = 16;
    this.titleTextLayoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0F);
    this.titleTextLayoutParams.gravity = 16;
    this.rightViewLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    this.rightViewLayoutParams.gravity = 16;
    this.titleTextLayout = new LinearLayout(paramContext);
    this.titleTextLayout.setOrientation(1);
    this.titleTextLayout.setGravity(16);
    this.titleTextLayout.setPadding(0, 0, 0, 0);
    this.titleTextBtn = new Button(paramContext);
    this.titleTextBtn.setTextColor(Color.rgb(255, 255, 255));
    this.titleTextBtn.setTextSize(20.0F);
    this.titleTextBtn.setPadding(5, 0, 5, 0);
    this.titleTextBtn.setGravity(16);
    this.titleTextBtn.setBackgroundDrawable(null);
    this.titleTextBtn.setSingleLine();
    this.titleTextLayout.addView(this.titleTextBtn, new LinearLayout.LayoutParams(-2, -2, 1.0F));
    this.titleSmallTextBtn = new Button(paramContext);
    this.titleSmallTextBtn.setTextColor(Color.rgb(255, 255, 255));
    this.titleSmallTextBtn.setTextSize(15.0F);
    this.titleSmallTextBtn.setPadding(6, 0, 5, 0);
    this.titleSmallTextBtn.setGravity(16);
    this.titleSmallTextBtn.setBackgroundDrawable(null);
    this.titleSmallTextBtn.setSingleLine();
    this.titleTextLayout.addView(this.titleSmallTextBtn, new LinearLayout.LayoutParams(-2, 0));
    this.logoView = new ImageView(paramContext);
    this.logoView.setVisibility(8);
    this.logoLineView = new ImageView(paramContext);
    this.logoLineView.setVisibility(8);
    this.logoView2 = new ImageView(paramContext);
    this.logoView2.setVisibility(8);
    addView(this.logoView, this.layoutParamsWW);
    addView(this.logoLineView, this.layoutParamsWW);
    addView(this.logoView2, this.layoutParamsWW);
    addView(this.titleTextLayout, this.titleTextLayoutParams);
    this.rightLayout = new LinearLayout(paramContext);
    this.rightLayout.setOrientation(0);
    this.rightLayout.setGravity(5);
    this.rightLayout.setPadding(0, 0, 0, 0);
    this.rightLayout.setHorizontalGravity(5);
    this.rightLayout.setGravity(16);
    this.rightLayout.setVisibility(8);
    addView(this.rightLayout, this.rightViewLayoutParams);
    this.logoView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbTitleBar.this.mActivity.finish();
      }
    });
  }

  public void setChildViewFillParent(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.titleTextLayoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0F);
      this.titleTextLayoutParams.gravity = 16;
      this.titleTextLayout.setLayoutParams(this.titleTextLayoutParams);
      this.rightViewLayoutParams = new LinearLayout.LayoutParams(-2, -2);
      this.rightViewLayoutParams.gravity = 16;
      this.rightLayout.setLayoutParams(this.rightViewLayoutParams);
      return;
    }
    this.titleTextLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    this.titleTextLayoutParams.gravity = 16;
    this.titleTextLayout.setLayoutParams(this.titleTextLayoutParams);
    this.rightViewLayoutParams = new LinearLayout.LayoutParams(-2, -2, 1.0F);
    this.rightViewLayoutParams.gravity = 16;
    this.rightLayout.setLayoutParams(this.rightViewLayoutParams);
  }

  public void setLogo(int paramInt)
  {
    this.logoView.setVisibility(0);
    this.logoView.setBackgroundResource(paramInt);
  }

  public void setLogo(Drawable paramDrawable)
  {
    this.logoView.setVisibility(0);
    this.logoView.setBackgroundDrawable(paramDrawable);
  }

  public void setLogo2(int paramInt)
  {
    this.logoView2.setVisibility(0);
    this.logoView2.setBackgroundResource(paramInt);
  }

  public void setLogo2(Drawable paramDrawable)
  {
    this.logoView2.setVisibility(0);
    this.logoView2.setBackgroundDrawable(paramDrawable);
  }

  public void setLogo2OnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.logoView2.setOnClickListener(paramOnClickListener);
  }

  public void setLogoLine(int paramInt)
  {
    this.logoLineView.setVisibility(0);
    this.logoLineView.setBackgroundResource(paramInt);
  }

  public void setLogoLine(Drawable paramDrawable)
  {
    this.logoLineView.setVisibility(0);
    this.logoLineView.setBackgroundDrawable(paramDrawable);
  }

  public void setLogoOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.logoView.setOnClickListener(paramOnClickListener);
  }

  public void setTitleBarBackground(int paramInt)
  {
    setBackgroundResource(paramInt);
  }

  public void setTitleBarBackgroundColor(int paramInt)
  {
    setBackgroundColor(paramInt);
  }

  public void setTitleBarBackgroundDrawable(Drawable paramDrawable)
  {
    setBackgroundDrawable(paramDrawable);
  }

  public void setTitleBarGravity(int paramInt1, int paramInt2)
  {
    AbViewUtil.measureView(this.rightLayout);
    AbViewUtil.measureView(this.logoView);
    int i = this.logoView.getMeasuredWidth();
    int j = this.rightLayout.getMeasuredWidth();
    this.titleTextLayoutParams.rightMargin = 0;
    this.titleTextLayoutParams.leftMargin = 0;
    if ((paramInt1 == 1) || (paramInt1 == 17))
      if ((i == 0) && (j == 0))
        this.titleTextLayout.setGravity(1);
    do
    {
      do
      {
        return;
        if (paramInt2 == 5)
        {
          if (j != 0)
            this.titleTextBtn.setPadding(j / 3 * 2, 0, 0, 0);
          this.titleTextBtn.setGravity(17);
          this.rightLayout.setHorizontalGravity(5);
        }
      }
      while ((paramInt2 != 17) && (paramInt2 != 1));
      this.titleTextLayout.setGravity(1);
      this.rightLayout.setHorizontalGravity(3);
      this.titleTextBtn.setGravity(17);
      paramInt1 = i - j;
      if (paramInt1 > 0)
      {
        this.titleTextLayoutParams.rightMargin = paramInt1;
        return;
      }
      this.titleTextLayoutParams.leftMargin = Math.abs(paramInt1);
      return;
      if ((paramInt1 == 3) && (paramInt2 == 5))
      {
        this.titleTextLayout.setGravity(3);
        this.rightLayout.setHorizontalGravity(5);
        return;
      }
      if ((paramInt1 == 5) && (paramInt2 == 5))
      {
        this.titleTextLayout.setGravity(5);
        this.rightLayout.setHorizontalGravity(5);
        return;
      }
    }
    while ((paramInt1 != 3) || (paramInt2 != 3));
    this.titleTextLayout.setGravity(3);
    this.rightLayout.setHorizontalGravity(3);
  }

  public void setTitleSmallText(int paramInt)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    this.titleSmallTextBtn.setLayoutParams(localLayoutParams);
    this.titleSmallTextBtn.setText(paramInt);
  }

  public void setTitleSmallText(String paramString)
  {
    if (AbStrUtil.isEmpty(paramString))
    {
      paramString = new LinearLayout.LayoutParams(-2, 0);
      this.titleSmallTextBtn.setLayoutParams(paramString);
      this.titleSmallTextBtn.setText("");
      return;
    }
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    this.titleSmallTextBtn.setLayoutParams(localLayoutParams);
    this.titleSmallTextBtn.setText(paramString);
  }

  public void setTitleText(int paramInt)
  {
    this.titleTextBtn.setText(paramInt);
  }

  public void setTitleText(String paramString)
  {
    this.titleTextBtn.setText(paramString);
  }

  public void setTitleTextBackgroundDrawable(Drawable paramDrawable)
  {
    this.titleTextBtn.setBackgroundDrawable(paramDrawable);
  }

  public void setTitleTextBackgroundResource(int paramInt)
  {
    this.titleTextBtn.setBackgroundResource(paramInt);
  }

  public void setTitleTextBold(boolean paramBoolean)
  {
    TextPaint localTextPaint = this.titleTextBtn.getPaint();
    if (paramBoolean)
    {
      localTextPaint.setFakeBoldText(true);
      return;
    }
    localTextPaint.setFakeBoldText(false);
  }

  public void setTitleTextDropDown(final View paramView)
  {
    if (paramView == null)
      return;
    setTitleTextOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AbTitleBar.this.showWindow(AbTitleBar.this.titleTextBtn, paramView, true);
      }
    });
  }

  public void setTitleTextMargin(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.titleTextLayoutParams.setMargins(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setTitleTextOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.titleTextBtn.setOnClickListener(paramOnClickListener);
  }

  public void setTitleTextSize(int paramInt)
  {
    this.titleTextBtn.setTextSize(paramInt);
  }

  public void showWindow(View paramView1, View paramView2, boolean paramBoolean)
  {
    AbViewUtil.measureView(paramView2);
    int i = paramView1.getMeasuredWidth();
    int j = (getMeasuredHeight() - paramView1.getMeasuredHeight()) / 2;
    if (paramView2.getMeasuredWidth() > paramView1.getMeasuredWidth())
      i = paramView2.getMeasuredWidth();
    if (paramBoolean);
    for (this.popupWindow = new PopupWindow(paramView2, i + 10, -2, true); ; this.popupWindow = new PopupWindow(paramView2, -1, -2, true))
    {
      this.popupWindow.setFocusable(true);
      this.popupWindow.setOutsideTouchable(true);
      this.popupWindow.setBackgroundDrawable(new ColorDrawable(17170445));
      this.popupWindow.showAsDropDown(paramView1, 0, j + 2);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.titlebar.AbTitleBar
 * JD-Core Version:    0.6.2
 */