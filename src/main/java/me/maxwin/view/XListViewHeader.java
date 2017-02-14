package me.maxwin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XListViewHeader extends LinearLayout
{
  public static final int STATE_NORMAL = 0;
  public static final int STATE_READY = 1;
  public static final int STATE_REFRESHING = 2;
  private final int ROTATE_ANIM_DURATION = 180;
  private ImageView mArrowImageView;
  private LinearLayout mContainer;
  private TextView mHintTextView;
  private ProgressBar mProgressBar;
  private Animation mRotateDownAnim;
  private Animation mRotateUpAnim;
  private int mState = 0;
  private TextView mTimeTextView;

  public XListViewHeader(Context paramContext)
  {
    super(paramContext);
    initView(paramContext);
  }

  public XListViewHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView(paramContext);
  }

  private void initView(Context paramContext)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, 0);
    this.mContainer = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903254, null));
    addView(this.mContainer, localLayoutParams);
    setGravity(80);
    this.mArrowImageView = ((ImageView)findViewById(2131231503));
    this.mHintTextView = ((TextView)findViewById(2131231501));
    this.mTimeTextView = ((TextView)findViewById(2131231502));
    this.mProgressBar = ((ProgressBar)findViewById(2131231504));
    this.mRotateUpAnim = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.mRotateUpAnim.setDuration(180L);
    this.mRotateUpAnim.setFillAfter(true);
    this.mRotateDownAnim = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.mRotateDownAnim.setDuration(180L);
    this.mRotateDownAnim.setFillAfter(true);
  }

  public int getVisiableHeight()
  {
    return this.mContainer.getHeight();
  }

  public void setState(int paramInt)
  {
    if (paramInt == this.mState)
      return;
    if (paramInt == 2)
    {
      this.mArrowImageView.clearAnimation();
      this.mArrowImageView.setVisibility(4);
      this.mProgressBar.setVisibility(0);
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
      this.mArrowImageView.setVisibility(0);
      this.mProgressBar.setVisibility(4);
      break;
      if (this.mState == 1)
        this.mArrowImageView.startAnimation(this.mRotateDownAnim);
      if (this.mState == 2)
        this.mArrowImageView.clearAnimation();
      this.mHintTextView.setText("下拉刷新");
      continue;
      if (this.mState != 1)
      {
        this.mArrowImageView.clearAnimation();
        this.mArrowImageView.startAnimation(this.mRotateUpAnim);
        this.mHintTextView.setText("松开刷新数据");
        continue;
        this.mHintTextView.setText("正在加载...");
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.mTimeTextView.setText(str);
      }
    }
  }

  public void setVisiableHeight(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0)
      i = 0;
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mContainer.getLayoutParams();
    localLayoutParams.height = i;
    this.mContainer.setLayoutParams(localLayoutParams);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     me.maxwin.view.XListViewHeader
 * JD-Core Version:    0.6.2
 */