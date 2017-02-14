package com.umeng.message.proguard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class bw
{
  public static final long a(String paramString)
  {
    try
    {
      long l = new SimpleDateFormat("yyyyMMddHHmmss").parse(paramString).getTime();
      return l;
    }
    catch (Throwable paramString)
    {
    }
    return -1L;
  }

  public static final String a(long paramLong)
  {
    try
    {
      String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(paramLong));
      return str;
    }
    catch (Throwable localThrowable)
    {
    }
    return "none";
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.bw
 * JD-Core Version:    0.6.2
 */