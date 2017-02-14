package com.umeng.message.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.g;
import java.io.Serializable;
import java.util.Calendar;

public class UmengLocalNotification
  implements Serializable
{
  public static final int CHINESE_NEW_YEAR = 3;
  public static final int CHINESE_NEW_YEAR_EVE = 2;
  public static final int CHUNG_YEUNG_FESTIVAL = 11;
  public static final int DRAGON_BOAT_FESTIVAL = 7;
  public static final int LABA_FESTIVAL = 12;
  public static final int LABOR_DAY = 6;
  public static final int LANTERN = 4;
  public static final int MID_AUTUMN_FESTIVAL = 9;
  public static final int NATIONAL_DAY = 10;
  public static final int NEW_YEAR_DAY = 1;
  public static final int QINGMING_FESTIVAL = 5;
  public static final int QIXI_FESTIVAL = 8;
  public static final int REPEATING_UNIT_DAY = 3;
  public static final int REPEATING_UNIT_HOUR = 4;
  public static final int REPEATING_UNIT_MINUTE = 5;
  public static final int REPEATING_UNIT_MONTH = 2;
  public static final int REPEATING_UNIT_SECOND = 6;
  public static final int REPEATING_UNIT_YEAR = 1;
  private static final long a = -1080206884562188471L;
  private static final String b = UmengLocalNotification.class.getName();
  private static final String c = "id";
  private static final String d = "year";
  private static final String e = "month";
  private static final String f = "day";
  private static final String g = "hour";
  private static final String h = "minute";
  private static final String i = "second";
  private static final String j = "repeating_num";
  private static final String k = "repeating_unit";
  private static final String l = "repeating_interval";
  private static final String m = "special_day";
  private static final String n = "title";
  private static final String o = "content";
  private static final String p = "ticker";
  private int A;
  private int B;
  private int C;
  private int D;
  private String E;
  private String F;
  private String G;
  private UmengNotificationBuilder H;
  private String q;
  private int r;
  private int s;
  private int t;
  private int u;
  private int v;
  private int w;
  private int x;
  private int y;
  private int z;

  public UmengLocalNotification()
  {
    this.q = g.b();
    Calendar localCalendar = Calendar.getInstance();
    this.r = localCalendar.get(1);
    this.t = (localCalendar.get(2) + 1);
    this.v = (localCalendar.get(5) + 1);
    this.x = 12;
    this.y = 0;
    this.z = 0;
    this.A = 1;
    this.B = 3;
    this.C = 1;
    this.D = 0;
    this.E = "test";
    this.F = "test message";
    this.G = "test";
    this.H = new UmengNotificationBuilder(this.q);
  }

  public UmengLocalNotification(Cursor paramCursor)
  {
    if (paramCursor == null)
      return;
    this.q = paramCursor.getString(paramCursor.getColumnIndex("id"));
    this.r = paramCursor.getInt(paramCursor.getColumnIndex("year"));
    this.t = paramCursor.getInt(paramCursor.getColumnIndex("month"));
    this.v = paramCursor.getInt(paramCursor.getColumnIndex("day"));
    this.x = paramCursor.getInt(paramCursor.getColumnIndex("hour"));
    this.y = paramCursor.getInt(paramCursor.getColumnIndex("minute"));
    this.z = paramCursor.getInt(paramCursor.getColumnIndex("second"));
    this.A = paramCursor.getInt(paramCursor.getColumnIndex("repeating_num"));
    this.B = paramCursor.getInt(paramCursor.getColumnIndex("repeating_unit"));
    this.C = paramCursor.getInt(paramCursor.getColumnIndex("repeating_interval"));
    this.D = paramCursor.getInt(paramCursor.getColumnIndex("special_day"));
    this.E = paramCursor.getString(paramCursor.getColumnIndex("title"));
    this.F = paramCursor.getString(paramCursor.getColumnIndex("content"));
    this.G = paramCursor.getString(paramCursor.getColumnIndex("ticker"));
    this.H = new UmengNotificationBuilder(this.q);
  }

  public String getContent()
  {
    return this.F;
  }

  public ContentValues getContentValues()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("id", this.q);
    localContentValues.put("year", Integer.valueOf(this.r));
    localContentValues.put("month", Integer.valueOf(this.t));
    localContentValues.put("day", Integer.valueOf(this.v));
    localContentValues.put("hour", Integer.valueOf(this.x));
    localContentValues.put("minute", Integer.valueOf(this.y));
    localContentValues.put("second", Integer.valueOf(this.z));
    localContentValues.put("repeating_num", Integer.valueOf(this.A));
    localContentValues.put("repeating_unit", Integer.valueOf(this.B));
    localContentValues.put("repeating_interval", Integer.valueOf(this.C));
    localContentValues.put("special_day", Integer.valueOf(this.D));
    localContentValues.put("title", this.E);
    localContentValues.put("content", this.F);
    localContentValues.put("ticker", this.G);
    return localContentValues;
  }

  public String getDateTime()
  {
    String str = "" + this.r + "-";
    if (this.t < 10)
    {
      str = str + "0" + this.t + "-";
      if (this.v >= 10)
        break label258;
      str = str + "0" + this.v + " ";
      label110: if (this.x >= 10)
        break label288;
      str = str + "0" + this.x + ":";
      label151: if (this.y >= 10)
        break label318;
    }
    label258: label288: label318: for (str = str + "0" + this.y + ":"; ; str = str + this.y + ":")
    {
      if (this.z >= 10)
        break label348;
      return str + "0" + this.z;
      str = str + this.t + "-";
      break;
      str = str + this.v + " ";
      break label110;
      str = str + this.x + ":";
      break label151;
    }
    label348: return str + this.z;
  }

  public int getDay()
  {
    return this.v;
  }

  public int getHour()
  {
    return this.x;
  }

  public String getId()
  {
    return this.q;
  }

  public int getMinute()
  {
    return this.y;
  }

  public int getMonth()
  {
    return this.t;
  }

  public UmengNotificationBuilder getNotificationBuilder()
  {
    return this.H;
  }

  public int getRepeatingInterval()
  {
    return this.C;
  }

  public int getRepeatingNum()
  {
    return this.A;
  }

  public int getRepeatingUnit()
  {
    return this.B;
  }

  public String getRepeatingUnitName()
  {
    switch (this.B)
    {
    default:
      return "";
    case 1:
      return "年";
    case 2:
      return "月";
    case 3:
      return "日";
    case 4:
      return "小时";
    case 5:
      return "分钟";
    case 6:
    }
    return "秒";
  }

  public int getSecond()
  {
    return this.z;
  }

  public int getSpecialDay()
  {
    return this.D;
  }

  public String getSpecialDayName()
  {
    switch (this.D)
    {
    default:
      return "";
    case 1:
      return "New Year's Day";
    case 2:
      return "Chinese New Year's Eve";
    case 3:
      return "Chinese New Year";
    case 4:
      return "Lantern";
    case 5:
      return "Qing Ming Festival";
    case 6:
      return "Labor's Day";
    case 7:
      return "Dragon Boat Festival";
    case 8:
      return "Qixi Festival";
    case 9:
      return "Mid Autumn Festival";
    case 10:
      return "National Day";
    case 11:
      return "Chung Yeung Festival";
    case 12:
    }
    return "Laba Festival";
  }

  public String getTicker()
  {
    return this.G;
  }

  public String getTitle()
  {
    return this.E;
  }

  public int getYear()
  {
    return this.r;
  }

  public void setContent(String paramString)
  {
    this.F = paramString;
  }

  public void setDateTime(String paramString)
  {
    try
    {
      long l1 = UmengLocalNotificationHelper.getTimeFromDate(paramString);
      paramString = Calendar.getInstance();
      paramString.setTimeInMillis(l1);
      this.r = paramString.get(1);
      this.t = (paramString.get(2) + 1);
      this.v = paramString.get(5);
      this.x = paramString.get(11);
      this.y = paramString.get(12);
      this.z = paramString.get(13);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      Log.d(b, paramString.toString());
    }
  }

  public void setDay(int paramInt)
  {
    this.v = paramInt;
  }

  public void setHour(int paramInt)
  {
    this.x = paramInt;
  }

  public void setLunarDateTime(String paramString)
  {
    try
    {
      setDateTime(UmengCalendar.lunarTosolar(paramString));
      return;
    }
    catch (Exception paramString)
    {
      Log.d(b, paramString.toString());
      paramString.printStackTrace();
    }
  }

  public void setMinute(int paramInt)
  {
    this.y = paramInt;
  }

  public void setMonth(int paramInt)
  {
    this.t = paramInt;
  }

  public void setNotificationBuilder(UmengNotificationBuilder paramUmengNotificationBuilder)
  {
    if (!TextUtils.equals(this.q, paramUmengNotificationBuilder.getLocalNotificationId()))
    {
      Log.e(b, "localNotificationId for notificationBuilder is not equal");
      paramUmengNotificationBuilder.setLocalNotificationId(this.q);
    }
    this.H = paramUmengNotificationBuilder;
  }

  public void setRepeatingInterval(int paramInt)
  {
    this.C = paramInt;
  }

  public void setRepeatingNum(int paramInt)
  {
    this.A = paramInt;
  }

  public void setRepeatingUnit(int paramInt)
  {
    this.B = paramInt;
  }

  public void setSecond(int paramInt)
  {
    this.z = paramInt;
  }

  public void setSpecialDay(int paramInt)
  {
    if ((paramInt >= 1) && (paramInt <= 7))
      this.B = 1;
    this.D = paramInt;
  }

  public void setTicker(String paramString)
  {
    this.G = paramString;
  }

  public void setTitle(String paramString)
  {
    this.E = paramString;
  }

  public void setYear(int paramInt)
  {
    this.r = paramInt;
  }

  public boolean validateData(Context paramContext)
  {
    if (this.A < -1)
    {
      Log.d(b, "repeatingNum is isValid");
      return false;
    }
    if (this.C <= 0)
    {
      Log.d(b, "repeatingInterval is isValid");
      return false;
    }
    if ((this.B < 1) || (this.B > 6))
    {
      Log.d(b, "repeatingUnit is isValid");
      return false;
    }
    if ((PushAgent.getInstance(paramContext).getLocalNotificationIntervalLimit()) && (((this.B == 6) && (this.C < 600)) || ((this.B == 5) && (this.C < 10))))
    {
      Log.d(b, "repeatingInterval is less than 10 minutes for limit");
      return false;
    }
    return true;
  }

  public boolean validateDateTime()
  {
    if ((this.D < 0) || (this.D > 12))
    {
      Log.d(b, "specialDay is isValid");
      return false;
    }
    int i1 = Calendar.getInstance().get(1);
    if (this.r < i1)
    {
      Log.d(b, "year is isValid");
      return false;
    }
    if ((this.t < 1) || (this.t > 12))
    {
      Log.d(b, "month is isValid");
      return false;
    }
    if ((this.v < 1) || (this.v > 31))
    {
      Log.d(b, "day is isValid");
      return false;
    }
    switch (this.t)
    {
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    default:
    case 4:
    case 6:
    case 9:
    case 11:
    case 2:
    }
    while ((this.x < 0) || (this.x > 23))
    {
      Log.d(b, "hour is isValid");
      return false;
      if (this.v > 30)
      {
        Log.d(b, "dateTime: day is invalid");
        return false;
        if (((this.r % 4 == 0) && (this.r % 100 != 0)) || (this.r % 400 == 0))
        {
          if (this.v > 29)
          {
            Log.d(b, "dateTime: day is invalid");
            return false;
          }
        }
        else if (this.v > 28)
        {
          Log.d(b, "dateTime: day is invalid");
          return false;
        }
      }
    }
    if ((this.y < 0) || (this.y > 59))
    {
      Log.d(b, "minute is isValid");
      return false;
    }
    if ((this.z < 0) || (this.z > 59))
    {
      Log.d(b, "second is isValid");
      return false;
    }
    long l2;
    if ((this.D >= 1) && (this.D <= 12))
    {
      l2 = 0L;
      long l3 = System.currentTimeMillis();
      l1 = l2;
      try
      {
        i1 = this.D;
        switch (i1)
        {
        default:
          l1 = l2;
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        case 11:
        case 12:
        }
        label663: label724: label859: label889: label1915: 
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    do
                    {
                      do
                      {
                        do
                        {
                          localObject = Calendar.getInstance();
                          ((Calendar)localObject).setTimeInMillis(l1);
                          this.r = ((Calendar)localObject).get(1);
                          this.t = (((Calendar)localObject).get(2) + 1);
                          this.v = ((Calendar)localObject).get(5);
                          this.x = ((Calendar)localObject).get(11);
                          this.y = ((Calendar)localObject).get(12);
                          this.z = ((Calendar)localObject).get(13);
                          Log.d(b, "year=" + this.r + ",month=" + this.t + ",day=" + this.v + ",specialDay=" + this.D);
                          return true;
                          l1 = l2;
                          localObject = this.r + "-01-01 ";
                          l1 = l2;
                          localStringBuilder = new StringBuilder().append((String)localObject);
                          l1 = l2;
                          if (this.x >= 10)
                          {
                            l1 = l2;
                            localObject = Integer.valueOf(this.x);
                            l1 = l2;
                            localObject = localObject + ":";
                            l1 = l2;
                            localStringBuilder = new StringBuilder().append((String)localObject);
                            l1 = l2;
                            if (this.y < 10)
                              break label859;
                            l1 = l2;
                            localObject = Integer.valueOf(this.y);
                            l1 = l2;
                            localObject = localObject + ":";
                            l1 = l2;
                            localStringBuilder = new StringBuilder().append((String)localObject);
                            l1 = l2;
                            if (this.z < 10)
                              break label889;
                            l1 = l2;
                          }
                          for (localObject = Integer.valueOf(this.z); ; localObject = "0" + this.z)
                          {
                            l1 = l2;
                            l2 = UmengLocalNotificationHelper.getTimeFromDate(localObject);
                            l1 = l2;
                            if (l2 >= l3)
                              break;
                            l1 = l2;
                            Log.d(b, "元旦的开始年份小于当前时间，请重新设置");
                            return false;
                            l1 = l2;
                            localObject = "0" + this.x;
                            break label663;
                            l1 = l2;
                            localObject = "0" + this.y;
                            break label724;
                            l1 = l2;
                          }
                          l1 = l2;
                          this.t = 12;
                          l1 = l2;
                          this.v = UmengCalendar.iGetLMonthDays(this.r, 12);
                          l1 = l2;
                          l2 = UmengLocalNotificationHelper.getTimeFromDate(UmengCalendar.lunarTosolar(getDateTime()));
                          l1 = l2;
                        }
                        while (l2 >= l3);
                        l1 = l2;
                        Log.d(b, "除夕的开始年份小于当前时间，请重新设置");
                        return false;
                        l1 = l2;
                        this.t = 1;
                        l1 = l2;
                        this.v = 1;
                        l1 = l2;
                        l2 = UmengLocalNotificationHelper.getTimeFromDate(UmengCalendar.lunarTosolar(getDateTime()));
                        l1 = l2;
                      }
                      while (l2 >= l3);
                      l1 = l2;
                      Log.d(b, "春节的开始年份小于当前时间，请重新设置");
                      return false;
                      l1 = l2;
                      this.t = 1;
                      l1 = l2;
                      this.v = 15;
                      l1 = l2;
                      l2 = UmengLocalNotificationHelper.getTimeFromDate(UmengCalendar.lunarTosolar(getDateTime()));
                      l1 = l2;
                    }
                    while (l2 >= l3);
                    l1 = l2;
                    Log.d(b, "元宵节的开始年份小于当前时间，请重新设置");
                    return false;
                    l1 = l2;
                    l2 = UmengLocalNotificationHelper.getQingMingTime(this.r, this.x, this.y, this.z);
                    l1 = l2;
                  }
                  while (l2 >= l3);
                  l1 = l2;
                  Log.d(b, "清明的开始年份小于当前时间，请重新设置");
                  return false;
                  l1 = l2;
                  localObject = this.r + "-05-01 ";
                  l1 = l2;
                  localStringBuilder = new StringBuilder().append((String)localObject);
                  l1 = l2;
                  if (this.x >= 10)
                  {
                    l1 = l2;
                    localObject = Integer.valueOf(this.x);
                    l1 = l2;
                    localObject = localObject + ":";
                    l1 = l2;
                    localStringBuilder = new StringBuilder().append((String)localObject);
                    l1 = l2;
                    if (this.y < 10)
                      break label1415;
                    l1 = l2;
                    localObject = Integer.valueOf(this.y);
                    l1 = l2;
                    localObject = localObject + ":";
                    l1 = l2;
                    localStringBuilder = new StringBuilder().append((String)localObject);
                    l1 = l2;
                    if (this.z < 10)
                      break label1445;
                    l1 = l2;
                  }
                  for (localObject = Integer.valueOf(this.z); ; localObject = "0" + this.z)
                  {
                    l1 = l2;
                    l2 = UmengLocalNotificationHelper.getTimeFromDate(localObject);
                    l1 = l2;
                    if (l2 >= l3)
                      break;
                    l1 = l2;
                    Log.d(b, "五一劳动节的开始年份小于当前时间，请重新设置");
                    return false;
                    l1 = l2;
                    localObject = "0" + this.x;
                    break label1219;
                    l1 = l2;
                    localObject = "0" + this.y;
                    break label1280;
                    l1 = l2;
                  }
                  l1 = l2;
                  this.t = 5;
                  l1 = l2;
                  this.v = 5;
                  l1 = l2;
                  l2 = UmengLocalNotificationHelper.getTimeFromDate(UmengCalendar.lunarTosolar(getDateTime()));
                  l1 = l2;
                }
                while (l2 >= l3);
                l1 = l2;
                Log.d(b, "端午节的开始年份小于当前时间，请重新设置");
                return false;
                l1 = l2;
                this.t = 7;
                l1 = l2;
                this.v = 7;
                l1 = l2;
                l2 = UmengLocalNotificationHelper.getTimeFromDate(UmengCalendar.lunarTosolar(getDateTime()));
                l1 = l2;
              }
              while (l2 >= l3);
              l1 = l2;
              Log.d(b, "七夕节的开始年份小于当前时间，请重新设置");
              return false;
              l1 = l2;
              this.t = 8;
              l1 = l2;
              this.v = 15;
              l1 = l2;
              l2 = UmengLocalNotificationHelper.getTimeFromDate(UmengCalendar.lunarTosolar(getDateTime()));
              l1 = l2;
            }
            while (l2 >= l3);
            l1 = l2;
            Log.d(b, "春节的开始年份小于当前时间，请重新设置");
            return false;
            l1 = l2;
            Object localObject = this.r + "-10-01 ";
            l1 = l2;
            StringBuilder localStringBuilder = new StringBuilder().append((String)localObject);
            l1 = l2;
            if (this.x >= 10)
            {
              l1 = l2;
              localObject = Integer.valueOf(this.x);
              l1 = l2;
              localObject = localObject + ":";
              l1 = l2;
              localStringBuilder = new StringBuilder().append((String)localObject);
              l1 = l2;
              if (this.y < 10)
                break label1915;
              l1 = l2;
              localObject = Integer.valueOf(this.y);
              l1 = l2;
              localObject = localObject + ":";
              l1 = l2;
              localStringBuilder = new StringBuilder().append((String)localObject);
              l1 = l2;
              if (this.z < 10)
                break label1945;
              l1 = l2;
            }
            for (localObject = Integer.valueOf(this.z); ; localObject = "0" + this.z)
            {
              l1 = l2;
              l2 = UmengLocalNotificationHelper.getTimeFromDate(localObject);
              l1 = l2;
              if (l2 >= l3)
                break;
              l1 = l2;
              Log.d(b, "国庆节的开始年份小于当前时间，请重新设置");
              return false;
              l1 = l2;
              localObject = "0" + this.x;
              break label1719;
              l1 = l2;
              localObject = "0" + this.y;
              break label1780;
              l1 = l2;
            }
            l1 = l2;
            this.t = 9;
            l1 = l2;
            this.v = 9;
            l1 = l2;
            l2 = UmengLocalNotificationHelper.getTimeFromDate(UmengCalendar.lunarTosolar(getDateTime()));
            l1 = l2;
          }
          while (l2 >= l3);
          l1 = l2;
          Log.d(b, "清明节的开始年份小于当前时间，请重新设置");
          return false;
          l1 = l2;
          this.t = 12;
          l1 = l2;
          this.v = 8;
          l1 = l2;
          l2 = UmengLocalNotificationHelper.getTimeFromDate(UmengCalendar.lunarTosolar(getDateTime()));
          l1 = l2;
        }
        while (l2 >= l3);
        label1219: label1780: l1 = l2;
        label1280: label1415: label1445: label1719: Log.d(b, "腊八节的开始年份小于当前时间，请重新设置");
        label1945: return false;
      }
      catch (Exception localException1)
      {
        while (true)
          localException1.printStackTrace();
      }
    }
    long l1 = 0L;
    try
    {
      l2 = UmengLocalNotificationHelper.getTimeFromDate(getDateTime());
      l1 = l2;
      if (l1 < System.currentTimeMillis())
      {
        Log.d(b, "dateTime: date time is invalid");
        return false;
      }
    }
    catch (Exception localException2)
    {
      while (true)
        localException2.printStackTrace();
    }
    return true;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.local.UmengLocalNotification
 * JD-Core Version:    0.6.2
 */