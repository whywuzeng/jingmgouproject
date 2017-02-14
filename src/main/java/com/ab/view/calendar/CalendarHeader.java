package com.ab.view.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import com.ab.util.AbGraphical;

public class CalendarHeader extends View
{
  private String TAG = "CalendarHeader";
  private int cellWidth = 40;
  private String[] dayName = new String[10];
  private boolean defaultTextBold = false;
  private int defaultTextColor = Color.rgb(86, 86, 86);
  private int defaultTextSize = 25;
  private boolean hasBg = false;
  private int height = 480;
  private final Paint mPaint;
  private RectF rect = new RectF();
  private int specialTextColor = Color.rgb(240, 140, 26);
  private int weekDay = 1;
  private int width = 320;

  public CalendarHeader(Context paramContext)
  {
    this(paramContext, null);
  }

  public CalendarHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext);
    this.dayName[1] = "周日";
    this.dayName[2] = "周一";
    this.dayName[3] = "周二";
    this.dayName[4] = "周三";
    this.dayName[5] = "周四";
    this.dayName[6] = "周五";
    this.dayName[7] = "周六";
    this.mPaint = new Paint();
    this.mPaint.setColor(this.defaultTextColor);
    this.mPaint.setAntiAlias(true);
    this.mPaint.setTypeface(Typeface.DEFAULT);
    this.mPaint.setTextSize(this.defaultTextSize);
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    this.width = paramContext.getWidth();
    this.height = paramContext.getHeight();
    this.cellWidth = ((this.width - 20) / 7);
  }

  private void drawDayHeader(Canvas paramCanvas)
  {
    if (!this.hasBg)
    {
      this.mPaint.setColor(Color.rgb(150, 195, 70));
      paramCanvas.drawRect(this.rect, this.mPaint);
    }
    if (this.defaultTextBold)
      this.mPaint.setFakeBoldText(true);
    this.mPaint.setColor(this.defaultTextColor);
    int i = 1;
    while (true)
    {
      if (i >= 8)
        return;
      if ((i == 1) || (i == 7))
        this.mPaint.setColor(this.specialTextColor);
      String str = getWeekDayName(i);
      TextPaint localTextPaint = new TextPaint(1);
      localTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
      localTextPaint.setTextSize(this.defaultTextSize);
      Paint.FontMetrics localFontMetrics = localTextPaint.getFontMetrics();
      int m = (int)Math.ceil(localFontMetrics.descent - localFontMetrics.ascent);
      int n = (int)AbGraphical.getStringWidth(str, localTextPaint);
      int j = (int)this.rect.left;
      int k = this.cellWidth;
      n = (this.cellWidth - n) / 2;
      m = (int)(getHeight() - (getHeight() - m) / 2 - this.mPaint.getFontMetrics().bottom);
      paramCanvas.drawText(str, j + k * (i - 1) + n, m, this.mPaint);
      this.mPaint.setColor(this.defaultTextColor);
      i += 1;
    }
  }

  public int getTextSize()
  {
    return this.defaultTextSize;
  }

  public String getWeekDayName(int paramInt)
  {
    return this.dayName[paramInt];
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (!this.hasBg)
    {
      paramCanvas.drawColor(-1);
      this.rect.set(0.0F, 0.0F, getWidth(), getHeight());
      this.rect.inset(0.5F, 0.5F);
    }
    drawDayHeader(paramCanvas);
  }

  public void setHeaderBackgroundResource(int paramInt)
  {
    setBackgroundResource(paramInt);
    this.hasBg = true;
  }

  public void setTextSize(int paramInt)
  {
    this.defaultTextSize = paramInt;
    this.mPaint.setTextSize(this.defaultTextSize);
    invalidate();
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.calendar.CalendarHeader
 * JD-Core Version:    0.6.2
 */