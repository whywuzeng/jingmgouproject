package com.ab.view.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import com.ab.global.AbAppData;
import java.util.TimeZone;

public class AbAnalogClock extends View
{
  private static final boolean D = AbAppData.DEBUG;
  private static String TAG = "AbAnalogClock";
  private boolean mAttached;
  private Time mCalendar;
  private boolean mChanged;
  private Drawable mDial;
  private int mDialHeight;
  private int mDialWidth;
  private final Handler mHandler = new Handler();
  private float mHour;
  private Drawable mHourHand;
  private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getAction().equals("android.intent.action.TIMEZONE_CHANGED"))
      {
        paramAnonymousContext = paramAnonymousIntent.getStringExtra("time-zone");
        AbAnalogClock.this.mCalendar = new Time(TimeZone.getTimeZone(paramAnonymousContext).getID());
      }
    }
  };
  private Drawable mMinuteHand;
  private float mMinutes;
  private float mSecond;
  private Drawable mSecondHand;

  public AbAnalogClock(Context paramContext, Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4)
  {
    super(paramContext);
    this.mDial = paramDrawable1;
    this.mHourHand = paramDrawable2;
    this.mMinuteHand = paramDrawable3;
    this.mSecondHand = paramDrawable4;
    this.mCalendar = new Time();
    this.mDialWidth = this.mDial.getIntrinsicWidth();
    this.mDialHeight = this.mDial.getIntrinsicHeight();
  }

  private void onTimeChanged()
  {
    this.mCalendar.setToNow();
    int i = this.mCalendar.hour;
    int j = this.mCalendar.minute;
    int k = this.mCalendar.second;
    this.mSecond = k;
    this.mMinutes = (j + k / 60.0F);
    this.mHour = (i + this.mMinutes / 60.0F);
    this.mChanged = true;
    if (D)
      Log.d(TAG, "时间改变:mHour:" + this.mHour + ",mMinutes:" + this.mMinutes + ",mSecond:" + this.mSecond);
    updateContentDescription(this.mCalendar);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        AbAnalogClock.this.onTimeChanged();
      }
    }
    , 1000L);
    invalidate();
  }

  private void updateContentDescription(Time paramTime)
  {
    setContentDescription(DateUtils.formatDateTime(getContext(), paramTime.toMillis(false), 129));
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.TIME_TICK");
      localIntentFilter.addAction("android.intent.action.TIME_SET");
      localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
      getContext().registerReceiver(this.mIntentReceiver, localIntentFilter, null, this.mHandler);
    }
    this.mCalendar = new Time();
    onTimeChanged();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mAttached)
    {
      getContext().unregisterReceiver(this.mIntentReceiver);
      this.mAttached = false;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    boolean bool = this.mChanged;
    if (bool)
      this.mChanged = false;
    int m = getRight() - getLeft();
    int n = getBottom() - getTop();
    int j = m / 2;
    int k = n / 2;
    Drawable localDrawable = this.mDial;
    int i1 = localDrawable.getIntrinsicWidth();
    int i2 = localDrawable.getIntrinsicHeight();
    int i = 0;
    if ((m < i1) || (n < i2))
    {
      i = 1;
      float f = Math.min(m / i1, n / i2);
      paramCanvas.save();
      paramCanvas.scale(f, f, j, k);
    }
    if (bool)
      localDrawable.setBounds(j - i1 / 2, k - i2 / 2, i1 / 2 + j, i2 / 2 + k);
    localDrawable.draw(paramCanvas);
    paramCanvas.save();
    paramCanvas.rotate(this.mHour / 12.0F * 360.0F, j, k);
    localDrawable = this.mHourHand;
    if (bool)
    {
      m = localDrawable.getIntrinsicWidth();
      n = localDrawable.getIntrinsicHeight();
      localDrawable.setBounds(j - m / 2, k - n / 2, m / 2 + j, n / 2 + k);
    }
    localDrawable.draw(paramCanvas);
    paramCanvas.restore();
    paramCanvas.save();
    paramCanvas.rotate(this.mMinutes / 60.0F * 360.0F, j, k);
    localDrawable = this.mMinuteHand;
    if (bool)
    {
      m = localDrawable.getIntrinsicWidth();
      n = localDrawable.getIntrinsicHeight();
      localDrawable.setBounds(j - m / 2, k - n / 2, m / 2 + j, n / 2 + k);
    }
    localDrawable.draw(paramCanvas);
    paramCanvas.restore();
    paramCanvas.save();
    paramCanvas.rotate(this.mSecond / 60.0F * 360.0F, j, k);
    localDrawable = this.mSecondHand;
    if (bool)
    {
      m = localDrawable.getIntrinsicWidth();
      n = localDrawable.getIntrinsicHeight();
      localDrawable.setBounds(j - m / 2, k - n / 2, m / 2 + j, n / 2 + k);
    }
    localDrawable.draw(paramCanvas);
    paramCanvas.restore();
    if (i != 0)
      paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    float f2 = 1.0F;
    float f3 = 1.0F;
    float f1 = f2;
    if (i != 0)
    {
      f1 = f2;
      if (paramInt1 < this.mDialWidth)
        f1 = paramInt1 / this.mDialWidth;
    }
    f2 = f3;
    if (j != 0)
    {
      f2 = f3;
      if (paramInt2 < this.mDialHeight)
        f2 = paramInt2 / this.mDialHeight;
    }
    f1 = Math.min(f1, f2);
    setMeasuredDimension(this.mDialWidth * (int)f1, this.mDialHeight * (int)f1);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mChanged = true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.app.AbAnalogClock
 * JD-Core Version:    0.6.2
 */