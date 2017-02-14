package com.ab.view.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout.LayoutParams;
import com.ab.view.listener.AbOnItemClickListener;
import java.util.Calendar;

public class CalendarCell extends View
{
  public static int ANIM_ALPHA_DURATION = 100;
  private boolean bTouchedDown = false;
  private int bgColor = Color.rgb(163, 163, 163);
  private int cellColor = -1;
  private boolean hasRecord = false;
  private int iDateDay = 0;
  private int iDateMonth = 0;
  private int iDateYear = 0;
  private boolean isActiveMonth = false;
  private boolean isHoliday = false;
  private boolean isSelected = false;
  private boolean isToday = false;
  private AbOnItemClickListener mOnItemClickListener;
  private int notActiveMonthColor = Color.rgb(178, 178, 178);
  private int numberColor = Color.rgb(86, 86, 86);
  private int position = 0;
  private Paint pt = new Paint();
  private RectF rect = new RectF();
  private int selectCellColor = Color.rgb(150, 195, 70);
  private String textDateValue = "";
  private int textSize = 22;
  private int todayColor = Color.rgb(150, 200, 220);

  public CalendarCell(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramContext);
    setFocusable(true);
    setLayoutParams(new LinearLayout.LayoutParams(paramInt2, paramInt3));
    this.position = paramInt1;
  }

  private void drawDayView(Canvas paramCanvas, boolean paramBoolean)
  {
    this.pt.setColor(getCellColor());
    paramCanvas.drawRect(this.rect, this.pt);
    if (this.hasRecord)
      createReminder(paramCanvas, -65536);
  }

  private int getTextHeight()
  {
    return (int)(-this.pt.ascent() + this.pt.descent());
  }

  public static void startAlphaAnimIn(View paramView)
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.5F, 1.0F);
    localAlphaAnimation.setDuration(ANIM_ALPHA_DURATION);
    localAlphaAnimation.startNow();
    paramView.startAnimation(localAlphaAnimation);
  }

  public boolean IsViewFocused()
  {
    return (isFocused()) || (this.bTouchedDown);
  }

  public void createReminder(Canvas paramCanvas, int paramInt)
  {
    this.pt.setUnderlineText(true);
    this.pt.setStyle(Paint.Style.FILL_AND_STROKE);
    this.pt.setColor(paramInt);
    Path localPath = new Path();
    localPath.moveTo(this.rect.right - this.rect.width() / 4.0F, this.rect.top);
    localPath.lineTo(this.rect.right, this.rect.top);
    localPath.lineTo(this.rect.right, this.rect.top + this.rect.width() / 4.0F);
    localPath.lineTo(this.rect.right - this.rect.width() / 4.0F, this.rect.top);
    localPath.close();
    paramCanvas.drawPath(localPath, this.pt);
    this.pt.setUnderlineText(true);
  }

  public void doItemClick()
  {
    if (this.mOnItemClickListener != null)
      this.mOnItemClickListener.onClick(this.position);
  }

  public void drawDayNumber(Canvas paramCanvas)
  {
    this.pt.setTypeface(null);
    this.pt.setAntiAlias(true);
    this.pt.setShader(null);
    this.pt.setFakeBoldText(true);
    this.pt.setTextSize(this.textSize);
    this.pt.setColor(this.numberColor);
    this.pt.setUnderlineText(false);
    if (!this.isActiveMonth)
      this.pt.setColor(this.notActiveMonthColor);
    int i = (int)this.rect.left;
    int j = (int)this.rect.width();
    int k = (int)this.pt.measureText(this.textDateValue);
    int m = (int)(getHeight() - (getHeight() - getTextHeight()) / 2 - this.pt.getFontMetrics().bottom);
    paramCanvas.drawText(this.textDateValue, i + (j >> 1) - (k >> 1), m, this.pt);
  }

  public int getCellColor()
  {
    if (this.isToday)
      return this.todayColor;
    if (this.isSelected)
      return this.selectCellColor;
    if (this.isHoliday)
      return this.cellColor;
    return this.cellColor;
  }

  public Calendar getThisCellDate()
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.clear();
    localCalendar.set(1, this.iDateYear);
    localCalendar.set(2, this.iDateMonth);
    localCalendar.set(5, this.iDateDay);
    return localCalendar;
  }

  public boolean isActiveMonth()
  {
    return this.isActiveMonth;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.drawColor(this.bgColor);
    this.rect.set(0.0F, 0.0F, getWidth(), getHeight());
    this.rect.inset(0.5F, 0.5F);
    drawDayView(paramCanvas, IsViewFocused());
    drawDayNumber(paramCanvas);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = super.onKeyDown(paramInt, paramKeyEvent);
    if ((paramInt == 23) || (paramInt == 66))
      doItemClick();
    return bool;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    if (paramMotionEvent.getAction() == 0)
    {
      bool = true;
      this.bTouchedDown = true;
      invalidate();
      startAlphaAnimIn(this);
    }
    if (paramMotionEvent.getAction() == 3)
    {
      bool = true;
      this.bTouchedDown = false;
      invalidate();
    }
    if (paramMotionEvent.getAction() == 1)
    {
      bool = true;
      this.bTouchedDown = false;
      invalidate();
      doItemClick();
    }
    return bool;
  }

  public void setHasRecord(boolean paramBoolean)
  {
    if (this.hasRecord != paramBoolean)
    {
      this.hasRecord = paramBoolean;
      invalidate();
    }
  }

  public void setOnItemClickListener(AbOnItemClickListener paramAbOnItemClickListener)
  {
    this.mOnItemClickListener = paramAbOnItemClickListener;
  }

  public void setSelected(boolean paramBoolean)
  {
    if (this.isSelected != paramBoolean)
    {
      this.isSelected = paramBoolean;
      invalidate();
    }
  }

  public void setThisCellDate(int paramInt1, int paramInt2, int paramInt3, Boolean paramBoolean1, Boolean paramBoolean2, Boolean paramBoolean3, int paramInt4, boolean paramBoolean)
  {
    this.iDateYear = paramInt1;
    this.iDateMonth = paramInt2;
    this.iDateDay = paramInt3;
    this.textDateValue = Integer.toString(this.iDateDay);
    if (this.iDateMonth == paramInt4);
    for (boolean bool = true; ; bool = false)
    {
      this.isActiveMonth = bool;
      this.isToday = paramBoolean1.booleanValue();
      this.isHoliday = paramBoolean3.booleanValue();
      this.hasRecord = paramBoolean;
      this.isSelected = paramBoolean2.booleanValue();
      return;
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.calendar.CalendarCell
 * JD-Core Version:    0.6.2
 */