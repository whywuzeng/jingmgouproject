package com.ab.view.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.ab.view.listener.AbOnItemClickListener;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarView extends LinearLayout
{
  public static Calendar calStartDate = Calendar.getInstance();
  private String TAG = "CalendarView";
  private Calendar calCalendar = Calendar.getInstance();
  private Calendar calSelected = null;
  private Calendar calToday = Calendar.getInstance();
  private int cellWidth = 40;
  private Context context;
  private int currentMonth = 0;
  private int currentYear = 0;
  private int firstDayOfWeek = 1;
  private int headerHeight = 45;
  private int height = 480;
  private LinearLayout.LayoutParams layoutParamsFW = null;
  private ArrayList<CalendarCell> mCalendarCells = new ArrayList();
  private CalendarHeader mCalendarHeader = null;
  private LinearLayout mLinearLayoutContent = null;
  private LinearLayout mLinearLayoutHeader = null;
  private AbOnItemClickListener mOnDayCellClick = new AbOnItemClickListener()
  {
    public void onClick(int paramAnonymousInt)
    {
      CalendarCell localCalendarCell = (CalendarCell)CalendarView.this.mCalendarCells.get(paramAnonymousInt);
      int i;
      if (localCalendarCell.isActiveMonth())
      {
        CalendarView.this.calSelected.setTimeInMillis(localCalendarCell.getThisCellDate().getTimeInMillis());
        i = 0;
      }
      while (true)
      {
        if (i >= CalendarView.this.mCalendarCells.size())
        {
          localCalendarCell.setSelected(true);
          if (CalendarView.this.mOnItemClickListener != null)
            CalendarView.this.mOnItemClickListener.onClick(paramAnonymousInt);
          return;
        }
        ((CalendarCell)CalendarView.this.mCalendarCells.get(i)).setSelected(false);
        i += 1;
      }
    }
  };
  private AbOnItemClickListener mOnItemClickListener;
  private int rowHeight = 40;
  private int width = 320;

  public CalendarView(Context paramContext)
  {
    this(paramContext, null);
  }

  public CalendarView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.layoutParamsFW = new LinearLayout.LayoutParams(-1, -2);
    setOrientation(1);
    setBackgroundColor(Color.rgb(255, 255, 255));
    paramAttributeSet = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    this.width = paramAttributeSet.getWidth();
    this.height = paramAttributeSet.getHeight();
    this.mLinearLayoutHeader = new LinearLayout(paramContext);
    this.mLinearLayoutHeader.setLayoutParams(new LinearLayout.LayoutParams(this.width, this.headerHeight));
    this.mLinearLayoutHeader.setOrientation(1);
    this.mCalendarHeader = new CalendarHeader(paramContext);
    this.mCalendarHeader.setLayoutParams(new LinearLayout.LayoutParams(this.width, this.headerHeight));
    this.mLinearLayoutHeader.addView(this.mCalendarHeader, new LinearLayout.LayoutParams(-1, -1));
    addView(this.mLinearLayoutHeader);
    this.mLinearLayoutContent = new LinearLayout(paramContext);
    this.mLinearLayoutContent.setOrientation(1);
    addView(this.mLinearLayoutContent);
    this.cellWidth = ((this.width - 20) / 7);
    this.rowHeight = this.cellWidth;
    this.calSelected = Calendar.getInstance();
    initRow();
    initStartDateForMonth();
    initCalendar();
  }

  private void initCalendar()
  {
    if (this.calSelected.getTimeInMillis() != 0L);
    int k;
    int m;
    int n;
    int j;
    for (int i = 1; ; i = 0)
    {
      k = this.calSelected.get(1);
      m = this.calSelected.get(2);
      n = this.calSelected.get(5);
      this.calCalendar.setTimeInMillis(calStartDate.getTimeInMillis());
      j = 0;
      if (j < this.mCalendarCells.size())
        break;
      invalidate();
      return;
    }
    CalendarCell localCalendarCell = (CalendarCell)this.mCalendarCells.get(j);
    int i1 = this.calCalendar.get(1);
    int i2 = this.calCalendar.get(2);
    int i3 = this.calCalendar.get(5);
    int i4 = this.calCalendar.get(7);
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool1 = false;
    boolean bool2 = bool3;
    if (this.calToday.get(1) == i1)
    {
      bool2 = bool3;
      if (this.calToday.get(2) == i2)
      {
        bool2 = bool3;
        if (this.calToday.get(5) == i3)
          bool2 = true;
      }
    }
    if ((i4 == 7) || (i4 == 1))
      bool1 = true;
    bool3 = bool1;
    if (i2 == 0)
    {
      bool3 = bool1;
      if (i3 == 1)
        bool3 = true;
    }
    bool1 = bool4;
    if (i != 0)
      if ((n != i3) || (m != i2) || (k != i1))
        break label312;
    label312: for (bool1 = true; ; bool1 = false)
    {
      localCalendarCell.setThisCellDate(i1, i2, i3, Boolean.valueOf(bool2), Boolean.valueOf(bool1), Boolean.valueOf(bool3), this.currentMonth, false);
      this.calCalendar.add(5, 1);
      j += 1;
      break;
    }
  }

  private void initStartDateForMonth()
  {
    calStartDate.setTimeInMillis(this.calSelected.getTimeInMillis());
    this.currentMonth = calStartDate.get(2);
    this.currentYear = calStartDate.get(1);
    calStartDate.set(5, 1);
    calStartDate.set(11, 0);
    calStartDate.set(12, 0);
    calStartDate.set(13, 0);
    int i = 0;
    int k = this.firstDayOfWeek;
    int j;
    if (k == 2)
    {
      j = calStartDate.get(7) - 2;
      i = j;
      if (j < 0)
        i = 6;
    }
    if (k == 1)
    {
      j = calStartDate.get(7) - 1;
      i = j;
      if (j < 0)
        i = 6;
    }
    calStartDate.add(7, -i);
  }

  private void updateCalendar()
  {
    if (this.calSelected.getTimeInMillis() != 0L);
    int m;
    int n;
    int i1;
    int k;
    int j;
    for (int i = 1; ; i = 0)
    {
      m = this.calSelected.get(1);
      n = this.calSelected.get(2);
      i1 = this.calSelected.get(5);
      k = 0;
      j = k;
      if (this.calToday.get(1) == m)
      {
        j = k;
        if (this.calToday.get(2) == n)
          j = 1;
      }
      this.calCalendar.setTimeInMillis(calStartDate.getTimeInMillis());
      k = 0;
      if (k < this.mCalendarCells.size())
        break;
      invalidate();
      return;
    }
    CalendarCell localCalendarCell = (CalendarCell)this.mCalendarCells.get(k);
    int i2 = this.calCalendar.get(1);
    int i3 = this.calCalendar.get(2);
    int i4 = this.calCalendar.get(5);
    int i5 = this.calCalendar.get(7);
    boolean bool3 = false;
    boolean bool4 = false;
    boolean bool1 = false;
    boolean bool2 = bool3;
    if (this.calToday.get(1) == i2)
    {
      bool2 = bool3;
      if (this.calToday.get(2) == i3)
      {
        bool2 = bool3;
        if (this.calToday.get(5) == i4)
          bool2 = true;
      }
    }
    if ((i5 == 7) || (i5 == 1))
      bool1 = true;
    bool3 = bool1;
    if (i3 == 0)
    {
      bool3 = bool1;
      if (i4 == 1)
        bool3 = true;
    }
    bool1 = bool4;
    if (i != 0)
      if ((i1 != i4) || (n != i3) || (m != i2))
        break label369;
    label369: for (bool1 = true; ; bool1 = false)
    {
      bool4 = bool1;
      if (i4 == 1)
      {
        bool4 = bool1;
        if (j != 0)
          bool4 = false;
      }
      localCalendarCell.setThisCellDate(i2, i3, i4, Boolean.valueOf(bool2), Boolean.valueOf(bool4), Boolean.valueOf(bool3), this.currentMonth, false);
      this.calCalendar.add(5, 1);
      k += 1;
      break;
    }
  }

  public String getCalSelected()
  {
    int i = this.calSelected.get(1);
    int j = this.calSelected.get(2);
    int k = this.calSelected.get(5);
    return i + "-" + (j + 1) + "-" + k;
  }

  public int getCalendarCellSize()
  {
    return this.mCalendarCells.size();
  }

  public ArrayList<CalendarCell> getCalendarCells()
  {
    return this.mCalendarCells;
  }

  public String getStrDateAtPosition(int paramInt)
  {
    Calendar localCalendar = ((CalendarCell)this.mCalendarCells.get(paramInt)).getThisCellDate();
    paramInt = localCalendar.get(1);
    int i = localCalendar.get(2);
    int j = localCalendar.get(5);
    return paramInt + "-" + (i + 1) + "-" + j;
  }

  public void initRow()
  {
    this.mLinearLayoutContent.removeAllViews();
    this.mCalendarCells.clear();
    int i = 0;
    if (i >= 6)
      return;
    LinearLayout localLinearLayout = new LinearLayout(this.context);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(this.width, this.rowHeight));
    localLinearLayout.setOrientation(0);
    int j = 0;
    while (true)
    {
      if (j >= 7)
      {
        this.mLinearLayoutContent.addView(localLinearLayout);
        i += 1;
        break;
      }
      CalendarCell localCalendarCell = new CalendarCell(this.context, i * 7 + j, this.cellWidth, this.rowHeight);
      localCalendarCell.setOnItemClickListener(this.mOnDayCellClick);
      localLinearLayout.addView(localCalendarCell);
      this.mCalendarCells.add(localCalendarCell);
      j += 1;
    }
  }

  public void rebuildCalendar(Calendar paramCalendar)
  {
    this.calSelected.setTimeInMillis(paramCalendar.getTimeInMillis());
    initRow();
    initStartDateForMonth();
    updateCalendar();
  }

  public void setHeaderBackgroundResource(int paramInt)
  {
    this.mCalendarHeader.setHeaderBackgroundResource(paramInt);
  }

  public void setHeaderHeight(int paramInt)
  {
    this.headerHeight = paramInt;
    this.mLinearLayoutHeader.removeAllViews();
    this.mCalendarHeader.setLayoutParams(new LinearLayout.LayoutParams(this.width, this.headerHeight));
    this.mLinearLayoutHeader.addView(this.mCalendarHeader, new LinearLayout.LayoutParams(-1, -1));
    invalidate();
  }

  public void setHeaderTextSize(int paramInt)
  {
    this.mCalendarHeader.setTextSize(paramInt);
    invalidate();
  }

  public void setOnItemClickListener(AbOnItemClickListener paramAbOnItemClickListener)
  {
    this.mOnItemClickListener = paramAbOnItemClickListener;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ab.view.calendar.CalendarView
 * JD-Core Version:    0.6.2
 */