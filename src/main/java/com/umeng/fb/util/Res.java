package com.umeng.fb.util;

import android.content.Context;
import java.lang.reflect.Field;

public class Res
{
  private static final String a = Res.class.getName();
  private static Res b = null;
  private static String c = null;
  private static Class d = null;
  private static Class e = null;
  private static Class f = null;
  private static Class g = null;
  private static Class h = null;
  private static Class i = null;
  private static Class j = null;
  private static Class k = null;
  private static Class l = null;

  private Res(String paramString)
  {
    try
    {
      e = Class.forName(paramString + ".R$drawable");
    }
    catch (ClassNotFoundException localClassNotFoundException8)
    {
      try
      {
        f = Class.forName(paramString + ".R$layout");
      }
      catch (ClassNotFoundException localClassNotFoundException8)
      {
        try
        {
          d = Class.forName(paramString + ".R$id");
        }
        catch (ClassNotFoundException localClassNotFoundException8)
        {
          try
          {
            g = Class.forName(paramString + ".R$anim");
          }
          catch (ClassNotFoundException localClassNotFoundException8)
          {
            try
            {
              h = Class.forName(paramString + ".R$style");
            }
            catch (ClassNotFoundException localClassNotFoundException8)
            {
              try
              {
                i = Class.forName(paramString + ".R$string");
              }
              catch (ClassNotFoundException localClassNotFoundException8)
              {
                try
                {
                  j = Class.forName(paramString + ".R$array");
                }
                catch (ClassNotFoundException localClassNotFoundException8)
                {
                  try
                  {
                    k = Class.forName(paramString + ".R$color");
                  }
                  catch (ClassNotFoundException localClassNotFoundException8)
                  {
                    try
                    {
                      while (true)
                      {
                        l = Class.forName(paramString + ".R$styleable");
                        return;
                        localClassNotFoundException1 = localClassNotFoundException1;
                        Log.b(a, localClassNotFoundException1.getMessage());
                        continue;
                        localClassNotFoundException2 = localClassNotFoundException2;
                        Log.b(a, localClassNotFoundException2.getMessage());
                        continue;
                        localClassNotFoundException3 = localClassNotFoundException3;
                        Log.b(a, localClassNotFoundException3.getMessage());
                        continue;
                        localClassNotFoundException4 = localClassNotFoundException4;
                        Log.b(a, localClassNotFoundException4.getMessage());
                        continue;
                        localClassNotFoundException5 = localClassNotFoundException5;
                        Log.b(a, localClassNotFoundException5.getMessage());
                        continue;
                        localClassNotFoundException6 = localClassNotFoundException6;
                        Log.b(a, localClassNotFoundException6.getMessage());
                        continue;
                        localClassNotFoundException7 = localClassNotFoundException7;
                        Log.b(a, localClassNotFoundException7.getMessage());
                        continue;
                        localClassNotFoundException8 = localClassNotFoundException8;
                        Log.b(a, localClassNotFoundException8.getMessage());
                      }
                    }
                    catch (ClassNotFoundException paramString)
                    {
                      Log.b(a, paramString.getMessage());
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }

  private int a(Class<?> paramClass, String paramString)
  {
    if (paramClass == null)
    {
      Log.b(a, "getRes(null," + paramString + ")");
      throw new IllegalArgumentException("ResClass is not initialized. Please make sure you have added necessary resources. Also make sure you have " + c + ".R$* configured in obfuscation. field=" + paramString);
    }
    try
    {
      int m = paramClass.getField(paramString).getInt(paramString);
      return m;
    }
    catch (Exception localException)
    {
      Log.b(a, "getRes(" + paramClass.getName() + ", " + paramString + ")");
      Log.b(a, "Error getting resource. Make sure you have copied all resources (res/) from SDK to your project. ");
      Log.b(a, localException.getMessage());
    }
    return -1;
  }

  public static Res getInstance(Context paramContext)
  {
    try
    {
      if (b == null)
        if (c == null)
          break label45;
      label45: for (paramContext = c; ; paramContext = paramContext.getPackageName())
      {
        c = paramContext;
        b = new Res(c);
        paramContext = b;
        return paramContext;
      }
    }
    finally
    {
    }
    throw paramContext;
  }

  public static void setPackageName(String paramString)
  {
    c = paramString;
  }

  public int a(String paramString)
  {
    return a(g, paramString);
  }

  public int b(String paramString)
  {
    return a(d, paramString);
  }

  public int c(String paramString)
  {
    return a(e, paramString);
  }

  public int d(String paramString)
  {
    return a(f, paramString);
  }

  public int e(String paramString)
  {
    return a(h, paramString);
  }

  public int f(String paramString)
  {
    return a(i, paramString);
  }

  public int g(String paramString)
  {
    return a(j, paramString);
  }

  public int h(String paramString)
  {
    return a(k, paramString);
  }

  public int i(String paramString)
  {
    return a(l, paramString);
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.util.Res
 * JD-Core Version:    0.6.2
 */