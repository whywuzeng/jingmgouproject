package com.ismartgo.app.ownself.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ismartgo.app.andbase.util.AbDateUtil;
import com.ismartgo.app.andbase.util.AbViewUtil;

public class AbListViewHeader extends LinearLayout
{
  public static final int STATE_NORMAL = 0;
  public static final int STATE_READY = 1;
  public static final int STATE_REFRESHING = 2;
  private final int ROTATE_ANIM_DURATION = 180;
  private Bitmap arrowImage = null;
  private ImageView arrowImageView;
  private int headerHeight;
  private ProgressBar headerProgressBar;
  private TextView headerTimeView;
  private LinearLayout headerView;
  private String lastRefreshTime = null;
  private Context mContext;
  private Animation mRotateDownAnim;
  private Animation mRotateUpAnim;
  private int mState = -1;
  private TextView tipsTextview;

  public AbListViewHeader(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public AbListViewHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    this.mContext = paramContext;
    this.headerView = new LinearLayout(paramContext);
    this.headerView.setOrientation(0);
    this.headerView.setGravity(17);
    AbViewUtil.setPadding(this.headerView, 0, 10, 0, 10);
    Object localObject1 = new FrameLayout(paramContext);
    this.arrowImageView = new ImageView(paramContext);
    this.arrowImageView.setImageResource(2130837828);
    this.headerProgressBar = new ProgressBar(paramContext, null, 16842871);
    this.headerProgressBar.setVisibility(8);
    Object localObject2 = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout.LayoutParams)localObject2).gravity = 17;
    ((LinearLayout.LayoutParams)localObject2).width = AbViewUtil.scale(this.mContext, 70.0F);
    ((LinearLayout.LayoutParams)localObject2).height = AbViewUtil.scale(this.mContext, 70.0F);
    ((FrameLayout)localObject1).addView(this.arrowImageView, (ViewGroup.LayoutParams)localObject2);
    ((FrameLayout)localObject1).addView(this.headerProgressBar, (ViewGroup.LayoutParams)localObject2);
    localObject2 = new LinearLayout(paramContext);
    this.tipsTextview = new TextView(paramContext);
    this.headerTimeView = new TextView(paramContext);
    ((LinearLayout)localObject2).setOrientation(1);
    ((LinearLayout)localObject2).setGravity(16);
    AbViewUtil.setPadding((View)localObject2, 0, 0, 0, 0);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    ((LinearLayout)localObject2).addView(this.tipsTextview, localLayoutParams);
    ((LinearLayout)localObject2).addView(this.headerTimeView, localLayoutParams);
    this.tipsTextview.setTextColor(Color.rgb(107, 107, 107));
    this.headerTimeView.setTextColor(Color.rgb(107, 107, 107));
    AbViewUtil.setTextSize(this.tipsTextview, 30.0F);
    AbViewUtil.setTextSize(this.headerTimeView, 27.0F);
    localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 17;
    localLayoutParams.rightMargin = AbViewUtil.scale(this.mContext, 10.0F);
    paramContext = new LinearLayout(paramContext);
    paramContext.setOrientation(0);
    paramContext.setGravity(17);
    paramContext.addView((View)localObject1, localLayoutParams);
    paramContext.addView((View)localObject2, localLayoutParams);
    localObject1 = new LinearLayout.LayoutParams(-1, -2);
    ((LinearLayout.LayoutParams)localObject1).gravity = 80;
    this.headerView.addView(paramContext, (ViewGroup.LayoutParams)localObject1);
    addView(this.headerView, (ViewGroup.LayoutParams)localObject1);
    AbViewUtil.measureView(this);
    this.headerHeight = getMeasuredHeight();
    this.mRotateUpAnim = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.mRotateUpAnim.setDuration(180L);
    this.mRotateUpAnim.setFillAfter(true);
    this.mRotateDownAnim = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.mRotateDownAnim.setDuration(180L);
    this.mRotateDownAnim.setFillAfter(true);
    setState(0);
  }

  public ImageView getArrowImageView()
  {
    return this.arrowImageView;
  }

  public int getHeaderHeight()
  {
    return this.headerHeight;
  }

  public ProgressBar getHeaderProgressBar()
  {
    return this.headerProgressBar;
  }

  public LinearLayout getHeaderView()
  {
    return this.headerView;
  }

  public int getState()
  {
    return this.mState;
  }

  public int getVisiableHeight()
  {
    return ((LinearLayout.LayoutParams)this.headerView.getLayoutParams()).height;
  }

  public void setArrowImage(int paramInt)
  {
    this.arrowImageView.setImageResource(paramInt);
  }

  public void setBackgroundColor(int paramInt)
  {
    this.headerView.setBackgroundColor(paramInt);
  }

  public void setHeaderProgressBarDrawable(Drawable paramDrawable)
  {
    this.headerProgressBar.setIndeterminateDrawable(paramDrawable);
  }

  public void setRefreshTime(String paramString)
  {
    this.headerTimeView.setText(paramString);
  }

  public void setState(int paramInt)
  {
    if (paramInt == this.mState)
      return;
    if (paramInt == 2)
    {
      this.arrowImageView.clearAnimation();
      this.arrowImageView.setVisibility(4);
      this.headerProgressBar.setVisibility(0);
      switch (paramInt)
      {
      default:
      case 0:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      this.mState = paramInt;
      return;
      this.arrowImageView.setVisibility(0);
      this.headerProgressBar.setVisibility(4);
      break;
      if (this.mState == 1)
        this.arrowImageView.startAnimation(this.mRotateDownAnim);
      if (this.mState == 2)
        this.arrowImageView.clearAnimation();
      this.tipsTextview.setText("下拉刷新");
      if (this.lastRefreshTime == null)
      {
        this.lastRefreshTime = AbDateUtil.getCurrentDate("HH:mm:ss");
        this.headerTimeView.setText("刷新时间：" + this.lastRefreshTime);
      }
      else
      {
        this.headerTimeView.setText("上次刷新时间：" + this.lastRefreshTime);
        continue;
        if (this.mState != 1)
        {
          this.arrowImageView.clearAnimation();
          this.arrowImageView.startAnimation(this.mRotateUpAnim);
          this.tipsTextview.setText("松开刷新");
          this.headerTimeView.setText("上次刷新时间：" + this.lastRefreshTime);
          this.lastRefreshTime = AbDateUtil.getCurrentDate("HH:mm:ss");
          continue;
          this.tipsTextview.setText("正在刷新...");
          this.headerTimeView.setText("本次刷新时间：" + this.lastRefreshTime);
        }
      }
    }
  }

  public void setStateTextSize(int paramInt)
  {
    this.tipsTextview.setTextSize(paramInt);
  }

  public void setTextColor(int paramInt)
  {
    this.tipsTextview.setTextColor(paramInt);
    this.headerTimeView.setTextColor(paramInt);
  }

  public void setTimeTextSize(int paramInt)
  {
    this.headerTimeView.setTextSize(paramInt);
  }

  public void setVisiableHeight(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0)
      i = 0;
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.headerView.getLayoutParams();
    localLayoutParams.height = i;
    this.headerView.setLayoutParams(localLayoutParams);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.ownself.view.AbListViewHeader
 * JD-Core Version:    0.6.2
 */