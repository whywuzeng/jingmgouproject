package com.ab.view.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.view.View;
import android.view.View.MeasureSpec;
import com.ab.util.AbStrUtil;
import java.util.List;
import java.util.TimeZone;

public class AbNumberClock extends View
{
  private List<Drawable> dApmBmp;
  private List<Drawable> dTimeBmp;
  private boolean mAttached;
  private Time mCalendar;
  private final Handler mHandler = new Handler();
  private String mHour;
  private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getAction().equals("android.intent.action.TIMEZONE_CHANGED"))
      {
        paramAnonymousContext = paramAnonymousIntent.getStringExtra("time-zone");
        AbNumberClock.this.mCalendar = new Time(TimeZone.getTimeZone(paramAnonymousContext).getID());
      }
    }
  };
  private String mMinutes;
  private String mSecond;
  private Drawable mTimeBg;
  private int mTimeBgHeight;
  private int mTimeBgWidth;
  private Drawable mTimeColon;

  public AbNumberClock(Context paramContext, Drawable paramDrawable1, Drawable paramDrawable2, List<Drawable> paramList1, List<Drawable> paramList2)
  {
    super(paramContext);
    this.mTimeBg = paramDrawable1;
    this.mTimeColon = paramDrawable2;
    this.dTimeBmp = paramList1;
    this.dApmBmp = paramList2;
    this.mCalendar = new Time();
    if ((!this.dApmBmp.isEmpty()) && (this.dApmBmp.size() > 0))
    {
      i = this.mTimeColon.getIntrinsicWidth();
      int j = ((Drawable)this.dTimeBmp.get(0)).getIntrinsicWidth();
      this.mTimeBgWidth = (((Drawable)this.dApmBmp.get(0)).getIntrinsicWidth() + (i * 2 + j * 6));
      this.mTimeBgHeight = ((Drawable)this.dTimeBmp.get(0)).getIntrinsicHeight();
      return;
    }
    int i = this.mTimeColon.getIntrinsicWidth();
    this.mTimeBgWidth = (((Drawable)this.dTimeBmp.get(0)).getIntrinsicWidth() * 8 + i * 2);
    this.mTimeBgHeight = ((Drawable)this.dTimeBmp.get(0)).getIntrinsicHeight();
  }

  private boolean get24HourMode()
  {
    return DateFormat.is24HourFormat(getContext());
  }

  private void onTimeChanged()
  {
    this.mCalendar.setToNow();
    if ((!get24HourMode()) && (!this.dApmBmp.isEmpty()) && (this.dApmBmp.size() > 0))
      if (this.mCalendar.hour > 12)
        this.mHour = String.format("%02d", new Object[] { Integer.valueOf(this.mCalendar.hour - 12) });
    while (true)
    {
      this.mSecond = AbStrUtil.strFormat2(String.valueOf(this.mCalendar.second));
      this.mMinutes = String.format("%02d", new Object[] { Integer.valueOf(this.mCalendar.minute) });
      updateContentDescription(this.mCalendar);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          AbNumberClock.this.onTimeChanged();
        }
      }
      , 1000L);
      invalidate();
      return;
      this.mHour = String.format("%02d", new Object[] { Integer.valueOf(this.mCalendar.hour) });
      continue;
      this.mHour = String.format("%02d", new Object[] { Integer.valueOf(this.mCalendar.hour) });
    }
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
    int m = getRight() - getLeft();
    int i1 = getBottom() - getTop();
    int i2 = m / 2;
    int n = i1 / 2;
    int k;
    int j;
    int i;
    Drawable localDrawable1;
    label444: int i3;
    if ((!this.dApmBmp.isEmpty()) && (this.dApmBmp.size() > 0))
    {
      k = this.mTimeColon.getIntrinsicWidth() * 2 + ((Drawable)this.dTimeBmp.get(0)).getIntrinsicWidth() * 8 + ((Drawable)this.dApmBmp.get(0)).getIntrinsicWidth();
      j = ((Drawable)this.dTimeBmp.get(0)).getIntrinsicHeight();
      i = 0;
      if ((m < k) || (i1 < j))
      {
        i = 1;
        float f = Math.min(m / k, i1 / j);
        paramCanvas.save();
        paramCanvas.scale(f, f, i2, n);
      }
      m = i2 - k / 2;
      n -= j / 2;
      if (this.mTimeBg != null)
      {
        localDrawable1 = this.mTimeBg;
        localDrawable1.setBounds(m, n, m + k, n + j);
        localDrawable1.draw(paramCanvas);
        paramCanvas.save();
      }
      j = Integer.parseInt(this.mHour.substring(0, 1));
      localDrawable1 = (Drawable)this.dTimeBmp.get(j);
      k = localDrawable1.getIntrinsicWidth();
      i1 = localDrawable1.getIntrinsicHeight();
      localDrawable1.setBounds(m, n, m + k, n + i1);
      localDrawable1.draw(paramCanvas);
      j = Integer.parseInt(this.mHour.substring(1, 2));
      localDrawable1 = (Drawable)this.dTimeBmp.get(j);
      localDrawable1.setBounds(m + k, n, k * 2 + m, n + i1);
      localDrawable1.draw(paramCanvas);
      localDrawable1 = this.mTimeColon;
      i2 = localDrawable1.getIntrinsicWidth();
      j = localDrawable1.getIntrinsicHeight();
      if (j >= i1)
        break label995;
      localDrawable1.setBounds(k * 2 + m, (i1 - j) / 2 + n, k * 2 + m + i2, (i1 - j) / 2 + n + j);
      localDrawable1.draw(paramCanvas);
      i3 = Integer.parseInt(this.mMinutes.substring(0, 1));
      Drawable localDrawable2 = (Drawable)this.dTimeBmp.get(i3);
      localDrawable2.setBounds(k * 2 + m + i2, n, k * 3 + m + i2, n + i1);
      localDrawable2.draw(paramCanvas);
      i3 = Integer.parseInt(this.mMinutes.substring(1, 2));
      localDrawable2 = (Drawable)this.dTimeBmp.get(i3);
      localDrawable2.setBounds(k * 3 + m + i2, n, k * 4 + m + i2, n + i1);
      localDrawable2.draw(paramCanvas);
      if (j >= i1)
        break label1027;
      localDrawable1.setBounds(k * 4 + m + i2, (i1 - j) / 2 + n, k * 4 + m + i2 * 2, (i1 - j) / 2 + n + j);
      label643: localDrawable1.draw(paramCanvas);
      j = Integer.parseInt(this.mSecond.substring(0, 1));
      localDrawable1 = (Drawable)this.dTimeBmp.get(j);
      localDrawable1.setBounds(k * 4 + m + i2 * 2, n, k * 5 + m + i2 * 2, n + i1);
      localDrawable1.draw(paramCanvas);
      j = Integer.parseInt(this.mSecond.substring(1, 2));
      localDrawable1 = (Drawable)this.dTimeBmp.get(j);
      localDrawable1.setBounds(k * 5 + m + i2 * 2, n, k * 6 + m + i2 * 2, n + i1);
      localDrawable1.draw(paramCanvas);
      if ((!this.dApmBmp.isEmpty()) && (this.dApmBmp.size() > 0) && (!get24HourMode()))
      {
        if (this.mCalendar.hour <= 12)
          break label1064;
        j = 1;
        label840: localDrawable1 = (Drawable)this.dApmBmp.get(j);
        j = localDrawable1.getIntrinsicWidth();
        i3 = localDrawable1.getIntrinsicHeight();
        if (i3 >= i1)
          break label1070;
        localDrawable1.setBounds(k * 4 + m + i2, (i1 - i3) / 2 + n, k * 4 + m + i2 + j, (i1 - i3) / 2 + n + i3);
      }
    }
    while (true)
    {
      localDrawable1.draw(paramCanvas);
      if (i != 0)
        paramCanvas.restore();
      return;
      k = this.mTimeColon.getIntrinsicWidth() * 2 + ((Drawable)this.dTimeBmp.get(0)).getIntrinsicWidth() * 6;
      j = ((Drawable)this.dTimeBmp.get(0)).getIntrinsicHeight();
      break;
      label995: localDrawable1.setBounds(k * 2 + m, n, k * 2 + m + i2, n + j);
      break label444;
      label1027: localDrawable1.setBounds(k * 4 + m + i2, n, k * 4 + m + i2 * 2, n + j);
      break label643;
      label1064: j = 0;
      break label840;
      label1070: localDrawable1.setBounds(k * 4 + m + i2, n, k * 4 + m + i2 + j, n + i3);
    }
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
      if (paramInt1 < this.mTimeBgWidth)
        f1 = paramInt1 / this.mTimeBgWidth;
    }
    f2 = f3;
    if (j != 0)
    {
      f2 = f3;
      if (paramInt2 < this.mTimeBgHeight)
        f2 = paramInt2 / this.mTimeBgHeight;
    }
    f1 = Math.min(f1, f2);
    setMeasuredDimension(this.mTimeBgWidth * (int)f1, this.mTimeBgHeight * (int)f1);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.app.AbNumberClock
 * JD-Core Version:    0.6.2
 */