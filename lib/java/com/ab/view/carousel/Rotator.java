package com.ab.view.carousel;

import android.content.Context;
import android.view.animation.AnimationUtils;

public class Rotator
{
  private static final int DEFAULT_DURATION = 250;
  private static final int FLING_MODE = 1;
  private static final int SCROLL_MODE = 0;
  private float mCoeffVelocity = 0.05F;
  private float mCurrAngle;
  private final float mDeceleration = 240.0F;
  private float mDeltaAngle;
  private long mDuration;
  private boolean mFinished = true;
  private int mMode;
  private float mStartAngle;
  private long mStartTime;
  private float mVelocity;

  public Rotator(Context paramContext)
  {
  }

  public void abortAnimation()
  {
    this.mFinished = true;
  }

  public boolean computeAngleOffset()
  {
    if (this.mFinished)
      return false;
    long l = AnimationUtils.currentAnimationTimeMillis() - this.mStartTime;
    if (l < this.mDuration)
    {
      switch (this.mMode)
      {
      default:
      case 0:
        while (true)
        {
          return true;
          f = (float)l / (float)this.mDuration;
          this.mCurrAngle = (this.mStartAngle + Math.round(this.mDeltaAngle * f));
        }
      case 1:
      }
      float f = (float)l / 1000.0F;
      if (this.mVelocity < 0.0F);
      for (f = this.mCoeffVelocity * this.mVelocity * f - 240.0F * f * f / 2.0F; ; f = -this.mCoeffVelocity * this.mVelocity * f - 240.0F * f * f / 2.0F)
      {
        this.mCurrAngle = (this.mStartAngle - Math.signum(this.mVelocity) * Math.round(f));
        break;
      }
    }
    this.mFinished = true;
    return false;
  }

  public void extendDuration(int paramInt)
  {
    this.mDuration = (timePassed() + paramInt);
    this.mFinished = false;
  }

  public void fling(float paramFloat)
  {
    this.mMode = 1;
    this.mFinished = false;
    this.mVelocity = paramFloat;
    this.mDuration = ((int)(1000.0D * Math.sqrt(2.0F * this.mCoeffVelocity * Math.abs(paramFloat) / 240.0F)));
    this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
  }

  public final void forceFinished(boolean paramBoolean)
  {
    this.mFinished = paramBoolean;
  }

  public final float getCurrAngle()
  {
    return this.mCurrAngle;
  }

  public float getCurrVelocity()
  {
    return this.mCoeffVelocity * this.mVelocity - 240.0F * timePassed();
  }

  public final long getDuration()
  {
    return this.mDuration;
  }

  public final float getStartAngle()
  {
    return this.mStartAngle;
  }

  public final boolean isFinished()
  {
    return this.mFinished;
  }

  public void startRotate(float paramFloat1, float paramFloat2)
  {
    startRotate(paramFloat1, paramFloat2, 250);
  }

  public void startRotate(float paramFloat1, float paramFloat2, int paramInt)
  {
    this.mMode = 0;
    this.mFinished = false;
    this.mDuration = paramInt;
    this.mStartTime = AnimationUtils.currentAnimationTimeMillis();
    this.mStartAngle = paramFloat1;
    this.mDeltaAngle = paramFloat2;
  }

  public int timePassed()
  {
    return (int)(AnimationUtils.currentAnimationTimeMillis() - this.mStartTime);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.carousel.Rotator
 * JD-Core Version:    0.6.2
 */