package com.linj.camera.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.ismartgo.apppub.R.styleable;

public class FocusImageView extends ImageView
{
  private static final int NO_ID = -1;
  public static final String TAG = "FocusImageView";
  private Animation mAnimation = AnimationUtils.loadAnimation(getContext(), 2130968580);
  private int mFocusFailedImg = -1;
  private int mFocusImg = -1;
  private int mFocusSucceedImg = -1;
  private Handler mHandler;

  public FocusImageView(Context paramContext)
  {
    super(paramContext);
    setVisibility(8);
    this.mHandler = new Handler();
  }

  public FocusImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mHandler = new Handler();
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.FocusImageView);
    this.mFocusImg = paramContext.getResourceId(0, -1);
    this.mFocusSucceedImg = paramContext.getResourceId(1, -1);
    this.mFocusFailedImg = paramContext.getResourceId(2, -1);
    paramContext.recycle();
    if ((this.mFocusImg == -1) || (this.mFocusSucceedImg == -1) || (this.mFocusFailedImg == -1))
      throw new RuntimeException("Animation is null");
  }

  public void onFocusFailed()
  {
    setImageResource(this.mFocusFailedImg);
    this.mHandler.removeCallbacks(null, null);
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        FocusImageView.this.setVisibility(8);
      }
    }
    , 1000L);
  }

  public void onFocusSuccess()
  {
    setImageResource(this.mFocusSucceedImg);
    this.mHandler.removeCallbacks(null, null);
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        FocusImageView.this.setVisibility(8);
      }
    }
    , 1000L);
  }

  public void setFocusImg(int paramInt)
  {
    this.mFocusImg = paramInt;
  }

  public void setFocusSucceedImg(int paramInt)
  {
    this.mFocusSucceedImg = paramInt;
  }

  public void startFocus(Point paramPoint)
  {
    if ((this.mFocusImg == -1) || (this.mFocusSucceedImg == -1) || (this.mFocusFailedImg == -1))
      throw new RuntimeException("focus image is null");
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)getLayoutParams();
    localLayoutParams.topMargin = (paramPoint.y - getHeight() / 2);
    localLayoutParams.leftMargin = (paramPoint.x - getWidth() / 2);
    setLayoutParams(localLayoutParams);
    setVisibility(0);
    setImageResource(this.mFocusImg);
    startAnimation(this.mAnimation);
    this.mHandler.postDelayed(new Runnable()
    {
      public void run()
      {
        FocusImageView.this.setVisibility(8);
      }
    }
    , 3500L);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.linj.camera.view.FocusImageView
 * JD-Core Version:    0.6.2
 */