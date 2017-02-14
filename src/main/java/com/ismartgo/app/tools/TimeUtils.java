package com.ismartgo.app.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils
{
  public static boolean compareTime(String paramString)
  {
    Date localDate = new Date();
    long l1 = 0L;
    try
    {
      paramString = new SimpleDateFormat("yyyy-MM-dd").parse(paramString);
      long l2 = (localDate.getTime() - paramString.getTime()) / 86400000L;
      l1 = l2;
      if (l1 >= 7L)
        return false;
    }
    catch (ParseException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
    return true;
  }

  public static String getCurrentTime()
  {
    return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
  }

  public static String getCurrentTime(String paramString)
  {
    Date localDate = new Date();
    return new SimpleDateFormat(paramString, Locale.getDefault()).format(localDate);
  }

  public static String getCurrentTime2(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm", Locale.getDefault());
    try
    {
      paramString = localSimpleDateFormat.format(localSimpleDateFormat.parse(paramString));
      return paramString;
    }
    catch (ParseException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }

  public static String getCurrentTimeAdapterTenMinute()
  {
    Object localObject = Calendar.getInstance();
    ((Calendar)localObject).set(12, ((Calendar)localObject).get(12) + 10);
    localObject = ((Calendar)localObject).getTime();
    return new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss", Locale.getDefault()).format((Date)localObject);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.ismartgo.app.tools.TimeUtils
 * JD-Core Version:    0.6.2
 */