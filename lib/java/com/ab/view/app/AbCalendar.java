package com.ab.view.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.text.format.Time;
import android.view.View;
import android.view.View.MeasureSpec;
import java.util.List;
import java.util.TimeZone;

public class AbCalendar extends View
{
  private List<Drawable> dArrayDate;
  private List<Drawable> dArrayMonth;
  private List<Drawable> dArrayWeek;
  private List<Drawable> dArrayYear;
  private final BroadcastReceiver dateChangedReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent.getAction().equals("android.intent.action.TIMEZONE_CHANGED"))
      {
        paramAnonymousContext = paramAnonymousIntent.getStringExtra("time-zone");
        AbCalendar.this.mCalendar = new Time(TimeZone.getTimeZone(paramAnonymousContext).getID());
      }
      AbCalendar.this.invalidate();
    }
  };
  private boolean mAttached;
  private int mBgHeight;
  private int mBgWidth;
  private Drawable mCaleBg;
  private Drawable mCaleDot;
  private Time mCalendar;
  private float mDateTextSize;
  private Point mPosDate;
  private Point mPosMonth;
  private Point mPosWeek;
  private Point mPosYear;

  public AbCalendar(Context paramContext, Drawable paramDrawable1, Drawable paramDrawable2, Point paramPoint1, List<Drawable> paramList1, Point paramPoint2, List<Drawable> paramList2, Point paramPoint3, List<Drawable> paramList3, Point paramPoint4, List<Drawable> paramList4)
  {
    super(paramContext);
    this.mCaleBg = paramDrawable1;
    this.mCaleDot = paramDrawable2;
    this.mPosYear = paramPoint1;
    this.dArrayYear = paramList1;
    this.mPosMonth = paramPoint2;
    this.dArrayMonth = paramList2;
    this.mPosDate = paramPoint3;
    this.dArrayDate = paramList3;
    this.mPosWeek = paramPoint4;
    this.dArrayWeek = paramList4;
    this.mBgWidth = this.mCaleBg.getIntrinsicWidth();
    this.mBgHeight = this.mCaleBg.getIntrinsicHeight();
    this.mCalendar = new Time();
    this.mDateTextSize = 14.0F;
  }

  public float getDateTextSize()
  {
    return this.mDateTextSize;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter("android.intent.action.DATE_CHANGED");
      getContext().registerReceiver(this.dateChangedReceiver, localIntentFilter);
    }
    this.mCalendar = new Time();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mAttached)
    {
      this.mAttached = false;
      getContext().unregisterReceiver(this.dateChangedReceiver);
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i1 = getRight() - getLeft();
    int i2 = getBottom() - getTop();
    int j = i1 / 2;
    int k = i2 / 2;
    Object localObject1 = this.mCaleBg;
    int m = ((Drawable)localObject1).getIntrinsicWidth();
    int n = ((Drawable)localObject1).getIntrinsicHeight();
    int i = 0;
    float f1;
    if ((i1 < m) || (i2 < n))
    {
      i = 1;
      f1 = Math.min(i1 / m, i2 / n);
      paramCanvas.save();
      paramCanvas.scale(f1, f1, j, k);
    }
    ((Drawable)localObject1).setBounds(j - m / 2, k - n / 2, m / 2 + j, n / 2 + k);
    ((Drawable)localObject1).draw(paramCanvas);
    paramCanvas.save();
    this.mCalendar.setToNow();
    Object localObject2;
    int i3;
    int i4;
    if (((!this.dArrayYear.isEmpty()) && (this.dArrayYear.size() > 0)) || ((!this.dArrayMonth.isEmpty()) && (this.dArrayMonth.size() > 0)) || ((!this.dArrayDate.isEmpty()) && (this.dArrayDate.size() > 0)) || ((!this.dArrayWeek.isEmpty()) && (this.dArrayWeek.size() > 0)))
    {
      if ((!this.dArrayYear.isEmpty()) && (this.dArrayYear.size() > 0))
      {
        localObject1 = String.format("%04d", new Object[] { Integer.valueOf(this.mCalendar.year) });
        i1 = Integer.parseInt(((String)localObject1).substring(0, 1));
        localObject2 = (Drawable)this.dArrayYear.get(i1);
        i1 = ((Drawable)localObject2).getIntrinsicWidth();
        i2 = ((Drawable)localObject2).getIntrinsicHeight();
        ((Drawable)localObject2).setBounds(j - m / 2 + this.mPosYear.x, k - n / 2 + this.mPosYear.y, j - m / 2 + this.mPosYear.x + i1, k - n / 2 + this.mPosYear.y + i2);
        ((Drawable)localObject2).draw(paramCanvas);
        i3 = Integer.parseInt(((String)localObject1).substring(1, 2));
        localObject2 = (Drawable)this.dArrayYear.get(i3);
        ((Drawable)localObject2).setBounds(j - m / 2 + this.mPosYear.x + i1, k - n / 2 + this.mPosYear.y, j - m / 2 + this.mPosYear.x + i1 * 2, k - n / 2 + this.mPosYear.y + i2);
        ((Drawable)localObject2).draw(paramCanvas);
        i3 = Integer.parseInt(((String)localObject1).substring(2, 3));
        localObject2 = (Drawable)this.dArrayYear.get(i3);
        ((Drawable)localObject2).setBounds(j - m / 2 + this.mPosYear.x + i1 * 2, k - n / 2 + this.mPosYear.y, j - m / 2 + this.mPosYear.x + i1 * 3, k - n / 2 + this.mPosYear.y + i2);
        ((Drawable)localObject2).draw(paramCanvas);
        i3 = Integer.parseInt(((String)localObject1).substring(3, 4));
        localObject1 = (Drawable)this.dArrayYear.get(i3);
        ((Drawable)localObject1).setBounds(j - m / 2 + this.mPosYear.x + i1 * 3, k - n / 2 + this.mPosYear.y, j - m / 2 + this.mPosYear.x + i1 * 4, k - n / 2 + this.mPosYear.y + i2);
        ((Drawable)localObject1).draw(paramCanvas);
        if ((this.mCaleDot != null) && (this.mPosYear.y == this.mPosMonth.y))
        {
          i3 = this.mCaleDot.getIntrinsicWidth();
          i4 = this.mCaleDot.getIntrinsicHeight();
          if (i4 >= i1)
            break label1491;
          this.mCaleDot.setBounds(j - m / 2 + this.mPosYear.x + i1 * 4, k - n / 2 + this.mPosYear.y + (i2 - i4) / 2, j - m / 2 + this.mPosYear.x + i1 * 4 + i3, k - n / 2 + this.mPosYear.y + (i2 - i4) / 2 + i4);
          this.mCaleDot.draw(paramCanvas);
        }
      }
      if ((!this.dArrayMonth.isEmpty()) && (this.dArrayMonth.size() > 0))
      {
        if (this.dArrayMonth.size() > 10)
        {
          localObject1 = (Drawable)this.dArrayMonth.get(this.mCalendar.month);
          i1 = ((Drawable)localObject1).getIntrinsicWidth();
          i2 = ((Drawable)localObject1).getIntrinsicHeight();
          ((Drawable)localObject1).setBounds(j - m / 2 + this.mPosMonth.x, k - n / 2 + this.mPosMonth.y, j - m / 2 + this.mPosMonth.x + i1, k - n / 2 + this.mPosMonth.y + i2);
          ((Drawable)localObject1).draw(paramCanvas);
        }
      }
      else
      {
        label1068: if ((!this.dArrayDate.isEmpty()) && (this.dArrayDate.size() > 0))
        {
          localObject1 = String.format("%02d", new Object[] { Integer.valueOf(this.mCalendar.monthDay) });
          i1 = Integer.parseInt(((String)localObject1).substring(0, 1));
          localObject2 = (Drawable)this.dArrayDate.get(i1);
          i1 = ((Drawable)localObject2).getIntrinsicWidth();
          i2 = ((Drawable)localObject2).getIntrinsicHeight();
          ((Drawable)localObject2).setBounds(j - m / 2 + this.mPosDate.x, k - n / 2 + this.mPosDate.y, j - m / 2 + this.mPosDate.x + i1, k - n / 2 + this.mPosDate.y + i2);
          ((Drawable)localObject2).draw(paramCanvas);
          i3 = Integer.parseInt(((String)localObject1).substring(1, 2));
          localObject1 = (Drawable)this.dArrayDate.get(i3);
          ((Drawable)localObject1).setBounds(j - m / 2 + this.mPosDate.x + i1, k - n / 2 + this.mPosDate.y, j - m / 2 + this.mPosDate.x + i1 * 2, k - n / 2 + this.mPosDate.y + i2);
          ((Drawable)localObject1).draw(paramCanvas);
        }
        if ((!this.dArrayWeek.isEmpty()) && (this.dArrayWeek.size() > 0))
        {
          localObject1 = (Drawable)this.dArrayWeek.get(this.mCalendar.weekDay);
          i1 = ((Drawable)localObject1).getIntrinsicWidth();
          i2 = ((Drawable)localObject1).getIntrinsicHeight();
          ((Drawable)localObject1).setBounds(j - m / 2 + this.mPosWeek.x, k - n / 2 + this.mPosWeek.y, j - m / 2 + this.mPosWeek.x + i1, k - n / 2 + this.mPosWeek.y + i2);
          ((Drawable)localObject1).draw(paramCanvas);
        }
      }
    }
    while (true)
    {
      if (i != 0)
        paramCanvas.restore();
      return;
      label1491: this.mCaleDot.setBounds(j - m / 2 + this.mPosYear.x + i1 * 4, k - n / 2 + this.mPosYear.y, j - m / 2 + this.mPosYear.x + i1 * 4 + i3, k - n / 2 + this.mPosYear.y + i4);
      break;
      localObject1 = String.format("%02d", new Object[] { Integer.valueOf(this.mCalendar.month + 1) });
      i1 = Integer.parseInt(((String)localObject1).substring(0, 1));
      localObject2 = (Drawable)this.dArrayMonth.get(i1);
      i1 = ((Drawable)localObject2).getIntrinsicWidth();
      i2 = ((Drawable)localObject2).getIntrinsicHeight();
      ((Drawable)localObject2).setBounds(j - m / 2 + this.mPosMonth.x, k - n / 2 + this.mPosMonth.y, j - m / 2 + this.mPosMonth.x + i1, k - n / 2 + this.mPosMonth.y + i2);
      ((Drawable)localObject2).draw(paramCanvas);
      i3 = Integer.parseInt(((String)localObject1).substring(1, 2));
      localObject1 = (Drawable)this.dArrayMonth.get(i3);
      ((Drawable)localObject1).setBounds(j - m / 2 + this.mPosMonth.x + i1, k - n / 2 + this.mPosMonth.y, j - m / 2 + this.mPosMonth.x + i1 * 2, k - n / 2 + this.mPosMonth.y + i2);
      ((Drawable)localObject1).draw(paramCanvas);
      if ((this.mCaleDot == null) || (this.mPosMonth.y != this.mPosDate.y))
        break label1068;
      i3 = this.mCaleDot.getIntrinsicWidth();
      i4 = this.mCaleDot.getIntrinsicHeight();
      if (i4 < i1)
        this.mCaleDot.setBounds(j - m / 2 + this.mPosMonth.x + i1 * 2, k - n / 2 + this.mPosMonth.y + (i2 - i4) / 2, j - m / 2 + this.mPosMonth.x + i1 * 2 + i3, k - n / 2 + this.mPosMonth.y + (i2 - i4) / 2 + i4);
      while (true)
      {
        this.mCaleDot.draw(paramCanvas);
        break;
        this.mCaleDot.setBounds(j - m / 2 + this.mPosMonth.x + i1 * 2, k - n / 2 + this.mPosMonth.y, j - m / 2 + this.mPosMonth.x + i1 * 2 + i3, k - n / 2 + this.mPosMonth.y + i4);
      }
      localObject1 = new Paint(1);
      localObject2 = String.format("%02d", new Object[] { Integer.valueOf(this.mCalendar.monthDay) });
      ((Paint)localObject1).setTextAlign(Paint.Align.CENTER);
      ((Paint)localObject1).setTextSize(this.mDateTextSize);
      ((Paint)localObject1).setColor(-16777216);
      j = getWidth();
      k = getHeight();
      Paint.FontMetrics localFontMetrics = ((Paint)localObject1).getFontMetrics();
      f1 = localFontMetrics.bottom;
      float f2 = localFontMetrics.top;
      paramCanvas.drawText((String)localObject2, j / 2, k - (k - (f1 - f2)) / 2.0F - localFontMetrics.bottom, (Paint)localObject1);
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
      if (paramInt1 < this.mBgWidth)
        f1 = paramInt1 / this.mBgWidth;
    }
    f2 = f3;
    if (j != 0)
    {
      f2 = f3;
      if (paramInt2 < this.mBgHeight)
        f2 = paramInt2 / this.mBgHeight;
    }
    f1 = Math.min(f1, f2);
    setMeasuredDimension(this.mBgWidth * (int)f1, this.mBgHeight * (int)f1);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setDateTextSize(float paramFloat)
  {
    this.mDateTextSize = paramFloat;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.app.AbCalendar
 * JD-Core Version:    0.6.2
 */