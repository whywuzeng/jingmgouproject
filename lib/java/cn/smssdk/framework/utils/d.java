package cn.smssdk.framework.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class d
{
  protected static a a = new a();
  protected static b b = new b();
  private static Context c;
  private static String d;

  public static int a(Object paramObject, Object[] paramArrayOfObject)
  {
    if (a.a > 3)
    {
      str = paramObject.toString();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      return b.b(3, paramObject);
    }
    String str = paramObject.toString();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    return b.a(3, paramObject);
  }

  public static int a(Throwable paramThrowable)
  {
    if (a.a <= 3)
      return b.a(3, Log.getStackTraceString(paramThrowable));
    return b.b(3, Log.getStackTraceString(paramThrowable));
  }

  public static int b(Object paramObject, Object[] paramArrayOfObject)
  {
    if (a.a > 4)
    {
      str = paramObject.toString();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      return b.b(4, paramObject);
    }
    String str = paramObject.toString();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    return b.a(4, paramObject);
  }

  public static int b(Throwable paramThrowable)
  {
    if (a.a <= 5)
      return b.a(5, Log.getStackTraceString(paramThrowable));
    return 0;
  }

  public static int c(Object paramObject, Object[] paramArrayOfObject)
  {
    if (a.a > 6)
    {
      str = paramObject.toString();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      return b.b(6, paramObject);
    }
    String str = paramObject.toString();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    return b.a(6, paramObject);
  }

  public static int c(Throwable paramThrowable)
  {
    if (a.a <= 6)
      return b.a(6, Log.getStackTraceString(paramThrowable));
    return b.b(6, Log.getStackTraceString(paramThrowable));
  }

  public static class a
  {
    protected int a = 7;
    protected String b = "";
    protected String c = "";
  }

  public static class b
  {
    protected static String a(int paramInt)
    {
      if (d.a.a <= 3)
      {
        Object localObject = Thread.currentThread().getStackTrace();
        if ((paramInt >= 0) && (paramInt < localObject.length))
        {
          String str2 = localObject[paramInt];
          localObject = str2.getFileName();
          if ((localObject == null) || (((String)localObject).length() <= 0));
          for (String str1 = str2.getClassName(); ; str1 = d.a.c + "/" + (String)localObject)
          {
            paramInt = str2.getLineNumber();
            localObject = String.valueOf(paramInt);
            if (paramInt < 0)
            {
              str2 = str2.getMethodName();
              if (str2 != null)
              {
                localObject = str2;
                if (str2.length() > 0);
              }
              else
              {
                localObject = "Unknown Source";
              }
            }
            return str1 + "(" + (String)localObject + ")";
          }
        }
      }
      return d.a.c;
    }

    public int a(int paramInt, String paramString)
    {
      return Log.println(paramInt, a(5), a(paramString));
    }

    protected String a(String paramString)
    {
      String str = paramString;
      if (d.a.a <= 3)
        str = String.format("%s %s", new Object[] { Thread.currentThread().getName(), paramString });
      return str;
    }

    public int b(int paramInt, String paramString)
    {
      if (d.a() != null);
      try
      {
        Intent localIntent = new Intent();
        localIntent.setAction("cn.sharesdk.log");
        localIntent.putExtra("package", d.b());
        localIntent.putExtra("priority", paramInt);
        localIntent.putExtra("msg", paramString);
        d.a().sendBroadcast(localIntent);
        label54: return 0;
      }
      catch (Throwable paramString)
      {
        break label54;
      }
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     cn.smssdk.framework.utils.d
 * JD-Core Version:    0.6.2
 */