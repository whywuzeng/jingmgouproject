package com.umeng.common.message;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.PushAgent;
import java.lang.reflect.Field;

public class c
{
  private static final String a = c.class.getName();
  private static c b;
  private static Class e = null;
  private static Class f = null;
  private static Class g = null;
  private static Class h = null;
  private static Class i = null;
  private static Class j = null;
  private static Class k = null;
  private static Class l = null;
  private Context c;
  private String d;

  private c(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
    Log.c(a, "packageName=" + this.c.getPackageName());
    try
    {
      localStringBuilder = new StringBuilder();
      if (!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()))
      {
        paramContext = PushAgent.getInstance(this.c).getResourcePackageName();
        f = Class.forName(paramContext + ".R$drawable");
      }
    }
    catch (ClassNotFoundException paramContext)
    {
      try
      {
        label97: localStringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()))
        {
          paramContext = PushAgent.getInstance(this.c).getResourcePackageName();
          label132: g = Class.forName(paramContext + ".R$layout");
        }
      }
      catch (ClassNotFoundException paramContext)
      {
        try
        {
          label151: localStringBuilder = new StringBuilder();
          if (!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()))
          {
            paramContext = PushAgent.getInstance(this.c).getResourcePackageName();
            label186: e = Class.forName(paramContext + ".R$id");
          }
        }
        catch (ClassNotFoundException paramContext)
        {
          try
          {
            label205: localStringBuilder = new StringBuilder();
            if (!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()))
            {
              paramContext = PushAgent.getInstance(this.c).getResourcePackageName();
              label240: h = Class.forName(paramContext + ".R$anim");
            }
          }
          catch (ClassNotFoundException paramContext)
          {
            try
            {
              label259: localStringBuilder = new StringBuilder();
              if (!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()))
              {
                paramContext = PushAgent.getInstance(this.c).getResourcePackageName();
                label294: i = Class.forName(paramContext + ".R$style");
              }
            }
            catch (ClassNotFoundException paramContext)
            {
              try
              {
                label313: localStringBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()))
                {
                  paramContext = PushAgent.getInstance(this.c).getResourcePackageName();
                  label348: j = Class.forName(paramContext + ".R$string");
                }
              }
              catch (ClassNotFoundException paramContext)
              {
                try
                {
                  label367: localStringBuilder = new StringBuilder();
                  if (!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()))
                  {
                    paramContext = PushAgent.getInstance(this.c).getResourcePackageName();
                    label402: k = Class.forName(paramContext + ".R$array");
                  }
                }
                catch (ClassNotFoundException paramContext)
                {
                  try
                  {
                    label421: StringBuilder localStringBuilder = new StringBuilder();
                    if (!TextUtils.isEmpty(PushAgent.getInstance(this.c).getResourcePackageName()));
                    for (paramContext = PushAgent.getInstance(this.c).getResourcePackageName(); ; paramContext = this.c.getPackageName())
                    {
                      l = Class.forName(paramContext + ".R$raw");
                      return;
                      paramContext = this.c.getPackageName();
                      break;
                      paramContext = paramContext;
                      Log.b(a, paramContext.getMessage());
                      break label97;
                      paramContext = this.c.getPackageName();
                      break label132;
                      paramContext = paramContext;
                      Log.b(a, paramContext.getMessage());
                      break label151;
                      paramContext = this.c.getPackageName();
                      break label186;
                      paramContext = paramContext;
                      Log.b(a, paramContext.getMessage());
                      break label205;
                      paramContext = this.c.getPackageName();
                      break label240;
                      paramContext = paramContext;
                      Log.b(a, paramContext.getMessage());
                      break label259;
                      paramContext = this.c.getPackageName();
                      break label294;
                      paramContext = paramContext;
                      Log.b(a, paramContext.getMessage());
                      break label313;
                      paramContext = this.c.getPackageName();
                      break label348;
                      paramContext = paramContext;
                      Log.b(a, paramContext.getMessage());
                      break label367;
                      paramContext = this.c.getPackageName();
                      break label402;
                      paramContext = paramContext;
                      Log.b(a, paramContext.getMessage());
                      break label421;
                    }
                  }
                  catch (ClassNotFoundException paramContext)
                  {
                    Log.b(a, paramContext.getMessage());
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
      throw new IllegalArgumentException("ResClass is not initialized. Please make sure you have added neccessary resources. Also make sure you have " + this.c.getPackageName() + ".R$* configured in obfuscation. field=" + paramString);
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

  public static c a(Context paramContext)
  {
    if (b == null)
      b = new c(paramContext);
    return b;
  }

  public int a(String paramString)
  {
    return a(h, paramString);
  }

  public String a()
  {
    if (TextUtils.isEmpty(this.d))
      return this.c.getPackageName();
    return this.d;
  }

  public int b(String paramString)
  {
    return a(e, paramString);
  }

  public int c(String paramString)
  {
    return a(f, paramString);
  }

  public int d(String paramString)
  {
    return a(g, paramString);
  }

  public int e(String paramString)
  {
    return a(i, paramString);
  }

  public int f(String paramString)
  {
    return a(j, paramString);
  }

  public int g(String paramString)
  {
    return a(k, paramString);
  }

  public int h(String paramString)
  {
    return a(l, paramString);
  }

  public void i(String paramString)
  {
    this.d = paramString;
  }
}

/* Location:           F:\一周备份\面试apk\希望代码没混淆\jingmgou\jingmgou2\classes-dex2jar.jar
 * Qualified Name:     com.umeng.common.message.c
 * JD-Core Version:    0.6.2
 */