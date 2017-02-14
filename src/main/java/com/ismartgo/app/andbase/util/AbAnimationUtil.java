package com.ismartgo.app.andbase.util;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AbAnimationUtil
{
  public static final long aniDurationMillis = 1L;

  public static void largerView(View paramView, float paramFloat)
  {
    if (paramView == null)
      return;
    paramView.bringToFront();
    scaleView(paramView, 1.0F + paramFloat / paramView.getWidth());
  }

  private void playJumpAnimation(final View paramView, final float paramFloat)
  {
    float f = -paramFloat;
    AnimationSet localAnimationSet = new AnimationSet(true);
    localAnimationSet.addAnimation(new TranslateAnimation(0.0F, 0.0F, 0.0F, f));
    localAnimationSet.setDuration(300L);
    localAnimationSet.setInterpolator(new AccelerateDecelerateInterpolator());
    localAnimationSet.setFillAfter(true);
    localAnimationSet.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        AbAnimationUtil.this.playLandAnimation(paramView, paramFloat);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    paramView.startAnimation(localAnimationSet);
  }

  private void playLandAnimation(final View paramView, final float paramFloat)
  {
    float f = -paramFloat;
    AnimationSet localAnimationSet = new AnimationSet(true);
    localAnimationSet.addAnimation(new TranslateAnimation(0.0F, 0.0F, f, 0.0F));
    localAnimationSet.setDuration(200L);
    localAnimationSet.setInterpolator(new AccelerateInterpolator());
    localAnimationSet.setFillAfter(true);
    localAnimationSet.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        paramView.postDelayed(new Runnable()
        {
          public void run()
          {
            AbAnimationUtil.this.playJumpAnimation(this.val$view, this.val$offsetY);
          }
        }
        , 2000L);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    paramView.startAnimation(localAnimationSet);
  }

  public static void playRotateAnimation(View paramView, long paramLong, int paramInt1, int paramInt2)
  {
    AnimationSet localAnimationSet = new AnimationSet(true);
    RotateAnimation localRotateAnimation = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
    localRotateAnimation.setDuration(paramLong);
    localRotateAnimation.setRepeatCount(paramInt1);
    localRotateAnimation.setRepeatMode(paramInt2);
    localRotateAnimation.setInterpolator(paramView.getContext(), 17432582);
    localAnimationSet.addAnimation(localRotateAnimation);
    paramView.startAnimation(localAnimationSet);
  }

  public static void restoreLargerView(View paramView, float paramFloat)
  {
    if (paramView == null)
      return;
    scaleView(paramView, -1.0F * (1.0F + paramFloat / paramView.getWidth()));
  }

  private static void scaleView(View paramView, float paramFloat)
  {
    if (paramFloat == 0.0F)
      return;
    if (paramFloat > 0.0F);
    for (ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, paramFloat, 1.0F, paramFloat, 1, 0.5F, 1, 0.5F); ; localScaleAnimation = new ScaleAnimation(-1.0F * paramFloat, 1.0F, -1.0F * paramFloat, 1.0F, 1, 0.5F, 1, 0.5F))
    {
      localScaleAnimation.setDuration(1L);
      localScaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
      localScaleAnimation.setFillAfter(true);
      paramView.startAnimation(localScaleAnimation);
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbAnimationUtil
 * JD-Core Version:    0.6.2
 */