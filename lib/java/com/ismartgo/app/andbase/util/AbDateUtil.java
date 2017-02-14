package com.ismartgo.app.andbase.util;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AbDateUtil
{
  public static final String AM = "AM";
  public static final String PM = "PM";
  public static final String dateFormatHM = "HH:mm";
  public static final String dateFormatHMS = "HH:mm:ss";
  public static final String dateFormatMD = "MM/dd";
  public static final String dateFormatYM = "yyyy-MM";
  public static final String dateFormatYMD = "yyyy-MM-dd";
  public static final String dateFormatYMDHM = "yyyy-MM-dd HH:mm";
  public static final String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";

  public static String formatDateStr2Desc(String paramString1, String paramString2)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar localCalendar1 = Calendar.getInstance();
    Calendar localCalendar2 = Calendar.getInstance();
    try
    {
      localCalendar2.setTime(localSimpleDateFormat.parse(paramString1));
      localCalendar1.setTime(new Date());
      int i = getOffectDay(localCalendar1.getTimeInMillis(), localCalendar2.getTimeInMillis());
      if (i == 0)
      {
        i = getOffectHour(localCalendar1.getTimeInMillis(), localCalendar2.getTimeInMillis());
        if (i > 0)
          return "今天" + getStringByFormat(paramString1, "HH:mm");
        if ((i >= 0) && (i == 0))
        {
          i = getOffectMinutes(localCalendar1.getTimeInMillis(), localCalendar2.getTimeInMillis());
          if (i > 0)
          {
            paramString2 = i + "分钟前";
            return paramString2;
          }
          if (i >= 0)
            return "刚刚";
        }
      }
      else
      {
        if (i <= 0)
          break label183;
        if (i == 1);
      }
      while (true)
      {
        paramString2 = getStringByFormat(paramString1, paramString2);
        boolean bool = AbStrUtil.isEmpty(paramString2);
        if (!bool)
          break;
        label181: return paramString1;
        label183: if ((i >= 0) || (i == -1));
      }
    }
    catch (Exception paramString2)
    {
      break label181;
    }
    return paramString2;
  }

  public static String getCurrentDate(String paramString)
  {
    AbLogUtil.d(AbDateUtil.class, "getCurrentDate:" + paramString);
    try
    {
      paramString = new SimpleDateFormat(paramString).format(new GregorianCalendar().getTime());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String getCurrentDateByOffset(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramString = new SimpleDateFormat(paramString);
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      localGregorianCalendar.add(paramInt1, paramInt2);
      paramString = paramString.format(localGregorianCalendar.getTime());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static Date getDateByFormat(String paramString1, String paramString2)
  {
    paramString2 = new SimpleDateFormat(paramString2);
    try
    {
      paramString1 = paramString2.parse(paramString1);
      return paramString1;
    }
    catch (ParseException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  private static String getDayOfWeek(String paramString, int paramInt)
  {
    try
    {
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      paramString = new SimpleDateFormat(paramString);
      int i = localGregorianCalendar.get(7);
      if (i == paramInt)
        return paramString.format(localGregorianCalendar.getTime());
      int j = paramInt - i;
      i = j;
      if (paramInt == 1)
        i = 7 - Math.abs(j);
      localGregorianCalendar.add(5, i);
      paramString = paramString.format(localGregorianCalendar.getTime());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String getFirstDayOfMonth(String paramString)
  {
    try
    {
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      paramString = new SimpleDateFormat(paramString);
      localGregorianCalendar.set(5, 1);
      paramString = paramString.format(localGregorianCalendar.getTime());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String getFirstDayOfWeek(String paramString)
  {
    return getDayOfWeek(paramString, 2);
  }

  public static long getFirstTimeOfDay()
  {
    try
    {
      long l = getDateByFormat(getCurrentDate("yyyy-MM-dd") + " 00:00:00", "yyyy-MM-dd HH:mm:ss").getTime();
      return l;
    }
    catch (Exception localException)
    {
    }
    return -1L;
  }

  public static String getLastDayOfMonth(String paramString)
  {
    try
    {
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      paramString = new SimpleDateFormat(paramString);
      localGregorianCalendar.set(5, 1);
      localGregorianCalendar.roll(5, -1);
      paramString = paramString.format(localGregorianCalendar.getTime());
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String getLastDayOfWeek(String paramString)
  {
    return getDayOfWeek(paramString, 1);
  }

  public static long getLastTimeOfDay()
  {
    try
    {
      long l = getDateByFormat(getCurrentDate("yyyy-MM-dd") + " 24:00:00", "yyyy-MM-dd HH:mm:ss").getTime();
      return l;
    }
    catch (Exception localException)
    {
    }
    return -1L;
  }

  public static int getOffectDay(long paramLong1, long paramLong2)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTimeInMillis(paramLong1);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(paramLong2);
    int i = localCalendar1.get(1);
    int j = localCalendar2.get(1);
    int k = localCalendar1.get(6);
    int m = localCalendar2.get(6);
    if (i - j > 0)
      return k - m + localCalendar2.getActualMaximum(6);
    if (i - j < 0)
      return k - m - localCalendar1.getActualMaximum(6);
    return k - m;
  }

  public static int getOffectHour(long paramLong1, long paramLong2)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTimeInMillis(paramLong1);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(paramLong2);
    return localCalendar1.get(11) - localCalendar2.get(11) + getOffectDay(paramLong1, paramLong2) * 24;
  }

  public static int getOffectMinutes(long paramLong1, long paramLong2)
  {
    Calendar localCalendar1 = Calendar.getInstance();
    localCalendar1.setTimeInMillis(paramLong1);
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar2.setTimeInMillis(paramLong2);
    return localCalendar1.get(12) - localCalendar2.get(12) + getOffectHour(paramLong1, paramLong2) * 60;
  }

  public static String getStringByFormat(long paramLong, String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat(paramString).format(Long.valueOf(paramLong));
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static String getStringByFormat(String paramString1, String paramString2)
  {
    try
    {
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      localGregorianCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString1));
      paramString1 = new SimpleDateFormat(paramString2).format(localGregorianCalendar.getTime());
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  public static String getStringByFormat(Date paramDate, String paramString)
  {
    paramString = new SimpleDateFormat(paramString);
    try
    {
      paramDate = paramString.format(paramDate);
      return paramDate;
    }
    catch (Exception paramDate)
    {
      paramDate.printStackTrace();
    }
    return null;
  }

  public static String getStringByOffset(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    try
    {
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      paramString2 = new SimpleDateFormat(paramString2);
      localGregorianCalendar.setTime(paramString2.parse(paramString1));
      localGregorianCalendar.add(paramInt1, paramInt2);
      paramString1 = paramString2.format(localGregorianCalendar.getTime());
      return paramString1;
    }
    catch (ParseException paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  public static String getStringByOffset(Date paramDate, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      GregorianCalendar localGregorianCalendar = new GregorianCalendar();
      paramString = new SimpleDateFormat(paramString);
      localGregorianCalendar.setTime(paramDate);
      localGregorianCalendar.add(paramInt1, paramInt2);
      paramDate = paramString.format(localGregorianCalendar.getTime());
      return paramDate;
    }
    catch (Exception paramDate)
    {
      paramDate.printStackTrace();
    }
    return null;
  }

  public static String getTimeDescription(long paramLong)
  {
    if (paramLong > 1000L)
    {
      if (paramLong / 1000L / 60L > 1L)
      {
        long l = paramLong / 1000L / 60L;
        paramLong /= 1000L;
        return l + "分" + paramLong % 60L + "秒";
      }
      return paramLong / 1000L + "秒";
    }
    return paramLong + "毫秒";
  }

  public static String getTimeQuantum(String paramString1, String paramString2)
  {
    if (getDateByFormat(paramString1, paramString2).getHours() >= 12)
      return "PM";
    return "AM";
  }

  public static String getWeekNumber(String paramString1, String paramString2)
  {
    String str = "星期日";
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    paramString2 = new SimpleDateFormat(paramString2);
    while (true)
    {
      try
      {
        localGregorianCalendar.setTime(paramString2.parse(paramString1));
        switch (localGregorianCalendar.get(7) - 1)
        {
        default:
          paramString1 = str;
          return paramString1;
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        }
      }
      catch (Exception paramString1)
      {
        return "错误";
      }
      paramString1 = "星期日";
      continue;
      paramString1 = "星期一";
      continue;
      paramString1 = "星期二";
      continue;
      paramString1 = "星期三";
      continue;
      paramString1 = "星期四";
      continue;
      paramString1 = "星期五";
      continue;
      paramString1 = "星期六";
    }
  }

  public static boolean isLeapYear(int paramInt)
  {
    return ((paramInt % 4 == 0) && (paramInt % 400 != 0)) || (paramInt % 400 == 0);
  }

  public static void main(String[] paramArrayOfString)
  {
    System.out.println(formatDateStr2Desc("2012-3-2 12:2:20", "MM月dd日  HH:mm"));
  }

  public Date getDateByOffset(Date paramDate, int paramInt1, int paramInt2)
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    try
    {
      localGregorianCalendar.setTime(paramDate);
      localGregorianCalendar.add(paramInt1, paramInt2);
      return localGregorianCalendar.getTime();
    }
    catch (Exception paramDate)
    {
      while (true)
        paramDate.printStackTrace();
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.andbase.util.AbDateUtil
 * JD-Core Version:    0.6.2
 */