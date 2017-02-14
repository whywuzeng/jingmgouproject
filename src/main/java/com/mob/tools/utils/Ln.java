package com.mob.tools.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

public class Ln
{
  private static BaseConfig config = new BaseConfig();
  private static Print print = new Print();

  public static void close()
  {
    config.minimumLogLevel = 7;
  }

  public static int d(Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 3)
    {
      str = paramObject.toString();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      return print.broadcast(3, paramObject);
    }
    String str = paramObject.toString();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    return print.println(3, paramObject);
  }

  public static int d(Throwable paramThrowable)
  {
    if (config.minimumLogLevel <= 3)
      return print.println(3, Log.getStackTraceString(paramThrowable));
    return print.broadcast(3, Log.getStackTraceString(paramThrowable));
  }

  public static int d(Throwable paramThrowable, Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 3)
    {
      str = paramObject.toString();
      localStringBuilder = new StringBuilder();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      paramThrowable = paramObject + '\n' + Log.getStackTraceString(paramThrowable);
      return print.broadcast(3, paramThrowable);
    }
    String str = paramObject.toString();
    StringBuilder localStringBuilder = new StringBuilder();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    paramThrowable = paramObject + '\n' + Log.getStackTraceString(paramThrowable);
    return print.println(3, paramThrowable);
  }

  public static int e(Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 6)
    {
      str = paramObject.toString();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      return print.broadcast(6, paramObject);
    }
    String str = paramObject.toString();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    return print.println(6, paramObject);
  }

  public static int e(Throwable paramThrowable)
  {
    if (config.minimumLogLevel <= 6)
      return print.println(6, Log.getStackTraceString(paramThrowable));
    return print.broadcast(6, Log.getStackTraceString(paramThrowable));
  }

  public static int e(Throwable paramThrowable, Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 6)
    {
      str = paramObject.toString();
      localStringBuilder = new StringBuilder();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      paramThrowable = paramObject + '\n' + Log.getStackTraceString(paramThrowable);
      return print.broadcast(6, paramThrowable);
    }
    String str = paramObject.toString();
    StringBuilder localStringBuilder = new StringBuilder();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    paramThrowable = paramObject + '\n' + Log.getStackTraceString(paramThrowable);
    return print.println(6, paramThrowable);
  }

  public static int i(Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 4)
    {
      str = paramObject.toString();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      return print.broadcast(4, paramObject);
    }
    String str = paramObject.toString();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    return print.println(4, paramObject);
  }

  public static int i(Throwable paramThrowable)
  {
    if (config.minimumLogLevel <= 4)
      return print.println(4, Log.getStackTraceString(paramThrowable));
    return print.broadcast(4, Log.getStackTraceString(paramThrowable));
  }

  public static int i(Throwable paramThrowable, Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 4)
    {
      str = paramObject.toString();
      localStringBuilder = new StringBuilder();
      paramObject = str;
      if (paramArrayOfObject.length > 0)
        paramObject = String.format(str, paramArrayOfObject);
      paramThrowable = paramObject + '\n' + Log.getStackTraceString(paramThrowable);
      return print.broadcast(4, paramThrowable);
    }
    String str = paramObject.toString();
    StringBuilder localStringBuilder = new StringBuilder();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    paramThrowable = paramObject + '\n' + Log.getStackTraceString(paramThrowable);
    return print.println(4, paramThrowable);
  }

  public static boolean isDebugEnabled()
  {
    return config.minimumLogLevel <= 3;
  }

  public static boolean isVerboseEnabled()
  {
    return config.minimumLogLevel <= 2;
  }

  public static String logLevelToString(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "UNKNOWN";
    case 2:
      return "VERBOSE";
    case 3:
      return "DEBUG";
    case 4:
      return "INFO";
    case 5:
      return "WARN";
    case 6:
      return "ERROR";
    case 7:
    }
    return "ASSERT";
  }

  public static void setContext(Context paramContext)
  {
    config.setContext(paramContext.getApplicationContext());
    print.setContext(paramContext.getApplicationContext());
  }

  public static int v(Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 2)
      return 0;
    String str = paramObject.toString();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    return print.println(2, paramObject);
  }

  public static int v(Throwable paramThrowable)
  {
    if (config.minimumLogLevel <= 2)
      return print.println(2, Log.getStackTraceString(paramThrowable));
    return 0;
  }

  public static int v(Throwable paramThrowable, Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 2)
      return 0;
    String str = paramObject.toString();
    StringBuilder localStringBuilder = new StringBuilder();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    paramThrowable = paramObject + '\n' + Log.getStackTraceString(paramThrowable);
    return print.println(2, paramThrowable);
  }

  public static int w(Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 5)
      return 0;
    String str = paramObject.toString();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    return print.println(5, paramObject);
  }

  public static int w(Throwable paramThrowable)
  {
    if (config.minimumLogLevel <= 5)
      return print.println(5, Log.getStackTraceString(paramThrowable));
    return 0;
  }

  public static int w(Throwable paramThrowable, Object paramObject, Object[] paramArrayOfObject)
  {
    if (config.minimumLogLevel > 5)
      return 0;
    String str = paramObject.toString();
    StringBuilder localStringBuilder = new StringBuilder();
    paramObject = str;
    if (paramArrayOfObject.length > 0)
      paramObject = String.format(str, paramArrayOfObject);
    paramThrowable = paramObject + '\n' + Log.getStackTraceString(paramThrowable);
    return print.println(5, paramThrowable);
  }

  private static class BaseConfig
  {
    public int minimumLogLevel = 2;
    public String packageName = "";
    public String scope = "";

    public void setContext(Context paramContext)
    {
      if (paramContext == null)
        return;
      this.packageName = paramContext.getPackageName();
      if (TextUtils.isEmpty(this.packageName))
      {
        this.packageName = "";
        return;
      }
      this.scope = this.packageName.toUpperCase();
    }
  }

  private static class Print
  {
    public Context context;
    public String packageName = "";

    protected static String getScope(int paramInt)
    {
      if (Ln.config.minimumLogLevel <= 3)
      {
        Object localObject = Thread.currentThread().getStackTrace();
        if ((paramInt >= 0) && (paramInt < localObject.length))
        {
          String str2 = localObject[paramInt];
          localObject = str2.getFileName();
          if ((localObject == null) || (((String)localObject).length() <= 0));
          for (String str1 = str2.getClassName(); ; str1 = Ln.config.scope + "/" + (String)localObject)
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
      return Ln.config.scope;
    }

    public int broadcast(int paramInt, String paramString)
    {
      if (this.context != null);
      try
      {
        Intent localIntent = new Intent();
        localIntent.setAction("cn.sharesdk.log");
        localIntent.putExtra("package", this.packageName);
        localIntent.putExtra("priority", paramInt);
        localIntent.putExtra("msg", paramString);
        this.context.sendBroadcast(localIntent);
        label57: return 0;
      }
      catch (Throwable paramString)
      {
        break label57;
      }
    }

    public int println(int paramInt, String paramString)
    {
      return Log.println(paramInt, getScope(5), processMessage(paramString));
    }

    protected String processMessage(String paramString)
    {
      String str = paramString;
      if (Ln.config.minimumLogLevel <= 3)
        str = String.format("%s %s", new Object[] { Thread.currentThread().getName(), paramString });
      return str;
    }

    public void setContext(Context paramContext)
    {
      if (paramContext == null);
      do
      {
        return;
        this.context = paramContext;
        this.packageName = paramContext.getPackageName();
      }
      while (!TextUtils.isEmpty(this.packageName));
      this.packageName = "";
    }
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.mob.tools.utils.Ln
 * JD-Core Version:    0.6.2
 */